package com.chumbok.poetry.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Poem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String title;

	@NotNull
	@Column(name = "line", columnDefinition = "LONGTEXT")
	private String lines;

	@NotNull
	@ManyToOne
	private Author author;

	@ManyToOne
	private Book book;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Poem_Keywords", joinColumns = @JoinColumn(name = "poem_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "id") )
	private Set<Keyword> keywords;

	@ManyToOne
	private Classification classification;

	private String period;
	private String reference;
	private String region;
	private String year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Poem [id=" + id + ", title=" + title + ", lines=" + lines
				+ ", author=" + author + ", book=" + book + ", keywords="
				+ keywords + ", classification=" + classification + ", period="
				+ period + ", reference=" + reference + ", region=" + region
				+ ", year=" + year + "]";
	}

}
