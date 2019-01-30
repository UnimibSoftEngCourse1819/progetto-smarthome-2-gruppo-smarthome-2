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
	private Map<Pair<Object,Object>, Map<Object,Object>> currentState;
	private IMiddlewareFacade receiver;
	
	public State() {
		this.currentState = new HashMap<>();
		this.receiver = new MiddlewareFacade();
	}
	
	
	public void updateState(IFunction state) throws MiddlewareException{
		 List<Property> temp = (List<Property>) this.getPropertiesFromThisFunction(state);
			
			if (temp != null)
			{
				
				temp = (List<Property>) this.receiver.updateProperties(state);
				for (Property property : temp) {
					Pair<Object,Object> key = new Pair(property.funId.getValue(), property.getName());
					this.currentState.put(key, new HashMap<>());
					this.addParametersToCurrentState(key,property.getParameters());
				}
			}
			
		}
	
	private void addParametersToCurrentState(Pair<Object,Object> propertykey, Map<Object, Object> parameters){
		for (Object key : parameters.keySet())
			this.currentState.get(propertykey).put(key, parameters.get(key));
	}
	
	private Collection<Property> getPropertiesFromThisFunction(IFunction function){
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
	
	
	public Collection<Map<Object, Object>> getParametersOfThisFunction(Object funId){
		List<Map<Object,Object>> paramsOfFun = new ArrayList<>();
		System.out.println(this.currentState.keySet() + "1");
		for(Pair<Object,Object> pair : this.currentState.keySet()){
			if(pair.getKey().toString().equals(funId.toString())) {		
				paramsOfFun.add(this.currentState.get(pair));
			}
				
		}
		return paramsOfFun;
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
