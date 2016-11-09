package com.qaframework.dom.columns;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "question")
public class Question {
	private String sql;

	private String yesno;

	private String prompt;

	public String getSql() {
		return sql;
	}

	@XmlElement
	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getYesno() {
		return yesno;
	}

	@XmlElement
	public void setYesno(String yesno) {
		this.yesno = yesno;
	}

	public String getPrompt() {
		return prompt;
	}

	@XmlElement
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
}
