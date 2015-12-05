package com.chumbok.poetry.repository.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chumbok.poetry.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	@Query("FROM Author ORDER BY name")
	List<Author> findAll();

	Author findByName(String name);
}
