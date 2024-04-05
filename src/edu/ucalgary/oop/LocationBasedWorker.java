package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;

public class LocationBasedWorker {
    public static void pressEnter() throws IOException {
        System.out.println("<<<PRESS ENTER TO CONTINUE>>>");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

    }

    public static void main(String[] args) throws IOException {
        DisasterVictimEntry DVEntry = new DisasterVictimEntry();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String locationID = null;
        try {
            DVEntry.createConnection();
            DVEntry.initializeCurrentIDCount();
        } catch (
                SQLException ex) {
            System.out.println("An error has occurred while trying to create a connection to the database. Try again.");
            return;
        }
        try {
            DVEntry.setCurrentDisasterVictims();
        } catch (SQLException e) {
            System.out.println("An error has occurred. Try again.");
            return;
        }

        try {
            System.out.println(DVEntry.selectLocations());
            while (true) {
                System.out.println("Enter the ID of your Location.");
                locationID = reader.readLine();
                if (!DVEntry.getLocationIDs().contains(locationID)) {
                    System.out.println("Invalid ID. Try again.");
                    locationID = null;
                } else {
                    String locationName = DVEntry.getLocationNameFromID(Integer.parseInt(locationID));
                    System.out.println("Your location set to: " + locationName);
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println("An error has occurred.");
            return;
        }


        boolean exit = false;
        while (!exit) {
            String choice;
            System.out.println("*** LOCATION BASED RELIEF WORKER ***");
            System.out.println("1. Add a Disaster Victim");
            System.out.println("2. View all Disaster Victims");
            System.out.println("3. Search for Disaster Victims");
            System.out.println("4. Add Disaster Victim Medical Records");
            System.out.println("5. Search for Disaster Victim Medical Records");
            System.out.println("6. Search for Disaster Victims with Dietary Restrictions");
            System.out.println("7. View All Disaster Victim Dietary Restrictions");
            System.out.println("8. Add Disaster Victim Family Relations");
            System.out.println("9. View Disaster Victim Family Relationships");
            System.out.println("10. Exit");

            choice = reader.readLine();

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
                            disasterVictim = new DisasterVictim(firstName, entryYear.trim() + "-" + entryMonth.trim() + "-" + entryDay.trim());
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
                        System.out.println("Would you like to enter the victim's birthday or age?\n1. Birthday\n2. Age\n3. None\n(CHOOSE 1, 2, OR 3)");
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
                    while (true) {
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
                    System.out.println("Would you like to enter the victim's dietary restrictions? (Y/N)");
                    boolean exitDietaryRestrictions = false;
                    while (!exitDietaryRestrictions) {
                        choice = reader.readLine();
                        HashSet<DietaryRestrictions> restrictions = new HashSet<>();

                        if (choice.toLowerCase().equals("y")) {
                            System.out.println("*** DIETARY RESTRICTIONS ***");
                            int count = 1;
                            for (DietaryRestrictions restriction : DietaryRestrictions.values()) {
                                System.out.println(Integer.toString(count) + ". " + restriction.getRestrictionName());
                                count++;
                            }
                            System.out.println("*************************");
                            boolean exitDietaryRestrictionsEntry = false;
                            while (!exitDietaryRestrictionsEntry) {

                                System.out.println("Enter the number of the dietary restriction you would like to add.");
                                choice = reader.readLine();
                                if (choice.equals("1")) {
                                    restrictions.add(DietaryRestrictions.AVML);
                                } else if (choice.equals("2")) {
                                    restrictions.add(DietaryRestrictions.DBML);
                                } else if (choice.equals("3")) {
                                    restrictions.add(DietaryRestrictions.GFML);
                                } else if (choice.equals("4")) {
                                    restrictions.add(DietaryRestrictions.KSML);
                                } else if (choice.equals("5")) {
                                    restrictions.add(DietaryRestrictions.LSML);
                                } else if (choice.equals("6")) {
                                    restrictions.add(DietaryRestrictions.MOML);
                                } else if (choice.equals("7")) {
                                    restrictions.add(DietaryRestrictions.PFML);
                                } else if (choice.equals("8")) {
                                    restrictions.add(DietaryRestrictions.VGML);
                                } else if (choice.equals("9")) {
                                    restrictions.add(DietaryRestrictions.VJML);
                                } else {
                                    System.out.println("Invalid dietary restriction. Enter a valid one please.");
                                    continue;
                                }
                                System.out.println("Would you like to enter more dietary restrictions? (Y/N)");

                                while (true) {
                                    String userChoice = reader.readLine();

                                    if (userChoice.toLowerCase().equals("n")) {
                                        disasterVictim.setDietaryRestrictions(restrictions);

                                        exitDietaryRestrictions = true;
                                        exitDietaryRestrictionsEntry = true;

                                        break;
                                    } else if (userChoice.toLowerCase().equals("y")) {
                                        break;
                                    } else {
                                        System.out.println("Invalid choice. Try again.");

                                    }
                                }
                            }

                        } else if (choice.toLowerCase().equals("n")) {
                            disasterVictim.setDietaryRestrictions(restrictions);
                            break;
                        } else {
                            System.out.println("Invalid choice. Try again.");

                        }
                    }

                    HashSet<DietaryRestrictions> victimDietaryRestrictions = disasterVictim.getDietaryRestrictions();
                    if (!victimDietaryRestrictions.isEmpty()) {
                        for (DietaryRestrictions restriction : victimDietaryRestrictions) {
                            try {
                                int restrictionID = 0;
                                if (restriction.equals(DietaryRestrictions.AVML)) {
                                    restrictionID = 1;
                                } else if (restriction.equals(DietaryRestrictions.DBML)) {
                                    restrictionID = 2;
                                } else if (restriction.equals(DietaryRestrictions.GFML)) {
                                    restrictionID = 3;
                                } else if (restriction.equals(DietaryRestrictions.KSML)) {
                                    restrictionID = 4;
                                } else if (restriction.equals(DietaryRestrictions.LSML)) {
                                    restrictionID = 5;
                                } else if (restriction.equals(DietaryRestrictions.MOML)) {
                                    restrictionID = 6;
                                } else if (restriction.equals(DietaryRestrictions.PFML)) {
                                    restrictionID = 7;
                                } else if (restriction.equals(DietaryRestrictions.VGML)) {
                                    restrictionID = 8;
                                } else if (restriction.equals(DietaryRestrictions.VJML)) {
                                    restrictionID = 9;
                                }
                                DVEntry.addDisasterVictimDietaryRestriction(disasterVictim.getAssignedSocialID(), restrictionID);
                            } catch (SQLException e) {
                                System.out.println("An error has occurred.");
                                break;
                            }
                        }
                    }
                    pressEnter();
                    break;


                case "2":
                    System.out.println("*** VIEW ALL DISASTER VICTIMS ***");

                    try {
                        System.out.println("Here are all the Disaster Victims of your Location:");

                        String results = DVEntry.selectDisasterVictims(Integer.parseInt(locationID));
                        System.out.println(results);

                    } catch (SQLException e) {
                        System.out.println("An error has occurred, please try again");
                        pressEnter();
                        break;
                    }

                    try{
                        System.out.println("Here are all the Disaster Victims in the entire system:");
                        String results = DVEntry.selectDisasterVictims(0);
                        System.out.println(results);
                    }catch(SQLException e){
                        System.out.println("An error has occurred, please try again");
                        pressEnter();
                        break;
                    }

                    pressEnter();
                    break;

                case "3":
                    System.out.println("*** SEARCH FOR DISASTER VICTIMS ***");
                    System.out.println("Enter your search (based on first name).");
                    String name = reader.readLine();
                    try {
                        String results = DVEntry.searchDisasterVictimByName(name, Integer.parseInt(locationID));
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred, try again.");
                        break;
                    }
                    pressEnter();
                    break;
                case "4":
                    System.out.println("*** ADD MEDICAL RECORDS ***");

                    String medicalDetails;
                    MedicalRecord medicalRecord;
                    String victimID;
                    while (true) {
                        System.out.println("Enter the ID of the victim you want to add a medical record for.");
                        victimID = reader.readLine();
                        try {
                            if (!DVEntry.getVictimIDs(Integer.parseInt(locationID)).contains(victimID)) {
                                System.out.println("Invalid id. Try again.");
                            } else {
                                break;
                            }
                        } catch (SQLException e) {
                            System.out.println("An error has occurred.");
                            pressEnter();
                            break;
                        }
                    }
                    System.out.println("What are the details of the medical treatment?");
                    medicalDetails = reader.readLine();

                    while (true) {
                        System.out.println("Enter the year of their treatment (YYYY).");
                        String treatmentYear = reader.readLine();

                        System.out.println("Enter the month of their treatment (MM).");
                        String treatmentMonth = reader.readLine();

                        System.out.println("Enter the day of their treatment (DD).");
                        String treatmentDay = reader.readLine();
                        String date = treatmentYear.trim() + "-" + treatmentMonth.trim() + "-" + treatmentDay.trim();
                        try {
                            medicalRecord = new MedicalRecord(medicalDetails, date);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid date, please try again.");
                        }

                    }
                    try {
                        DVEntry.addDisasterVictimMedicalRecord(Integer.parseInt(victimID), medicalRecord.getTreatmentDetails(), medicalRecord.getDateOfTreatment(), Integer.parseInt(locationID));
                    } catch (SQLException e) {
                        System.out.println("Medical record failed to be added to the database. Try again.");
                    }
                    pressEnter();
                    break;

                case "5":
                    System.out.println("*** SEARCH FOR DISASTER VICTIM MEDICAL RECORDS ***");
                    System.out.println("Enter the ID of the victim.");
                    while (true) {
                        victimID = reader.readLine();
                        try {
                            if (!DVEntry.getVictimIDs(Integer.parseInt(locationID)).contains(victimID)) {
                                System.out.println("Invalid ID. Try entering a victim that belongs to your location.");
                            } else {
                                break;
                            }
                        } catch (SQLException e) {
                            System.out.println("An error has occurred.");
                            pressEnter();
                            break;
                        }
                    }
                    try {
                        String results = DVEntry.searchMedicalRecordsByVictimID(Integer.parseInt(victimID));
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred. Try again.");
                        pressEnter();
                        break;
                    }

                    pressEnter();
                    break;

                case "6":
                    System.out.println("*** SEARCH FOR VICTIMS WITH DIETARY RESTRICTIONS ***");
                    int count = 1;
                    for (DietaryRestrictions restriction : DietaryRestrictions.values()) {
                        System.out.println(Integer.toString(count) + ". " + restriction.getRestrictionName());
                        count++;
                    }
                    System.out.println("*************************");
                    boolean exitDietaryRestrictionsEntry = false;
                    while (true) {

                        System.out.println("Enter a dietary restriction.");
                        choice = reader.readLine();
                        if (Integer.parseInt(choice) >= 1 || Integer.parseInt(choice) <= 9) {
                            try {
                                String results = DVEntry.selectDisasterVictimByDietaryRestriction(Integer.parseInt(choice), Integer.parseInt(locationID));
                                System.out.println(results);
                                break;
                            } catch (SQLException e) {
                                System.out.println("An error has occurred, try again.");
                                pressEnter();
                                break;
                            }
                        } else {
                            System.out.println("Invalid dietary restriction. Enter a valid number from 1-9 please.");
                            continue;
                        }
                    }

                    pressEnter();
                    break;
                case "7":
                    System.out.println("*** VIEW DISASTER VICTIM DIETARY RESTRICTIONS ***");
                    try {
                        String results = DVEntry.selectVictimDietaryRestrictions(Integer.parseInt(locationID));
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred. Try again.");
                        pressEnter();
                        break;
                    }

                    pressEnter();
                    break;
                case "8":
                    System.out.println("*** ADD FAMILY RELATIONSHIPS ***");
                    System.out.println("Enter the ID of one of the disaster victims part of the relationship.");
                    String victimID1;
                    while (true) {
                        victimID1 = reader.readLine();
                        try {
                            if (!DVEntry.getVictimIDs(0).contains(victimID1)) {
                                System.out.println("Invalid id. Try again.");
                                continue;
                            }
                            break;
                        } catch (SQLException e) {
                            System.out.println("An error has occurred.");
                            pressEnter();
                            break;
                        }
                    }
                    System.out.println("Enter the ID of the other the disaster victim part of the relationship.");
                    String victimID2;
                    while (true) {
                        victimID2 = reader.readLine();
                        try {
                            if (!DVEntry.getVictimIDs(0).contains(victimID2) || victimID2.equals(victimID1)) {
                                System.out.println("Invalid id. Try again.");
                                continue;
                            }
                            break;
                        } catch (SQLException e) {
                            System.out.println("An error has occurred.");
                            pressEnter();
                            break;
                        }
                    }
                    System.out.println("What is the nature of their relationship?\n1. Siblings\n2. Married\n3. Parent-Child\n4. Other\n(ENTER THE NUMBER CORRESPONDING TO THE RELATIONSHIP)");
                    String relationshipType = reader.readLine();

                    if (Integer.parseInt(relationshipType) >= 1 || Integer.parseInt(relationshipType) <= 4) {
                        try {
                            if (relationshipType.equals("1")) {
                                relationshipType = "siblings";
                            } else if (relationshipType.equals("2")) {
                                relationshipType = "married";
                            } else if (relationshipType.equals("3")) {
                                relationshipType = "parent-child";
                            } else if (relationshipType.equals("4")) {
                                relationshipType = "other";
                            }
                            DVEntry.addFamilyRelation(Integer.parseInt(victimID1), Integer.parseInt(victimID2), relationshipType);
                            DVEntry.setCurrentDisasterVictims();
                        } catch (SQLException ex) {
                            System.out.println("An error has occurred. Relationship already exists.");
                        }
                    }

                    pressEnter();
                    break;
                case "9":
                    System.out.println("*** SEARCH DISASTER VICTIM FAMILY RELATIONSHIPS ***");
                    System.out.println("Enter the ID of the disaster victim.");
                    String id = reader.readLine();
                    try {
                        if (!DVEntry.getVictimIDs(0).contains(id)) {
                            System.out.println("Invalid id. Try again.");
                        }
                    } catch (SQLException e) {
                        System.out.println("An error has occurred.");
                        pressEnter();
                        break;
                    }
                    try {
                        String results = DVEntry.selectFamilyRelationsByID(Integer.parseInt(id));
                        System.out.println(results);
                    } catch (SQLException e) {
                        System.out.println("An error has occurred.");
                        pressEnter();
                        break;
                    }
                    pressEnter();
                    break;
                case "10":
                    exit = true;
                    pressEnter();
                    break;
            }
        }
    }

}
