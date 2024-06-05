package com.loschiferos.ztech.loan.domain.model.aggregates;


import com.loschiferos.ztech.loan.domain.model.valueobjects.DateRange;
import com.loschiferos.ztech.loan.domain.model.valueobjects.Name;
import com.loschiferos.ztech.loan.domain.model.valueobjects.Photo;
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
    private Name name;

    @Embedded
    private Photo photo;

    @Embedded
    private DateRange dateRange;

    //@Getter
    //@ManyToOne
    //@JoinColumn(name = "flowerpot_metric_id")
    //private FlowerpotMetric flowerpotMetric;


    public FlowerpotAssigment() {

    }
    public FlowerpotAssigment(String name, String photo, String starDate, String endDate) {
        this.name = new Name(name);
        this.photo = new Photo(photo);
        this.dateRange = new DateRange(starDate, endDate);
       // this.flowerpotMetric = flowerpotMetric;
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
