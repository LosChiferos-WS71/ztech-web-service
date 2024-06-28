package com.loschiferos.ztech.pot.interfaces.rest;

import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByCodeQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetFlowerpotByIdQuery;
import com.loschiferos.ztech.pot.domain.model.queries.GetSensorsByFlowerpotIdQuery;
import com.loschiferos.ztech.pot.domain.model.valueobjects.SensorType;
import com.loschiferos.ztech.pot.domain.services.FlowerpotCommandService;
import com.loschiferos.ztech.pot.domain.services.FlowerpotQueryService;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateFlowerpotResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.CreateSensorResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.FlowerpotResource;
import com.loschiferos.ztech.pot.interfaces.rest.resources.SensorResource;
import com.loschiferos.ztech.pot.interfaces.rest.transform.CreateFlowerpotCommandFromResourceAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.CreateSensorCommandFromResourceAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.FlowerpotResourceFromEntityAssembler;
import com.loschiferos.ztech.pot.interfaces.rest.transform.SensorResourceFromEntityAssembler;
import com.loschiferos.ztech.shared.domain.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**" , maxAge = 3600)
@RestController
@RequestMapping("/api/v1/flowerpots")
public class FlowerpotController {

    private final FlowerpotCommandService flowerpotCommandService;
    private final FlowerpotQueryService flowerpotQueryService;

    @Autowired
    public FlowerpotController(FlowerpotCommandService flowerpotCommandService, FlowerpotQueryService flowerpotQueryService) {
        this.flowerpotCommandService = flowerpotCommandService;
        this.flowerpotQueryService = flowerpotQueryService;
    }

    @PostMapping
    public ResponseEntity<FlowerpotResource> createFlowerpot(@RequestBody CreateFlowerpotResource resource) {
        var createFlowerpotCommand = CreateFlowerpotCommandFromResourceAssembler.toCommandFromResource(resource);
        var flowerpotId = flowerpotCommandService.handle(createFlowerpotCommand);
        if(flowerpotId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getFlowerpotByIdQuery = new GetFlowerpotByIdQuery(flowerpotId);
        var flowerpot = flowerpotQueryService.handle(getFlowerpotByIdQuery);
        if(flowerpot.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var flowerpotResource = FlowerpotResourceFromEntityAssembler.toResourceFromEntity(flowerpot.get());
        return new ResponseEntity<>(flowerpotResource, HttpStatus.CREATED);
    }

    @GetMapping("/{flowerpotId}")
    public ResponseEntity<FlowerpotResource> getFlowerpotById(@PathVariable Long flowerpotId) {
        var getFlowerpotByIdQuery = new GetFlowerpotByIdQuery(flowerpotId);
        var flowerpot = flowerpotQueryService.handle(getFlowerpotByIdQuery);
        if(flowerpot.isEmpty()) {
            throw new ResourceNotFoundException("Flowerpot not found");
        }
        var flowerpotResource = FlowerpotResourceFromEntityAssembler.toResourceFromEntity(flowerpot.get());
        return ResponseEntity.ok(flowerpotResource);
    }

    @PostMapping("/code")
    public ResponseEntity<FlowerpotResource> getFlowerpotByCode(@RequestBody String code) {
        var getFlowerpotByCodeQuery = new GetFlowerpotByCodeQuery(code.toLowerCase());
        var flowerpot = flowerpotQueryService.handle(getFlowerpotByCodeQuery);
        if(flowerpot.isEmpty()) {
            throw new ResourceNotFoundException("Flowerpot not found");
        }
        var flowerpotResource = FlowerpotResourceFromEntityAssembler.toResourceFromEntity(flowerpot.get());
        return ResponseEntity.ok(flowerpotResource);
    }

    @PostMapping("/sensors")
    public ResponseEntity<Void> createSensor(@RequestBody CreateSensorResource resource) {
        var addSensorCommand = CreateSensorCommandFromResourceAssembler.toCommandFromResource(resource);
        flowerpotCommandService.handle(addSensorCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{flowerpotId}/temperature/sensors")
    public ResponseEntity<List<SensorResource>> getTemperatureSensors(@PathVariable Long flowerpotId) {
        var getSensorsByFlowerpotIdQuery = new GetSensorsByFlowerpotIdQuery(flowerpotId);
        var sensors = flowerpotQueryService.handle(getSensorsByFlowerpotIdQuery);
        if (sensors.isEmpty()) {
            throw new ResourceNotFoundException("Sensors not found");
        }
        var temperatureSensors = sensors.stream().filter(sensor -> sensor.getType().equals(SensorType.TEMPERATURE)).toList();
        var temperatureSensorResources = temperatureSensors.stream().map(SensorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(temperatureSensorResources);
    }

    @GetMapping("/{flowerpotId}/humidity/sensors")
    public ResponseEntity<List<SensorResource>> getHumiditySensors(@PathVariable Long flowerpotId) {
        var getSensorsByFlowerpotIdQuery = new GetSensorsByFlowerpotIdQuery(flowerpotId);
        var sensors = flowerpotQueryService.handle(getSensorsByFlowerpotIdQuery);
        if (sensors.isEmpty()) {
            throw new ResourceNotFoundException("Sensors not found");
        }
        var humiditySensors = sensors.stream().filter(sensor -> sensor.getType().equals(SensorType.HUMIDITY)).toList();
        var humiditySensorResources = humiditySensors.stream().map(SensorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(humiditySensorResources);
    }

    @GetMapping("/{flowerpotId}/sunlight/sensors")
    public ResponseEntity<List<SensorResource>> getSunlightSensors(@PathVariable Long flowerpotId) {
        var getSensorsByFlowerpotIdQuery = new GetSensorsByFlowerpotIdQuery(flowerpotId);
        var sensors = flowerpotQueryService.handle(getSensorsByFlowerpotIdQuery);
        if (sensors.isEmpty()) {
            throw new ResourceNotFoundException("Sensors not found");
        }
        var sunlightSensors = sensors.stream().filter(sensor -> sensor.getType().equals(SensorType.SUNLIGHT)).toList();
        var sunlightSensorResources = sunlightSensors.stream().map(SensorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(sunlightSensorResources);
    }
}
