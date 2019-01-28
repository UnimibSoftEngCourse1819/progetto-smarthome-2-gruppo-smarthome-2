package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class State {
	//[Key = Pair(FunID,property name) , Val = Map (Parameter Name, Value)]
	private Map<Pair, Map<Object,Object>> currentState;
	private IMiddlewareFacade receiver;
	
	public State() {
		this.currentState = new HashMap<>();
		this.receiver = new MiddlewareFacade();
	}
	
	public void updateState(IFunction state) throws MiddlewareException{
		List<Property> temp = new ArrayList<>();
		temp = (List<Property>) this.getProperties(state);
			// può darsi che ci sia una funzione senza proprietà..
			if (temp != null)
			{
				//Temp contiene le chiavi della Mappa esterna
				// chiami il middleware..
				temp = (List<Property>) this.receiver.updateProperties(state);
				for (Property property : temp) {
					this.currentState.put(new Pair(property.funId.getValue(), property.getName()), new HashMap<>());
					this.addParameters(property.getName(),property.getParameters());
				}
			}
			//System.out.println(this.currentState);
		}
	
	
	private void addParameters(Object propertyName, Map<Object, Object> parameters){
		for (Object key : parameters.keySet())
			this.currentState.get(propertyName).put(key, parameters.get(key));
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
	
	

	

	public Map<Object, Map<Object, Object>> getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Map<Object, Map<Object, Object>> currentState) {
		this.currentState = currentState;
	}

	public IMiddlewareFacade getReceiver() {
		return receiver;
	}

	public void setReceiver(IMiddlewareFacade receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "State [currentState=" + currentState + "]";
	}
	
	
	
	
}
