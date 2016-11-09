package com.qaframework.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qaframework.config.QaFrameworkProperties;
import com.qaframework.dom.columns.ColumnsInquiry;
import com.qaframework.dom.pivot.PivotInquiry;
import com.qaframework.dom.sql.SqlInquiry;

public class XmlReader {

	private Log log = LogFactory.getLog(XmlReader.class);

	private QaFrameworkProperties properties;

	@Autowired
	public XmlReader(QaFrameworkProperties properties) {
		this.properties = properties;
	}

	public List<ColumnsInquiry> readColumnsInquiryFile() {
		String columnsFolderPath = properties.getFunctional()
				.getColumnsFolderPath();
		System.out.println("Columns Folder Path >" + columnsFolderPath);
		return readFiles(ColumnsInquiry.class, columnsFolderPath);
	}

	public List<SqlInquiry> readSqlInquiryFile() {
		String rulesFolderPath = properties.getTechnical().getRulesFolderPath();
		System.out.println("Rules Folder Path >" + rulesFolderPath);
		return readFiles(SqlInquiry.class, rulesFolderPath);
	}

	public List<PivotInquiry> readPivotInquiryFile() {
		String pivotFolderPath = properties.getFunctional().getPivotFolderPath();
		System.out.println("Pivot Folder Path >" + pivotFolderPath);
		return readFiles(PivotInquiry.class, pivotFolderPath);
	}	
	
	private <T> List<T> readFiles(Class<T> clazz, String folderPath) {
		List<T> doms = new ArrayList<>();
		try {
			File folder = new File(folderPath);
			for (final File fileEntry : folder.listFiles()) {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				T dom = (T) jaxbUnmarshaller.unmarshal(fileEntry);
				doms.add(dom);
			}
		} catch (JAXBException e) {
			log.error(e.getMessage(), e);
		}

		return doms;
	}
}
