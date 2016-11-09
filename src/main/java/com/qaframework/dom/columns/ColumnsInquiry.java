package com.qaframework.dom.columns;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "columns_inquiry")
public class ColumnsInquiry {
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	@XmlElement(name = "question")
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
