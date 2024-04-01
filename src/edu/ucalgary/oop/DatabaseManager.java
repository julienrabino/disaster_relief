package edu.ucalgary.oop;
import java.sql.*;
public class DatabaseManager {
    private Connection dbConnect;
    private ResultSet results;
//CHANGE TO LOGGING QUERIES CLASS
    public DatabaseManager(){}
    public void createConnection(){

        try{
            dbConnect = DriverManager.getConnection("jdbc:postgresql://localhost/ensf380project", "oop", "ucalgary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String selectInquirers(){

        StringBuffer inquirers = new StringBuffer();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM inquirer");

            while (results.next()){

                inquirers.append("id: " + results.getString("id")  +"    phone number: " + results.getString("phoneNumber") + "    first name: " + results.getString("firstName")+ "    last name: "+
                        results.getString("lastName") );
                inquirers.append('\n');
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inquirers.toString();

    }

    public String selectInquiryLogs(){
        StringBuffer inquiryLogs = new StringBuffer();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM inquiry_log");

            while (results.next()){

                inquiryLogs.append("id: " + results.getString("id") + "   inquirer id: " + results.getString("inquirer") + "     call date: "+
                        results.getString("callDate")  + "    details: "+ results.getString("details"));
                inquiryLogs.append('\n');
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inquiryLogs.toString();


    }

    public void insertNewInquirer(int id, String firstName, String lastName, String phoneNumber){

        try {

            String query = "INSERT INTO inquirer (id, firstName, lastName, phoneNumber) VALUES (?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, id);
            myStmt.setString(2, firstName);
            myStmt.setString(3, lastName);
            myStmt.setString(4, phoneNumber);

            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertNewInquiryLog(int id, int inquirer, String callDate, String details){
        java.sql.Date date = java.sql.Date.valueOf(callDate);

        try {

            String query = "INSERT INTO inquiry_log (id, inquirer, callDate, details) VALUES (?,?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, id);
            myStmt.setInt(2, inquirer);
            myStmt.setDate(3, date);
            myStmt.setString(4, details);

            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        DatabaseManager myJDBC = new DatabaseManager();

        myJDBC.createConnection();
        // myJDBC.insertNewInquirer(7, "Jeb", "Jeb", "403-480-3800");
        //myJDBC.insertNewInquiryLog(10, 7, "2023-05-05", "Looking for mom");
        String inquirers = myJDBC.selectInquirers();
        System.out.println("Here is a list of inquirers: ");
        System.out.println(inquirers);
        String inquiryLogs = myJDBC.selectInquiryLogs();
        System.out.println(inquiryLogs);


        myJDBC.close();
    }

}
