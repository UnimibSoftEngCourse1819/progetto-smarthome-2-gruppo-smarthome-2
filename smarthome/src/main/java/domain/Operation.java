package domain;

public class Operation implements ICommand {
	
	Pair funId;
	
	Tag type;
	Object name;
	
	public Operation(Tag t, Object n,Pair p){
		this.type = t;
		this.name = n;
		this.funId = p;
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
