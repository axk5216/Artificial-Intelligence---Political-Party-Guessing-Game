package aiassignment2kephart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AIAssignment2Kephart {

    /**
     * @param args ask the user for input, and provide output based on questions
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(System.in); //inputs
        String question; //the answer to the first question
        String question2; //the second answer to further determine political party
        String question3; //the third answer to further determine political party
        int party; //the political party
        String proceed = "Y";
        Analytic analytics = new Analytic(); //initialize the analytic class

        do {
            //Gather question inputs.
            System.out.println("Welcome to the program.\n"
                    + "Input only one character such as 'A', 'B', 'C', or 'D'"
                    + "for the following question:  \n");
            System.out.print("What should the government do to help the poor? \n"
                    + "A.  Make it easier to apply for assistance.\n"
                    + "B.  Put more money into schools.\n"
                    + "C.  Incentivize Welfare\n"
                    + "D.  Nothing.\n\n"
                    + "Input your answer here:  ");

            //input the answer as uppercase
            question = scan.next().toUpperCase();
            

            //ask second question
            System.out.print("How do you feel about welfare assistance? \n"
                    + "A.  Assistance is definitely necessary for the poor \n"
                    + "B.  We need to introduce more government assistance programs\n"
                    + "C.  Everyone should get a universal income\n"
                    + "D.  Welfare does not benefit society.\n\n"
                    + "Input your answer here:  ");
            //input the answer as uppercase
            question2 = scan.next().toUpperCase();
            
            //ask third question
            System.out.print("How do you feel about the COVID-19 pandemic? \n"
                    + "A.  It is a real problem, people need to wear masks\n"
                    + "B.  I see it from both sides equally \n"
                    + "C.  I trust the government and will comply with any recommendations\n"
                    + "D.  The pandemic is a hoax and is costing a lot of money\n\n"
                    + "Input your answer here:  ");
            //input the answer as uppercase
            question3 = scan.next().toUpperCase();
            
            //if incorrect input is entered, try again.
            while (question.length() != 1) {
                System.out.print("Please enter only one character:  ");
                question = scan.next();
            }
            
            
            
           //output the information and guess the political party.
            analytics.predictPoliticalParty(question, question2, question3);
            
            
            //gather political party inputs.
            System.out.print("\nPlease define your political affiliation by "
                    + "choosing a number for each of the following choices "
                    + "(e.g. 1): \n"
                    + "1.  Democrat\n"
                    + "2.  Republican\n"
                    + "3.  Libertarian\n"
                    + "4.  Socialist\n\n"
                    + "Please enter a number choice:  ");
            party = scan.nextInt();
            while (party > 4 || party <= 0) {
                System.out.print("Please enter only a number 1-4:  ");
                party = scan.nextInt();
            }
            
            analytics.inputFile(question, party);
            analytics.inputFile(question2, party);
            analytics.inputFile(question3, party);
            
            
            //start loop for program, if user wants to proceed.
            System.out.print("Do you want to proceed? Input Y or N.:  ");
            proceed = scan.next();
            
            
        } while (proceed.equalsIgnoreCase("Y"));

    }

}
