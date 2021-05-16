/**
 * 
 */
package com.scopegroup.library.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.pojo.Publication;
import com.scopegroup.library.pojo.PublicationDTO;

/**
 * @author Raja
 *
 * Scope Group test
 */
public interface ILibraryService {
	
	public Set<Publication> saveFile(MultipartFile file) throws Exception;
	
	public List<Publication> getAllBooks();
	
	public List<Publication> getBookByIsbn(String isbn); 
	
	public List<PublicationDTO> getPublicationByEmail(String email);
	
}
