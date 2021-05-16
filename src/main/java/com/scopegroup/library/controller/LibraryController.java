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

import com.scopegroup.library.pojo.Publication;
import com.scopegroup.library.pojo.PublicationDTO;
import com.scopegroup.library.service.ILibraryService;
import com.scopegroup.library.utils.BookNotFoundException;

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
	
	/* 
	 * @Param Multi part file
	 *
	 * */

	@PostMapping("/addPublications")
	public ResponseEntity<?> addPublication(@RequestParam("file") MultipartFile multipartFile) throws Exception {

		Set<Publication> faultyPublication = libraryService.saveFile(multipartFile);
		Map<String, Set<Publication>> faultySetWithMessage = null;

		if (faultyPublication == null || faultyPublication.isEmpty()) {
			return new ResponseEntity<>("Books have been saved successfully", HttpStatus.OK);
		} else {
			faultySetWithMessage = new HashMap<String, Set<Publication>>();
			faultySetWithMessage.put("Following are the books with out mandatory fields", faultyPublication);
			return new ResponseEntity<>(faultySetWithMessage, HttpStatus.PARTIAL_CONTENT);
		}
	}

	@GetMapping(path = "/getAllPublications", produces = "application/json")
	public ResponseEntity<List<Publication>> getAllPublications() throws Exception {
		List<Publication> publications = libraryService.getAllBooks();
		if (!publications.isEmpty()) {
			return new ResponseEntity<>(publications, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("No books Found");
		}

	}
	
	/* 
	 * @Param String isbn
	 *
	 * */

	@GetMapping("/getPublicationByIsbn")
	public ResponseEntity<Publication> getPublicationByIsbn(@RequestParam String isbn) {
		List<Publication> publications = libraryService.getBookByIsbn(isbn);
		if (!publications.isEmpty()) {
			return new ResponseEntity<Publication>(publications.get(0), HttpStatus.OK);
		} else {
			throw new BookNotFoundException("Book Not Found with ISBN - "+ isbn);
		}
	}
	
	@GetMapping("/getPublicationByEmail")
	public ResponseEntity<List<PublicationDTO>> getPublicationByEmail(@RequestParam String email) {
		List<PublicationDTO> authors = libraryService.getPublicationByEmail(email);
		if (!authors.isEmpty()) {
			return new ResponseEntity<List<PublicationDTO>>(authors, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("No Author was found with the email - "+ email);
		}
	}

}
