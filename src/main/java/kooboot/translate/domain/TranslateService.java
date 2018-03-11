package kooboot.translate.domain;

import kooboot.translate.exception.TranslateException;

public interface TranslateService {
	//번역 조건 및 번역할 문장을 받아 번역을 한다.
	public String translateSentence(String source, String target, String sentence) throws TranslateException;
}
