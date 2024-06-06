package com.loschiferos.ztech.loan.domain.model.aggregates;

import com.loschiferos.ztech.loan.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class FlowerpotAssigment extends AbstractAggregateRoot<FlowerpotAssigment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PlantOwnerId plantOwnerId;

    @Embedded
    private FlowerpotId flowerpotId;

    @Embedded
    private PlantTypeId plantTypeId;

    @Embedded
    private Name name;

    @Embedded
    private Photo photo;

    @Embedded
    private DateRange dateRange;

    public FlowerpotAssigment() { }

    public FlowerpotAssigment(PlantOwnerId plantOwnerId, FlowerpotId flowerpotId, PlantTypeId plantTypeId, String name, String photo, String starDate, String endDate) {
        this.plantOwnerId = plantOwnerId;
        this.flowerpotId = flowerpotId;
        this.plantTypeId = plantTypeId;
        this.name = new Name(name);
        this.photo = new Photo(photo);
        this.dateRange = new DateRange(starDate, endDate);
    }

    public FlowerpotAssigment(PlantOwnerId plantOwnerId, FlowerpotId flowerpotId, PlantTypeId plantTypeId) {
        this();
        this.plantOwnerId = plantOwnerId;
        this.flowerpotId = flowerpotId;
        this.plantTypeId = plantTypeId;
    }

    public String getName() {
        return this.name.getName();
    }

    public String getPhoto() {
        return this.photo.getPhoto();
    }

    public String getDateRange() {
        return this.dateRange.getDateRange();
    }
}
