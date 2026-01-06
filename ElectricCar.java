public class ElectricCar extends Vehicle {
    private int batteryCapacity;
    private int chargeLevel;

    public ElectricCar(int vehicleID, String brand, String model, int yearModel,
                       String registrationNumber, String chassisNumber, boolean driveable,
                       int numberOfSellableWheels, int scrapyardID, int batteryCapacity, int chargeLevel) {
        super(vehicleID, brand, model, yearModel, registrationNumber, chassisNumber,
                driveable, numberOfSellableWheels, scrapyardID);
        this.batteryCapacity = batteryCapacity;
        this.chargeLevel = chargeLevel;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(int chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    @Override
    public String getVehicleType() {
        return "ElectricCar";
    }

    @Override
    public String toString() {
        return "ElectricCar{" +
                "batteryCapacity=" + batteryCapacity +
                ", chargeLevel=" + chargeLevel +
                "} " + super.toString();
    }
}