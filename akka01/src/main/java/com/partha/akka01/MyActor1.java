package com.partha.akka01;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class MyActor1 extends AbstractActor{
	
	 public static Props props() {
	    return Props.create(MyActor1.class);
	  }

	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, input ->{
					System.out.println(input);
				})
				.build();
	}

}
