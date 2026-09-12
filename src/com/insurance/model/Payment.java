package com.insurance.model;

public class Payment {

    private int paymentId;
    private int policyId;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String status;

    // No-argument constructor
    public Payment() {
    }

    // Parameterized constructor
    public Payment(int paymentId, int policyId,
                   double amount, String paymentDate,
                   String paymentMethod, String status) {

        this.paymentId = paymentId;
        this.policyId = policyId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId
                + ", policyId=" + policyId
                + ", amount=" + amount
                + ", paymentDate=" + paymentDate
                + ", paymentMethod=" + paymentMethod
                + ", status=" + status + "]";
    }
}
