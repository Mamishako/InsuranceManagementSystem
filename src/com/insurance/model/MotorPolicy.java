package com.insurance.model;

public class MotorPolicy extends Policy {

    // Motor-specific fields
    private String vehicleMake;
    private String vehicleModel;
    private String plateNumber;
    private double vehicleValue;

    // No-argument constructor
    public MotorPolicy() {
        super();
    }

    // Parameterized constructor
    public MotorPolicy(int policyId, String policyNumber, int customerId,
                       String policyType, double premium,
                       double coverageAmount, String startDate,
                       String endDate, String status,
                       String vehicleMake, String vehicleModel,
                       String plateNumber, double vehicleValue) {

        super(policyId, policyNumber, customerId, policyType,
              premium, coverageAmount, startDate, endDate, status);

        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.plateNumber = plateNumber;
        this.vehicleValue = vehicleValue;
    }

    // Getters and Setters

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    // toString method
    @Override
    public String toString() {
        return super.toString() + " -> MotorPolicy [vehicleMake=" + vehicleMake
                + ", vehicleModel=" + vehicleModel
                + ", plateNumber=" + plateNumber
                + ", vehicleValue=" + vehicleValue + "]";
    }
    }
