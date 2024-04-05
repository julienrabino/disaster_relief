package edu.ucalgary.oop;

public enum DietaryRestrictions {
    AVML("Asian vegetarian meal"),
    DBML("Diabetic meal"),
    GFML("Gluten intolerant meal"),
    KSML("Kosher meal"),
    LSML("Low salt meal"),
    MOML("Muslim meal"),
    PFML("Peanut-free meal"),
    VGML("Vegan meal"),
    VJML("Vegetarian Jain meal");

    private final String restrictionName;
    DietaryRestrictions(String restrictionName){
        this.restrictionName =restrictionName;
    }
    public String getRestrictionName(){
        return this.restrictionName;
    }

}
