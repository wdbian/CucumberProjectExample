package utility;

import java.io.Reader;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static boolean validateIfValueIsFound(String csvFilePath, String valueToSearch) {
		boolean isFound = false;
		try (Reader reader = new FileReader(csvFilePath);
			 CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).setTrim(true).build()))
		{
			for (CSVRecord csvRecord : csvParser) {
				for (String value : csvRecord) {
					if (value.trim().equalsIgnoreCase(valueToSearch.trim())) {
						isFound = true;
						break;
					}
				}
				if (isFound) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isFound;
	}
	
	public static boolean validateIfHeaderIsFound(String csvFilePath, String valueToSearch, String headerToSearch) {
		boolean isFound = false;
		try (Reader reader = new FileReader(csvFilePath);
			 CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).setTrim(true).build()))
		{
			Map<String, Integer> headerMap = csvParser.getHeaderMap();
			int searchColumnIndex = headerMap.get(headerToSearch);
			for (CSVRecord csvRecord : csvParser) {
				String value = csvRecord.get(searchColumnIndex);
				if (value.equalsIgnoreCase(valueToSearch.trim())) {
					isFound = true;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isFound;
	}
}
