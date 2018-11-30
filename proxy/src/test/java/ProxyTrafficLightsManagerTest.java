import org.junit.Before;

import eu.jpereira.trainings.designpatterns.structural.proxy.TrafficLightsManager;
import eu.jpereira.trainings.designpatterns.structural.proxy.TrafficLightsManagerTest;
import eu.jpereira.trainings.designpatterns.structural.proxy.fakes.FakeProxyTrafficLightFactory;
import eu.jpereira.trainings.designpatterns.structural.proxy.testconfig.TestConfiguration;

public class ProxyTrafficLightsManagerTest extends TrafficLightsManagerTest {

	@Before
	public void setUp()
	{
		TestConfiguration.fakeFailuresInController=true;
	}
	
	@Override
	protected TrafficLightsManager createTrafficLightsManager()
	{
		TrafficLightsManager manager=new TrafficLightsManager(new FakeProxyTrafficLightFactory());
		return manager;
	}
	
	public ProxyTrafficLightsManagerTest() {
		// TODO Auto-generated constructor stub
	}

}
