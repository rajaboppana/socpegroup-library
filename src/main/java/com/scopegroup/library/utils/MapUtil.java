/**
 * 
 */
package com.scopegroup.library.utils;

import java.util.List;
import java.util.Map;

import com.scopegroup.library.pojo.Book;

/**
 * @author Raja
 *
 * Scope Group test
 */
public class MapUtil {

	public static Book getMapSet(List<String> csvLineInArray, String[] headerArray,
			Map<String, String> arrayObjectMap) {

		Book book = new Book();

		book.setEmail(arrayObjectMap.get(new String("email")));
		book.setFirstName(arrayObjectMap.get("firstName"));
		book.setLastName(arrayObjectMap.get("lastName"));
		book.setType(arrayObjectMap.get("type"));
		book.setIsbn(arrayObjectMap.get("isbn"));
		book.setTitle(arrayObjectMap.get("title"));
		book.setDescription(arrayObjectMap.get("description"));
		book.setDate(arrayObjectMap.get("issueDate"));

		return book;
	}

}
