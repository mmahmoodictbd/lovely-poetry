package com.chumbok.poetry.repository.crud;

import org.springframework.data.repository.CrudRepository;

import com.chumbok.poetry.entity.Classification;

public interface ClassificationRepository
		extends
			CrudRepository<Classification, Long> {

	public Classification findByText(String text);
}
