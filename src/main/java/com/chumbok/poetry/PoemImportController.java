package com.chumbok.poetry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chumbok.poetry.entity.Author;
import com.chumbok.poetry.entity.Classification;
import com.chumbok.poetry.entity.Keyword;
import com.chumbok.poetry.entity.Poem;
import com.chumbok.poetry.repository.crud.AuthorRepository;
import com.chumbok.poetry.repository.crud.ClassificationRepository;
import com.chumbok.poetry.repository.crud.KeywordRepository;
import com.chumbok.poetry.repository.crud.PoemRepository;

@Controller
@RequestMapping(value = "/import")
public class PoemImportController {

	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private PoemRepository poemRepo;

	@Autowired
	private KeywordRepository keywordRepo;

	@Autowired
	private ClassificationRepository classificationRepo;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public void importPoems() {

		System.out.println();

		List<String> dirList = listDirectoryName(
				"/home/mmahmood/Downloads/poetry-collection-api-master/collection/");
		for (String dir : dirList) {

			List<String> fileList = listFiles(dir);
			for (String fpath : fileList) {

				JsonParser parser = new JacksonJsonParser();
				Map<String, Object> poemMap = parser.parseMap(read(fpath));
				// System.out.println(poemMap);

				String title = (String) poemMap.get("title");
				List<Poem> existPoems = poemRepo.findByTitle(title);

				if (existPoems != null && existPoems.size() > 0) {
					continue;
				}

				Poem newPoem = new Poem();
				newPoem.setTitle(title);

				StringBuilder sb = new StringBuilder();
				List<String> lines = (List<String>) poemMap.get("text");
				for (String line : lines) {
					sb.append(line);
					sb.append("\n");
				}
				newPoem.setLines(sb.toString());

				String authorStr = ((String) poemMap.get("author")).trim();
				Author author = authorRepo.findByName(authorStr);
				if (author == null) {
					author = new Author();
					author.setName(authorStr);
					authorRepo.save(author);
				}
				newPoem.setAuthor(author);

				Set<Keyword> keywords = new HashSet<Keyword>();
				List<String> keywordStrs = (List<String>) poemMap
						.get("keywords");
				for (String k : keywordStrs) {
					Keyword keyword = keywordRepo.findByText(k);
					if (keyword == null) {
						keyword = new Keyword();
						keyword.setText(k);
						keywordRepo.save(keyword);
					}
					keywords.add(keyword);
				}

				newPoem.setKeywords(keywords);

				String classificationStr = (String) poemMap
						.get("classification");
				if (classificationStr != null) {
					Classification classification = classificationRepo
							.findByText(classificationStr);
					if (classification == null) {
						classification = new Classification();
						classification.setText(classificationStr);
						classificationRepo.save(classification);
					}
					newPoem.setClassification(classification);
				}

				newPoem.setPeriod((String) poemMap.get("period"));
				newPoem.setReference((String) poemMap.get("reference"));
				newPoem.setRegion((String) poemMap.get("region"));
				newPoem.setYear((String) poemMap.get("year"));

				poemRepo.save(newPoem);
			}

		}

	}

	private String read(String filepath) {

		BufferedReader br = null;
		StringBuilder lines = new StringBuilder();

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(filepath));
			while ((sCurrentLine = br.readLine()) != null) {
				lines.append(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return lines.toString();
	}

	public List<String> listDirectoryName(String directoryName) {

		List<String> dirNames = new ArrayList<String>();
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();

		for (File file : fList) {
			if (file.isDirectory()) {
				dirNames.add(file.getAbsolutePath());
			}
		}
		return dirNames;
	}

	public List<String> listFiles(String directoryName) {
		List<String> fileNames = new ArrayList<String>();
		File directory = new File(directoryName);
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				fileNames.add(file.getAbsolutePath());
			}
		}

		return fileNames;

	}
}