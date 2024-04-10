package edu.epio.demo;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;

import java.time.Duration;
import java.time.LocalTime;

@PlanningEntity
public class Appointment {

    @PlanningId
    String name;

    Duration duration;

    @PlanningVariable
    LocalTime start;

    public Appointment() {

    }

    public Appointment(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public LocalTime getEnd(){
        return start.plus(duration);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }
}
