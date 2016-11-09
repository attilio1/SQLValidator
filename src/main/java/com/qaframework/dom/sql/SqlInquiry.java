package com.qaframework.dom.sql;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sql_inquiry")
public class SqlInquiry {
	private List<Rule> rules;

	public List<Rule> getRules() {
		return rules;
	}

	@XmlElement(name = "rule")
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
}
