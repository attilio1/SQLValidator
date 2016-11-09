package com.qaframework.processinquiry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qaframework.config.QaFrameworkProperties;
import com.qaframework.dom.sql.Rule;
import com.qaframework.dom.sql.SqlInquiry;
import com.qaframework.xml.XmlReader;

public class SqlValidator {

	private Log log = LogFactory.getLog(SqlValidator.class);

	private XmlReader xmlReader;
	private QaFrameworkProperties properties;

	@Autowired
	public SqlValidator(XmlReader xmlReader, QaFrameworkProperties properties) {
		this.xmlReader = xmlReader;
		this.properties = properties;
	}

	private String convertToFlatString(String input) {
		String output = input.replaceAll("[\\n\\r]", " ");
		return output.replaceAll("\\s+", " ").trim();
	}

	public void validateSql() {
		System.out.println("Sql validation...");
		List<SqlInquiry> sqlInquiries = xmlReader.readSqlInquiryFile();
		try {
			for (SqlInquiry sqlInquiry : sqlInquiries) {
				String content = convertToFlatString(new String(
						Files.readAllBytes(Paths.get(properties
								.getQueryFilePath()))));
				for (Rule rule : sqlInquiry.getRules()) {
					if (content.contains(rule.getTable())) {
						System.out.println("Found table >" + rule.getTable());
						String where = convertToFlatString(rule.getWhere());
						if (!content.contains(where)) {
							System.out.println("Invalid Query!");
							System.out.println(rule.getTable()
									+ " requires --> " + rule.getWhere());
						}
					}
				}
			}
			System.out.println("Sql validation completed.");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
