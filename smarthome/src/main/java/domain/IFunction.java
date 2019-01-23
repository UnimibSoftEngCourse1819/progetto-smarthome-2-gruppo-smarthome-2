package domain;

import java.util.Collection;

public interface IFunction {
	
	//TODO void call(ICommand com)
	public AbstractId getId();
	
	public Collection<ICommand> getCommands();

}
