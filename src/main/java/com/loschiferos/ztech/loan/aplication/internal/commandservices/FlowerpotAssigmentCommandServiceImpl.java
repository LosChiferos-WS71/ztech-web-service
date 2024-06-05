package com.loschiferos.ztech.loan.aplication.internal.commandservices;

import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssigment;
import com.loschiferos.ztech.loan.domain.model.commands.CreateFlowerpotAssigmentCommand;
import com.loschiferos.ztech.loan.domain.model.commands.RequestFlowerpotAssigmentCommand;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssigmentCommandService;
import com.loschiferos.ztech.loan.infrastructure.persistence.jpa.repositories.FlowerpotAssigmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is the implementation of the EnrollmentCommandService interface.
 * It is responsible for handling the commands related to the Enrollment aggregate.
 * It uses the EnrollmentRepository to save the Enrollment aggregate.
 */
@Service
public class FlowerpotAssigmentCommandServiceImpl implements FlowerpotAssigmentCommandService {
    private final FlowerpotAssigmentRepository flowerpotAssigmentRepository;

    //private final FlowerpotMetricRepository flowerpotMetricRepository;

    public FlowerpotAssigmentCommandServiceImpl(FlowerpotAssigmentRepository flowerpotAssigmentRepository) {
        this.flowerpotAssigmentRepository = flowerpotAssigmentRepository;
        //this.flowerpotMetricRepository = flowerpotMetricRepository;
    }


    //@Override
    //public Long handle(CreateFlowerpotAssigmentCommand command) {
    //    FlowerpotMetric flowerpotMetric = flowerpotMetricRepository.findById(command.flowerpotMetricId())
    //            .orElseThrow(() -> new RuntimeException("Student not found"));
    //    var flowerpotAssigment = new FlowerpotAssigment(command.name(), command.photo(), command.startDate(),
    //            command.endDate(), flowerpotMetric);
    //    flowerpotAssigmentRepository.save(flowerpotAssigment);
    //    return flowerpotAssigment.getId();
    //}

    @Override
    public Long handle(CreateFlowerpotAssigmentCommand command) {
        var flowerpotAssigment = new FlowerpotAssigment(command.name(), command.photo(), command.startDate(),
                command.endDate());
        flowerpotAssigmentRepository.save(flowerpotAssigment);
        return flowerpotAssigment.getId();
    }

    @Override
    public Long handle(RequestFlowerpotAssigmentCommand command) {
        return null;
    }
}
