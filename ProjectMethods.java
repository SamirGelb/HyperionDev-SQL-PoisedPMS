/***
 * This class contains methods to take in user input.
 */

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;


/* Creating a class to contain all the methods
* required for user input
* and for datetime calculations*/
public class ProjectMethods {

    // creating a static scanner object
    static Scanner input = new Scanner(System.in);

    /***
     *
     * @param instruction takes in integer input from the user
     * @return the integers the user entered
     */
    /* Creating a method called getNum() to get
     * numbers from the user using a try and catch block.
     * I made this method a float method rather than an integer in order to handle monetary values */
    public float getNum(String instruction) {

        while (true) {

            // In the try block I return the scanner and cast the string to an integer data type
            try {
                System.out.println(instruction);
                return Float.parseFloat(input.nextLine());
            }

            // if a string is entered I show an error message
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /***
     *
     * @param instruction takes in a String from user input
     * @return the string the user entered
     */
    /* Creating a method called getNum() to get
     * integers from the user using a try and catch block */
    public String getStr(String instruction) {

        while(true) {
            // In the try block I return the scanner and cast the string to an integer data type
            try {
                System.out.println(instruction);
                return input.nextLine();
            }

            // if a string is not entered I show an error message
            catch(Exception e) {
                System.out.println("Please enter a string of letters.");
            }
        }
    }

    /***
     *
     * @return the date entered by the user
     */
    /* Creating a method called dateCreator() to add
    * the date in a set format for each project.*/
    public String dateCreator(){
        String date;

        /* Creating an object of the SimpleDateFormat method and passing the format
        * that the user should enter.*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Using a while loop and try-catch block
        while(true){
            try{

                // Collecting the date from the user
                Date deadline = dateFormat.parse(getStr(" "));
                date = dateFormat.format(deadline);
                break;
            }

            /* Asking the user to enter the right format
            if they do not the first time around */
            catch(ParseException pe){
                System.out.println("Please enter the date in the format stated above.");
            }
        }

        // returning the project deadline
        return date;
    }

    /***
     *
     * @return the connection to the database
     */
    // Creating a method to connect to the MySQL database
    public static Connection connect() {

        Connection connection = null;

        // Using a try-catch block to connect to the database
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "SamirGelb",
                    "SamirJDBC"
            );
        }

        catch(SQLException eSQL){
            System.out.println("Connection Error.");
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }

        return connection;
    }

    /***
     *
     * @return a statement from the database
     */
    // Creating a statement method to create a statement which connects to the database
    public static Statement statement(){
        Statement statement = null;

        try{
            statement = connect().createStatement();
        }
        catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }

        return statement;
    }

    /***
     *
     * @param projectResults taking in a ResultSet of the table
     * @throws SQLException any SQL error that might occur
     */
    // Creating a method to print out the contents of the projects table to the user's console.
    public static void printProjects(ResultSet projectResults) throws SQLException {
        while(projectResults.next()){
            System.out.println("Project Number: " + projectResults.getInt("project_number") + " | "
                    + "Project Name: " + projectResults.getString("project_name") + " | "
                    + "Building Address: " + projectResults.getString("building_address") + " | "
                    + "Building Type: " + projectResults.getString("building_type") + " | "
                    + "Project Deadline: " + projectResults.getDate("project_deadline") + " | "
                    + "Project Complete: " + projectResults.getString("project_complete") + " | "
                    + "Completion Date: " + projectResults.getDate("completion_date") + " | "
                    + "Customer Fee: R" + projectResults.getString("customer_fee") + " | "
                    + "Amount Paid To Date: R" + projectResults.getString("amount_paid_to_date") + " | "
                    + "ERF: " + projectResults.getInt("erf") + " | "
                    + "Architect ID: " + projectResults.getInt("architect_id") + " | "
                    + "Customer ID: " + projectResults.getInt("customer_id") + " | "
                    + "Project Manager ID: " + projectResults.getInt("project_manager_id") + " | "
                    + "Structural Engineer ID: " + projectResults.getInt("structural_engineer_id") + " | "
            );
        }
    }


    /***
     *
     * @param architectResults taking in a ResultSet of the table
     * @throws SQLException any SQL error that might occur
     */
    // Creating a method to print out the contents of the architect table to the user's console.
    public static void printArchitect(ResultSet architectResults) throws SQLException {
        while(architectResults.next()){
            System.out.println("ID: " + architectResults.getInt("id") + " | "
                    + "Name: " + architectResults.getString("name") + " | "
                    + "Surname: " + architectResults.getString("surname") + " | "
                    + "Phone Number: " + architectResults.getString("phone_number") + " | "
                    + "Email Address: " + architectResults.getString("email_address") + " | "
                    + "Physical Address: " + architectResults.getString("physical_address") + " | "
            );
        }
    }

    /***
     *
     * @param customerResults taking in a ResultSet of the table
     * @throws SQLException any SQL error that might occur
     */
    // Creating a method to print out the contents of the customer table to the user's console.
    public static void printCustomer(ResultSet customerResults) throws SQLException {
        while(customerResults.next()){
            System.out.println("ID: " + customerResults.getInt("id") + " | "
                    + "Name: " + customerResults.getString("name") + " | "
                    + "Surname: " + customerResults.getString("surname") + " | "
                    + "Phone Number: " + customerResults.getString("phone_number") + " | "
                    + "Email Address: " + customerResults.getString("email_address") + " | "
                    + "Physical Address: " + customerResults.getString("physical_address") + " | "
            );
        }
    }

    /***
     *
     * @param projectManagerResults taking in a ResultSet of the table
     * @throws SQLException any SQL error that might occur
     */
    // Creating a method to print out the contents of the Project Manager table to the user's console.
    public static void printProjMan(ResultSet projectManagerResults) throws SQLException {
        while(projectManagerResults.next()){
            System.out.println("ID: " + projectManagerResults.getInt("id") + " | "
                    + "Name: " + projectManagerResults.getString("name") + " | "
                    + "Surname: " + projectManagerResults.getString("surname") + " | "
                    + "Phone Number: " + projectManagerResults.getString("phone_number") + " | "
                    + "Email Address: " + projectManagerResults.getString("email_address") + " | "
                    + "Physical Address: " + projectManagerResults.getString("physical_address") + " | "
            );
        }
    }

    /***
     *
     * @param structuralEngineerResults taking in a ResultSet of the table
     * @throws SQLException any SQL error that might occur
     */
    // Creating a method to print out the contents of the Structural Engineer table to the user's console.
    public static void printStEng(ResultSet structuralEngineerResults) throws SQLException {
        while(structuralEngineerResults.next()){
            System.out.println("ID: " + structuralEngineerResults.getInt("id") + " | "
                    + "Name: " + structuralEngineerResults.getString("name") + " | "
                    + "Surname: " + structuralEngineerResults.getString("surname") + " | "
                    + "Phone Number: " + structuralEngineerResults.getString("phone_number") + " | "
                    + "Email Address: " + structuralEngineerResults.getString("email_address") + " | "
                    + "Physical Address: " + structuralEngineerResults.getString("physical_address") + " | "
            );
        }
    }
}
