package com.chumbok.poetry.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.chumbok.poetry.entity.Keyword;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {

	public Keyword findByText(String text);
}