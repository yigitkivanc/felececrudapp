package com.felececrud.felececrudapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "other_information")
public class OtherInformation {
    // define our fields

    // define constructors

    // define getter setters

    // define toString

    // annotate fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "iban")
    private String iban;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_phone")
    private String emergencyContactPhone;


    public OtherInformation(){

    }

    public OtherInformation(String fullAdress, String bankName, String iban, String emergencyContactName, String emergencyContactPhone) {
        this.fullAddress = fullAdress;
        this.bankName = bankName;
        this.iban = iban;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAdress) {
        this.fullAddress = fullAdress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    @Override
    public String toString() {
        return "OtherInformation{" +
                "id=" + id +
                ", fullAddress='" + fullAddress + '\'' +
                ", bankName='" + bankName + '\'' +
                ", iban='" + iban + '\'' +
                ", emergencyContactName='" + emergencyContactName + '\'' +
                ", emergencyContactPhone='" + emergencyContactPhone + '\'' +
                '}';
    }
}
