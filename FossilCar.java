public class FossilCar extends Vehicle {
    private String fuelType;
    private int fuelAmount;

    public FossilCar(int vehicleID, String brand, String model, int yearModel,
                     String registrationNumber, String chassisNumber, boolean driveable,
                     int numberOfSellableWheels, int scrapyardID, String fuelType, int fuelAmount) {
        super(vehicleID, brand, model, yearModel, registrationNumber, chassisNumber,
                driveable, numberOfSellableWheels, scrapyardID);
        this.fuelType = fuelType;
        this.fuelAmount = fuelAmount;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(int fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    @Override
    public String getVehicleType() {
        return "FossilCar";
    }

    @Override
    public String toString() {
        return "FossilCar{" +
                "fuelType='" + fuelType + '\'' +
                ", fuelAmount=" + fuelAmount +
                "} " + super.toString();
    }
}