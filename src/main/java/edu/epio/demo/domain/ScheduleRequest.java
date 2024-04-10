package edu.epio.demo.domain;

import edu.epio.demo.Appointment;

import java.time.LocalTime;
import java.util.List;

public class ScheduleRequest {
    private List<Appointment> appointments;
    private List<LocalTime> startTimes;

    public ScheduleRequest() {

    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<LocalTime> getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(List<LocalTime> startTimes) {
        this.startTimes = startTimes;
    }
}
