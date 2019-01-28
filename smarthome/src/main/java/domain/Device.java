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
				function.callCommand(idcommand);
				this.state.updateState(function);
			}
		}
		
			
	}


}
