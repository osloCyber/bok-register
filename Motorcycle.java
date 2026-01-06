public class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    private int engineCapacity;
    private boolean isModified;
    private int numberOfWheels;

    public Motorcycle(int vehicleID, String brand, String model, int yearModel,
                      String registrationNumber, String chassisNumber, boolean driveable,
                      int numberOfSellableWheels, int scrapyardID, boolean hasSidecar,
                      int engineCapacity, boolean isModified, int numberOfWheels) {
        super(vehicleID, brand, model, yearModel, registrationNumber, chassisNumber,
                driveable, numberOfSellableWheels, scrapyardID);
        this.hasSidecar = hasSidecar;
        this.engineCapacity = engineCapacity;
        this.isModified = isModified;
        this.numberOfWheels = numberOfWheels;
    }

    public boolean getHasSidecar() {
        return hasSidecar;
    }

    public void setHasSidecar(boolean hasSidecar) {
        this.hasSidecar = hasSidecar;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public boolean getIsModified() {
        return isModified;
    }

    public void setIsModified(boolean isModified) {
        this.isModified = isModified;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "hasSidecar=" + hasSidecar +
                ", engineCapacity=" + engineCapacity +
                ", isModified=" + isModified +
                ", numberOfWheels=" + numberOfWheels +
                "} " + super.toString();
    }
}