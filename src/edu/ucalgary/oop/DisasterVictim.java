package edu.ucalgary.oop;

import java.util.LinkedHashSet;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;


public class DisasterVictim implements Person{
	
	//Attributes
    private String firstName;
	private String lastName;
	private String dateOfBirth;
	private int age;
	private String comments;
	private final int ASSIGNED_SOCIAL_ID;
	private ArrayList<MedicalRecord> medicalRecords;
	private ArrayList<FamilyRelation> familyConnections;
	private final String ENTRY_DATE;
	private ArrayList<Supply> personalBelongings;
	private String gender;
	private static int counter = DisasterVictimEntry.currentIDCount;
	private boolean ageKnown;
	private boolean birthdateKnown;
	private HashSet<DietaryRestrictions> dietaryRestrictions;
	private Location location;
	public static HashSet<String> genderOptions;






	//Methods
	
	//Constructors
	
	public DisasterVictim(String firstName, String ENTRY_DATE) throws IllegalArgumentException{
		String REGEX = "\\d{4}-\\d{2}-\\d{2}";
		Pattern PATTERN = Pattern.compile(REGEX);
		Matcher match = PATTERN.matcher(ENTRY_DATE);
		boolean valid_date = match.find();

		String[] tokens = ENTRY_DATE.split("-");
		if(tokens[0].length() != 4){
			valid_date = false;
		}else if(tokens[1].length() != 2 || tokens[2].length() !=2){
			valid_date=false;
		}
		if (!valid_date){
			throw new IllegalArgumentException("Invalid format for date");
		}

		
		this.firstName = firstName;
		this.ENTRY_DATE = ENTRY_DATE;
		this.lastName = null;
		this.dateOfBirth = null;
		this.ageKnown = false;
		this.birthdateKnown = false;
		this.age = 0;
		this.comments = null;
		counter++;
		this.ASSIGNED_SOCIAL_ID = counter;
		this.medicalRecords = new ArrayList<>();
		this.familyConnections = new ArrayList<>();
		this.personalBelongings = new ArrayList<>();
		this.dietaryRestrictions = new HashSet<>();
		this.location = null;
		this.gender = null;
	}

	public DisasterVictim(int id, String firstName, String ENTRY_DATE) throws IllegalArgumentException{
		String REGEX = "\\d{4}-\\d{2}-\\d{2}";
		Pattern PATTERN = Pattern.compile(REGEX);
		Matcher match = PATTERN.matcher(ENTRY_DATE);
		boolean valid_date = match.find();

		String[] tokens = ENTRY_DATE.split("-");
		if(tokens[0].length() != 4){
			valid_date = false;
		}else if(tokens[1].length() != 2 || tokens[2].length() !=2){
			valid_date=false;
		}
		if (!valid_date){
			throw new IllegalArgumentException("Invalid format for date");
		}


		this.firstName = firstName;
		this.ENTRY_DATE = ENTRY_DATE;
		this.lastName = null;
		this.dateOfBirth = null;
		this.ageKnown = false;
		this.birthdateKnown = false;
		this.age = 0;
		this.comments = null;
		this.ASSIGNED_SOCIAL_ID = id;
		this.medicalRecords = new ArrayList<>();
		this.familyConnections = new ArrayList<>();
		this.personalBelongings = new ArrayList<>();
		this.dietaryRestrictions = new HashSet<>();
		this.location = null;
		this.gender = null;
	}
	//Getters
	

	@Override
	public String getFirstName(){ return this.firstName; }
	@Override
	public String getLastName() { return this.lastName; }
	public String getDateOfBirth() {
		if (this.birthdateKnown){
			return this.dateOfBirth;
		}
		else{ return null;}
	}
	public String getComments() { return this.comments; }
	public ArrayList<MedicalRecord> getMedicalRecords() { return this.medicalRecords; }
	public String getEntryDate() { return this.ENTRY_DATE; }
	public int getAssignedSocialID() { return this.ASSIGNED_SOCIAL_ID; }
	public ArrayList<Supply> getPersonalBelongings() { return this.personalBelongings; }
	public ArrayList<FamilyRelation> getFamilyConnections() {return this.familyConnections; }
	public String getGender() { return this.gender; }
	
	//Setters
	@Override
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}


	@Override
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public void setDateOfBirth(String dateOfBirth) throws IllegalArgumentException{
		String REGEX = "\\d{4}-\\d{2}-\\d{2}";
		Pattern PATTERN = Pattern.compile(REGEX);
		Matcher match = PATTERN.matcher(dateOfBirth);
		boolean valid_date = match.find();
		String[] tokens = dateOfBirth.split("-");
		if(tokens[0].length() != 4){
			valid_date = false;
		}else if(tokens[1].length() != 2 || tokens[2].length() !=2){
			valid_date=false;
		}
		if (!valid_date){
			throw new IllegalArgumentException("Invalid format for date");
		}
		this.birthdateKnown = true;
		this.ageKnown = false;
		this.dateOfBirth = dateOfBirth;
		
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	
	public void setMedicalRecords(ArrayList<MedicalRecord> medicalRecords){
		this.medicalRecords = medicalRecords;
	}
	
	public void setPersonalBelongings(ArrayList<Supply> supplies){
		for (Supply supply: supplies){
			this.location.removeSupply(supply);
		}
		this.personalBelongings = supplies;
	}

	public void setFamilyConnections(ArrayList<FamilyRelation> relations) {
		List<FamilyRelation> connectionsToRemove = new ArrayList<>(this.familyConnections);

		for (FamilyRelation relation : connectionsToRemove) {
			this.removeFamilyConnection(relation);
		}

		for (FamilyRelation relation : relations) {
			this.addFamilyConnection(relation);
		}
	}




	public void setGender(String gender){
		for (String genderOption: genderOptions){
			if (gender.equalsIgnoreCase(genderOption)){
				this.gender = gender;
				return;
			}

		}
		throw new IllegalArgumentException("Gender not found in gender options");
	}
	
	public void addPersonalBelonging(Supply supply, boolean calledFromLocationClass ){
		boolean personalBelongingExists = false;
		for (Supply personalBelonging: this.personalBelongings){
			if (personalBelonging.getType().equals(supply.getType())){
				personalBelonging.setQuantity(supply.getQuantity() + personalBelonging.getQuantity());
				personalBelongingExists = true;
				break;
			}
		}

		if (!personalBelongingExists){
			Supply supplyToAdd = new Supply(supply.getType(), supply.getQuantity());
			this.personalBelongings.add(supplyToAdd);
		}

		if (!calledFromLocationClass){
			Location victimLocation = this.getLocation();
			victimLocation.removeSupply(supply);
		}
	}
	
	public void removePersonalBelonging(Supply supply) {
		for (Supply personalBelonging : this.personalBelongings) {
			if (personalBelonging.getType().equals(supply.getType())) {
				int currentQuantity = personalBelonging.getQuantity();
				int newQuantity = currentQuantity - supply.getQuantity();
				if (newQuantity == 0) {
					this.personalBelongings.remove(personalBelonging);
				} else {
					personalBelonging.setQuantity(newQuantity);
				}
				return;
			}
		}
	}



	
	public void addFamilyConnection(FamilyRelation familyConnection){
		for (FamilyRelation relation: this.familyConnections) {
			if (relation.getPersonTwo() == familyConnection.getPersonTwo()){
				// indicates that the relationship between the two victims already exist
				return;
			}

		}
		this.familyConnections.add(familyConnection);
		DisasterVictim personTwo = familyConnection.getPersonTwo();
		FamilyRelation duplicateRelation = familyConnection.duplicateRelation();


		duplicateRelation.setPersonOne(familyConnection.getPersonTwo());
		duplicateRelation.setPersonTwo(familyConnection.getPersonOne());
		personTwo.addFamilyConnection(duplicateRelation);
		ensureRelationshipWholeness(familyConnection);
	}
	
	public void removeFamilyConnection(FamilyRelation familyConnection){
		int personTwoSocialID = familyConnection.getPersonTwo().getAssignedSocialID();
		int personOneSocialID = familyConnection.getPersonOne().getAssignedSocialID();
		for (FamilyRelation relation: this.familyConnections){
			if (relation.getPersonTwo().getAssignedSocialID() == personTwoSocialID){
				this.familyConnections.remove(relation);
				break;
			}
		}

		DisasterVictim personTwo = familyConnection.getPersonTwo();
		for(FamilyRelation relation: personTwo.familyConnections){
			if (relation.getPersonTwo().getAssignedSocialID() == personOneSocialID){
				personTwo.familyConnections.remove(relation);
				break;
			}
		}
		
	}
	
	public void addMedicalRecord(MedicalRecord medicalRecord){
		this.medicalRecords.add(medicalRecord);
	}

	public void setAge(int age){
		if (age < 0){
			throw new IllegalArgumentException("Age cannot be negative");
		}
		this.age = age;
		this.birthdateKnown = false;
		this.ageKnown = true;
	}

	public int getAge() {
		if (ageKnown) {
			return this.age;
		}
		return -1;
	}

	private void ensureRelationshipWholeness(FamilyRelation newFamilyConnection){
		DisasterVictim personOne = newFamilyConnection.getPersonOne();
		DisasterVictim personTwo = newFamilyConnection.getPersonTwo();
		FamilyRelation duplicateRelation;
		String relationshipType = newFamilyConnection.getRelationshipTo();

		// ensure relationship wholeness for personTwo
		for (FamilyRelation relation: personOne.familyConnections){
			if (relation.getPersonTwo() != personTwo){
				createFamilyRelation(personTwo, relation, relationshipType);
			}
		}

		// ensure relationship wholeness for personOne
		for (FamilyRelation relation: personTwo.familyConnections){
			if (relation.getPersonTwo() != personOne){
				createFamilyRelation(personOne, relation, relationshipType);
			}
		}

	}
	private void createFamilyRelation(DisasterVictim person, FamilyRelation relation, String relationshipType){
		if (relation.getRelationshipTo().equals("siblings")){
			if (relationshipType.equals("siblings")){
				SiblingRelation siblingRelation = new SiblingRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(siblingRelation);
			}
			else if(relationshipType.equals("parent-child")){
				ParentChildRelation parentChildRelation = new ParentChildRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(parentChildRelation);

			}
			else {
				FamilyRelation familyRelation = new FamilyRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(familyRelation);

			}

		}
		else if(relation.getRelationshipTo().equals("parent-child")){

			if(relationshipType.equals("parent-child")) {
				MarriageRelation marriageRelation = new MarriageRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(marriageRelation);
			}
			else if (relationshipType.equals("married") || relationshipType.equals("siblings")){
				ParentChildRelation parentChildRelation = new ParentChildRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(parentChildRelation);
			}
			else{
				FamilyRelation familyRelation = new FamilyRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(familyRelation);

			}

		}
		else if(relation.getRelationshipTo().equals("married")){

			if(relationshipType.equals("parent-child")) {
				ParentChildRelation parentChildRelation = new ParentChildRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(parentChildRelation);
			}

			else if (relationshipType.equals("other") || relationshipType.equals("sibling")){
				FamilyRelation familyRelation = new FamilyRelation(person, relation.getPersonTwo());
				person.addFamilyConnection(familyRelation);

			}


		}
		else if(relation.getRelationshipTo().equals("other")){
			FamilyRelation familyRelation = new FamilyRelation(person, relation.getPersonTwo());
			person.addFamilyConnection(familyRelation);

		}
	}


	public void setDietaryRestrictions(HashSet<DietaryRestrictions> dietaryRestrictions){
		this.dietaryRestrictions = dietaryRestrictions;
	}

	public HashSet<DietaryRestrictions> getDietaryRestrictions(){
		return this.dietaryRestrictions;
	}

	public void setLocation(Location location){
		if (location == null){
			this.location = null;
		}
		else {
			this.location = location;
			location.addOccupant(this, true);
		}
	}

	public Location getLocation(){
		return this.location;
	}

	public static void readGenderOptions(String file){
		genderOptions = new HashSet<>();
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader(file));
			String line = inputStream.readLine();

			while (line != null){
				genderOptions.add(line.trim());
				line = inputStream.readLine();
			}
			inputStream.close();
		} catch(IOException e) {
			System.err.println("Could not open file");
			System.exit(1);
		}


	}
}


