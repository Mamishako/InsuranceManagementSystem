package com.insurance.model;

public class Policy {

    private int policyId;
    private String policyNumber;
    private int customerId;
    private String policyType;
    private double premium;
    private double coverageAmount;
    private String startDate;
    private String endDate;
    private String status;

    // No-argument constructor
    public Policy() {
    }

    // Parameterized constructor
    public Policy(int policyId, String policyNumber, int customerId,
                  String policyType, double premium,
                  double coverageAmount, String startDate,
                  String endDate, String status) {

        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.customerId = customerId;
        this.policyType = policyType;
        this.premium = premium;
        this.coverageAmount = coverageAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Policy [policyId=" + policyId
                + ", policyNumber=" + policyNumber
                + ", customerId=" + customerId
                + ", policyType=" + policyType
                + ", premium=" + premium
                + ", coverageAmount=" + coverageAmount
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", status=" + status + "]";
    }
}