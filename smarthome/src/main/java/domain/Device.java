package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import exceptions.MiddlewareException;
import exceptions.NoOperationException;
import javafx.util.Pair;

public class Device implements IDevice{
	
	private Collection<IFunction> functions;
	private IDescriptor desc;
	private State state;
	
	
	public Device(){
		this.functions = new ArrayList<>();
		this.state = new State();
	}
	

	public void setDescriptor(IDescriptor desc) {
		this.desc = desc;
	}

	public State getState() {
		return this.state;
	}
	
	public Map<Object,Object> getAttributeOfAProperty(Object funId, Object propertyName){
		return this.state.getCurrentState().get(new Pair<Object,Object>(funId,propertyName));
	}
	

	public IDescriptor getDescriptor() {
		return this.desc;
	}
	
	public void addFunction(IFunction funct){
		this.functions.add(funct);
	}


	public Collection<IFunction> getFunctions() {
		return functions;
	}
	
	public void callFunctionCommand(Object idfunct, Object idcommand) throws MiddlewareException {
		for (IFunction function : this.functions) {
			if (function.getId().equals(idfunct)) {
				function.callCommand(idcommand);
				this.state.updateState(function);
			}
		}	
	}
	
	public void initState() throws MiddlewareException{
		for(IFunction fn : this.functions)
				this.state.updateState(fn);
	}


	@Override
	public Collection<Object> getFunctionsIds() {
		List<Object> funIds = new ArrayList<>();
		for(IFunction fun : this.functions)
			funIds.add(fun.getId());
		return funIds;
	}


	@Override
	public Collection<Map<Object, Object>> getParametersOfThisFunction(Object funId) {
		return this.state.getParametersOfThisFunction(funId);
	}


	@Override
	public Collection<Operation> getOperations() throws NoOperationException{
		List<Operation> operations = new ArrayList<>();
		for(IFunction fun : this.functions){
			for(Operation op : fun.getOperations())
				operations.add(op);
		}
		return operations;
	}


	@Override
	public Collection<Property> getProperties() {
		List<Property> properties = new ArrayList<>();
		for(IFunction fun : this.functions){
			for(Property prop : fun.getProperties())
				properties.add(prop);
		}
		return properties;
	}




}
