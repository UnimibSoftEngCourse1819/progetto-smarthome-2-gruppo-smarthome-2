package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class State {
	
	private Map<Object, Object> currentState;
	private IMiddlewareFacade receiver;
	
	public State() {
		this.currentState = new HashMap<>();
		this.receiver = new MiddlewareFacade();
	}
	
	public Map<Object, Object> getState() {
		return this.currentState;
	}
	
	public void updateState(IFunction state) throws MiddlewareException{
		List<Property> temp = new ArrayList<>();
		temp = (List<Property>) this.getProperties(state);
			// può darsi che ci sia una funzione senza proprietà..
			if (temp != null)
			{
				// chiami il middleware..
				temp = (List<Property>) this.receiver.updateProperties(state);
				for (Property property : temp) {
					this.addParameters(property.getParameters());
				}
			}
			System.out.println(this.currentState);
		}
	
	
	private void addParameters(Map<Object, Object> properties){
		for (Object key : properties.keySet())
		{
			this.currentState.put(key, properties.get(key));
		}
		
	}
	
	private Collection<Property> getProperties(IFunction function){
		TagFunction tagFunction = new TagFunction("property.name");
		List<Property> result = new ArrayList<>();
		for (ICommand command : function.getCommands()) {
			if(command.getTag().equals(tagFunction))
				result.add((Property) command);
		}
			return result;
	}
}
