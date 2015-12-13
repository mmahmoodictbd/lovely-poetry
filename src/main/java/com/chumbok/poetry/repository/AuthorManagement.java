package com.chumbok.poetry.repository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chumbok.poetry.entity.Author;
import com.chumbok.poetry.entity.Poem;
import com.chumbok.poetry.repository.crud.AuthorRepository;
import com.chumbok.poetry.repository.crud.PoemRepository;
import com.chumbok.poetry.util.ImageUtil;
import com.chumbok.poetry.util.PathBuilder;

@Service
public class AuthorManagement {

	@Autowired
	private PoemRepository poemRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public Author getAuthor(long authorId) {
		return authorRepository.findOne(authorId);
	}

	public Author saveAuthor(Author author, MultipartFile profileImageFile) {

		if (!profileImageFile.isEmpty()) {

			Author savedAuthor = authorRepository.findOne(author.getId());

			if (savedAuthor.getCurrentProfilePicUrl() != null) {
				if (author.getProfilePicUrls() == null) {
					author.setProfilePicUrls(Collections
							.singleton(savedAuthor.getCurrentProfilePicUrl()));
				} else {
					author.getProfilePicUrls()
							.add(savedAuthor.getCurrentProfilePicUrl());
				}
			}

			BufferedImage croppedImage = null;
			try {
				croppedImage = ImageUtil.readImageFromMpf(profileImageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String ext = "jpg";
			String fileName = profileImageFile.getName()
					+ UUID.randomUUID().toString() + "." + ext;

			File outputfile = new File(PathBuilder.buildAppImageDirPath()
					+ File.separator + fileName);
			try {
				ImageIO.write(croppedImage, ext, outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String filepath = new DateTime().toString("ddMMyy") + File.separator
					+ fileName;
			author.setCurrentProfilePicUrl(filepath);
			
			
		}

		author.setLastUpdated(new DateTime());
		authorRepository.save(author);
		return author;
	}

	public void deleteAuthor(long authorId) {

		Author author = authorRepository.findOne(authorId);
		for (Poem poem : poemRepository.findByAuthor(author)) {
			poemRepository.delete(poem);
		}

		String path = System.getProperty("user.home") + File.separator
				+ ".LovelyPoetry" + File.separator + "img" + File.separator
				+ author.getCurrentProfilePicUrl();
		File imgFile = new File(path);
		imgFile.delete();

		authorRepository.delete(authorId);
	}

}
