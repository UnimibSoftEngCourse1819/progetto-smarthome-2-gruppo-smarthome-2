package domain;

public class Property implements ICommand {
	
	//TODO COLLEZIONE DI PARAMETRI
	//TODO FORSE PROPERTY E OP POSSONO ESTENDERE UNA CLASSE ASTRATTA
	//ABSTRACTCOMMAND
	
	Tag type;
	Object name;
	
	public Property(Tag t, Object n){
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
