import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        DbScrapyard dbScrapyard = new DbScrapyard();
        Program program = new Program();

        try {
            program.run();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        /*
        fileReader.readAllData();

        for (Scrapyard scrapyard : fileReader.getScrapyardList()) {
            dbScrapyard.insertScrapyard(scrapyard);
        }

        for (Vehicle vehicle : fileReader.getVehicleList()) {
            dbScrapyard.insertVehicle(vehicle);
        }
        */
    }
}