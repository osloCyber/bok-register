import java.util.*;

abstract class Vehicle {
    protected int vehicleID;
    protected String brand;
    protected String model;
    protected int yearModel;
    protected String registrationNumber;
    protected String chassisNumber;
    protected boolean driveable;
    protected int numberOfSellableWheels;
    protected int scrapyardID;

    public Vehicle(int vehicleID, String brand, String model, int yearModel,
                   String registrationNumber, String chassisNumber, boolean driveable,
                   int numberOfSellableWheels, int scrapyardID) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.yearModel = yearModel;
        this.registrationNumber = registrationNumber;
        this.chassisNumber = chassisNumber;
        this.driveable = driveable;
        this.numberOfSellableWheels = numberOfSellableWheels;
        this.scrapyardID = scrapyardID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearModel() {
        return yearModel;
    }

    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public boolean isDriveable() {
        return driveable;
    }

    public void setDriveable(boolean driveable) {
        this.driveable = driveable;
    }

    public int getNumberOfSellableWheels() {
        return numberOfSellableWheels;
    }

    public void setNumberOfSellableWheels(int numberOfSellableWheels) {
        this.numberOfSellableWheels = numberOfSellableWheels;
    }

    public int getScrapyardID() {
        return scrapyardID;
    }

    public void setScrapyardID(int scrapyardID) {
        this.scrapyardID = scrapyardID;
    }

    public abstract String getVehicleType();

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearModel=" + yearModel +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", driveable=" + driveable +
                ", numberOfSellableWheels=" + numberOfSellableWheels +
                ", scrapyardID=" + scrapyardID +
                '}';
    }
}