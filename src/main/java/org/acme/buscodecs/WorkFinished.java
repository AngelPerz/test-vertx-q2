package org.acme.buscodecs;

import java.util.Objects;

public class WorkFinished {

    private String workerConsumer;
    private Integer work;

    public WorkFinished(String workerConsumer, Integer work) {
        this.workerConsumer = workerConsumer;
        this.work = work;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkFinished that = (WorkFinished) o;
        return Objects.equals(workerConsumer, that.workerConsumer) && Objects.equals(work, that.work);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workerConsumer, work);
    }

    public String getWorkerConsumer() {
        return workerConsumer;
    }

    public WorkFinished setWorkerConsumer(String workerConsumer) {
        this.workerConsumer = workerConsumer;
        return this;
    }

    public Integer getWork() {
        return work;
    }

    public WorkFinished setWork(Integer work) {
        this.work = work;
        return this;
    }
}
