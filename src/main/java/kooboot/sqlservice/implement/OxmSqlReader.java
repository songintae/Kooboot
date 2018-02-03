package kooboot.sqlservice.implement;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import kooboot.sqlservice.definition.SqlReader;
import kooboot.sqlservice.definition.SqlRegistry;
import kooboot.sqlservice.implement.jaxb.SqlType;
import kooboot.sqlservice.implement.jaxb.Sqlmap;

public class OxmSqlReader implements SqlReader {
	
	private static final String DEFAULT_SQLMAP_FILENAME="sqlmap.xml";
	private static final String DEFAULT_SQLMAP_FILEPATH="kooboot/sqlservice/implement/";
	
	private Unmarshaller unmarshaller;
	private String sqlmapFile = DEFAULT_SQLMAP_FILENAME;
	private String sqlmapPath = DEFAULT_SQLMAP_FILEPATH;
	
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	public void setSqlmapFile(String sqlmapFile){
		this.sqlmapFile = sqlmapFile;
	}
	public void setSqlmapPath(String sqlmapPath){
		this.sqlmapPath = sqlmapPath;
	}


	@Override
	public void read(SqlRegistry sqlRegistry) {
		// TODO Auto-generated method stub
		Resource sqlmapResource = null;
		try{
			 sqlmapResource = new ClassPathResource(this.sqlmapPath+this.sqlmapFile);
			 Source source = new StreamSource(sqlmapResource.getInputStream());
			 Sqlmap sqlmap = (Sqlmap)unmarshaller.unmarshal(source);
			 
			 for(SqlType sql : sqlmap.getSql()){
				 sqlRegistry.registerSql(sql.getKey(), sql.getValue());
			 }
		}catch(IOException e){
			throw new IllegalArgumentException(sqlmapResource.getFilename() + "을 가져올 수 없습니다.");
			
		}
		
	}

}
