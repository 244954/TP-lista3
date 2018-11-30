package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {

	@Test
	public void testChainThreeDecorators() {
		// Create the builder
		SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

		// create a spy social channel
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

		// Chain decorators
		SocialChannel channel = builder.
				with(new MessageTruncator(10)).
				with(new URLAppender("http://jpereira.eu")).
				with(new WordCensor("this")).
				getDecoratedChannel(props);

		channel.deliverMessage("this is a message");
		// Spy channel. Should get the one created before
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
		assertEquals("#### is... http://jpereira.eu", spyChannel.lastMessagePublished());
	}

	@Test
	public void testChainThreeDecoratorsWithoutBuilder() {
		
		SocialChannel channel = new TestSpySocialChannel();
		
		SocialChannel urlAppenderChannel = new URLAppender("http://jpereira.eu", channel);
		
		//Now create a truncator
		SocialChannel messageTruncatorChannel = new MessageTruncator(10, urlAppenderChannel);
		
		//Now censor
		SocialChannel censorChannel = new WordCensor("this",messageTruncatorChannel);
		
		censorChannel.deliverMessage("this is a message");
		// Spy channel. Should get the one created before
		TestSpySocialChannel spy = (TestSpySocialChannel)channel;
		assertEquals("#### is... http://jpereira.eu", spy.lastMessagePublished());
	}

	@Test
	public void testOtherChainThreeDecorators() {
		// Create the builder
		SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

		// create a spy social channel
		SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

		// Chain decorators
		SocialChannel channel = builder.with(new URLAppender("http://jpereira.eu")).with(new WordCensor("this")).andWith(new MessageTruncator(30)).getDecoratedChannel(props);

		channel.deliverMessage("this is a message");
		// Spy channel. Should get the one created before
		TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
		assertEquals("#### is a message http://jp...", spyChannel.lastMessagePublished());
	}

}
