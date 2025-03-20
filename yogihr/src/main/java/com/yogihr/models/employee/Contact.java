package com.yogihr.models.employee;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "emp_no")
    private int id;

    @Column(name = "work_email")
    private String workEmail;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "apt")
    private String apt;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    public Contact() {
    }

    public Contact(int id, String workEmail, String personalEmail, String phoneNumber, String streetAddress, String apt, String city, String state, String postalCode) {
        this.id = id;
        this.workEmail = workEmail;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.apt = apt;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", workEmail='" + workEmail + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", apt='" + apt + '\'' +
                ", city='" + city + '\'' +
                ", State='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && Objects.equals(workEmail, contact.workEmail) && Objects.equals(personalEmail, contact.personalEmail) && Objects.equals(phoneNumber, contact.phoneNumber) && Objects.equals(streetAddress, contact.streetAddress) && Objects.equals(apt, contact.apt) && Objects.equals(city, contact.city) && Objects.equals(state, contact.state) && Objects.equals(postalCode, contact.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workEmail, personalEmail, phoneNumber, streetAddress, apt, city, state, postalCode);
    }
}
