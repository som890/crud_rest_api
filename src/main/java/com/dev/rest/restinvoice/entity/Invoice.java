package com.dev.rest.restinvoice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // It indicates that the class is JPA entity, meaning it associated with a table in the database
public class Invoice {
    // The ID should be automatically generated
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double amount;
    private Double finalAmount;
    private String number;
    private String receivedDate;
    private String type;
    private String vendor;
    private String comments;

    public Invoice() {}

    public Invoice(Long id, String name, Double amount, Double finalAmount, String number, String receivedDate, String type, String vendor, String comments) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.finalAmount = finalAmount;
        this.number = number;
        this.receivedDate = receivedDate;
        this.type = type;
        this.vendor = vendor;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", finalAmount=" + finalAmount +
                ", number='" + number + '\'' +
                ", receivedDate='" + receivedDate + '\'' +
                ", type='" + type + '\'' +
                ", vendor='" + vendor + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
