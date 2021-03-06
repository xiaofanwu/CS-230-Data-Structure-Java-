/* School.java
 * CS230 Assignment 3 Task 3-Subtask 2 
 * Written by: Shiping Xu & Xiaofan Wu
 * September 30th, 2015
 */

public class School {
  // instace variables
  private String name; 
  private int academics; // academic programs
  private int research; // effectiveness in educating research scholars
  private int publications; // impact of its faculty publications
  private int rating; // overall rating, weighted sum of 3
  private int rank; 
  
  // constructor 
  public School(String name, int academics, int research, int publications) {
    this.name = name;
    this.academics = academics;
    this.research = research;
    this.publications = publications;
    rating = 0;
    rank=0;
  }
  
 // toString() an instance of type school
  public String toString() {
    String s = ("<html>School: " + name + " Academics: " + academics +
                " Research: " + research + " Publications: " + publications + " Overall: " + 
                rating + " Current Rank: " +rank+"<br><br><html>");
    return s;
  }
  
//  public String toString() {
//    String s = "\n********\nSchool name: " + name; // displays school name
//    s += "\nAcademic program: " + academics; // display academics
//    s += "\nEffectiveness in educating research scholars: " + research; // displays research
//    s += "\nImpact of its faculty publications: " + publications; // displays publications
//    s += "\nOverall rating: " +rating; // displays rating
//    s += "\nCurrent rank: " + rank; // displays rank
//    return s;
//  }
  
  // getters
  public String getName() {
    return name;
  }

  
  public int getAcademics() {
    return academics;
  }
  
  public int getResearch() {
    return research;
  }
  
  public int getPublications() {
    return publications;
  }
  
  public int getRating() {
    return rating;
  }
  
  public int getRank() {
    return rank;
  }
  
  // setters
  public void setAcademics(int ad) {
    academics = ad;
  }
  
  public void setResearch(int rsch) {
    research = rsch;
  }
  
  public void setPublications(int pubs) {
    publications = pubs;
  }
  
  public void setRank(int rank) {
    this.rank = rank;
  }
  
  // computes and sets the appropriate overall rating 
  public int computeRating(int weight1, int weight2, int weight3) {    
    rating = weight1*academics+weight2*research+weight3*publications; 
    return rating;
  }
  
  
  // main method
  public static void main (String[] args) {
    School MIT = new School("MIT", 10, 10, 10); //creates a new School object
    MIT.computeRating(3,5,4); // compute MIT's overall rating
    System.out.println(MIT); // displays school's information
    
    School Wellesley = new School("Wellesley",9, 9, 9);  //creates a new School object
    Wellesley.computeRating(2,5,5); // compute Wellesley's overall rating
    System.out.println(Wellesley); // displays school's information    
  }
}
  
    