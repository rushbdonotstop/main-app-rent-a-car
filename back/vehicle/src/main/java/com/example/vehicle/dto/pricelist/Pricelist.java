package com.example.vehicle.dto.pricelist;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class Pricelist implements Comparable<Pricelist>{

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime  endDate;

    private float price;

    private float priceByMile;

    private float priceCollision;

    private Long vehicleId;

    private VehicleDiscount vehicleDiscount;

    public Pricelist() {
    }

    public Pricelist(Long id, LocalDateTime startDate, LocalDateTime endDate, float price, float priceByMile, float priceCollision, Long vehicleId, VehicleDiscount vehicleDiscount) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.vehicleId = vehicleId;
        this.vehicleDiscount = vehicleDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceByMile() {
        return priceByMile;
    }

    public void setPriceByMile(float priceByMile) {
        this.priceByMile = priceByMile;
    }

    public float getPriceCollision() {
        return priceCollision;
    }

    public void setPriceCollision(float priceCollision) {
        this.priceCollision = priceCollision;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDiscount getVehicleDiscount() {
        return vehicleDiscount;
    }

    public void setVehicleDiscount(VehicleDiscount vehicleDiscount) {
        this.vehicleDiscount = vehicleDiscount;
    }

    @Override
    public String toString() {
        return "PricelistDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", priceByMile=" + priceByMile +
                ", priceCollision=" + priceCollision +
                ", vehicleId=" + vehicleId +
                ", vehicleDiscountId=" + vehicleDiscount +
                '}';
    }

    @Override
    public int compareTo(Pricelist o) {
        return this.startDate.compareTo(o.startDate);
    }

    public com.example.vehicle.xmlmodel.pricelist.Pricelist toXML(Pricelist pricelist) throws DatatypeConfigurationException {
        com.example.vehicle.xmlmodel.pricelist.Pricelist pricelistXML = new com.example.vehicle.xmlmodel.pricelist.Pricelist();
        pricelistXML.setId(pricelist.getId());
        pricelistXML.setPrice(pricelist.getPrice());
        pricelistXML.setPriceByMile(pricelist.getPriceByMile());
        pricelistXML.setPriceCollision(pricelist.getPriceCollision());

        LocalDate date = pricelist.getStartDate().toLocalDate();
        GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
        XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        pricelistXML.setStartDate(xcal);

        date = pricelist.getEndDate().toLocalDate();
        gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
        xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

        pricelistXML.setEndDate(xcal);

        com.example.vehicle.xmlmodel.pricelist.vehicle_discount.VehicleDiscount vehicleDiscountXML = new com.example.vehicle.xmlmodel.pricelist.vehicle_discount.VehicleDiscount();

        vehicleDiscountXML.setDiscount(pricelist.getVehicleDiscount().getDiscount());
        vehicleDiscountXML.setId(pricelist.getVehicleDiscount().getId());
        vehicleDiscountXML.setNumDays(pricelist.getVehicleDiscount().getNumDays());

        pricelistXML.setVehicleDiscount(vehicleDiscountXML);

        return pricelistXML;
    }
}
