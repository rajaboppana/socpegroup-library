/**
 * 
 */
package com.scopegroup.library.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.dao.IAuthorCustomRepository;
import com.scopegroup.library.dao.ILibraryCustomRepository;
import com.scopegroup.library.pojo.Publication;
import com.scopegroup.library.pojo.PublicationDTO;

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
	IFileService excelService;
	@Autowired
	ILibraryCustomRepository libraryCustomRepositoryImpl;
	@Autowired
	IAuthorCustomRepository authorRepository;

	@Override
	public Set<Publication> saveFile(MultipartFile file) throws Exception {

		Set<Publication> publicationSet = null;
		Set<Publication> faultyPublicationSet = null;

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (extension.equals("csv")) {
			publicationSet = csvService.readFile(file);
		}else if(extension.equals("xlsx")) {
			publicationSet = excelService.readFile(file);
		}
		
		//filtering the the original set to create a set with no mandatory fields
		faultyPublicationSet = publicationSet.stream()
	                .flatMap(root -> root.getAuthors()
	                        .stream()
	                        .filter(c -> c.getEmail().isEmpty() || c.getFirstName().isEmpty() || c.getLastName().isEmpty())
	                        .map(filteredElement -> new Publication(root.getTitle(), root.getIsbn(), root.getDescription(), root.getDate(), root.getAuthors())))
	                .collect(Collectors.toSet());
		//remove faulty record with no mandatory fields
		publicationSet.removeIf(root -> root.getAuthors().stream().allMatch(c -> c.getEmail().isEmpty() || c.getFirstName().isEmpty() || c.getLastName().isEmpty()));
				                
				
		for (Publication publication : publicationSet) {
			libraryCustomRepositoryImpl.savePublication(publication);	
		}
		
		return faultyPublicationSet;
	}

	@Override
	public List<Publication> getAllBooks() {
		return libraryCustomRepositoryImpl.findAll();
	}

	@Override
	public List<Publication> getBookByIsbn(String isbn) {
		return libraryCustomRepositoryImpl.getBookByisbn(isbn);
	}

	@Override
	public List<PublicationDTO> getPublicationByEmail(String email) {
		return authorRepository.getPublicationByEmail(email);
	}

}
