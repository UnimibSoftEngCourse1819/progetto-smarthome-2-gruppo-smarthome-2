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
				System.out.println(function.getId());
				function.callCommand(idcommand);
				this.state.updateState(function);
			}
		}
		
			
	}
	
	public void initState(Collection<IFunction> fncts) throws MiddlewareException{
		int i = 0;
		for(IFunction fn : fncts){
			if(i > 0)
				this.state.updateState(fn);
			++i;
		}
		
		System.out.println(this.state.getCurrentState());
	}


}
