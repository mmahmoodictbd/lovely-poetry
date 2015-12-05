package com.chumbok.poetry;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chumbok.poetry.dto.FeedItem;
import com.chumbok.poetry.entity.Feed;
import com.chumbok.poetry.entity.Poem;
import com.chumbok.poetry.repository.crud.PoemRepository;
import com.chumbok.poetry.util.HttpRequestUtil;

@RestController
public class PostFeedController {

	@Autowired
	private PoemRepository poemRepo;

	@RequestMapping("/feed")
	public Feed feed(
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
			HttpServletRequest request, HttpServletResponse response) {
		
		response.setHeader("max-age", "0");

		List<FeedItem> items = new ArrayList<FeedItem>();
		List<Poem> poems = poemRepo.findAll(new PageRequest(pageNo, 20));

		for (Poem poem : poems) {
			items.add(new FeedItem(poem));
		}

		String baseURL = HttpRequestUtil.getBaseURL(request);

		for (FeedItem item : items) {

			if (item.getProfilePicUrl() != null) {
				item.setProfilePicUrl(baseURL + "img/" + item.getProfilePicUrl());
			}

			if (item.getImageUrl() != null) {
				item.setImageUrl(baseURL + "img/" + item.getImageUrl());
			}
		}

		Feed feed = new Feed();
		feed.setFeed(items);

		return feed;
	}

}