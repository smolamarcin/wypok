package com.smola.transport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sourceAddress;
    private String destinationAddress;
    private BigDecimal price;
    @OneToOne(cascade = CascadeType.ALL)
    private Meters meters;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;


    public LocalDate getDate() {
        return date;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meters getMeters() {
        return meters;
    }

    public void setMeters(Meters meters) {
        this.meters = meters;
    }

}
