package hello;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:baza.db";

    private Connection conn;
    private Statement stat;

    Database(){
        try {
            Class.forName(Database.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        createTable();
    }

    public boolean createTable(){
        String createComputers = "CREATE TABLE IF NOT EXISTS computers (id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), ip varchar(15), mac varchar(17))";
        try {
            stat.execute(createComputers);
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean set(String name, String ip, String mac){
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into computers values (NULL, ?, ?, ?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, ip);
            prepStmt.setString(3, mac);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy zapisywaniu danych");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List get() {
        List<Computer> computers = new ArrayList<Computer>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM computers");
            while(result.next()) {
                Computer pc = new Computer(
                        result.getString("name"),
                        result.getString("ip"),
                        result.getString("mac"));
                computers.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return computers;
    }

    public boolean clean(){
        try {
            stat.execute("DELETE FROM computers");
            stat.execute("DELETE FROM sqlite_sequence WHERE name='computers'");
        } catch (SQLException e) {
            System.err.println("Błąd przy czyszczeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}