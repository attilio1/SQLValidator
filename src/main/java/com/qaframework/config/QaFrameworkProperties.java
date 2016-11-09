package com.qaframework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qa")
public class QaFrameworkProperties {

	private String dataFilePath;
	private String queryFilePath;

	private String fileDelimiter = "|";

	private Functional functional = new Functional();

	private Technical technical = new Technical();

	public String getDataFilePath() {
		return dataFilePath;
	}

	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}

	public String getQueryFilePath() {
		return queryFilePath;
	}

	public void setQueryFilePath(String queryFilePath) {
		this.queryFilePath = queryFilePath;
	}

	public String getFileDelimiter() {
		return fileDelimiter;
	}

	public void setFileDelimiter(String fileDelimiter) {
		this.fileDelimiter = fileDelimiter;
	}

	public Functional getFunctional() {
		return functional;
	}

	public Technical getTechnical() {
		return technical;
	}

	public class Functional {
		private String columnsFolderPath;
		private String pivotFolderPath;

		public String getColumnsFolderPath() {
			return columnsFolderPath;
		}

		public void setColumnsFolderPath(String columnsFolderPath) {
			this.columnsFolderPath = columnsFolderPath;
		}

		public String getPivotFolderPath() {
			return pivotFolderPath;
		}

		public void setPivotFolderPath(String pivotFolderPath) {
			this.pivotFolderPath = pivotFolderPath;
		}
	}

	public class Technical {
		private String rulesFolderPath;

		public String getRulesFolderPath() {
			return rulesFolderPath;
		}

		public void setRulesFolderPath(String rulesFolderPath) {
			this.rulesFolderPath = rulesFolderPath;
		}
	}

}
