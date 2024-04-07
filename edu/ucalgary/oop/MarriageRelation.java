package edu.ucalgary.oop;

public class MarriageRelation extends FamilyRelation{
    private static String relationshipTo = "married";

    public MarriageRelation(DisasterVictim personOne, DisasterVictim personTwo){
        super(personOne, personTwo);
    }
    public MarriageRelation duplicateRelation(){
        return new MarriageRelation(this.getPersonOne(), this.getPersonTwo());
    }
    @Override
    public String getRelationshipTo() {
        return relationshipTo;
    }

}
