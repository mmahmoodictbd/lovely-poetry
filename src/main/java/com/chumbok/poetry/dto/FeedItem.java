package com.chumbok.poetry.dto;

import org.joda.time.DateTime;

import com.chumbok.poetry.entity.Poem;

public class FeedItem {

	private long id;
	private long authorId;
	private String authorName;
	private String desc;
	private String descBodyUrl;
	private String imageUrl;
	private String profilePicUrl;
	private String timeStamp;
	private DateTime lastUpdated;

	public FeedItem(Poem poem) {
		this.id = poem.getId();
		this.authorId = poem.getAuthor().getId();
		this.authorName = poem.getAuthor().getName();
		this.desc = poem.getLines();
		this.descBodyUrl = null;
		this.imageUrl = null;
		this.profilePicUrl = poem.getAuthor().getCurrentProfilePicUrl();
		this.timeStamp = null;
		this.lastUpdated = poem.getLastUpdated().getMillis() > poem.getAuthor()
				.getLastUpdated().getMillis()
						? poem.getLastUpdated()
						: poem.getAuthor().getLastUpdated();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDescBodyUrl() {
		return descBodyUrl;
	}

	public void setDescBodyUrl(String descBodyUrl) {
		this.descBodyUrl = descBodyUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public DateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(DateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "FeedItem [id=" + id + ", authorId=" + authorId + ", authorName="
				+ authorName + ", desc=" + desc + ", descBodyUrl=" + descBodyUrl
				+ ", imageUrl=" + imageUrl + ", profilePicUrl=" + profilePicUrl
				+ ", timeStamp=" + timeStamp + ", lastUpdated=" + lastUpdated
				+ "]";
	}

}
