package edu.ucalgary.oop;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DisasterVictimSearchTest {
    DisasterVictimEntry DVEntry;
    @Before
    public void setUp(){
        DVEntry = new DisasterVictimEntry();
        try{
            DVEntry.createConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSearchWithPartialNamePra(){
        String results;
        try{
             results = DVEntry.searchDisasterVictimByName("Pra",0);
                String expectedResults = "*************************************************************************************\n"+"id, first name, last name, age, birthdate, comments, location id, gender, entry date \n"+
                        "*************************************************************************************\n" + "2, Praveen, null, null, 1995-12-12, null, 2, non-binary, 2024-03-11\n" +
                        "6, Oprah, null, null, null, null, 2, null, 2024-03-11\n";
                assertEquals("search method should return all disaster victims that have 'PRA' in their name.",results, expectedResults);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    @Test
    public void testSearchWithPartialNameMar(){
        String results;
        try{
            results = DVEntry.searchDisasterVictimByName("Mar",0);
            String expectedResults = "*************************************************************************************\n"+
            "id, first name, last name, age, birthdate, comments, location id, gender, entry date \n"+
                    "*************************************************************************************\n"+
            "3, Margs, null, 71, null, null, 2, null, 2024-03-11\n"+
            "4, Mario, null, null, null, null, 1, null, 2024-03-11\n"+
            "5, Omar, null, null, null, null, 1, null, 2024-04-02\n";
            assertEquals("search method should return all disaster victims that have 'MAR' in their name.",results, expectedResults);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }




}
