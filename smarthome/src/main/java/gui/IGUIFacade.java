package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.json.simple.parser.ParseException;

import domain.IDescriptor;
import domain.IFunction;

public interface IGUIFacade {
	public void show();
	public void show(Collection <IDescriptor> descs);
	public Collection<IDescriptor> scan() throws FileNotFoundException, IOException, ParseException, Exception;
	public Collection<IFunction> add(IDescriptor descDev) throws FileNotFoundException, IOException, ParseException;
}
