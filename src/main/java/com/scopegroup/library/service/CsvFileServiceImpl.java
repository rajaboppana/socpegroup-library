/**
 * 
 */
package com.scopegroup.library.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.pojo.Author;
import com.scopegroup.library.pojo.Publication;
import com.scopegroup.library.utils.ParseUtil;
import com.scopegroup.library.utils.PublicationMapUtil;

/**
 * @author Raja
 *
 *         Scope Group test
 */

@Service("csvService")
public class CsvFileServiceImpl implements IFileService {

	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DOUBLE_QUOTES = '"';
	private static final char DEFAULT_QUOTE_CHAR = DOUBLE_QUOTES;

	public Set<Publication> readFile(MultipartFile csvFile) throws Exception {

		int indexLine = 1;
		int skipLine = 1;
		List<String> csvLineInArray = null;
		String[] headerArray = null;
		Map<String, String> arrayObjectMap = new HashMap<String, String>();
		Set<Publication> publicationSet = new HashSet<Publication>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), "UTF-8"))) {

			String line;
			String excludeDoublequotes = null;
			while ((line = br.readLine()) != null) {

				int textLength = line.length();

				if (indexLine++ <= skipLine) { // first line header array
					excludeDoublequotes = line.substring(2, textLength - 1);
					headerArray = excludeDoublequotes.split(",");
					continue;
				}

				// remove quotes start and end of the string
				if (textLength >= 2 && line.charAt(0) == '"' && line.charAt(textLength - 1) == '"') { 
					excludeDoublequotes = line.substring(1, textLength - 1);
				}

				// add comma at the end of the string for comfortable parsing
				String addComma = excludeDoublequotes + ",";

				csvLineInArray = ParseUtil.parse(addComma, DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHAR);
				
				Set<Author> authors = new HashSet<>();

				for (int i = 0; i < headerArray.length; i++) {
					arrayObjectMap.put(headerArray[i], csvLineInArray.get(i));
				}

				Publication publication = PublicationMapUtil.getPublicationMap(csvLineInArray, headerArray, arrayObjectMap, authors);

				publicationSet.add(publication);

			}
		}

		return publicationSet;
	}

}
