package com.chumbok.poetry;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chumbok.poetry.entity.Poem;
import com.chumbok.poetry.repository.crud.AuthorRepository;
import com.chumbok.poetry.repository.crud.PoemRepository;

@Controller
public class PoemController {

	@Autowired
	private PoemRepository poemRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model,
			@RequestParam(value = "poemId", required = false) Long poemId) {

		if (poemId == null) {
			model.addAttribute("poem", new Poem());
		} else {
			model.addAttribute("poem", poemRepository.findOne(poemId));
		}

		model.addAttribute("authors", authorRepository.findAll());
		return "addPoem";

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute Poem poem) {

		poem.setLastUpdated(new DateTime());
		poemRepository.save(poem);
		return "redirect:/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, 
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo) {
		model.addAttribute("poems",
				poemRepository.findAll(new PageRequest(pageNo, 20)));
		return "poems";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam Long poemId) {
		poemRepository.delete(poemId);
		return "redirect:/list";
	}
}