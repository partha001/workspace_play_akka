package com.partha.akka05.actor;

import java.time.Duration;

import com.partha.akka05.exception.RestartException;
import com.partha.akka05.exception.ResumeException;
import com.partha.akka05.exception.StopException;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.japi.pf.DeciderBuilder;

public class ParentActor extends AbstractActor{


	private ActorRef childActor;

	@Override
	public SupervisorStrategy supervisorStrategy() {
		return   new OneForOneStrategy(10,Duration.ofMinutes(1),DeciderBuilder
				.match(ResumeException.class, e -> SupervisorStrategy.resume())
				.match(RestartException.class, e -> SupervisorStrategy.restart())
				.match(StopException.class, e -> SupervisorStrategy.stop())
				.matchAny(e-> SupervisorStrategy.escalate())
				.build());		
	}

	

public static Props props(){
	return Props.create(ParentActor.class);
}

	@Override
	public void preStart() throws Exception {
		childActor= context().actorOf(ChildActor.props(),"childActor");
		super.preStart();
	}


	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.matchAny(req -> {
					childActor.tell(req, self());
					Thread.sleep(5000);
					childActor.tell("valid_message", self());
				})
				.build();
	}



}
