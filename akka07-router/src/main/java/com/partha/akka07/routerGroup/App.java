package com.partha.akka07.routerGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class App {

	public static void main( String[] args )
	{
		ActorSystem system=ActorSystem.create("myActorSystem");

		system.actorOf(Worker.props(), "w1");
		system.actorOf(Worker.props(), "w2");
		system.actorOf(Worker.props(), "w3");
		system.actorOf(Worker.props(), "w4");
		system.actorOf(Worker.props(), "w5");


		List<String> actorPaths=new ArrayList<>(Arrays.asList(
				new String[]{
						"/user/w1",
						"/user/w2",
						"/user/w3",
						"/user/w4",
						"/user/w5",
				}
				));

		ActorRef routerGroup=system.actorOf(RouterGroup.props(actorPaths), "routerGroup");
		routerGroup.tell(new Worker.Work(), ActorRef.noSender());

		//system.terminate();
	}

} 
