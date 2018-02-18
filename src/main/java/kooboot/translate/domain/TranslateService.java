package kooboot.translate.domain;

import kooboot.translate.exception.TranslateException;

public interface TranslateService {
	public String translateSentence(String source , String target , String sentence) throws TranslateException;
}
