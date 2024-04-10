package edu.epio.demo;

import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.api.solver.SolverManager;
import ai.timefold.solver.core.config.solver.SolverConfig;
import edu.epio.demo.domain.ScheduleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@RestController
public class Epio2024JavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Epio2024JavaApplication.class, args);
	}

	@Autowired
	SolverManager<Schedule, String> solverManager;

	@GetMapping("/")
	public Schedule schedule() throws ExecutionException, InterruptedException {
		var appointments = List.of(
				new Appointment("doctor", Duration.ofHours(1)),
				new Appointment("pub", Duration.ofHours(4)),
				new Appointment("barber", Duration.ofMinutes(70))
				);
		var startTimes = List.of(
				LocalTime.of(15, 0),
				LocalTime.of(16, 0),
				LocalTime.of(17, 0),
				LocalTime.of(18, 0)
		);
		var problem = new Schedule(appointments, startTimes);
		var solution = solverManager.solve("job 1", problem).getFinalBestSolution();
		return solution;
	}

	@PostMapping("/")
	public Schedule schedule(@RequestBody ScheduleRequest request) throws ExecutionException, InterruptedException {
		List<Appointment> appointments = request.getAppointments();
		List<LocalTime> startTimes = request.getStartTimes();

		SolverFactory<Schedule> solverFactory = SolverFactory.createFromXmlResource(
				"appointments.xml");

		var solver = solverFactory.buildSolver();
		var problem = new Schedule(appointments, startTimes);
		var solution = solver.solve(problem);
		return solution;
	}
}
