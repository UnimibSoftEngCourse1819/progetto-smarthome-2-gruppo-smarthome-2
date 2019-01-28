package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.*;

import middleware.IMiddlewareFacade;
import middleware.MiddlewareException;
import middleware.MiddlewareFacade;

public class State {
	//[Key = Pair(FunID,property name) , Val = Map (Parameter Name, Value)]
	private Map<Pair<Object,Object>, Map<Object,Object>> currentState;
	private IMiddlewareFacade receiver;
	
	//constructor
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
					Pair<Object,Object> key = new Pair(property.funId.getValue(), property.getName());
					this.currentState.put(key, new HashMap<>());
					this.addParametersToCurrentState(key,property.getParameters());
				}
			}
			//System.out.println(this.currentState);
		}
	
	private void addParametersToCurrentState(Pair<Object,Object> propertykey, Map<Object, Object> parameters){
		for (Object key : parameters.keySet())
			this.currentState.get(propertykey).put(key, parameters.get(key));
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
	
	

	public Map<Pair<Object,Object>, Map<Object, Object>> getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Map<Pair<Object,Object>, Map<Object, Object>> currentState) {
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
