package com.devoxx.connection.vote;

import com.annimon.stream.Optional;
import com.devoxx.BuildConfig;
import com.devoxx.data.conference.ConferenceManager;
import com.devoxx.data.model.RealmConference;
import com.devoxx.utils.Logger;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import android.content.Context;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSink;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@EBean(scope = EBean.Scope.Singleton)
public class VoteConnection {

	@RootContext
	Context context;

	@Bean
	ConferenceManager conferenceManager;

	private VoteApi voteApi;

	public void setupApi(String apiUrl) {
		final OkHttpClient client = new OkHttpClient();

		if (BuildConfig.LOGGING) {
			client.interceptors().add(new LoggingInterceptor());
		}
		final Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(apiUrl)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		voteApi = retrofit.create(VoteApi.class);
	}

	public VoteApi getVoteApi() {
		if (voteApi == null) {
			final Optional<RealmConference> conference = conferenceManager.getActiveConference();
			if (conference.isPresent()) {
				setupApi(conference.get().getVotingURL());
			}
		}
		return voteApi;
	}

	class LoggingInterceptor implements Interceptor {
		@Override
		public Response intercept(Chain chain) throws IOException {
			Request request = chain.request();

			final BufferedSink s = new Buffer();
			request.body().writeTo(s);
			final long size = s.buffer().size();
			final byte[] da = new byte[(int) size];
			s.buffer().read(da);
			s.flush();
			s.close();

			final String requestBody = new String(da);

			Logger.l(String.format("[VOTE_API] Sending request %s %s on %s%n%s",
					requestBody, request.url(),
					chain.connection(), request.headers()));

			final Response response = chain.proceed(request);
			final long sentMillis = Long.parseLong(response.header("OkHttp-Sent-Millis"));
			final long receivedMillis = Long.parseLong(response.header("OkHttp-Received-Millis"));

			Logger.l(String.format("[VOTE_API] Received response for %s in %dms%n%s",
					response.request().url(), (receivedMillis - sentMillis), response.headers()));

			return response;
		}
	}
}