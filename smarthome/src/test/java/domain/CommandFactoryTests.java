package domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.util.Pair;

public class CommandFactoryTests {

	CommandFactory factory = new CommandFactory();
	
	@Test
	public void testCostructor(){
		Operation op = new Operation(new TagFunction("operation.name"),"open" ,
				new Pair<Tag, Object>(new TagFunction("UID"),
			     "ZigBee:ah.app.12345195726903800-1:DoorLock" ));
		Property pro = new Property(new TagFunction("property.name"),"status" ,
				new Pair<Tag, Object>(new TagFunction("UID"),
			     "ZigBee:ah.app.12345195726903800-1:DoorLock" ));
		
		assertTrue(this.factory.createCommand(op.getTag(), op.getName(), new Pair<Tag, Object>(new TagFunction("UID"),
			     "ZigBee:ah.app.12345195726903800-1:DoorLock" ))instanceof Operation );
		assertTrue(this.factory.createCommand(pro.getTag(), pro.getName(), new Pair<Tag, Object>(new TagFunction("UID"),
			     "ZigBee:ah.app.12345195726903800-1:DoorLock" )) instanceof Property);
	}

}
