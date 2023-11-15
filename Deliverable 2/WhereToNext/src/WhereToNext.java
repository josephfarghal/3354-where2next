import java.util.*;
import java.io.*;
//import java.time.*;

/*
    test case to fail; make sure fraud cannot work
    set threshold for age for new account
        - verify age
        - if name exceeds 20 characters
        - password should be 6 <= x <= 12
 */

public class WhereToNext{
    public static void main(String[] args)throws IOException{
        User[] arrayOfUser = new User[10]; //Arbitrarily small array for testing purposes only
        int counter = 0;
        String str = "";
        char userCondition = '0';
        //read database file
        try {
            Scanner reader = new Scanner(new File("Database.txt"));   //we now open the input file with the driver's contents
            while (reader.hasNextLine()) {
                str = reader.nextLine();
                //create an array of User objects
                //add databases to the array
                arrayOfUser[counter] = loadDatabase(str);
                counter++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //returning user
        userCondition = startUp();
        if(userCondition == 'r') {
            User you = returningUser(arrayOfUser);
            if(you.getName().equals("")){
                while(you.getName().equals("")){
                    System.out.println("User could not be found, please try again.");
                    you = returningUser(arrayOfUser);
                }
            }
            System.out.println("Welcome " + you.getName() + " lets get started!");
        }else if(userCondition == 'n'){
            //create a new user
            User you = makeNewUser();
            if(!(you.getName() == "") || !(you.requestPass() == "")){
                arrayOfUser[counter] = you;
                counter++;
            }
        }
    }

    public static User makeNewUser(){
        System.out.println("Thank you for choosing our app, to register please enter a username.\nYou will first be prompted to add the first half of your username and then the other half after.\nUsernames are stored in the following format: first_last.\nNames should be no more than 20 characters for BOTH first and last!");
        String name = input('u');
        System.out.println("Username 1/2 recieved;");
        name = name + "_" + input('u');
        System.out.println("Verifying name");
        while(!verifyName(name)){
            System.out.println("Thank you for choosing our app, to register please enter a username.\n You will first be prompted to add the first half of your username and then the other half after.\n Usernames are stored in the following format: first_last.\nNames should be no more than 20 characters for BOTH first and last!");
            name = input('u');
            System.out.println("Username 1/2 recieved;");
            name = name + "_" + input('u');
            System.out.println("Verifying name");
        }

        System.out.println("Now please enter a password. Length should be no more than 12 characters and no smaller than 6!");
        String password = input('p');
        System.out.println("Verifying password.");
        while(!verifyPass(password)){
            System.out.println("Now please enter a password. Length should be no more than 12 characters and no smaller than 6!");
            password = input('p');
            System.out.println("Verifying password.");
        }
        System.out.println("Thank you, last step now is to verify your age. Enter your DOB in the numerical form 'Month_Day_Year'[Year must be four digits]");
        String DOB = input('d');
        int age = verifyAge(DOB);
        //Not allowing a cycle for age criteria is intentional to dissuade users from making underage & joke accounts
        if(age == -1){
            User failed_state = new User("", "");
        }

        System.out.println("Thank you for your cooperation, your account has been made!");
        User newUser = new User(name, password, age);
        return newUser;
    }

    public static int verifyAge(String x){
        int month, day, year = 0;
        month = Integer.parseInt(x.substring(0, x.indexOf("_")));
        x = x.substring(x.indexOf("_") + 1);
        day = Integer.parseInt(x.substring(0, x.indexOf("_")));
        x = x.substring(x.indexOf("_") + 1);
        year = Integer.parseInt(x);

        int age = ((2023 - year) * 365) + ((11 - month)*30) + (14 - day); //Assuming this is done on 11/14/2023

        //Post demo will feature actual date comparisons, for now hardcoded for testing purposes.
        //LocalDate date = LocalDate.now();

        //1550 days = 14 years, 40,150 days = 110 years
        if(1550 < age && age < 40150){
            return age;
        }else if (age < 1550){
            System.out.println("We regret to inform you that you are too young to be serviced, please inform your parent / legal guardian");
            return -1;
        }else{
            System.out.println("We cannot in good confidence service you, please receive assistance from a caretaker who has this app.");
            return -1;
        }
    }

    public static boolean verifyPass(String x){
        Boolean verified = false;

        if(x.length() > 12 || x.length() < 6){
            if(x.length() > 12) {
                System.out.println("Your password appears to be too large, please try again.");
            }else{
                System.out.println("Your password appears to be too small, please try again.");
            }
        }else{
            verified = true;
        }

        return verified;
    }

    public static boolean verifyName(String x){
        boolean verified = false;

        //Future development will implement a hashed ID system so that multiple users may have the same username if desired.

        if(x.length() > 41){
            if(x.substring(0, x.indexOf("_")).length() > 20){
                System.out.println("The first part of your username appears to be too long, please abbreviate or choose another name.");
            }else{
                System.out.println("The last part of your username appears to be too long, please abbreviate or choose another name.");
            }
        }else{
            verified = true;
        }

        return verified;
    }

    public static String input(char x){
        Scanner takeInput = new Scanner(System.in); //Take in user input for names, password, ids
        String str = "";
        if(x == 'p'){
            System.out.println("Please enter a password.");
            str = takeInput.nextLine();
        }
        else if(x == 'u'){
            System.out.println("Please enter a Username.");
            str = takeInput.nextLine();
        }
        else if(x == 'd'){
            str = takeInput.nextLine();
        }
        return str;
    }

    public static char startUp(){
        char user_status = 'n';
        String user_choice;
        Scanner takeInput = new Scanner(System.in);
        System.out.println("Are you a new or returning user? (Enter 'new' / 'ret' for the demo)");
        user_choice = takeInput.nextLine();

        if(Objects.equals(user_choice, "new")){
            user_status = 'n';
        }else if (Objects.equals(user_choice, "ret")){
            user_status = 'r';
        }else{
            user_status = startUp();
        }
        return user_status;
    }

    public static User loadDatabase(String dataBaseUser){
        String name =  dataBaseUser.substring(0, dataBaseUser.indexOf(" "));
        dataBaseUser = dataBaseUser.substring(dataBaseUser.indexOf(" ") + 1);
        String pass = dataBaseUser.substring(0, dataBaseUser.indexOf(" "));
        dataBaseUser = dataBaseUser.substring((dataBaseUser.indexOf(" ")) + 1);
        int age = Integer.parseInt(dataBaseUser);

        User newUser = new User(name, pass, age);
        return newUser;
    }

    //function for returning user
    public static User returningUser(User[] x){
        String user = input('u');

        for(int i = 0; i < x.length; i++){
            if(x[i].getName().toUpperCase().contains(user.toUpperCase())){
                String pass = input('p');
                if(x[i].requestPass().equals(pass)){
                    return x[i];
                }
            }
        }

        User you = new User("", "");
        return you;
        //find object and return it
    }
}
