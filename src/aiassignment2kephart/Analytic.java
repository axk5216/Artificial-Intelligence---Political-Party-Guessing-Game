package aiassignment2kephart;

import java.io.*;

/**
 * Writes to file based on input to log information in data stores.
 * Also utilizes Bayes theorem to calculate answer probabilities.
 * @author Aaron Kephart
 */
public class Analytic {
    FileWriter fileWriter;  //the filewriter
    BufferedReader fileReader; //the file reader
    int[] totalAnswer; //total of all the answers in the files
    int[][] answerPoliticalParty; //two dimensional array deciphering which answer is affiliated with which political party.
    int totalResponses; //the total responses overall

    //write answer to an output file, with the corresponding political party.
    public void inputFile(String answer, int politicalParty) throws IOException {
        //define which file to write answer choice to the corrresponding file.

        switch (politicalParty) {
            case 1:
                fileWriter = new FileWriter("Democrat.txt", true);
                fileWriter.append(answer + "\n");
                fileWriter.flush();
                fileWriter.close();
                break;
            case 2:
                fileWriter = new FileWriter("Republican.txt", true);
                fileWriter.append(answer + "\n");
                fileWriter.flush();
                fileWriter.close();
                break;
            case 3:
                fileWriter = new FileWriter("Libertarian.txt", true);
                fileWriter.append(answer + "\n");
                fileWriter.flush();
                fileWriter.close();
                break;
            case 4:
                fileWriter = new FileWriter("Socialist.txt", true);
                fileWriter.append(answer + "\n");
                fileWriter.flush();
                fileWriter.close();
                break;
        }
    }

    /**
     * Predict political party utilizing other functions below to count data and
     * predict the political party based on bayes theorem. Also sorts this data
     * in order to find the top potential contender.
     *
     * @param ans the answer A corresponds to 0, B-1, C-2, D-3
     */
    public void predictPoliticalParty(String ans, String ans2, String ans3) {
        countData(); //count all data
        int answer = 0;
        int answer2 = 0;
        int answer3 = 0;
        //convert input string to integer.
        switch (ans) {
            case "A":
                answer = 0;
            case "B":
                answer = 1;
            case "C":
                answer = 2;
            case "D":
                answer = 3;
        }
        
        switch (ans2) {
            case "A":
                answer2 = 0;
            case "B":
                answer2 = 1;
            case "C":
                answer2 = 2;
            case "D":
                answer2 = 3;
        }

        switch (ans2) {
            case "A":
                answer3 = 0;
            case "B":
                answer3 = 1;
            case "C":
                answer3 = 2;
            case "D":
                answer3 = 3;
        }

        double democrat1 = bayesTheorem(answer, "Democrat");
        double republican1 = bayesTheorem(answer, "Republican");
        double libertarian1 = bayesTheorem(answer, "Libertarian");
        double socialist1 = bayesTheorem(answer, "Socialist");

        double democrat2 = bayesTheorem(answer, "Democrat");
        double republican2 = bayesTheorem(answer, "Republican");
        double libertarian2 = bayesTheorem(answer, "Libertarian");
        double socialist2 = bayesTheorem(answer, "Socialist");

        double democrat3 = bayesTheorem(answer, "Democrat");
        double republican3 = bayesTheorem(answer, "Republican");
        double libertarian3 = bayesTheorem(answer, "Libertarian");
        double socialist3 = bayesTheorem(answer, "Socialist");

        double democrat = (democrat1 + democrat2 + democrat3) / 3;
        double republican = (republican1 + republican2 + republican3) / 3;
        double libertarian = (libertarian1 + libertarian2 + libertarian3) / 3;
        double socialist = (socialist1 + socialist2 + socialist3) / 3;

        //weight responses
        if (ans.equals(ans2) && ans2.equals(ans3)) {
            switch (ans) {
                case "A":
                    democrat *= 3;
                    break;
                case "B":
                    libertarian *= 3;
                    break;
                case "C":
                    socialist *= 3;
                    break;
                case "D":
                    republican *= 3;
                    break;
            }
        } else if (ans2.equals(ans3) || ans2.equals(ans)) {
            switch (ans2) {
                case "A":
                    democrat *= 2;
                    break;
                case "B":
                    libertarian *= 2;
                    break;
                case "C":
                    socialist *= 2;
                    break;
                case "D":
                    republican *= 2;
                    break;
            }
        } else if (ans.equals(ans3)) {
            switch (ans) {
                case "A":
                    democrat *= 2;
                    break;
                case "B":
                    libertarian *= 2;
                    break;
                case "C":
                    socialist *= 2;
                    break;
                case "D":
                    republican *= 2;
                    break;
            }
        }

        if (democrat > republican && democrat > libertarian && democrat > socialist) {
            System.out.println("***************************************\n\n"
                    + "Based on statistics, you are most affiliated with the following political party: DEMOCRAT\n\n"
                    + "************************************************\n\n");
        } else if (republican >= democrat && republican > libertarian && republican > socialist) {
            System.out.println("***************************************\n\n"
                    + "Based on statistics, you are most affiliated with the following political party: REPUBLICAN\n\n"
                    + "************************************************\n\n");
        } else if (libertarian > democrat && libertarian > democrat && libertarian > socialist) {
            System.out.println("***************************************\n\n"
                    + "Based on statistics, you are most affiliated with the following political party: LIBERTARIAN\n\n"
                    + "************************************************\n\n");
        } else if (socialist > democrat && socialist > democrat && socialist > libertarian) {
            System.out.println("***************************************\n\n"
                    + "Based on statistics, you are most affiliated with the following political party: SOCIALIST\n\n"
                    + "************************************************\n\n");
        }

    }
/**
 * Counts all of the political party data, and also places into two dimensional arrays for questions
 */
    public void countData() {
        totalAnswer =  new int[4];
        answerPoliticalParty = new int[4][4];
        String line = "";
        try {
            BufferedReader readerDemocrat = new BufferedReader(new FileReader("Democrat.txt"));
            while ((line = readerDemocrat.readLine()) != null) {
                totalAnswer[0]++; //count responses
                switch (line) {
                    case "A":
                        answerPoliticalParty[0][0]++;
                        break;
                    case "B":
                        answerPoliticalParty[0][1]++;
                        break;
                    case "C":
                        answerPoliticalParty[0][2]++;
                        break;
                    case "D":
                        answerPoliticalParty[0][3]++;
                        break;
                    default:
                        break;
                }
            }
            readerDemocrat.close();
        } catch (IOException e) {
        }

        try {
            BufferedReader readerRepublican = new BufferedReader(new FileReader("Republican.txt"));
            while ((line = readerRepublican.readLine()) != null) {
                totalAnswer[1]++; //count responses
                switch (line) {
                    case "A":
                        answerPoliticalParty[1][0]++;
                        break;
                    case "B":
                        answerPoliticalParty[1][1]++;
                        break;
                    case "C":
                        answerPoliticalParty[1][2]++;
                        break;
                    case "D":
                        answerPoliticalParty[1][3]++;
                        break;
                    default:
                        break;
                }
            }
            readerRepublican.close();
        } catch (IOException e) {
        }

        try {
            BufferedReader readerLibertarian = new BufferedReader(new FileReader("Libertarian.txt"));
            while ((line = readerLibertarian.readLine()) != null) {
                totalAnswer[2]++; //count responses
                switch (line) {
                    case "A":
                        answerPoliticalParty[2][0]++;
                        break;
                    case "B":
                        answerPoliticalParty[2][1]++;
                        break;
                    case "C":
                        answerPoliticalParty[2][2]++;
                        break;
                    case "D":
                        answerPoliticalParty[2][3]++;
                        break;
                    default:
                        break;
                }
            }
            readerLibertarian.close();
        } catch (IOException e) {
        }

        try {
            BufferedReader readerSocialist = new BufferedReader(new FileReader("Socialist.txt"));
            while ((line = readerSocialist.readLine()) != null) {
                totalAnswer[3]++; //count responses
                switch (line) {
                    case "A":
                        answerPoliticalParty[3][0]++;
                        break;
                    case "B":
                        answerPoliticalParty[3][1]++;
                        break;
                    case "C":
                        answerPoliticalParty[3][2]++;
                        break;
                    case "D":
                        answerPoliticalParty[3][3]++;
                        break;
                    default:
                        break;
                }
            }
            readerSocialist.close();
        } catch (IOException e) {
        }
        totalResponses = totalAnswer[0] + totalAnswer[1] + totalAnswer[2] + totalAnswer[3];
    }
    
    /**
     * calculates probability of each party using total, overall guesses based on answers.
     * @param party - the political party
     * @return the probability of the party parameter occurring based on responses
     */
    public double probability(String party) {
        double democrat = (double) totalAnswer[0] / totalResponses;
        double republican = (double) totalAnswer[1] / totalResponses;
        double libertarian = (double) totalAnswer[2] / totalResponses;
        double socialist = (double) totalAnswer[3] / totalResponses;
        
        switch (party) {
            case "Democrat":
                return democrat;
            case "Republican":
                return republican;
            case "Libertarian":
                return libertarian;
            case "Socialist":
                return socialist;
            default:  return 0.0;
        }
    }
    /**
     * Calculates conditional probability based on question number, and answer.
     * @param answer the question answer - A corresponds to 0, B-1, C-2, D-3
     * @param party the political party
     * @return the probability of the answer and the party coinciding together.
     */
    public double conditionalProbability (int answer, String party) {
        int specificTotalAnswer = 0;
        double probabilityAnswer = 0;
        
        switch (answer) {
            case 0:
                specificTotalAnswer = answerPoliticalParty[0][0]
                        + answerPoliticalParty[1][0]
                        + answerPoliticalParty[2][0]
                        + answerPoliticalParty[3][0];
                probabilityAnswer = (double) specificTotalAnswer / totalResponses;
                break;

            case 1:
                specificTotalAnswer
                        = answerPoliticalParty[0][1]
                        + answerPoliticalParty[1][1]
                        + answerPoliticalParty[2][1]
                        + answerPoliticalParty[3][1];
                probabilityAnswer = (double) specificTotalAnswer / totalResponses;
               break;

            case 2:
                specificTotalAnswer
                        = answerPoliticalParty[0][2]
                        + answerPoliticalParty[1][2]
                        + answerPoliticalParty[2][2]
                        + answerPoliticalParty[3][2];
                probabilityAnswer = (double) specificTotalAnswer / totalResponses;
               break;

            case 3:
                specificTotalAnswer
                        = answerPoliticalParty[0][3]
                        + answerPoliticalParty[1][3]
                        + answerPoliticalParty[2][3]
                        + answerPoliticalParty[3][3];
                probabilityAnswer = (double) specificTotalAnswer / totalResponses;
                break;
            default:  return 0.0;
        }
        return probabilityAnswer * probability(party);
    }
        /**
         * Bayes theorem utilizing the probabilities of each party, and conditional probabilities
         * @param answer the question answer - A corresponds to 0, B-1, C-2, D-3
         * @param party the political party
         * @return the probability of the party given the answer probability.
         */
  public double bayesTheorem (int answer, String party)
    {
        double probability = probability(party) * conditionalProbability(answer, party)
                / ((probability("Democrat") * conditionalProbability(answer, "Democrat"))
                + (probability("Republican") * conditionalProbability(answer, "Republican"))
                + (probability("Libertarian") * conditionalProbability(answer, "Libertarian"))
                + (probability("Socialist") * conditionalProbability(answer, "Socialist")));

        return probability;
    }
}
   
