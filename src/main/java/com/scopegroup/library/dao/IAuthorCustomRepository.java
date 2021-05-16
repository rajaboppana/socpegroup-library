/**
 * 
 */
package com.scopegroup.library.dao;

import java.util.List;

import com.scopegroup.library.pojo.PublicationDTO;

/**
 * @author Raja
 *
 * Socpe Group test
 */
public interface IAuthorCustomRepository {
	
	List<PublicationDTO> getPublicationByEmail(String email);

}
