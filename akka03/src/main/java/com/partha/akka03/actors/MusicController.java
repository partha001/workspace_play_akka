package com.partha.akka03.actors;

import com.partha.akka03.messages.ControllerMessage;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;

public class MusicController extends AbstractLoggingActor{

	static class Play extends ControllerMessage{
		
	}
	
	static class Stop extends ControllerMessage{
		
	}
	
	static Props props(){
		return Props.create(MusicController.class);
	}
	
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Play.class,req -> System.out.println("music starter - from inside MusicController.receive()" ))
				.match(Stop.class,req -> System.out.println("music stopped - from inside MusicController.receive()" ))
				.build();
	}

}
