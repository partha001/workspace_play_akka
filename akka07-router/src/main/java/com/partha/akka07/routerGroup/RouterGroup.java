package com.partha.akka07.routerGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.actor.Props;

public class RouterGroup extends AbstractActor{
	
	List<String> routeSelectionList=new ArrayList<>();
	
	public static Props props(List<String> routeSelectionList){
		return Props.create(RouterGroup.class,routeSelectionList);
	}
	
	public RouterGroup(List<String> routeSelectionList) {
		this.routeSelectionList=routeSelectionList;
	}


	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Worker.Work.class, req ->{
					ActorSelection selection=context().actorSelection(routeSelectionList.get(new Random().nextInt(routeSelectionList.size())));
					//ActorSelection selection=context().actorSelection(routeSelectionList.get(0));
					selection.forward(req, context());
				})
				.build();
	}

}
