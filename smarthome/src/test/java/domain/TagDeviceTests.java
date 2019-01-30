package domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TagDeviceTests {
	
@Test
public void constructor() {
	TagDevice tagDevice = new TagDevice();
	assertTrue(tagDevice.getTagValue().equals("dal.device."));
}

@Test
public void constructorParameter() {
	TagDevice tagDevice = new TagDevice("example");
	assertTrue(tagDevice.getTagValue().equals("dal.device.example"));
}
}
