package domain;

import java.util.ArrayList;
import java.util.Collection;

public class Function implements IFunction {
	
	AbstractId id;
	Collection<ICommand> commands;
	
	public Function(String fId){
		this.id = new FunctionId(fId);
		this.commands = new ArrayList<>();
	}

	@Override
	public Collection<ICommand> getCommands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractId getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
