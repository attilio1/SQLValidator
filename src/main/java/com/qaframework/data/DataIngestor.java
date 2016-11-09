package com.qaframework.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qaframework.config.QaFrameworkProperties;
import com.qaframework.repository.QaTableRepository;
import com.qaframework.util.CSVFileParser;
import com.qaframework.util.FileHeader;

public class DataIngestor {

	private Log log = LogFactory.getLog(DataIngestor.class);

	private QaTableRepository qaTableRepository;
	private QaFrameworkProperties properties;

	@Autowired
	public DataIngestor(QaTableRepository qaTableRepository,
			QaFrameworkProperties properties) {
		this.qaTableRepository = qaTableRepository;
		this.properties = properties;
	}

	public FileHeader ingestData() {
		System.out.println("Data loading...");
		FileHeader fileHeader = null;
		try {
			String dataFilePath = properties.getDataFilePath();
			if (dataFilePath == null) {
				System.out.println("Undefined Data File Path!");
			} else {
				System.out.println("Data File Path >" + dataFilePath);
				CSVFileParser cSVFileParser = new CSVFileParser(dataFilePath,
						properties.getFileDelimiter().toCharArray()[0]);
				fileHeader = cSVFileParser.getHeader();
				List<String> columnNames = fileHeader.getColumnNames();
				qaTableRepository.createTable(columnNames);

				List<String[]> rawData = cSVFileParser.getRawData();
				for (String[] data : rawData) {
					List<String> record = new ArrayList<>();
					for (String element : data) {
						record.add(element);
					}
					qaTableRepository.addRecord(columnNames, record);
				}
			}
			System.out.println("Data loading completed.");
		} catch (IOException e) {
			System.out.println("Data File not found!");
			log.error(e.getMessage(), e);
		}

		return fileHeader;
	}
}
