package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Objects;

public class Main {
    public static void handleDisasterVictimChoice() throws IOException {
        DisasterVictimEntry DVEntry = new DisasterVictimEntry();
        String locationID = null;
        try {
            DVEntry.createConnection();
            DVEntry.initializeCurrentIDCount();
        } catch (SQLException ex) {
            System.out.println("An error has occurred, please try again");
        }
        boolean exit = false;
        while (!exit) {
            System.out.println("*** DISASTER VICTIM MENU ***");
            System.out.println("1. Add a Disaster Victim");
            System.out.println("2. View all Disaster Victims");
            System.out.println("3. Search for Disaster Victims");
            System.out.println("4. View Disaster Victim Medical Records");
            System.out.println("5. View Disaster Victim Dietary Restrictions");
            System.out.println("6. View Disaster Victim Family Relationships");
            System.out.println("7. Exit to main menu");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String choice = reader.readLine();

            switch (choice) {

                case "1":
                    DisasterVictim disasterVictim;
                    DisasterVictim.readGenderOptions("GenderOptions.txt");
                    System.out.println("*** ADD A DISASTER VICTIM ***");
                    System.out.println("What is the person's first name?");
                    String firstName = reader.readLine();
                    while (true) {
                        System.out.println("Enter the year of their entry date (YYYY).");
                        String entryYear = reader.readLine();

                        System.out.println("Enter the month of their entry date (MM).");
                        String entryMonth = reader.readLine();

                        System.out.println("Enter the day of their entry date (DD).");
                        String entryDay = reader.readLine();

                        try {
                            disasterVictim = new DisasterVictim(firstName, entryYear + "-" + entryMonth + "-" + entryDay);
                            System.out.println("Entering disaster victim with first name " + firstName + " and entry date " + entryYear.trim() + "-" + entryMonth.trim() + "-" + entryDay.trim());
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid date, please try again.");
                        }
                    }

                    while (true) {

                        System.out.println("Would you like to enter the victim's last name? (Y/N)");
                        choice = reader.readLine();
                        if (choice.toLowerCase().equals("y")) {
                            System.out.println("Enter the victim's last name.");
                            String lastName = reader.readLine();
                            disasterVictim.setLastName(lastName);
                            break;
                        } else if (choice.toLowerCase().equals("n")) {
                            disasterVictim.setLastName(null);
                            break;
                        } else {
                            System.out.println("Invalid choice. Try again.");
                        }
                    }
                    while (true) {
                        System.out.println("Would you like to enter the victim's birthday or age?\n1. Birthday\n2. Age\n3. None");
                        choice = reader.readLine();
                        if (Objects.equals(choice, "1")) {
                            while (true) {
                                System.out.println("Enter the year of their birthdate (YYYY).");
                                String birthYear = reader.readLine();

                                System.out.println("Enter the month of their birthdate (MM).");
                                String birthMonth = reader.readLine();

                                System.out.println("Enter the day of their birthdate (DD).");
                                String birthDay = reader.readLine();

                                try {
                                    disasterVictim.setDateOfBirth(birthYear.trim() + "-" + birthMonth.trim() + "-" + birthDay.trim());
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Invalid date, please try again.");
                                }

                            }
                            break;

                        } else if (Objects.equals(choice, "2")) {
                            while (true) {
                                System.out.println("Enter their age.");
                                try {
                                    disasterVictim.setAge(Integer.parseInt(reader.readLine()));
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid age. Try again.");
                                }
                            }
                            break;
                        } else if (Objects.equals(choice, "3")) {
                            break;
                        }


                    }
                    while (true) {
                        System.out.println("Do you have any comments? (Y/N)");
                        choice = reader.readLine();
                        if (choice.toLowerCase().equals("y")) {
                            System.out.println("Enter your comments.");
                            String lastName = reader.readLine();
                            disasterVictim.setComments(lastName);
                            break;
                        } else if (choice.toLowerCase().equals("n")) {
                            disasterVictim.setComments(null);
                            break;
                        } else {
                            System.out.println("Invalid choice. Try again.");
                        }
                    }
                    while (true) {
                        System.out.println("Would you like to add the victim's location? (Y/N)");
                        choice = reader.readLine();
                        if (choice.toLowerCase().equals("y")) {
                            try {
                                System.out.println(DVEntry.selectLocations());
                                while (true) {
                                    System.out.println("Enter the ID of the victim's location.");
                                    locationID = reader.readLine();
                                    if (!DVEntry.getLocationIDs().contains(locationID)) {
                                        System.out.println("Invalid id. Try again.");
                                        locationID = null;
                                    } else {
                                        String locationName = DVEntry.getLocationNameFromID(Integer.parseInt(locationID));
                                        System.out.println("Victim's location set to: " + locationName);
                                        break;
                                    }

                                }
                            } catch (SQLException e) {
                                System.out.println("An error has occurred.");
                                continue;
                            }
                            break;

                        } else if (choice.toLowerCase().equals("n")) {
                            disasterVictim.setLocation(null);
                            break;
                        } else {
                            System.out.println("Invalid choice. Try again.");
                        }

                    }
                    System.out.println("Would you like to enter the victim's gender? (Y/N)");


                    while (true) {
                        choice = reader.readLine();
                        if (choice.toLowerCase().equals("y")) {
                            while (true) {
                                System.out.println("***GENDER OPTIONS***");
                                for (String gender : DisasterVictim.genderOptions) {
                                    System.out.println("- " + gender);
                                }
                                System.out.println("********************");

                                System.out.println("Enter the gender.");
                                String gender = reader.readLine();
                                try {
                                    disasterVictim.setGender(gender.trim());
                                    break;
                                } catch (IllegalArgumentException ex) {
                                    System.out.println("Invalid entry. Try again.");

                                }
                            }
                            break;
                        } else if (choice.toLowerCase().equals("n")) {
                            break;
                        } else {
                            System.out.println("Invalid choice. Try again.");

                        }
                    }
                    System.out.println("Victim will now be entered to the database.");
                    while(true) {
                        try {
                            firstName = disasterVictim.getFirstName();
                            String lastName = disasterVictim.getLastName();
                            int id = disasterVictim.getAssignedSocialID();
                            String age = Integer.toString(disasterVictim.getAge());
                            String birthdate = disasterVictim.getDateOfBirth();
                            String comments = disasterVictim.getComments();
                            String gender = disasterVictim.getGender();
                            String entryDate = disasterVictim.getEntryDate();


                            if (age.equals("-1")) {
                                age = null;
                            }
                            DVEntry.insertDisasterVictim(id, firstName, lastName, age, birthdate, comments, locationID, gender, entryDate);
                            break;
                        } catch (SQLException ex) {
                            System.out.println("Insertion of victim failed. Trying again..");
                        }
                    }
                    pressEnter();
                    break;

                case "2":
                    System.out.println("*** VIEW ALL DISASTER VICTIMS ***");

                    try {
                        String results = DVEntry.selectDisasterVictims();
                        System.out.println(results);

                    } catch (SQLException e) {
                        System.out.println("An error has occurred, please try again");
                    }
                    pressEnter();
                    break;

                case "7":


                    exit = true;
                    break;


            }
        }
    }

    public static void pressEnter() throws IOException {
        System.out.println("<<<PRESS ENTER TO CONTINUE>>>");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Disaster Relief System!");
        Main.pressEnter();

        while (true) {
            System.out.println("*** MENU ***");
            System.out.println("What information would you like to see?");
            System.out.println("1. Disaster Victims");
            System.out.println("2. Inquirer Logs");
            System.out.println("3. Exit");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    handleDisasterVictimChoice();
                    pressEnter();
                    break;
            }
        }
    }
}
