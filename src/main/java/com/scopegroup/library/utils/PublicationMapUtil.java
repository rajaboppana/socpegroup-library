/**
 * 
 */
package com.scopegroup.library.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.scopegroup.library.pojo.Author;
import com.scopegroup.library.pojo.Publication;

/**
 * @author Raja
 *
 * Scope Group test
 */
public class PublicationMapUtil {
	
	public static Publication getPublicationMap(List<String> csvLineInArray, String[] headerArray,
			Map<String, String> arrayObjectMap, Set<Author> authors) {
		
		Publication publication = new Publication();
		publication.setTitle(arrayObjectMap.get("title"));
		publication.setIsbn(arrayObjectMap.get("isbn"));
		publication.setDescription(arrayObjectMap.get("description"));
		publication.setDate(arrayObjectMap.get("issueDate"));

		Author author = new Author();
		author.setEmail(arrayObjectMap.get(new String("email")));
		author.setFirstName(arrayObjectMap.get("firstName"));
		author.setLastName(arrayObjectMap.get("lastName"));

		authors.add(author);
		publication.setAuthors(authors);
		
		return publication;
	}

}
