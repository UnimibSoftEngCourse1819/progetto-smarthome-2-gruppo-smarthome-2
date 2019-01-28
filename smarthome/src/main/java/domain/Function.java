 package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.util.Pair;
import middleware.MiddlewareException;

public class Function implements IFunction {
	
	private Pair<Tag, Object> id;
	private Collection<ICommand> commands;
	
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
	
	public void callCommand(Object name) throws MiddlewareException {
		for (ICommand command : this.commands) {
			if (command.getName().equals(name)){
				System.out.println(command.getName() + " " + command.getClass());
				command.execute(); 
			}
		}
	}
	

}
