package domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import exceptions.MiddlewareException;
import javafx.util.Pair;
import middleware.IMiddlewareFacade;
import middleware.MiddlewareFacade;
import middleware.MiddlewareFacadeTest;

public class PropertyTests {
	
	private MiddlewareFacadeTest receiver = new MiddlewareFacadeTest();
	
@Test
public void constructor() throws MiddlewareException {
	Property pro = new Property(new TagFunction("property.name"),"status" ,
			new Pair<Tag, Object>(new TagFunction("UID"),
				     "ZigBee:ah.app.12345195726903800-1:DoorLock"));
	assertTrue(pro.getTag().getTagValue().equals("dal.function.property.name"));
	assertTrue(pro.getName().toString().equals("status"));
	assertTrue(pro.getFunctionId().equals("ZigBee:ah.app.12345195726903800-1:DoorLock"));
	assertTrue(pro.getParameters().size()==0);
}

@Test
public void addParameter() throws MiddlewareException {
	Property pro = new Property(new TagFunction("property.name"),"status" ,
			new Pair<Tag, Object>(new TagFunction("UID"),
				     "ZigBee:ah.app.12345195726903800-1:DoorLock"));
	pro.addParameter("timestamp", "22");
	assertTrue(pro.getParameters().size()==1);
	assertTrue(pro.getParameters().get("timestamp").equals("22"));
}

@Test
public void executeTest() throws MiddlewareException {
	Property pro = new Property(new TagFunction("property.name"),"status" ,
			new Pair<Tag, Object>(new TagFunction("UID"),
				     "ZigBee:ah.app.12345195726903800-1:DoorLock"));
	URL url = getClass().getResource("property_doorLock_status.txt");
	this.receiver.getPropertyTest(pro, new File(url.getPath()));
	Map<Object,Object> parameters = new HashMap<>();
	parameters.put("status", "OPEN");
	parameters.put("timestamp", "1548846147141");
	assertTrue(parameters.keySet().equals(pro.getParameters().keySet()));
}


}
