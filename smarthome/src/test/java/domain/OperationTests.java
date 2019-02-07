package domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import exceptions.MiddlewareException;
import javafx.util.Pair;

public class OperationTests {

	
	@Test
	public void constructor() throws MiddlewareException {
		Operation op = new Operation(new TagFunction("operation.name"),"open" ,
				new Pair<Tag, Object>(new TagFunction("UID"),
			     "ZigBee:ah.app.12345195726903800-1:DoorLock" ));
		assertTrue(op.getTag().getTagValue().equals("dal.function.operation.name"));
		assertTrue(op.getName().toString().equals("open"));
		assertTrue(op.getFunctionId().equals("ZigBee:ah.app.12345195726903800-1:DoorLock"));
	}

}

