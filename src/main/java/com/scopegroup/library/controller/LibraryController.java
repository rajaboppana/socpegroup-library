/**
 * 
 */
package com.scopegroup.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.pojo.Book;
import com.scopegroup.library.service.ILibraryService;

/**
 * @author Raja
 *
 * Scope Group test
 */

@RestController
@RequestMapping("/api/library")
public class LibraryController {

	@Autowired
	@Qualifier("libraryService")
	private ILibraryService libraryService;

	@PostMapping("/addBooks")
	public ResponseEntity<?> addBook(@RequestParam("file") MultipartFile multipartFile) throws Exception {

		Set<Book> faultyBook = libraryService.saveFile(multipartFile);
		Map<String, Set<Book>> faultySetWithMessage = null;

		if (faultyBook == null || faultyBook.isEmpty()) {
			return new ResponseEntity<>("Books have been saved successfully", HttpStatus.OK);
		} else {
			faultySetWithMessage = new HashMap<String, Set<Book>>();
			faultySetWithMessage.put("Following are the books with out mandatory fields", faultyBook);
			return new ResponseEntity<>(faultySetWithMessage, HttpStatus.PARTIAL_CONTENT);
		}
	}

	@GetMapping("/getAllBooks")
	public ResponseEntity<?> getAllBooks() {
		List<Book> books = libraryService.getAllBooks();
		if (!books.isEmpty()) {
			return new ResponseEntity<>(books, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Books in the Library", HttpStatus.OK);
		}

	}

	@GetMapping("/getBookByIsbn")
	public ResponseEntity<?> getBookByIsbn(@RequestParam String isbn) {
		List<Book> books = libraryService.getBookByIsbn(isbn);
		if (!books.isEmpty()) {
			return new ResponseEntity<>(books, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Book exists with provided ISBN", HttpStatus.OK);
		}
	}

}
