import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        // Key name paIr sets with an internal ref and an external display
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        //First pop up
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");
//first header and printed line
        System.out.println("Welcome to LaunchCode's TechJobs App!");
        System.out.println();



        // Allow the user to search until they manually quit
        while (true) {
//created header

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break; //stops program if null
            } else if (actionChoice.equals("list")) {
//otherwise if input = list selections print getuserselection method
                String columnChoice = getUserSelection("List", columnChoices);
//if all then show the entire jobdata
                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {
//otherwise find the column choice and print
                    ArrayList<String> results = JobData.findAll(columnChoice);
                    System.out.println();
                    System.out.println("*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                // if search is chosen display
                System.out.println();
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term? with search you can input what you would like in the previous selection
                System.out.println();
                System.out.println("Search term:");
                String searchTerm = in.nextLine();
// if user selection is all then searches the csv for all occourances
                if (searchField.equals("all")) {
                        //call find by value in the print jobs to print the searched term that is value in the findbyvalue method
                        printJobs(JobData.findByValue(searchTerm));}
               /*if (searchField.equals("core competency")) {
                    printJobs(JobData.findByValue(searchTerm)); }
                if (searchField.equals("position type")) {
                    printJobs(JobData.findByValue(searchTerm)); }
                if (searchField.equals("location")) {
                    printJobs(JobData.findByValue(searchTerm)); }
                if (searchField.equals("employer")) {
                    printJobs(JobData.findByValue(searchTerm)); }*/
                    else { // other wise use method in jobdata to find the search term in the other search fields
                        printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                    }

            }
        }
    }

    //Returns the key of the selected item from the choices Dictionary
    //create a method used only here named get user selection. that takes a menuHeader and a hashmap named choices, to
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {
//start at -1 because 0 would display, assume it will be used later in a loop
        int choiceIdx = -1;
        //also validation on if it is a validchoice, assume a validChoice = true then later
        Boolean validChoice = false;
        //make array of keys from hashmap choice
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        int i = 0;
        //for loop to move through the choice
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {
//prints header and iterates through the array choices
            System.out.println( menuHeader);

            // Print available choices from choiceKeys
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }
//validation if int is contained in scanner in, if so choiceInx becomes the integer selected
            if (in.hasNextInt()) {
                choiceIdx = in.nextInt();
                in.nextLine();
            }
            // CLoses the program
            else {
                String line = in.nextLine();
                boolean shouldQuit = line.equals("x");
                if (shouldQuit) {
                    return null;
                    //returns null and references break previous mentioned
                }
            }

            // Validate user's input
            //if choiceinx is  less than 0 (used as -1 earlier) or choiceIdx id greater then the choice keys, well, key's length then you made an invalid choice, but otherwise it is true (mentioned earlier)
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }
//but while valid choice is false return most recent choice menu
        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        if (someJobs.size() < 1) {

            System.out.println("No Results");

        } else {
           for (HashMap<String, String> selectedJob: someJobs){

               //print ***** at top and bottom of each
               System.out.println();
               System.out.println("*****");
               //nest it for each entry set print the key and value
               for (Map.Entry<String, String> jobInfo: selectedJob.entrySet()){
                   System.out.println((jobInfo.getKey() + ": " + jobInfo.getValue()));
               }
               //print ***** at top and bottom of each
               System.out.println("*****");
               System.out.println();
 }


            }

        //Iterate over the ArrayList of hashmaps(each somejob is a hash map as seen above) needs to return no results if there is no results, ie if it has 0 length(.size()) DO NOT use individual identifiers use .entryset to call the set of entrys.


    }
}
