package edu.ucalgary.oop;

public class FamilyRelation {
    private DisasterVictim personOne;

    private DisasterVictim personTwo;

    public FamilyRelation(DisasterVictim personOne,DisasterVictim personTwo){
        this.setPersonOne(personOne);
        this.setPersonTwo(personTwo);
    }
    
    public DisasterVictim getPersonOne(){
        return this.personOne;
    }


    public void setPersonOne(DisasterVictim personOne){
        this.personOne = personOne;
    }

    public DisasterVictim getPersonTwo(){
        return this.personTwo;
    }

    public void setPersonTwo(DisasterVictim personTwo){
        this.personTwo = personTwo;
    }
    public FamilyRelation duplicateRelation(){
        return new FamilyRelation(this.getPersonOne(), this.getPersonTwo());
    }
    public String getRelationshipTo() {
        return "family-relation";
    }
}
