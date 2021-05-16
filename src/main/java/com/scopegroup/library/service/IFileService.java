/**
 * 
 */
package com.scopegroup.library.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.pojo.Publication;

/**
 * @author Raja
 *
 * Scope Group test
 */
public interface IFileService {
	
	public Set<Publication> readFile(MultipartFile file) throws Exception;

}
