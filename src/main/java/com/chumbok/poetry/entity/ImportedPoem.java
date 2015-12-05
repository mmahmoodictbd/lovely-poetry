package com.chumbok.poetry.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "author", "classification", "keywords", "period",
		"reference", "region", "text", "title", "year" })
public class ImportedPoem {

	@JsonProperty("author")
	private String author;
	@JsonProperty("classification")
	private String classification;
	@JsonProperty("keywords")
	private List<String> keywords = new ArrayList<String>();
	@JsonProperty("period")
	private String period;
	@JsonProperty("reference")
	private String reference;
	@JsonProperty("region")
	private String region;
	@JsonProperty("text")
	private List<String> text = new ArrayList<String>();
	@JsonProperty("title")
	private String title;
	@JsonProperty("year")
	private String year;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	@JsonProperty("author")
	public void setAuthor(String author) {
		this.author = author;
	}

	@JsonProperty("classification")
	public String getClassification() {
		return classification;
	}

	@JsonProperty("classification")
	public void setClassification(String classification) {
		this.classification = classification;
	}

	@JsonProperty("keywords")
	public List<String> getKeywords() {
		return keywords;
	}

	@JsonProperty("keywords")
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	@JsonProperty("period")
	public String getPeriod() {
		return period;
	}

	@JsonProperty("period")
	public void setPeriod(String period) {
		this.period = period;
	}

	@JsonProperty("reference")
	public String getReference() {
		return reference;
	}

	@JsonProperty("reference")
	public void setReference(String reference) {
		this.reference = reference;
	}

	@JsonProperty("region")
	public String getRegion() {
		return region;
	}

	@JsonProperty("region")
	public void setRegion(String region) {
		this.region = region;
	}

	@JsonProperty("text")
	public List<String> getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(List<String> text) {
		this.text = text;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("year")
	public String getYear() {
		return year;
	}

	@JsonProperty("year")
	public void setYear(String year) {
		this.year = year;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}