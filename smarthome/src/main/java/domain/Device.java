package domain;

import java.util.ArrayList;
import java.util.Collection;

import javassist.bytecode.Descriptor;
import middleware.MiddlewareException;

public class Device{
	
	private Collection<IFunction> functions;
	private DeviceDescriptor desc;
	private State state;
	// attributo stato mappa si altera con l'esecuzione di un operazione 
	
	public Device(){
		//this.desc = new DeviceDescriptor();
		this.functions = new ArrayList<>();
		this.state = new State();
	}
	

	public void setDescriptor(IDescriptor desc) {
		this.desc = (DeviceDescriptor) desc;
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
	
	public void callFunction(Object idfunct, Object idcommand) throws MiddlewareException {
		for (IFunction function : this.functions) {
			if (function.getId().equals(idfunct)) {
				//System.out.println(function.getId());
				function.callCommand(idcommand);
				this.state.updateState(function);
				System.out.println(this.state.getCurrentState());
			}
		}
		
			
	}
	
	public void initState(Collection<IFunction> fncts) throws MiddlewareException{
		
		for(IFunction fn : fncts)
				this.state.updateState(fn);
		System.out.println(this.state.getCurrentState());
	}


}
