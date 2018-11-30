package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

public class WordCensor extends SocialChannelDecorator {

	private String pattern = "";

	/**
	 * @param pattern
	 */
	public WordCensor(String pattern) {
		this.pattern=pattern;
	}
	
	/**
	 * @param i
	 * @param decoratedChannel
	 */
	public WordCensor(String i, SocialChannel decoratedChannel) {
		this.pattern = i;
		this.delegate = decoratedChannel;
	}

	@Override
	public void deliverMessage(String message) {
		String message2=message.replaceAll(pattern, "####");
		delegate.deliverMessage(message2);

	}

}
