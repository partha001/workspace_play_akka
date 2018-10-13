package com.partha.akka06_actorSelection.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Counter extends AbstractActor{
	
	int count=0;
	
	public static Props props(){
		return Props.create(Counter.class);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, req-> req.equals("increment") , req ->{
					count++;
				})
				.match(String.class, req-> req.equals("decrement") , req ->{
					count--;
				})
				.build();
	}
	

}
