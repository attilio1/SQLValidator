package com.qaframework.processinquiry;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qaframework.dom.pivot.PivotInquiry;
import com.qaframework.dom.pivot.Question;
import com.qaframework.repository.QaTableRepository;
import com.qaframework.util.FileHeader;
import com.qaframework.xml.XmlReader;

public class PivotProcessor {

	private Log log = LogFactory.getLog(PivotProcessor.class);

	private QaTableRepository qaTableRepository;
	private XmlReader xmlReader;

	@Autowired
	public PivotProcessor(QaTableRepository qaTableRepository,
			XmlReader xmlReader) {
		this.qaTableRepository = qaTableRepository;
		this.xmlReader = xmlReader;
	}

	public void processPivot(FileHeader fileHeader) {
		List<PivotInquiry> pivotInquiries = xmlReader.readPivotInquiryFile();
		for (PivotInquiry pivotInquiry : pivotInquiries) {
			for (Question question : pivotInquiry.getQuestions()) {
				while (true) {
					if (runYesNoQuestion(question.getYesno())) {
						String pivot = getInput(question.getPivot());
						if (!columnExist(fileHeader, pivot)) {
							System.out
							.println("Invalid pivot, column name not found!");
						} else {
							String columnName = getInput(question
									.getColumnName());
							List<String> columns = getColumns(fileHeader,
									pivot, columnName);
							if (columns.isEmpty()) {
								System.out
								.println("No columns found with pattern entered!");
							} else {
								for (String column : columns) {
									System.out.println("Pivot > " + pivot
											+ " - Column > " + column);
									for (String sql : question.getQuery()
											.getSqlList()) {
										qaTableRepository.getRecords(pivot,
												column, sql);
									}
								}
							}
						}
					} else {
						break;
					}
				}
			}
		}
	}

	private boolean runYesNoQuestion(String display) {
		boolean isValid = true;
		while (true) {
			String prompt1 = getInput(display + " (Y = Yes/N = No) >");
			if (!"Y".equalsIgnoreCase(prompt1)
					&& !"N".equalsIgnoreCase(prompt1)) {
				System.out.println("Invalid entry please enter Y or N!");
			} else {
				if ("N".equalsIgnoreCase(prompt1)) {
					isValid = false;
				}
				break;
			}
		}
		return isValid;
	}

	private boolean columnExist(FileHeader fileHeader, String test) {
		boolean found = false;
		for (String columnName : fileHeader.getColumnNames()) {
			if (test.equalsIgnoreCase(columnName)) {
				found = true;
				break;
			}
		}
		return found;
	}

	private List<String> getColumns(FileHeader fileHeader, String pivot,
			String columnName) {
		List<String> columns = new ArrayList<>();
		String columnPattern = columnName.replace("%", ".*");
		try {
			Pattern pattern = Pattern.compile(columnPattern);
			for (String name : fileHeader.getColumnNames()) {
				Matcher matcher = pattern.matcher(name);
				if (matcher.matches() && !pivot.equalsIgnoreCase(name)) {
					columns.add(name);
				}
			}
		} catch (PatternSyntaxException exception) {
			System.out.println("Invalid regular expression!");
			log.error(exception);
		}
		return columns;
	}

	private String getInput(String display) {
		Scanner input = new Scanner(System.in);
		System.out.print(display + " >");
		return input.nextLine();
	}

}
