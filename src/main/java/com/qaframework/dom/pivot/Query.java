package com.qaframework.dom.pivot;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "query")
public class Query {
	private List<String> sqlList;

	public List<String> getSqlList() {
		return sqlList;
	}

	@XmlElement(name = "sql")
	public void setSqlList(List<String> sqlList) {
		this.sqlList = sqlList;
	}

}
