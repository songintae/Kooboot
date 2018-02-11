package kooboot.user.domain.substatus;

import kooboot.search.domain.SearchCode;
import kooboot.translate.domain.TranslateCode;

public class SearchStatus extends SubStatus{
	
	private SearchCode searchCode;
	
	public SearchStatus(){
		super();
		searchCode = SearchCode.INIT;
	} 
	
	@Override
	public String getStatusValue() {
		// TODO Auto-generated method stub
		return this.searchCode.getValue();
	}

	@Override
	public void setStatusCode(String value) {
		// TODO Auto-generated method stub
		this.searchCode = SearchCode.valueOfCode(value);
		
	}

}
