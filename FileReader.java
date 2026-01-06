import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    protected ArrayList<Vehicle> vehicleList = new ArrayList<>();
    protected ArrayList<Scrapyard> scrapyardList = new ArrayList<>();

    public void readAllData() {
        String filename = PropertiesProvider.PROPS.getProperty("vehicles_file");
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            readScrapyards(scanner);
            readVehicles(scanner);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot read file: " + e.getMessage(), e);
        }
    }

    private void readScrapyards(Scanner scanner) {

        int numScrapyards = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numScrapyards; i++) {
            int id = scanner.nextInt();
            scanner.nextLine();
            String name = scanner.nextLine();
            String address = scanner.nextLine();
            String phone = scanner.nextLine();

            scrapyardList.add(new Scrapyard(id, name, address, phone));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }

        for (Scrapyard scrapyard : scrapyardList) {
            System.out.println(scrapyard);
        }
    }

    private void readVehicles(Scanner scanner) {

        int numVehicles = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numVehicles; i++) {
            int vehicleID = scanner.nextInt();
            scanner.nextLine();
            int scrapyardID = scanner.nextInt();
            scanner.nextLine();
            String vehicleType = scanner.nextLine();
            String brand = scanner.nextLine();
            String model = scanner.nextLine();
            int yearModel = scanner.nextInt();
            scanner.nextLine();
            String registrationNumber = scanner.nextLine();
            String chassisNumber = scanner.nextLine();
            boolean driveable = scanner.nextBoolean();
            scanner.nextLine();
            int numberOfSellableWheels = scanner.nextInt();
            scanner.nextLine();

            switch (vehicleType) {
                case "FossilCar":
                    String fuelType = scanner.nextLine();
                    int fuelAmount = scanner.nextInt();
                    scanner.nextLine();
                    vehicleList.add(new FossilCar(vehicleID, brand, model, yearModel,
                            registrationNumber, chassisNumber, driveable, numberOfSellableWheels,
                            scrapyardID, fuelType, fuelAmount));
                    break;

                case "ElectricCar":
                    int batteryCapacity = scanner.nextInt();
                    scanner.nextLine();
                    int chargeLevel = scanner.nextInt();
                    scanner.nextLine();
                    vehicleList.add(new ElectricCar(vehicleID, brand, model, yearModel,
                            registrationNumber, chassisNumber, driveable, numberOfSellableWheels,
                            scrapyardID, batteryCapacity, chargeLevel));
                    break;

                case "Motorcycle":
                    boolean hasSidecar = scanner.nextBoolean();
                    scanner.nextLine();
                    int engineCapacity = scanner.nextInt();
                    scanner.nextLine();
                    boolean isModified = scanner.nextBoolean();
                    scanner.nextLine();
                    int numberOfWheels = scanner.nextInt();
                    scanner.nextLine();
                    vehicleList.add(new Motorcycle(vehicleID, brand, model, yearModel,
                            registrationNumber, chassisNumber, driveable, numberOfSellableWheels,
                            scrapyardID, hasSidecar, engineCapacity, isModified, numberOfWheels));
                    break;
            }

            if (i < numVehicles - 1 && scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }

        for (Vehicle vehicle : vehicleList) {
            System.out.println("ID: " + vehicle.getVehicleID() + "  " + vehicle);
        }
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public ArrayList<Scrapyard> getScrapyardList() {
        return scrapyardList;
    }
}