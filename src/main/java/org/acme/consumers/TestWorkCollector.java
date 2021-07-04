package org.acme.consumers;

import io.quarkus.vertx.ConsumeEvent;
import org.acme.buscodecs.WorkFinished;
import org.acme.constants.Constants;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class TestWorkCollector {

    private static final Logger LOGGER = Logger.getLogger(TestWorkCollector.class);

    private final Map<WorkFinished, Boolean> finishedWorks = new HashMap<>();


    @ConsumeEvent(value = Constants.FINISHED_WORK_UNIT_EVENT_BUS_CHANNEL)
    public void consumeTestStart(WorkFinished workFinished) {
        LOGGER.infof("WorkCollector :: received finish work [%s, %d]", workFinished.getWorkerConsumer(), workFinished.getWork());

        finishedWorks.put(workFinished, Boolean.TRUE);

        if (Boolean.TRUE.equals(finishedWorks.get(new WorkFinished("ConsumerA", workFinished.getWork()))) &&
            Boolean.TRUE.equals(finishedWorks.get(new WorkFinished("ConsumerB", workFinished.getWork()))) &&
            Boolean.TRUE.equals(finishedWorks.get(new WorkFinished("ConsumerC", workFinished.getWork())))) {

            LOGGER.infof("WorkCollector :: all works completed by consumers for workUnit [%d]", workFinished.getWork());
        }
    }
}
