package com.smola.transport.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Embedded
    private Distance distance = new Distance(0);
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    public Transit() {
    }

    private Transit(Builder builder) {
        sourceAddress = builder.sourceAddress;
        destinationAddress = builder.destinationAddress;
        price = builder.price;
        distance = builder.distance;
        date = builder.date;
    }

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

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    private static class Builder {
        private String sourceAddress;
        private String destinationAddress;
        private BigDecimal price;
        private Distance distance;
        private LocalDate date;

        public Builder withSourceAddress(String sourceAddress) {
            this.sourceAddress = sourceAddress;
            return this;
        }

        private Builder withdDstinationAddress(String destinationAddress) {
            this.destinationAddress = destinationAddress;
            return this;
        }

        private Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        private Builder withDistance(Distance distance) {
            this.distance = distance;
            return this;
        }

        private Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Transit build() {
            return new Transit(this);
        }

    }

}
