package com.devoxx.data.model;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.devoxx.connection.cfp.model.ConferenceApiModel;
import com.devoxx.connection.cfp.model.FloorApiModel;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmConference extends RealmObject {

	@PrimaryKey
	private String id;
	private String confType;
	private String confIcon;
	private String description;
	private String venue;
	private String address;
	private String country;
	private String latitude;
	private String longitude;
	private String fromDate;
	private String capacity;
	private String sessions;
	private String splashImgURL;
	private String toDate;
	private String wwwURL;
	private String regURL;
	private String cfpURL;
	private String votingURL;
	private String talkURL;
	private String votingEnabled;
	private String cfpEndpoint;
	private String cfpVersion;
	private String youTubeId;
	private String hashtag;
	private String integrationId;
	private RealmList<RealmFloor> floors;

	public RealmConference() {
		// Default one.
	}

	public RealmConference(ConferenceApiModel model) {
		id = model.id;
		confType = model.confType;
		description = model.confDescription;
		confIcon = model.confIcon;
		venue = model.venue;
		address = model.address;
		country = model.country;
		latitude = model.latitude;
		longitude = model.longitude;
		fromDate = model.fromDate;
		toDate = model.toDate;
		wwwURL = model.wwwURL;
		regURL = model.regURL;
		cfpURL = model.cfpURL;
		votingURL = model.votingURL;
		votingEnabled = model.votingEnabled;
		cfpEndpoint = model.cfpEndpoint;
		cfpVersion = model.cfpVersion;
		youTubeId = model.youTubeId;
		capacity = model.capacity;
		sessions = model.sessions;
		hashtag = model.hashtag;
		talkURL = model.talkURL;
		splashImgURL = model.splashImgURL;
		integrationId = model.integration_id;
		floors = new RealmList<>();
		for (FloorApiModel floor : model.floors) {
			floors.add(new RealmFloor(floor));
		}
	}

	public static List<RealmFloor> extractFloors(RealmConference conference, String res) {
		return Stream.of(conference.getFloors())
				.filter(value -> value.getTarget().equalsIgnoreCase(res))
				.collect(Collectors.toList());
	}

	public static LatLng getLocation(RealmConference conference) {
		return new LatLng(Double.parseDouble(conference.getLatitude()),
				Double.parseDouble(conference.getLongitude()));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfType() {
		return confType;
	}

	public void setConfType(String confType) {
		this.confType = confType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getWwwURL() {
		return wwwURL;
	}

	public void setWwwURL(String wwwURL) {
		this.wwwURL = wwwURL;
	}

	public String getRegURL() {
		return regURL;
	}

	public void setRegURL(String regURL) {
		this.regURL = regURL;
	}

	public String getCfpURL() {
		return cfpURL;
	}

	public void setCfpURL(String cfpURL) {
		this.cfpURL = cfpURL;
	}

	public String getVotingURL() {
		return votingURL;
	}

	public void setVotingURL(String votingURL) {
		this.votingURL = votingURL;
	}

	public String getVotingEnabled() {
		return votingEnabled;
	}

	public void setVotingEnabled(String votingEnabled) {
		this.votingEnabled = votingEnabled;
	}

	public String getCfpEndpoint() {
		return cfpEndpoint;
	}

	public void setCfpEndpoint(String cfpEndpoint) {
		this.cfpEndpoint = cfpEndpoint;
	}

	public String getCfpVersion() {
		return cfpVersion;
	}

	public void setCfpVersion(String cfpVersion) {
		this.cfpVersion = cfpVersion;
	}

	public String getYouTubeId() {
		return youTubeId;
	}

	public void setYouTubeId(String youTubeId) {
		this.youTubeId = youTubeId;
	}

	public RealmList<RealmFloor> getFloors() {
		return floors;
	}

	public void setFloors(RealmList<RealmFloor> floors) {
		this.floors = floors;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getSplashImgURL() {
		return splashImgURL;
	}

	public void setSplashImgURL(String splashImgURL) {
		this.splashImgURL = splashImgURL;
	}

	public String getTalkURL() {
		return talkURL;
	}

	public void setTalkURL(String talkURL) {
		this.talkURL = talkURL;
	}

	public String getConfIcon() {
		return confIcon;
	}

	public void setConfIcon(String confIcon) {
		this.confIcon = confIcon;
	}

	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}
}
