package com.insurance.model;

public class Customer {

    // Attributes
    private int customerId;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String phone;
    private String email;
    private String address;

    // No-argument constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(int customerId, String fullName, String gender,
                    String dateOfBirth, String phone,
                    String email, String address) {

        this.customerId = customerId;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer [customerId=" + customerId
                + ", fullName=" + fullName
                + ", gender=" + gender
                + ", dateOfBirth=" + dateOfBirth
                + ", phone=" + phone
                + ", email=" + email
                + ", address=" + address + "]";
    }
}
