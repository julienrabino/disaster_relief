package edu.ucalgary.oop;

public class ParentChildRelation extends FamilyRelation {
    private static String relationshipTo = "parent-child";

    public ParentChildRelation(DisasterVictim personOne, DisasterVictim personTwo){
        super(personOne, personTwo);
    }

    public ParentChildRelation duplicateRelation(){
        return new ParentChildRelation(this.getPersonOne(), this.getPersonTwo());
    }
    @Override
    public  String getRelationshipTo() {
        return relationshipTo;
    }

}
