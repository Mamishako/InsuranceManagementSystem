package com.insurance.model;

public class Claim {

    private int claimId;
    private int policyId;
    private String claimDate;
    private double claimAmount;
    private String description;
    private String status;

    // No-argument constructor
    public Claim() {
    }

    // Parameterized constructor
    public Claim(int claimId, int policyId,
                 String claimDate, double claimAmount,
                 String description, String status) {

        this.claimId = claimId;
        this.policyId = policyId;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.description = description;
        this.status = status;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Claim [claimId=" + claimId
                + ", policyId=" + policyId
                + ", claimDate=" + claimDate
                + ", claimAmount=" + claimAmount
                + ", description=" + description
                + ", status=" + status + "]";
    }
}