package com.qaframework.processinquiry;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.qaframework.dom.columns.ColumnsInquiry;
import com.qaframework.dom.columns.Question;
import com.qaframework.repository.QaTableRepository;
import com.qaframework.xml.XmlReader;

public class ColumnsProcessor {

	private QaTableRepository qaTableRepository;
	private XmlReader xmlReader;

	@Autowired
	public ColumnsProcessor(QaTableRepository qaTableRepository,
			XmlReader xmlReader) {
		this.qaTableRepository = qaTableRepository;
		this.xmlReader = xmlReader;
	}

	public void processColumns() {
		List<ColumnsInquiry> columnsInquiries = xmlReader
				.readColumnsInquiryFile();
		for (ColumnsInquiry columnsInquiry : columnsInquiries) {
			for (Question question : columnsInquiry.getQuestions()) {
				while (true) {
					Scanner input1 = new Scanner(System.in);
					System.out.print(question.getYesno()
							+ " (Y = Yes/N = No) >");
					String prompt1 = input1.next();
					if (!"Y".equalsIgnoreCase(prompt1)
							&& !"N".equalsIgnoreCase(prompt1)) {
						System.out
								.println("Invalid entry please enter Y or N!");
					} else {
						if ("N".equalsIgnoreCase(prompt1)) {
							break;
						}
						Scanner input2 = new Scanner(System.in);
						System.out.print(question.getPrompt() + " >");
						String prompt2 = input2.nextLine();
						qaTableRepository
								.getRecords(prompt2, question.getSql());
					}
				}
			}
		}
	}
}
