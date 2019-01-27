package domain;

import java.util.ArrayList;
import java.util.Collection;

import javassist.bytecode.Descriptor;

public class Device{
	
	private Collection<IFunction> functions;
	private DeviceDescriptor desc;
	
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
	
	


}
