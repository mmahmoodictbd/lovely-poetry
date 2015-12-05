package com.chumbok.poetry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chumbok.poetry.entity.Author;
import com.chumbok.poetry.repository.AuthorManagement;
import com.chumbok.poetry.repository.crud.AuthorRepository;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorManagement authorManagement;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model,
			@RequestParam(value = "authorId", required = false) Long authorId) {

		if (authorId == null) {
			model.addAttribute("author", new Author());
		} else {
			model.addAttribute("author", authorManagement.getAuthor(authorId));
		}

		return "addAuthor";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Author author,
			@RequestParam("profileImage") MultipartFile profileImageFile) {
		authorManagement.saveAuthor(author, profileImageFile);
		return "redirect:/author/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("authors", authorRepository.findAll());
		return "authors";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam Long authorId) {
		authorManagement.deleteAuthor(authorId);
		return "redirect:/author/list";
	}

}