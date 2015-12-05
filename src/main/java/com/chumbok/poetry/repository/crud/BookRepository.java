package com.chumbok.poetry.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.chumbok.poetry.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
