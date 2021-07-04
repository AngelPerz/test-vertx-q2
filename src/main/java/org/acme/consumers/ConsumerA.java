package org.acme.consumers;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.eventbus.EventBus;
import org.acme.buscodecs.WorkFinished;
import org.acme.constants.Constants;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ConsumerA {

    private static final Logger LOGGER = Logger.getLogger(ConsumerA.class);

    @Inject
    EventBus bus;


    @ConsumeEvent(value = Constants.WORK_UNIT_EVENT_BUS_CHANNEL)
    public void consumeTestStart(Integer i) {
        LOGGER.infof("ConsumerA :: starting workUnit [%d]", i);
        try {
            Thread.sleep(150L);
        } catch (InterruptedException e) {
            LOGGER.error("Wait interrupted", e);
        }

        LOGGER.infof("ConsumerA :: finished workUnit [%d]", i);
        bus.publish(Constants.FINISHED_WORK_UNIT_EVENT_BUS_CHANNEL, new WorkFinished("ConsumerA", i));
    }

}
