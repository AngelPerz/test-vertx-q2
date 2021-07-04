package org.acme.controller;

import io.vertx.core.eventbus.EventBus;
import org.acme.constants.Constants;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestController {

    @Inject
    EventBus bus;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {

        bus.publish(Constants.TEST_START_EVENT_BUS_CHANNEL, Boolean.TRUE);

        return "Test launched";
    }
}