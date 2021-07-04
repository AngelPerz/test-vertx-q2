package org.acme.consumers;

import io.quarkus.vertx.ConsumeEvent;
import io.vertx.core.eventbus.EventBus;
import org.acme.constants.Constants;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TestLauncher {

    private static final Logger LOGGER = Logger.getLogger(TestLauncher.class);

    @Inject
    EventBus bus;

    private final int workUnits = 5;
    private final boolean waitBetweenWorks = true;


    @ConsumeEvent(value = Constants.TEST_START_EVENT_BUS_CHANNEL)
    public void consumeTestStart(Boolean b) {
        LOGGER.infof("Starting test with [%d] workUnits", workUnits);

        for (int i = 0; i < workUnits; i++) {

            bus.publish(Constants.WORK_UNIT_EVENT_BUS_CHANNEL, i);

            if (waitBetweenWorks){
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException e) {
                    LOGGER.error("Wait interrupted", e);
                }
            }
        }

    }

}
