package com.qaframework.util;

import java.util.ArrayList;
import java.util.List;

public class FileHeader {

	private List<Column> header;

	public List<Column> getHeader() {
		if (header == null) {
			header = new ArrayList<>();
		}
		return header;
	}

	public void setHeader(List<Column> header) {
		this.header = header;
	}

	public List<String> getColumnNames() {
		List<String> columnNames = new ArrayList<>();
		for (FileHeader.Column column : getHeader()) {
			columnNames.add(column.getColumnName());
		}
		return columnNames;
	}

	public class Column {
		private String columnName;

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
	}
}
