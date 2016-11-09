package com.qaframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.qaframework.data.DataIngestor;
import com.qaframework.processinquiry.ColumnsProcessor;
import com.qaframework.processinquiry.PivotProcessor;
import com.qaframework.processinquiry.Runner;
import com.qaframework.processinquiry.SqlValidator;
import com.qaframework.repository.QaTableRepository;
import com.qaframework.xml.XmlReader;

@Configuration
@EnableConfigurationProperties(QaFrameworkProperties.class)
public class ApplicationConfig {
	@Autowired
	private QaFrameworkProperties properties;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Bean
	public Runner runner() {
		return new Runner();
	}

	@Bean
	public QaTableRepository qaTableRepository() {
		return new QaTableRepository(jdbcTemplate);
	}

	@Bean
	public DataIngestor dataIngestor() {
		return new DataIngestor(qaTableRepository(), properties);
	}

	@Bean
	public ColumnsProcessor columnsProcessor() {
		return new ColumnsProcessor(qaTableRepository(), xmlReader());
	}

	@Bean
	public PivotProcessor pivotProcessor() {
		return new PivotProcessor(qaTableRepository(), xmlReader());
	}

	@Bean
	public SqlValidator sqlValidator() {
		return new SqlValidator(xmlReader(), properties);
	}

	@Bean
	public XmlReader xmlReader() {
		return new XmlReader(properties);
	}
}
