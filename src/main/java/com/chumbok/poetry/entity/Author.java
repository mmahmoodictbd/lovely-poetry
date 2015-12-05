package com.chumbok.poetry.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.util.Assert;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String currentProfilePicUrl;

	@ElementCollection
	@CollectionTable(name = "Author_ProfilePicUrls", joinColumns = @JoinColumn(name = "author_id") )
	@Column(name = "profilePicUrl")
	private Set<String> profilePicUrls;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Assert.hasText(name, "Author name can not be empty.");
		this.name = name;
	}

	public String getCurrentProfilePicUrl() {
		return currentProfilePicUrl;
	}

	public void setCurrentProfilePicUrl(String currentProfilePicUrl) {
		this.currentProfilePicUrl = currentProfilePicUrl;
	}

	public Set<String> getProfilePicUrls() {
		return profilePicUrls;
	}

	public void setProfilePicUrls(Set<String> profilePicUrls) {
		this.profilePicUrls = profilePicUrls;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", currentProfilePicUrl="
				+ currentProfilePicUrl + ", profilePicUrls=" + profilePicUrls
				+ "]";
	}

}
