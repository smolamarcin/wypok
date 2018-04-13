package com.smola.transport.model;

import java.math.BigDecimal;

public class Transit {
    private String sourceAddress;
    private String destinationAddress;
    private BigDecimal price;
    private String date;

    public Transit() {
    }

    public Transit(String sourceAddress, String destinationAddress) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
    }

    public Transit (TransitBuilder transitBuilder) {
        this.sourceAddress = transitBuilder.sourceAddress;
        this.destinationAddress = transitBuilder.destinationAddress;
        this.price = transitBuilder.price;
        this.date = transitBuilder.date;
    }


    public String getDate() {
        return date;
    }

    public static class TransitBuilder {
        private final String sourceAddress;
        private final String destinationAddress;
        private BigDecimal price;
        private String date;

        public TransitBuilder(String sourceAddress, String destinationAddress) {
            this.sourceAddress = sourceAddress;
            this.destinationAddress = destinationAddress;
        }

        public TransitBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TransitBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public Transit build() {
            return new Transit(this);
        }
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

    public void setDate(String date) {
        this.date = date;
    }
}
