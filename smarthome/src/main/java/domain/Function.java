 package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.MiddlewareException;
import exceptions.NoOperationException;
import javafx.util.Pair;

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
				command.execute(); 
			}
		}
	}


	@Override
	public String toString() {
		return "Function [id=" + id + ", commands=" + commands + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Function other = (Function) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}


	@Override
	public Collection<Operation> getOperations() throws NoOperationException {
		List<Operation> operations = new ArrayList<>();
		for(ICommand command : this.commands)
			if(command instanceof Operation)
				operations.add((Operation) command);
		return operations;
	}
	
	@Override
	public Collection<Property> getProperties(){
		List<Property> properties = new ArrayList<>();
		for(ICommand command : this.commands)
			if(command instanceof Property)
				properties.add((Property) command);
		return properties;
	}

	
}
