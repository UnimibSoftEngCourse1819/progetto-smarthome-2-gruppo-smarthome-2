package domain;

public class TagFunction extends Tag {
	
	public TagFunction(){
		super.setRoot("dal.function.");
	}
	
	public TagFunction(String parameter){
		super.setRoot("dal.function.");
		this.setParameter(parameter);
	}

}
