package com.chumbok.poetry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chumbok.poetry.entity.Book;
import com.chumbok.poetry.repository.crud.AuthorRepository;
import com.chumbok.poetry.repository.crud.BookRepository;

@Controller
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model,
			@RequestParam(value = "bookId", required = false) Long bookId) {

		if (bookId == null) {
			model.addAttribute("book", new Book());
		} else {
			model.addAttribute("book", bookRepository.findOne(bookId));
		}

		model.addAttribute("authors", authorRepository.findAll());

		return "addPost";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Book book) {

		bookRepository.save(book);
		return "redirect:/book/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "books";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam Long bookId) {
		bookRepository.delete(bookId);
		return "redirect:/book/list";
	}

}