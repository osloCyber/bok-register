import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    FileReader file = new FileReader();
    DbScrapyard db = new DbScrapyard();

    public void run() throws SQLException {
        System.out.println(UserCommunication.WELCOME);
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println(UserCommunication.IMPORT_DATA);
            System.out.println(UserCommunication.SEE_ALL_VEHICLES);
            System.out.println(UserCommunication.SEE_TOTAL_FUEL);
            System.out.println(UserCommunication.SEE_DRIVEABLE_VEHICLES);
            System.out.println(UserCommunication.SEE_VEHICLES_BY_SCRAPYARD);
            System.out.println(UserCommunication.EXIT_PROGRAM);
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> importDataFromFile();
                case 2 -> seeAllVehicles();
                case 3 -> seeTotalFuel();
                case 4 -> seeDriveableVehicles();
                case 5 -> seeVehiclesByScrapyard();
                case 6 -> exitProgram();
                default -> System.out.println("YOU MUST CHOOSE BETWEEN 1-6:");
            }
        }
    }

    private void importDataFromFile() {
        System.out.println("IMPORTING DATA:");

        file.readAllData();

        System.out.println("Starting import to database");

        for (Scrapyard scrapyard : file.getScrapyardList()) {
            db.insertScrapyard(scrapyard);
            System.out.println("Imported: " + scrapyard);
        }

        int importedCount = 0;
        for (Vehicle vehicle : file.getVehicleList()) {
            db.insertVehicle(vehicle);
            importedCount++;
            System.out.println("Imported " + importedCount + " : " + vehicle);
        }

        System.out.println("IMPORT COMPLETED:");
        System.out.println("Number of scrapyards: " + file.getScrapyardList().size());
        System.out.println("Number of vehicles: " + importedCount);
    }

    private void seeAllVehicles() throws SQLException {
        System.out.println("Information about all vehicles:");

        for (FossilCar car : db.getAllFossilCarsList()) {
            System.out.println(car);
        }
        for (ElectricCar car : db.getAllElectricCarsList()) {
            System.out.println(car);
        }
        for (Motorcycle motorcycle : db.getAllMotorcyclesList()) {
            System.out.println(motorcycle);
        }
    }

    private void seeTotalFuel() throws SQLException {
        System.out.println("TOTAL FUEL IN FOSSIL CARS:");
        int totalFuel = db.getTotalFuelAmount();
        System.out.println("Total amount of fuel: " + totalFuel + " liters");

        if (totalFuel == 0) {
            System.out.println("No fossil cars found or no fuel registered.");
        }
    }

    private void seeDriveableVehicles() throws SQLException {
        System.out.println("DRIVEABLE VEHICLES:");
        boolean found = false;

        for (FossilCar car : db.getAllFossilCarsList()) {
            if (car.isDriveable()) {
                System.out.println(car);
                found = true;
            }
        }
        for (ElectricCar car : db.getAllElectricCarsList()) {
            if (car.isDriveable()) {
                System.out.println(car);
                found = true;
            }
        }
        for (Motorcycle motorcycle : db.getAllMotorcyclesList()) {
            if (motorcycle.isDriveable()) {
                System.out.println(motorcycle);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No driveable vehicles found.");
        }
    }

    private void seeVehiclesByScrapyard() throws SQLException {
        System.out.println("VEHICLES PER SCRAPYARD:");

        ArrayList<Scrapyard> scrapyards = db.getAllScrapyardsList();

        if (scrapyards.isEmpty()) {
            System.out.println("No scrapyards found in database.");
            return;
        }

        for (Scrapyard scrapyard : scrapyards) {
            System.out.println("\n" + scrapyard);

            int vehicleCount = 0;

            for (FossilCar car : db.getAllFossilCarsList()) {
                if (car.getScrapyardID() == scrapyard.getScrapyardID()) {
                    System.out.println(car);
                    vehicleCount++;
                }
            }
            for (ElectricCar car : db.getAllElectricCarsList()) {
                if (car.getScrapyardID() == scrapyard.getScrapyardID()) {
                    System.out.println(car);
                    vehicleCount++;
                }
            }
            for (Motorcycle motorcycle : db.getAllMotorcyclesList()) {
                if (motorcycle.getScrapyardID() == scrapyard.getScrapyardID()) {
                    System.out.println(motorcycle);
                    vehicleCount++;
                }
            }

            if (vehicleCount == 0) {
                System.out.println("No vehicles registered");
            } else {
                System.out.println("Total " + vehicleCount + " vehicles");
            }
        }
    }

    private void exitProgram() {
        System.out.println("Goodbye!");
    }
}