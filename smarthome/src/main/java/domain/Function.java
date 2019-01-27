package domain;

import java.util.ArrayList;
import java.util.Collection;

public class Function implements IFunction {
	
	Pair id;
	Collection<ICommand> commands;
	
	public Function(String fId){
		this.id = new Pair(new TagFunction("UID"),fId);	
		this.commands = new ArrayList<>();
	}
	

	@Override
	public Collection<ICommand> getCommands() {
		return this.commands;
	}

	@Override
	public Object getId() {
		return this.id.getValue();
	}


	public void setCommands(Collection<ICommand> commands) {
		this.commands = commands;
	}

}
