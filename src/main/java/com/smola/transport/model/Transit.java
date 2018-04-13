package com.smola.transport.model;

public class Transit {
    private SourceAddress sourceAddress;
    private DestinationAddress destinationAddress;
    private Price price;
    private Date date;

    public Transit(SourceAddress sourceAddress, DestinationAddress destinationAddress) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
    }

    public Transit(TransitBuilder transitBuilder) {
        this.sourceAddress = transitBuilder.sourceAddress;
        this.destinationAddress = transitBuilder.destinationAddress;
        this.price = transitBuilder.price;
        this.date = transitBuilder.date;
    }

    public SourceAddress getSourceAddress() {
        return sourceAddress;
    }

    public DestinationAddress getDestinationAddress() {
        return destinationAddress;
    }

    public Price getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public static class TransitBuilder {
        private final SourceAddress sourceAddress;
        private final DestinationAddress destinationAddress;
        private Price price;
        private Date date;

        public TransitBuilder(SourceAddress sourceAddress, DestinationAddress destinationAddress) {
            this.sourceAddress = sourceAddress;
            this.destinationAddress = destinationAddress;
        }

        public TransitBuilder setPrice(Price price) {
            this.price = price;
            return this;
        }

        public TransitBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Transit build() {
            return new Transit(this);
        }
    }
}
