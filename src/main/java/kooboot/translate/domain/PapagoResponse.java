package kooboot.translate.domain;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class PapagoResponse {
	private String type;
	private String service;
	private String version;
	private String translatedText;
	private String srcLangType;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTranslatedText() {
		return translatedText;
	}
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
	public String getSrcLangType() {
		return srcLangType;
	}
	public void setSrcLangType(String srcLangType) {
		this.srcLangType = srcLangType;
	}
}
