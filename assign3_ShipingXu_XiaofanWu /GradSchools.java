/* GradSchools.java
 * CS230 Assignment #3 Task3 - subtask3
 * Written by: Shiping Xu & Xiaofan Wu
 * September 30, 2015
 */

// GradSchools class -- stores information about a collection of School objects
public class GradSchools {
  // instance variable
  private final int initLength = 100;
  private School[] gradSchools; 
  private int count;
 
  
  // constructor
  public GradSchools() {
    gradSchools = new School[initLength]; // gradSchools array with 100 spaces
    count = 0;  // sets the initial count as 0
  }
  
  // toString() to print an instance of type GradSchools
  public String toString() {
    String s = ""; // creates an empty string s
    for (int i=0; i<count; i++) { // for each school object in the array
    s += (gradSchools[i] + "\n"); // implicitly use the printing method in School class
    }
    return s;
  }
  
  // add a graduate school to gradSchools list
  public void addSchool(School s) {
    if (count < gradSchools.length) { // if there's still space in gradSchools array
    gradSchools[count] = s; // add the school into school list
    count++; // increment the count by 1
    } else { // if there's no more space in the array, prints out a message
      System.out.println("Not enough space in the Graduate School.");
    }
  }
  
  // computes the overall rating based on three weights input 
  public int[] computeRatings(int weight1, int weight2, int weight3) {
    int[] overallRating = new int[count]; // creates a new overallRating array 
    for (int i=0; i<count; i++) { // for every object in the array
      // computes the overall rating
      overallRating[i] = gradSchools[i].computeRating(weight1, weight2, weight3);
    } 
    return overallRating;
  }
  
  // rank all the schools in the collection, based on the input factor
  public void rankSchool(String input) {
    System.out.println("\nRanking of schools from highest to lowest using " + input + 
                       " as a factor: ");
    
    for (int i=0; i<count; i++) {
      if (input.equals("Academics")) { // if user enters academics, set schools' rank with academics rating
        gradSchools[i].setRank(gradSchools[i].getAcademics());
      } else if (input.equals("Research")) { // if user enters academics, set schools' rank with research rating
        gradSchools[i].setRank(gradSchools[i].getResearch());
      } else if (input.equals("Publications")) { // if user enters academics, set schools' rank with publications rating
        gradSchools[i].setRank(gradSchools[i].getPublications());
      } else { // if user enters overall, set schools' rank with overall rating
        gradSchools[i].setRank(gradSchools[i].getRating());
      }
    }
    // implement sortArray method, sort the schools' in increasing order depending on current rank
    sortArray(); 
    for (int i=count-1; i>=0; i--) { // for each school in the array, prints out the school name
      System.out.println(gradSchools[i].getName());
    }
  }

    // sorts the schools in the gradSchools array in increasing current rank order
   private void sortArray() {
    int maxRankValue;    // maximum rank value
    int maxIndex;  // index of maximum integer
    int i, j;
    for (j = count - 1; j > 0; j--) { // start from the last element in the array
      maxIndex = 0; // sets in the index to zero
      maxRankValue = gradSchools[0].getRank(); // sets the rank value to the last school's current rank
      for (i = 1; i <= j; i++) // go through the schools array and finds the school with largest current rank
        // if the school at index i has a higher current rank, set the max rank value to that value and index to be i
        if (gradSchools[i].getRank() > maxRankValue) {
          maxRankValue = gradSchools[i].getRank(); 
          maxIndex = i;
        }
      swap(gradSchools, maxIndex, j); // switch the schools at index i and index j
    }
  }
  
/** 
  * exchanges the contents of locations i and j in the input array
  */
  private static void swap (School[] schoolList, int i, int j) {
    // creates a temporary school object and let it point to school at index i in the array
    School temp = schoolList[i]; 
    // let the school at index i to point at school at index j
    schoolList[i] =schoolList[j];
    schoolList[j] =temp; 
  }
  
  // get the size of the grad school list
  public int getSize() {
    return count;
  }
  
  // main method, test cases
  public static void main (String[] args) {
    // if the user doesn't enter anything in the command line
    if (args.length ==0) { // prints out a message
      System.out.println("Please provide 3 weights (1..5) for Academics, Research and Publications");
      
    } else {     
    //create some graduate schools to start with, and print them out
    School MIT = new School("MIT", 10, 10, 7);
    School Stanford = new School("Stanford", 8, 10, 9);
    School CMU = new School("CMU", 7, 8, 6);
    School UCBerkeley = new School("UC Berkeley", 9, 9, 9);
    
    GradSchools my_gradSchools = new GradSchools(); // creates a new GradSchools object
    // add schools to the array
    my_gradSchools.addSchool(MIT);
    my_gradSchools.addSchool(Stanford);
    my_gradSchools.addSchool(CMU);
    my_gradSchools.addSchool(UCBerkeley);
    // computes the overall ratings of all the schools in the array with user's input
    my_gradSchools.computeRatings(Integer.parseInt(args[0]),
                                  Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    // displays the schools' information
    System.out.println("There are " + my_gradSchools.getSize() + " schools in the database:");
    System.out.println(my_gradSchools);
    my_gradSchools.rankSchool("Academics"); // rank all the schools by academics rating
    my_gradSchools.rankSchool("Research"); // rank all the schools by research rating
    my_gradSchools.rankSchool("Publications"); // rank all the schools by publications rating
    my_gradSchools.rankSchool("Overall"); // rank all the schools by overall rating
  }
  }
}
    
  
    
    
    
    
    