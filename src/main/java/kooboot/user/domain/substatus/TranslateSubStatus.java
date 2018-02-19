package kooboot.user.domain.substatus;

import kooboot.translate.domain.TranslateCode;

public class TranslateSubStatus extends SubStatus {

	private TranslateCode translate;

	public TranslateSubStatus() {
		super();
		translate = TranslateCode.INIT;
	}

	@Override
	public String getStatusValue() {
		// TODO Auto-generated method stub
		return translate.getValue();
	}

	@Override
	public void setStatusCode(String code) {
		// TODO Auto-generated method stub
		this.translate = TranslateCode.valueOfCode(code);
	}

}
