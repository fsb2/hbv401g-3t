package controllers;

import entities.UserEntity;
import entities.ProfileEntity;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * This is the controller class for the user class.
 * @author hjortur
 */

public class UserController {
    UserEntity user1 = new UserEntity("Jon", "Jonsson", "jon1","1234", "01.01.1970", "Reykjavik", 101, "555123456", "jon@heima.is");
    
    UserEntity user2 = new UserEntity("Helga", "Helgadottir", "helga22", "1234", "01.01.1970", "Reykjavik", 101, "555123456", "helga@heima.is");

    
    UserEntity user3=new UserEntity("Jim","Smith","js1980","1234","01.01.1970","New York",1001,"23442321","jim.smith@google.com");

    ArrayList<String> c1 = new ArrayList<String>(Arrays.asList("gos", "jokull", "hike", "safn"));
    ArrayList<String> c2 = new ArrayList<String>(Arrays.asList("fuglar", "raft", "horse", "foss"));
    ArrayList<String> c3 = new ArrayList<String>(Arrays.asList("golf", "stadir", "fjall", "geysir"));


    ProfileEntity profile1 = new ProfileEntity("jon1", user1,c1);
    ProfileEntity profile2 = new ProfileEntity("helga22", user2,c2);
    ProfileEntity profile3 = new ProfileEntity("jimsmith", user3, c3);



}
