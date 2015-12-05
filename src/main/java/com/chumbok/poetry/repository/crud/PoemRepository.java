package com.chumbok.poetry.repository.crud;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chumbok.poetry.entity.Author;
import com.chumbok.poetry.entity.Poem;

public interface PoemRepository extends CrudRepository<Poem, Long> {

	@Query("FROM Poem ORDER BY Title")
	List<Poem> findAll(Pageable pageable);

	List<Poem> findByAuthor(Author author);

	List<Poem> findByTitle(String title);
}
