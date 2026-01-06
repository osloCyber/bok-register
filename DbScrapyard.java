import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;
import java.util.ArrayList;

public class DbScrapyard {
    private final MysqlDataSource scrapyardDS;

    public static final String INSERT_SCRAPYARD = "INSERT IGNORE INTO Scrapyard (ScrapyardID, Name, Address, PhoneNumber) VALUES (?, ?, ?, ?)";
    private static final String INSERT_FOSSIL_CAR = "INSERT IGNORE INTO FossilCar (VehicleID, Brand, Model, YearModel, RegistrationNumber, ChassisNumber, Driveable, NumberOfSellableWheels, ScrapyardID, FuelType, FuelAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ELECTRIC_CAR = "INSERT IGNORE INTO ElectricCar (VehicleID, Brand, Model, YearModel, RegistrationNumber, ChassisNumber, Driveable, NumberOfSellableWheels, ScrapyardID, BatteryCapacity, ChargeLevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_MOTORCYCLE = "INSERT IGNORE INTO Motorcycle (VehicleID, Brand, Model, YearModel, RegistrationNumber, ChassisNumber, Driveable, NumberOfSellableWheels, ScrapyardID, HasSidecar, EngineCapacity, IsModified, NumberOfWheels) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_FOSSIL_CARS = "SELECT * FROM FossilCar";
    private static final String SELECT_ALL_ELECTRIC_CARS = "SELECT * FROM ElectricCar";
    private static final String SELECT_ALL_MOTORCYCLES = "SELECT * FROM Motorcycle";
    private static final String SELECT_ALL_SCRAPYARDS = "SELECT * FROM Scrapyard";
    private static final String SELECT_TOTAL_FUEL = "SELECT SUM(FuelAmount) as total FROM FossilCar";

    public DbScrapyard() {
        scrapyardDS = new MysqlDataSource();
        scrapyardDS.setServerName(PropertiesProvider.PROPS.getProperty("host"));
        scrapyardDS.setDatabaseName(PropertiesProvider.PROPS.getProperty("db_name"));
        scrapyardDS.setPortNumber(Integer.parseInt(PropertiesProvider.PROPS.getProperty("port")));
        scrapyardDS.setUser(PropertiesProvider.PROPS.getProperty("uname"));
        scrapyardDS.setPassword(PropertiesProvider.PROPS.getProperty("pwd"));
    }

    public void insertScrapyard(Scrapyard scrapyard) {
        try (Connection conn = scrapyardDS.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_SCRAPYARD);
            stmt.setInt(1, scrapyard.getScrapyardID());
            stmt.setString(2, scrapyard.getName());
            stmt.setString(3, scrapyard.getAddress());
            stmt.setString(4, scrapyard.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertFossilCar(FossilCar car) {
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_FOSSIL_CAR)) {
            stmt.setInt(1, car.getVehicleID());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYearModel());
            stmt.setString(5, car.getRegistrationNumber());
            stmt.setString(6, car.getChassisNumber());
            stmt.setBoolean(7, car.isDriveable());
            stmt.setInt(8, car.getNumberOfSellableWheels());
            stmt.setInt(9, car.getScrapyardID());
            stmt.setString(10, car.getFuelType());
            stmt.setInt(11, car.getFuelAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertElectricCar(ElectricCar car) {
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_ELECTRIC_CAR)) {
            stmt.setInt(1, car.getVehicleID());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setInt(4, car.getYearModel());
            stmt.setString(5, car.getRegistrationNumber());
            stmt.setString(6, car.getChassisNumber());
            stmt.setBoolean(7, car.isDriveable());
            stmt.setInt(8, car.getNumberOfSellableWheels());
            stmt.setInt(9, car.getScrapyardID());
            stmt.setInt(10, car.getBatteryCapacity());
            stmt.setInt(11, car.getChargeLevel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertMotorcycle(Motorcycle motorcycle) {
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_MOTORCYCLE)) {
            stmt.setInt(1, motorcycle.getVehicleID());
            stmt.setString(2, motorcycle.getBrand());
            stmt.setString(3, motorcycle.getModel());
            stmt.setInt(4, motorcycle.getYearModel());
            stmt.setString(5, motorcycle.getRegistrationNumber());
            stmt.setString(6, motorcycle.getChassisNumber());
            stmt.setBoolean(7, motorcycle.isDriveable());
            stmt.setInt(8, motorcycle.getNumberOfSellableWheels());
            stmt.setInt(9, motorcycle.getScrapyardID());
            stmt.setBoolean(10, motorcycle.getHasSidecar());
            stmt.setInt(11, motorcycle.getEngineCapacity());
            stmt.setBoolean(12, motorcycle.getIsModified());
            stmt.setInt(13, motorcycle.getNumberOfWheels());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertVehicle(Vehicle vehicle) {
        if (vehicle instanceof FossilCar) {
            insertFossilCar((FossilCar) vehicle);
        } else if (vehicle instanceof ElectricCar) {
            insertElectricCar((ElectricCar) vehicle);
        } else if (vehicle instanceof Motorcycle) {
            insertMotorcycle((Motorcycle) vehicle);
        }
    }

    public ArrayList<FossilCar> getAllFossilCarsList() throws SQLException {
        ArrayList<FossilCar> fossilCarList = new ArrayList<>();
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_FOSSIL_CARS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FossilCar car = new FossilCar(
                        rs.getInt("VehicleID"), rs.getString("Brand"), rs.getString("Model"),
                        rs.getInt("YearModel"), rs.getString("RegistrationNumber"), rs.getString("ChassisNumber"),
                        rs.getBoolean("Driveable"), rs.getInt("NumberOfSellableWheels"), rs.getInt("ScrapyardID"),
                        rs.getString("FuelType"), rs.getInt("FuelAmount")
                );
                fossilCarList.add(car);
            }
        }
        return fossilCarList;
    }

    public ArrayList<ElectricCar> getAllElectricCarsList() throws SQLException {
        ArrayList<ElectricCar> electricCarList = new ArrayList<>();
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ELECTRIC_CARS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ElectricCar car = new ElectricCar(
                        rs.getInt("VehicleID"), rs.getString("Brand"), rs.getString("Model"),
                        rs.getInt("YearModel"), rs.getString("RegistrationNumber"), rs.getString("ChassisNumber"),
                        rs.getBoolean("Driveable"), rs.getInt("NumberOfSellableWheels"), rs.getInt("ScrapyardID"),
                        rs.getInt("BatteryCapacity"), rs.getInt("ChargeLevel")
                );
                electricCarList.add(car);
            }
        }
        return electricCarList;
    }

    public ArrayList<Motorcycle> getAllMotorcyclesList() throws SQLException {
        ArrayList<Motorcycle> motorcycleList = new ArrayList<>();
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_MOTORCYCLES);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Motorcycle motorcycle = new Motorcycle(
                        rs.getInt("VehicleID"), rs.getString("Brand"), rs.getString("Model"),
                        rs.getInt("YearModel"), rs.getString("RegistrationNumber"), rs.getString("ChassisNumber"),
                        rs.getBoolean("Driveable"), rs.getInt("NumberOfSellableWheels"), rs.getInt("ScrapyardID"),
                        rs.getBoolean("HasSidecar"), rs.getInt("EngineCapacity"),
                        rs.getBoolean("IsModified"), rs.getInt("NumberOfWheels")
                );
                motorcycleList.add(motorcycle);
            }
        }
        return motorcycleList;
    }

    public ArrayList<Scrapyard> getAllScrapyardsList() throws SQLException {
        ArrayList<Scrapyard> scrapyardList = new ArrayList<>();
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_SCRAPYARDS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Scrapyard scrapyard = new Scrapyard(
                        rs.getInt("ScrapyardID"), rs.getString("Name"),
                        rs.getString("Address"), rs.getString("PhoneNumber")
                );
                scrapyardList.add(scrapyard);
            }
        }
        return scrapyardList;
    }

    public int getTotalFuelAmount() throws SQLException {
        try (Connection conn = scrapyardDS.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_TOTAL_FUEL);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public MysqlDataSource getScrapyardDS() {
        return scrapyardDS;
    }
}