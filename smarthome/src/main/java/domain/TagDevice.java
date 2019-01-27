package domain;

public class TagDevice extends Tag{
	
	
	public TagDevice(){
		super.setRoot("dal.device.");
	}
	
	public TagDevice(String parameter){
		super.setRoot("dal.device.");
		this.setParameter(parameter);
	}
	
	
	
}
