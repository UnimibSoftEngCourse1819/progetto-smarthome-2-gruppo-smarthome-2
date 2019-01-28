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
	private Map<javafx.util.Pair<Object,Object>, Map<Object,Object>> currentState;
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
					javafx.util.Pair<Object,Object> chiave = new javafx.util.Pair(property.funId.getValue(), property.getName());
					this.currentState.put(chiave, new HashMap<>());
					this.addParameters(chiave,property.getParameters());
				}
			}
			//System.out.println(this.currentState);
		}
	
	
	private void addParameters(javafx.util.Pair<Object,Object> chiave, Map<Object, Object> parameters){
		for (Object key : parameters.keySet())
			this.currentState.get(chiave).put(key, parameters.get(key));
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
	
	

	

	public Map<javafx.util.Pair<Object,Object>, Map<Object, Object>> getCurrentState() {
		return currentState;
	}

	public void setCurrentState(Map<javafx.util.Pair<Object,Object>, Map<Object, Object>> currentState) {
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
