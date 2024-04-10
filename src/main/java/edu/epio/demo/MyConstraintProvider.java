package edu.epio.demo;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;

import static ai.timefold.solver.core.api.score.stream.Joiners.overlapping;

public class MyConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[]{
                overlappingTime(constraintFactory)
        };
    }

    private Constraint overlappingTime(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(Appointment.class,
                overlapping(Appointment::getStart, Appointment::getEnd))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("overlapping time");
    }
}
