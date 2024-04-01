package edu.ucalgary.oop;
import java.util.ArrayList;
import java.util.List;

public class Location {
    // Attributes
    private String name;
    private String address;
    private ArrayList<DisasterVictim> occupants;
    private ArrayList<Supply> supplies;

    // Methods

    // Constructor
    public Location(String name, String address){
        this.setName(name);
        this.setAddress(address);
        this.occupants = new ArrayList<DisasterVictim>();
        this.supplies = new ArrayList<Supply>();
    }

    public void addOccupant(DisasterVictim occupant, boolean calledFromDisasterVictimClass){
        this.occupants.add(occupant);
        if (!calledFromDisasterVictimClass){
        occupant.setLocation(this);}
    }

    public void removeOccupant(DisasterVictim occupant){
        int socialID = occupant.getAssignedSocialID();
        for (DisasterVictim person: this.occupants){
            if (person.getAssignedSocialID() == socialID){
                this.occupants.remove(person);
                break;
            }
        }
        occupant.setLocation(null);
    }

    public void addSupply(Supply supply){
        int quantity = supply.getQuantity();
        String type = supply.getType();
        for (Supply existingSupply: this.supplies){
            if (existingSupply.getType().equals(type)) {
                int newQuantity = existingSupply.getQuantity() + quantity;
                existingSupply.setQuantity(newQuantity);
                return;
            }

        }
        this.supplies.add(new Supply(supply.getType(), supply.getQuantity()));
    }

    public void removeSupply(Supply supply) {
        int quantity = supply.getQuantity();
        String type = supply.getType();
        for (Supply existingSupply : this.supplies) {
            if (existingSupply.getType().equals(type)) {
                int newQuantity = existingSupply.getQuantity() - quantity;
                if (newQuantity == 0) {
                    this.supplies.remove(existingSupply);
                }
                else{
                        existingSupply.setQuantity(newQuantity);
                    }
                return;
            }

        }
    }
    // Setters
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public void setOccupants(ArrayList<DisasterVictim> occupants){
        List<DisasterVictim> occupantsToRemove = new ArrayList<>(this.occupants);

        for (DisasterVictim person : occupantsToRemove) {
            this.removeOccupant(person);
        }

        for (DisasterVictim person : occupants) {
            this.addOccupant(person,false);
        }
    }
    public void setSupplies(ArrayList<Supply> supplies){this.supplies = supplies;}

    // Getters
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public ArrayList<DisasterVictim> getOccupants(){return this.occupants;}
    public ArrayList<Supply> getSupplies(){return this.supplies;}

    public void allocateSupplyToVictim(DisasterVictim person, Supply supply){
        this.removeSupply(supply);
        person.addPersonalBelonging(supply, true);


    }

}
