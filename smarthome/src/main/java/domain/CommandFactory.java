package domain;

import javafx.util.Pair;

public class CommandFactory {
	
	private ICommand concreteCommand;
	
	public ICommand createCommand(Tag t, Object id,Pair fId){
		if(t.equals(new TagFunction("operation.name")))
			this.concreteCommand = new Operation(t,id,fId);
		else
			this.concreteCommand = new Property(t,id,fId);
		return this.concreteCommand;
	}
}
