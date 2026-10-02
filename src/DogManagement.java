/*-------------------------------------------------------------
# Program 6: MPLS Dog Management System using OOP Principles
**Programs 6 is similar in functionality to program 5**


    Course: COMP 170, Fall 2026
    System: Visual Studio Code, Windows 11
    Author: G. Bolles
 */
import java.util.*;
class Dog {
    int id;
    String name;
    double weight;
    int age;

    public Dog(int id, String name, double weight, int age) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.age = age;
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
        
        
    }
    public static int displayPrompt(){
        int menuOption;

        System.out.println("\nSelect a menu option:");
        System.out.println("\t1) Create a dog record");
        System.out.println("\t2) Display dog record");
        System.out.println("\t3) Update dog record");
        System.out.println("\t4) Display dog years");
        System.out.println("\t5) Exit Program");
        
        System.out.print("Enter selection here --> ");
        //INPUT
        menuOption = Integer.parseInt(SCN.nextLine());

        return menuOption;
    }
    public static void DisplayRecord(){
        int idToDisplay=ValidateAlternateIDInput("Enter the ID of the record to display: ");
        if(idToDisplay==SENTINEL_VALUE){
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
        /*int idToUpdate=ValidateAlternateIDInput("Enter the ID of the record to update: ");
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
                System.out.println("Current record:");
                System.out.println("ID: " + ids[index]);
                System.out.println("Name: " + names[index]);
                System.out.println("Weight: " + weights[index]);
                System.out.println("Age: " + ages[index]);
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
                
                names[index] = newName;
                weights[index] = newWeight;
                ages[index] = newAge;
                System.out.println("Record updated successfully.");
            }
        }*/
    }

    public static void CreateRecord() {
       /*
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
        String collectBreed = ValidateStringInput("Please enter the dog's breed: ");
        if(collectBreed.equals(SENTINEL_VALUE)==true){
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
        ids[positionInArray] = collectID;
        names[positionInArray] = collectName;
        breeds[positionInArray] = collectBreed;
        weights[positionInArray] = collectWeight;
        ages[positionInArray] = collectAge;
        System.out.println("Record creation successful.");
        }*/
    }
    
    public static int FindNextAvailableIndex(){
        /*for(int i=0;i<ids.length;i++){
            if(ids[i]==0 && names[i].isEmpty()){
                return i;
            }
        }*/
        return -1; //all slots taken
    }

    public static String ValidateStringInput(String promptText){
        String output="";
        //Only need to validate that the input is not empty.
        /*while(output.equals("")==true){
            System.out.println(promptText);
            output = scn.nextLine();
        }*/
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
    public static int PromptForOverride(){
        boolean exit=false;
        System.out.println("Do you want to override the existing record? (Y/N)");
        int outSignal=-1;
        /*while(exit==false){
            String response = scn.nextLine();
            if(response.equals("Y")==true){
                exit=true;
                outSignal=1;
            }
            else if(response.equals("N")==true){
                exit=true;
                outSignal=0;
            }
            else{
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }*/
        return outSignal;
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
