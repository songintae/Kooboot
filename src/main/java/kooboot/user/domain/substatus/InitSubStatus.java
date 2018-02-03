package kooboot.user.domain.substatus;

public class InitSubStatus extends SubStatus {
	private String value;
	
	public InitSubStatus(){
		this.value = "";
	}
	@Override
	public String getStatusValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void setStatusCode(String value) {
		// TODO Auto-generated method stub
	}

}
