package com.qaframework.dom.pivot;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pivot_inquiry")
public class PivotInquiry {
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	@XmlElement(name = "question")
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
