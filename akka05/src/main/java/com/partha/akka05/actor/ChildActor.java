package com.partha.akka05.actor;

import com.partha.akka05.exception.RestartException;
import com.partha.akka05.exception.ResumeException;
import com.partha.akka05.exception.StopException;

import akka.actor.AbstractActor;
import akka.actor.Props;
import scala.Option;


public class ChildActor extends AbstractActor{
	
	public static Props props(){
		return Props.create(ChildActor.class);
	}
	
	

	@Override
	public void postRestart(Throwable reason) throws Exception {
		System.out.println("ChildActor preRestart() hook invoked");
		super.postRestart(reason);
	}



	@Override
	public void postStop() throws Exception {
		System.out.println("ChildActor preStop() hook invoked");
		super.postStop();
	}



	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		System.out.println("ChildActor preRestart() hook invoked");
		super.preRestart(reason, message);
	}



	@Override
	public void preStart() throws Exception {
		System.out.println("ChildActor preStart() hook invoked");
		super.preStart();
	}



	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(String.class, (req-> req.equals("resume")),req-> {
					System.out.println("exception which causes resume has occured");
					throw new ResumeException();
					})
				.match(String.class, (req-> req.equals("stop")),req-> {
					System.out.println("exception which causes stop has occured");
					throw new StopException();
					})
				.match(String.class, (req-> req.equals("restart")),req-> {
					System.out.println("exception which causes restart has occured");
					throw new RestartException();
					})
				.match(String.class, (req-> req.equals("valid_message")),req-> {
					System.out.println("input received is :"+req);
					})
				.matchAny(req ->{
					System.out.println("input received is :"+ req);
					throw new Exception();
				})
				.build();
		
	}

}
