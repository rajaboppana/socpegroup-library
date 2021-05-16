/**
 * 
 */
package com.scopegroup.library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.scopegroup.library.utils.ParseUtil;

/**
 * @author Raja
 *
 * Scope group test
 */

public class CsvParserTest {
	
	
	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DOUBLE_QUOTES = '"';
	private static final char DEFAULT_QUOTE_CHAR = DOUBLE_QUOTES;
	
	@Test
    void test_csv_line_contain_double_quotes_in_field() throws Exception {

        String line = "\"jan@scope.com,Jan,Novak,B,278-1-56619-909-4,Sketching the Future,\"\"Key principles to make your, project a, success\"\",\"";
        int textLength = line.length();
        String excludeDoublequotes = null;
        
        if (textLength >= 2 && line.charAt(0) == '"' && line.charAt(textLength - 1) == '"') { 
			excludeDoublequotes = line.substring(1, textLength - 1);
			
		}
		// add comma at the end of the string for comfortable parsing
		String addComma = excludeDoublequotes + ",";
		
        List<String> result = ParseUtil.parse(addComma,DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHAR);

        assertEquals(8, result.size());
        assertEquals("jan@scope.com", result.get(0));
        assertEquals("Key principles to make your, project a, success", result.get(6));

    }
	
	@Test
    void test_csv_line_no_double_quotes_in_field() throws Exception {

        String line = "\"jan@scope.com,Jan,Novak,B,278-1-56619-909-4,Sketching the Future,Key principles to make your project a success,\"";
        int textLength = line.length();
        String excludeDoublequotes = null;
        
        if (textLength >= 2 && line.charAt(0) == '"' && line.charAt(textLength - 1) == '"') { 
			excludeDoublequotes = line.substring(1, textLength - 1);
			
		}
		// add comma at the end of the string for comfortable parsing
		String addComma = excludeDoublequotes + ",";
        
        List<String> result = ParseUtil.parse(addComma,DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHAR);

        assertEquals(8, result.size());
        assertEquals("jan@scope.com", result.get(0));
        assertEquals("Key principles to make your project a success", result.get(6));

    }
	
	

}
