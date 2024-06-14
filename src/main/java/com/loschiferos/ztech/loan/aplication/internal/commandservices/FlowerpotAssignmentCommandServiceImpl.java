package com.loschiferos.ztech.loan.aplication.internal.commandservices;

import com.loschiferos.ztech.loan.aplication.internal.outboundedservices.acl.ExternalFlowerpotService;
import com.loschiferos.ztech.loan.aplication.internal.outboundedservices.acl.ExternalPlantOwnerService;
import com.loschiferos.ztech.loan.aplication.internal.outboundedservices.acl.ExternalPlantTypeService;
import com.loschiferos.ztech.loan.domain.model.aggregates.FlowerpotAssignment;
import com.loschiferos.ztech.loan.domain.model.commands.CreateFlowerpotAssignmentCommand;
import com.loschiferos.ztech.loan.domain.model.commands.RequestFlowerpotAssignmentCommand;
import com.loschiferos.ztech.loan.domain.model.valueobjects.FlowerpotId;
import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantOwnerId;
import com.loschiferos.ztech.loan.domain.model.valueobjects.PlantTypeId;
import com.loschiferos.ztech.loan.domain.services.FlowerpotAssignmentCommandService;
import com.loschiferos.ztech.loan.infrastructure.persistence.jpa.repositories.FlowerpotAssignmentRepository;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import com.loschiferos.ztech.shared.domain.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is the implementation of the EnrollmentCommandService interface.
 * It is responsible for handling the commands related to the Enrollment aggregate.
 * It uses the EnrollmentRepository to save the Enrollment aggregate.
 */
@Service
public class FlowerpotAssignmentCommandServiceImpl implements FlowerpotAssignmentCommandService {
    private final FlowerpotAssignmentRepository flowerpotAssignmentRepository;
    private final ExternalPlantOwnerService externalPlantOwnerService;
    private final ExternalFlowerpotService externalFlowerpotService;
    private final ExternalPlantTypeService externalPlantTypeService;

    public FlowerpotAssignmentCommandServiceImpl(FlowerpotAssignmentRepository flowerpotAssignmentRepository, ExternalPlantOwnerService externalPlantOwnerService, ExternalFlowerpotService externalFlowerpotService, ExternalPlantTypeService externalPlantTypeService) {
        this.flowerpotAssignmentRepository = flowerpotAssignmentRepository;
        this.externalPlantOwnerService = externalPlantOwnerService;
        this.externalFlowerpotService = externalFlowerpotService;
        this.externalPlantTypeService = externalPlantTypeService;
    }

    @Override
    public Long handle(CreateFlowerpotAssignmentCommand command) {
        var plantOwnerId = externalPlantOwnerService.fetchPlantOwnerIdById(command.plantOwnerId());
        var flowerpotId = externalFlowerpotService.fetchFlowerpotIdById(command.flowerpotId());
        var plantTypeId = externalPlantTypeService.fetchPlantTypeIdById(command.plantTypeId());

        validationCreateFlowerpotAssignmentCommand(command, plantOwnerId, flowerpotId, plantTypeId);

        var flowerpotAssignment = new FlowerpotAssignment(plantOwnerId.get(), flowerpotId.get(), plantTypeId.get(), command.name(), command.photo(), command.startDate(),
                command.endDate());

        flowerpotAssignmentRepository.save(flowerpotAssignment);
        return flowerpotAssignment.getId();
    }

    @Override
    public Long handle(RequestFlowerpotAssignmentCommand command) {
        return null;
    }

    private void validationCreateFlowerpotAssignmentCommand(CreateFlowerpotAssignmentCommand command, Optional<PlantOwnerId> plantOwnerId, Optional<FlowerpotId> flowerpotId, Optional<PlantTypeId> plantTypeId) {
        if(command.plantOwnerId().describeConstable().isEmpty()) {
            throw new ValidationException("Plant Owner Id cannot be empty");
        }
        if(command.flowerpotId().describeConstable().isEmpty()) {
            throw new ValidationException("Flowerpot Id cannot be empty");
        }
        if(command.plantTypeId().describeConstable().isEmpty()) {
            throw new ValidationException("Plant Type Id cannot be empty");
        }

        if (plantOwnerId.isEmpty()) {
            throw new ResourceNotFoundException("Plant Owner not found");
        }
        if (flowerpotId.isEmpty()) {
            throw new ResourceNotFoundException("Flowerpot not found");
        }
        if (plantTypeId.isEmpty()) {
            throw new ResourceNotFoundException("Plant Type not found");
        }
        if (command.name() == null || command.name().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }
        if (command.photo() == null || command.photo().isEmpty()) {
            throw new ValidationException("Photo cannot be empty");
        }
    }
}
