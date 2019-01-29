 package domain;

import java.util.ArrayList;
import java.util.Collection;

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
	
}
