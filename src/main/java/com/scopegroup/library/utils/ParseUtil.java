/**
 * 
 */
package com.scopegroup.library.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raja
 *
 *         Scope Group test
 */
public class ParseUtil {

	public static List<String> parse(String line, char separator, char quoteChar) throws Exception {

		List<String> result = new ArrayList<>();
		int inQuotesCounter = 0;
		boolean inQuotes = false;
		StringBuilder field = new StringBuilder();

		for (char c : line.toCharArray()) {

			if (c == quoteChar) {
				inQuotes = true;
				inQuotesCounter++;
			}

			if (c == separator && !inQuotes) { // if find separator and not in quotes, add field to the list
				result.add(field.toString());
				field.setLength(0); // empty the field and ready for the next
			} else if (c == separator && inQuotesCounter == 4) { // if description is in quotes handle count the quotes
																	// until end of the string
				result.add(field.toString());
				field.setLength(0);
				inQuotesCounter = 0;
				inQuotes = false;
			} else if (c != quoteChar) { // else append the char into a field
				field.append(c);
			}
		}

		return result;

	}

}
