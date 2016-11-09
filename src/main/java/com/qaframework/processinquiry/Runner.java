package com.qaframework.processinquiry;

import org.springframework.beans.factory.annotation.Autowired;

import com.qaframework.data.DataIngestor;
import com.qaframework.util.FileHeader;

public class Runner {

	@Autowired
	private DataIngestor dataIngestor;

	@Autowired
	private ColumnsProcessor columnsProcessor;

	@Autowired
	private PivotProcessor pivotProcessor;	
	
	@Autowired
	private SqlValidator sqlValidator;

	public void execute() {
		sqlValidator.validateSql();
		FileHeader fileHeader = dataIngestor.ingestData();
		columnsProcessor.processColumns();
		pivotProcessor.processPivot(fileHeader);
	}

}
