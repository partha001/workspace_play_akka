package com.partha.akka03;

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
				.match(Play.class, System.out:: println)
				.match(Stop.class, System.out:: println)
				.build();
	}

}
