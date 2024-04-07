package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CentralBasedWorker {
    public static void pressEnter() throws IOException {
        System.out.println("<<<PRESS ENTER TO CONTINUE>>>");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        QueryLogger inquirerQueries = new QueryLogger();
        DisasterVictimEntry DVEntry = new DisasterVictimEntry();

        try {
            inquirerQueries.createConnection();
            DVEntry.createConnection();
        } catch (SQLException e) {
            System.out.println("An error has occurred while trying to create a connection to the database. Try again.");
            return;
        }
        try {
            inquirerQueries.initializeCurrentInquirerIDCount();
            inquirerQueries.initializeCurrentLogIDCount();
        } catch (SQLException e) {
            System.out.println("An error has occurred while trying to connect to the database.");
            return;

        }
        boolean exit = false;
        while (!exit) {
            String choice;
            System.out.println("*** CENTRAL BASED RELIEF WORKER ***");
            System.out.println("1. View all Logs");
            System.out.println("2. View Inquirers");
            System.out.println("3. Add Log");
            System.out.println("4. Add Inquirer");
            System.out.println("5. Search for Disaster Victims");
            System.out.println("6. View all Disaster Victims");
            System.out.println("7. Exit");


            choice = reader.readLine();

            switch (choice) {
                case "1":
                    System.out.println("*** VIEW ALL INQUIRY LOGS ***");
                    try {
                        String results = inquirerQueries.selectInquiryLogs();
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred. Try again.");
                    }
                    pressEnter();
                    break;
                case "2":
                    System.out.println("*** VIEW ALL INQUIRERS ***");
                    try {
                        String results = inquirerQueries.selectInquirers();
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred. Try again.");
                    }
                    pressEnter();
                    break;
                case "3":
                    System.out.println("*** ADD LOG ***");
                    System.out.println("Enter the inquirer's ID.");
                    String inquirerID;
                    Log newLog;
                    while (true) {
                        inquirerID = reader.readLine();
                        try {
                            if (inquirerQueries.getInquirerIDs().contains(inquirerID.trim())) {
                                System.out.println("What are the details of the log?");
                                String details = reader.readLine();
                                while (true) {
                                    System.out.println("Enter the year of their call date (YYYY).");
                                    String entryYear = reader.readLine();

                                    System.out.println("Enter the month of their call date (MM).");
                                    String entryMonth = reader.readLine();

                                    System.out.println("Enter the day of their call date (DD).");
                                    String entryDay = reader.readLine();

                                    try {
                                        newLog = new Log(entryYear + "-" + entryMonth + "-" + entryDay, details);
                                        break;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Invalid date, please try again.");
                                    }
                                }
                                break;
                            } else {
                                System.out.println("The ID you have entered is invalid and does not exist. Try again.");
                            }
                        } catch (SQLException e) {
                            System.out.println("An error has occurred.");
                        }
                    }
                    String detailsToInsert = newLog.getLogDetails();
                    String date = newLog.getDate();
                    try {
                        inquirerQueries.insertNewInquiryLog(Integer.parseInt(inquirerID), newLog);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred during log insertion. Try again.");
                    }


                    pressEnter();
                    break;

                case "4":
                    System.out.println("*** ADD NEW INQUIRER ***");
                    Inquirer inquirer;
                    System.out.println("What is the inquirer's first name?");
                    String firstName = reader.readLine();
                    System.out.println("What is the inquirer's last name?");
                    String lastName = reader.readLine();
                    System.out.println("What is the inquirer's phone number? (XXX-XXX-XXXX)");
                    String phoneNumber = reader.readLine();
                    Inquirer newInquirer = new Inquirer(firstName,lastName,phoneNumber);
                    try{
                        inquirerQueries.insertNewInquirer(newInquirer);
                        System.out.println("Inquirer now added to the database (ID: "+ newInquirer.getID() +")");
                    } catch (SQLException e){
                        System.out.println("An error has occurred during insertion. Try again.");
                    }
                    pressEnter();
                    break;




                case "5":
                    System.out.println("*** SEARCH FOR DISASTER VICTIMS ***");
                    System.out.println("Enter your search (based on first name).");
                    String name = reader.readLine();
                    try {
                        String results = DVEntry.searchDisasterVictimByName(name, 0);
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred, try again.");
                    }
                    pressEnter();
                    break;
                case "6":
                    System.out.println("*** VIEW ALL DISASTER VICTIMS ***");
                    try {
                        String results = DVEntry.selectDisasterVictims(0);
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred, try again.");
                    }
                    pressEnter();
                    break;
                case "7":
                    exit = true;
                    pressEnter();
                    inquirerQueries.close();
                    DVEntry.close();
                    break;
            }


        }
    }
}
