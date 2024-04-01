package edu.ucalgary.oop;

public class Supply {
    // Attributes
    private String type;
    private int quantity;

    // Methods

    // Constructor
    public Supply(String type, int quantity){

        if (quantity <0){
            throw new IllegalArgumentException("Quantity must be non-negative.");
        }

        this.type = type;
        this.quantity = quantity;
    }

    // Setters
    public void setType(String type){this.type = type;}
    public void setQuantity(int quantity){
        if (quantity <0){
            throw new IllegalArgumentException("Quantity must be non-negative.");
        }
        this.quantity = quantity;}

    // Getters
    public String getType(){return this.type;}
    public int getQuantity(){return this.quantity;}
}
