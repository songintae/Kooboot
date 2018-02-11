package kooboot.user.domain.substatus;

import kooboot.initialstate.domain.InitialstateCode;

public class InitSubStatus extends SubStatus {
	private InitialstateCode initialstate;
	
	public InitSubStatus(){
		super();
		initialstate = InitialstateCode.INIT;
	}
	@Override
	public String getStatusValue() {
		// TODO Auto-generated method stub
		return this.initialstate.getValue();
	}

	@Override
	public void setStatusCode(String value) {
		// TODO Auto-generated method stub
		this.initialstate = InitialstateCode.valueOfCode(value);
	}

}
