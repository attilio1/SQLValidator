package com.qaframework.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

@Repository
public class QaTableRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public QaTableRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String PROMPT_TOKEN = "{prompt}";
	private static final String TABLE_TOKEN = "{table}";
	private static final String PIVOT_TOKEN = "{pivot}";
	private static final String COLUMN_NAME_TOKEN = "{column_name}";
	private static final String QA_TABLE = "qaTable";

	private enum TOKEN {
		CREATE, INSERT_INTO, INSERT_VALUE
	};

	private String getCommaSeparated(List<String> list, TOKEN tokenType) {
		String string = "";
		for (String token : list) {
			String tokenForList = null;
			switch (tokenType) {
			case CREATE:
				tokenForList = token + " varchar";
				break;
			case INSERT_INTO:
				tokenForList = token;
				break;
			case INSERT_VALUE:
				tokenForList = "'" + token + "'";
				break;
			}

			string = string + ", " + tokenForList;
		}

		return string.substring(1);
	}

	public void createTable(List<String> columnNames) {
		jdbcTemplate.execute("create table qatable("
				+ getCommaSeparated(columnNames, TOKEN.CREATE) + ")");
	}

	public int addRecord(List<String> columnNames, List<String> values) {
		String columnNamesCommaSeparated = getCommaSeparated(columnNames,
				TOKEN.INSERT_INTO);
		String valuesCommaSeparated = getCommaSeparated(values,
				TOKEN.INSERT_VALUE);

		String sql = "INSERT INTO qatable(" + columnNamesCommaSeparated
				+ ") VALUES(" + valuesCommaSeparated + ")";
		return jdbcTemplate.update(sql);
	}

	public SqlRowSet getRecords(String columnNames, String sql) {
		String query = sql.replace(PROMPT_TOKEN, columnNames);
		query = query.replace(TABLE_TOKEN, QA_TABLE);
		return getRecords(query);
	}

	public SqlRowSet getRecords(String pivot, String columnName, String sql) {
		String query = sql.replace(PIVOT_TOKEN, pivot);
		query = query.replace(COLUMN_NAME_TOKEN, columnName);
		query = query.replace(TABLE_TOKEN, QA_TABLE);
		return getRecords(query);
	}

	private SqlRowSet getRecords(String query) {
		System.out.println(query);
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query);
		displaySqlRowSet(sqlRowSet);
		return sqlRowSet;
	}

	private void displaySqlRowSet(SqlRowSet sqlRowSet) {
		SqlRowSetMetaData sqlRowSetMetaData = sqlRowSet.getMetaData();
		int columnCount = sqlRowSetMetaData.getColumnCount();

		int rowCount = 0;
		while (sqlRowSet.next()) {
			rowCount++;
			String record = "Data " + rowCount + "--> ";
			for (int index = 1; index <= columnCount; index++) {
				record = record + " " + sqlRowSet.getString(index);
			}
			System.out.println(record);
		}
		System.out.println("Number of records --> " + rowCount);
	}
}
