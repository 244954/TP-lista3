import org.junit.Before;

import eu.jpereira.trainings.designpatterns.structural.proxy.TrafficLightsManager;
import eu.jpereira.trainings.designpatterns.structural.proxy.TrafficLightsManagerTest;
import eu.jpereira.trainings.designpatterns.structural.proxy.fakes.FakeResilientTrafficLightFactory;
import eu.jpereira.trainings.designpatterns.structural.proxy.testconfig.TestConfiguration;

public class ResilientTrafficLightsManagerTest extends TrafficLightsManagerTest {

	@Before
	public void setUp()
	{
		TestConfiguration.fakeFailuresInController=true;
	}
	
	public ResilientTrafficLightsManagerTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected TrafficLightsManager createTrafficLightsManager()
	{
		TrafficLightsManager manager=new TrafficLightsManager (new FakeResilientTrafficLightFactory());
		return manager;
	}

}
