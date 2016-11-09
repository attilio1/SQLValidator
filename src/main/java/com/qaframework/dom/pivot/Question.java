package com.qaframework.dom.pivot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "question")
public class Question {
	private String yesno;
	private Query query;
	private String pivot;
	private String columnName;

	public String getColumnName() {
		return columnName;
	}

	@XmlElement(name = "column_name")
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getYesno() {
		return yesno;
	}

	@XmlElement
	public void setYesno(String yesno) {
		this.yesno = yesno;
	}

	public Query getQuery() {
		return query;
	}

	@XmlElement(name = "query")
	public void setQuery(Query query) {
		this.query = query;
	}

	public String getPivot() {
		return pivot;
	}

	@XmlElement
	public void setPivot(String pivot) {
		this.pivot = pivot;
	}

}
