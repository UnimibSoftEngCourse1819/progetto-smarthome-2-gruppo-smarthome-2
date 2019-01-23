package domain;

public class Operation implements ICommand {
	
	Tag type;
	Object name;
	
	public Operation(Tag t, Object n){
		this.type = t;
		this.name = n;
	}
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public Tag getTag() {
		return this.type;
	}

}
