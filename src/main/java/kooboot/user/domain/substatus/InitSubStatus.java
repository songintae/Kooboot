package kooboot.user.domain.substatus;

import kooboot.initialstate.domain.Initialstate;

public class InitSubStatus extends SubStatus {
	private Initialstate initialstate;
	
	public InitSubStatus(){
		super();
		initialstate = Initialstate.INIT;
	}
	@Override
	public String getStatusValue() {
		// TODO Auto-generated method stub
		return this.initialstate.getValue();
	}

	@Override
	public void setStatusCode(String value) {
		// TODO Auto-generated method stub
		this.initialstate = Initialstate.valueOfCode(value);
	}

}
