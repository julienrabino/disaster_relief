package edu.ucalgary.oop;

public class SiblingRelation extends FamilyRelation {

    private static String relationshipTo = "sibling";

    public SiblingRelation(DisasterVictim personOne, DisasterVictim personTwo){
        super(personOne, personTwo);
    }

    public SiblingRelation duplicateRelation(){
        return new SiblingRelation(this.getPersonOne(), this.getPersonTwo());
    }

    @Override
    public String getRelationshipTo() {
        return relationshipTo;
    }

}
