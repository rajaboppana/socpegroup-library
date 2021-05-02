/**
 * 
 */
package com.scopegroup.library.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.dao.LibraryRepository;
import com.scopegroup.library.pojo.Book;

/**
 * @author Raja
 *
 *         Scope Group test
 */

@Service("libraryService")
public class LibraryServiceImpl implements ILibraryService {

	@Autowired
	IFileService csvService;
	@Autowired
	LibraryRepository libraryRepository;

	@Override
	public Set<Book> saveFile(MultipartFile file) throws Exception {

		Set<Book> bookSet = null;
		Set<Book> faultySet = null;

		bookSet = csvService.readFile(file);

		// filter books with no mandatory fields
		faultySet = bookSet.stream()
				.filter(c -> c.getEmail().isEmpty() || c.getFirstName().isEmpty() || c.getLastName().isEmpty())
				.collect(Collectors.toSet());

		// save valid books
		bookSet.removeIf(c -> c.getEmail().isEmpty() || c.getFirstName().isEmpty() || c.getLastName().isEmpty());

		for (Book book : bookSet) {
			libraryRepository.save(book);
		}
		//return the book with no mandatory fields with out saving.
		return faultySet;
	}

	@Override
	public List<Book> getAllBooks(){
		return libraryRepository.findAll();
	}

	@Override
	public List<Book> getBookByIsbn(String isbn) {
		return libraryRepository.findBookByISBN(isbn);
	}

}
