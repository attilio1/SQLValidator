package com.qaframework.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVFileParser {

	private CSVFormat format;
	private String fileName;

	public CSVFileParser(String fileName, char delimiter) throws IOException {
		format = CSVFormat.RFC4180.withHeader().withDelimiter(delimiter);
		this.fileName = fileName;
	}

	public FileHeader getHeader() throws IOException {
		FileReader fileReader = new FileReader(fileName);
		CSVParser parser = new CSVParser(fileReader, format);
		FileHeader fileHeader = new FileHeader();
		Map<String, Integer> map = parser.getHeaderMap();
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			FileHeader.Column column = fileHeader.new Column();
			column.setColumnName(iterator.next());
			fileHeader.getHeader().add(column);
		}
		parser.close();
		fileReader.close();

		return fileHeader;
	}

	public List<String[]> getRawData() throws IOException {
		FileReader fileReader = new FileReader(fileName);
		CSVParser parser = new CSVParser(fileReader, format);
		parser.getHeaderMap();

		List<String[]> rows = new ArrayList<String[]>();
		for (CSVRecord record : parser) {
			int size = record.size();
			String[] columns = new String[size];
			for (int index = 0; index < size; index++) {
				columns[index] = record.get(index);
			}
			rows.add(columns);
		}
		parser.close();
		fileReader.close();

		return rows;
	}
}
