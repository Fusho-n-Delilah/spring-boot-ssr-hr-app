package com.yogihr.dtos;

import com.yogihr.models.employee.Contact;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WebContact {
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 50, message = "is too long")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String workEmail;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 50, message = "is too long")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String personalEmail;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 15, message = "is too long, please use a format with less characters")
    private String phoneNumber;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 50, message = "is too long")
    private String streetAddress;

    @Size(max = 20, message = "is too long")
    private String apt;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 30, message = "is too long. Please abbreviate the city name.")
    private String city;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 11, message = "is too long of a name")
    private String state;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    @Size(max = 10, message = "is too long. please use a standard US postal code.")
    @Pattern(regexp = "[0-9]{5}(['-']?[0-9]{4})?$")
    private String postalCode;

    public WebContact(){

    }

    public WebContact(Contact contact){
        this.workEmail = contact.getWorkEmail();
        this.personalEmail = contact.getPersonalEmail();
        this.phoneNumber = contact.getPhoneNumber();
        this.streetAddress = contact.getStreetAddress();
        this.apt = contact.getApt();
        this.city = contact.getCity();
        this.state = contact.getState();
        this.postalCode = contact.getPostalCode();
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
}
