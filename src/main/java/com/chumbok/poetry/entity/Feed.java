package com.chumbok.poetry.entity;

import java.util.List;

import com.chumbok.poetry.dto.FeedItem;

public class Feed {

	private List<FeedItem> feed;

	public List<FeedItem> getFeed() {
		return feed;
	}

	public void setFeed(List<FeedItem> feed) {
		this.feed = feed;
	}

}
