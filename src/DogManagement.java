/*-------------------------------------------------------------
# Program 6: MPLS Dog Management System using OOP Principles
**Programs 6 is similar in functionality to program 5**


    Course: COMP 170, Fall 2026
    System: Visual Studio Code, Windows 11
    Author: G. Bolles
 */
import java.util.*;
import java.io.*;
class Dog {
    int id;
    String name;
    int weight;
    int age;

    public Dog() {
        this.id = 0;
        this.name = "Not provided";
        this.weight = 0;
        this.age = 0;
    }
    public Dog(int id, String name, int weight, int age) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.age = age;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }

    public double getWeight(){
        return weight;
    }

    public int getAge(){
        return age;
    }
    public String toString(){
        return "ID: " + id + ",\nName: " + name + ",\nWeight: " + weight + ",\nAge: " + age;
    }
    /*public void displayInfo(){
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Weight: " + weight);
        System.out.println("Age: " + age);
    }*/
}
public class DogManagement {
    static ArrayList<Dog> dogs = new ArrayList<>();
    static final int SENTINEL_VALUE=-1;
    static final Scanner SCN = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        importPriorData();
        System.out.println("Welcome, this program allows for a care attendant to be able to create, retrieve and update a dog record from the system.");
        boolean exit = false;
        int userChoice=0;
        while(exit==false){
            userChoice = displayPrompt();
            if(userChoice==1){
                CreateRecord();
            }
            else if(userChoice==2){
                DisplayCurrentRecordIDs();
                DisplayRecord();
            }
            else if(userChoice==3){
                DisplayCurrentRecordIDs();
                UpdateRecord();
            }
            else if(userChoice==4||userChoice==SENTINEL_VALUE){
                exit = true;
            }
            else{
                System.out.println("Invalid menu option; please try again.");
            }
        }
    }
    public static void DisplayCurrentRecordIDs(){
        System.out.println("Current Record IDs:");
        for(int i=0;i<dogs.size();i++){
            Dog dog = dogs.get(i);
            System.out.println(dog.getID() + " - " + dog.getName());
        }
    }
    public static void importPriorData(){
        File file = new File("doginfo.csv");
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] values = line.split(","); //this is what I found for splitting Strings by delim in java
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                int weight = Integer.parseInt(values[2]);
                int age = Integer.parseInt(values[3]);
                dogs.add(new Dog(id, name, weight, age));
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
    public static int displayPrompt(){
        int menuOption;

        System.out.println("\nSelect a menu option:");
        System.out.println("\t1) Create a dog record");
        System.out.println("\t2) Display dog record");
        System.out.println("\t3) Update dog record");
        System.out.println("\t4) Exit Program");
        
        System.out.print("Enter selection here --> ");
        
        menuOption = Integer.parseInt(SCN.nextLine());

        return menuOption;
    }
    public static void DisplayRecord(){
        int idToDisplay=ValidateAlternateIDInput("Enter the ID of the record to display: ");
        if(idToDisplay==SENTINEL_VALUE) {
            System.out.println("Display cancelled.");
            return;
        }
        else{
            int index = FindIndexFromID(idToDisplay);
            if(index == -1){
                System.out.println("Record not found.");
            }
            else{
                System.out.println(dogs.get(index).toString());
            }
        }
    }

    public static void UpdateRecord(){
        int idToUpdate=ValidateAlternateIDInput("Enter the ID of the record to update: ");
        if(idToUpdate==SENTINEL_VALUE){
            System.out.println("Update cancelled.");
            return;
        }
        else{
            int index = FindIndexFromID(idToUpdate);
            if(index == -1){
                System.out.println("Record not found."); //Shouldn't hit this
            }
            else{
                System.out.println("");
                System.out.println("Current record:");
                System.out.println(dogs.get(index).toString());
                System.out.println("Enter new values for the record:");
                
                String newName = ValidateStringInput("Enter the dog's new name: ");
                if(newName.equals(SENTINEL_VALUE)==true){
                    System.out.println("Record update cancelled.");
                    return;
                }
                
                int newWeight = ValidateIntInput("Enter the dog's new weight: ");
                if(newWeight==SENTINEL_VALUE){
                    System.out.println("Record update cancelled.");
                    return;
                }
                int newAge = ValidateIntInput("Enter the dog's new age: ");
                if(newAge==SENTINEL_VALUE){
                    System.out.println("Record update cancelled.");
                    return;
                }
                dogs.get(index).setName(newName);
                dogs.get(index).setWeight(newWeight);
                dogs.get(index).setAge(newAge);
                System.out.println("Record updated successfully.");
            }
        }
    }

    public static void CreateRecord() {
       
        int collectID = ValidateIDInput("Please enter the dog's desired ID: ");
        if(collectID==SENTINEL_VALUE){
            System.out.println("Record creation cancelled.");
            return;
        }
        
        String collectName = ValidateStringInput("Please enter the dog's name: ");
        if(collectName.equals(SENTINEL_VALUE)==true){
            System.out.println("Record creation cancelled.");
            return;
        }
        
        int collectWeight = ValidateIntInput("Please enter the dog's weight: ");
        if(collectWeight==SENTINEL_VALUE){
            System.out.println("Record creation cancelled.");
            return;
        }
        
        int collectAge = ValidateIntInput("Please enter the dog's age: ");
        if(collectAge==SENTINEL_VALUE){
            System.out.println("Record creation cancelled.");
               return;
        }
        //Record creation wasn't cancelled! Store and increment.
        Dog toAdd= new Dog(collectID, collectName, collectWeight, collectAge);
        dogs.add(toAdd);
        System.out.println("Record creation successful.");
    }

    public static String ValidateStringInput(String promptText){
        String output="";
        //Only need to validate that the input is not empty.
        while(output.equals("")==true){
            System.out.println(promptText);
            output = SCN.nextLine();
        }
        return output;
    }
    public static int ValidateIDInput(String promptText){
        int output=0;
        boolean exit=false;
        while(exit==false){
            System.out.println(promptText);
            String attemptOutput= SCN.nextLine();
            try{
                output = Integer.parseInt(attemptOutput);
                boolean available=true;
                for(int i =0;i<dogs.size();i++){
                    if(output==dogs.get(i).getID()){
                        available=false;
                        output=0;
                    }
                }
                if(available==false){
                        System.out.println("ID already exists. Please enter a different ID.");
                }
                if(output<0 && output!=SENTINEL_VALUE){available=false; System.out.println("Please enter a positive ID."); output=0;}
                if(output==SENTINEL_VALUE || available==true){
                    exit=true; //sentinel entered or output is valid
                } 
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid whole number.");
                exit=false; //should be redundant
            }
        }
        return output;
    }
    public static int ValidateAlternateIDInput(String promptText){
        int output=0;
        boolean found=false;
        while(found==false){
            System.out.println(promptText);
            String attemptOutput= SCN.nextLine();
            try{
                output = Integer.parseInt(attemptOutput);
                for(int i =0;i<dogs.size();i++){
                    if(output==dogs.get(i).getID()){
                        found=true;
                    }
                }
                if(output==SENTINEL_VALUE){
                    found =true; //not truly found, but exit with sentinel value
                } 
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid whole number or "+SENTINEL_VALUE+" to cancel.");
                found=false; //should be redundant
            }
        }
        return output;
    }
    public static int ValidateIntInput(String promptText){
        int output=0;
        boolean exit=false;
        while(exit==false){
            System.out.println(promptText);
            String attemptOutput= SCN.nextLine();
            try{
                output = Integer.parseInt(attemptOutput);
                if(output==SENTINEL_VALUE || output>0){
                    exit=true; //sentinel entered or output is valid
                } 
            }
            catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid whole number.");
                exit=false; //should be redundant
            }
        }
        return output;
    }
    
    public static int FindIndexFromID(int id){
        for(int i=0;i<dogs.size();i++){
            if(dogs.get(i).getID()==id){
                return i;
            }
        }
        return -1; //ID not in array 
    }
}
