package com.qaframework.dom.sql;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rule")
public class Rule {

	private String table;

	private String where;

	public String getTable() {
		return table;
	}

	@XmlElement
	public void setTable(String table) {
		this.table = table;
	}

	public String getWhere() {
		return where;
	}

	@XmlElement
	public void setWhere(String where) {
		this.where = where;
	}
}
