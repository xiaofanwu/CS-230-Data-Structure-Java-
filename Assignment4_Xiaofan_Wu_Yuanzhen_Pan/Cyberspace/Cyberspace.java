/*Xiaofan Wu and Yuanzhen Pan
 * Assignment 4:Cyberspace.java
 * date:10/08/2015
*/
import java.io.*;
import java.util.Scanner;
import java.net.URL;

//create a Cyberspace class and initial the variables
public class Cyberspace{
  Webpage[] collection;
  int countOfWeb;
  
  public Cyberspace(){
    //purposely set this as 2 to test for increase size of array need to test increase array test case.
    collection=new Webpage[2];
    countOfWeb=0;
  }
  
//Create a method that takes in urls from the keybaord
  public void readFromKey(){
    //create the scanner object outside of the do loop, so that we can use it repetively
    Scanner scan=new Scanner(System.in);
    System.out.print("http://");
    //ask the user for input once, then check if the user keep enter, 
    //if the user does, keep going. If the user press control d, stop ask
    //for input.
    do{
      String websiteUrl=scan.nextLine();
          System.out.print("http://");
      Webpage newweb=fetchContent("http://"+websiteUrl);
        
        addToArray(newweb);
      

    }
    while (scan.hasNextLine());

  }
//read a file with urls 
  public void readFromFile(String fileName){
    //need to use try and catch incase that the file does not exist. 
    try {
      Scanner fileScan=new Scanner(new File(fileName));
      while (fileScan.hasNextLine()){
        //scan each website name 
        String websiteUrl=fileScan.nextLine();
        //get the line count and content of the Url and put these information in an Webpage object
        Webpage newweb=fetchContent(websiteUrl);
        //Then add the Webpage object to the array. 
        addToArray(newweb);           
      }
      fileScan.close();
    }catch (IOException ex){
      System.out.println(ex);
    }
  }
  //double the size of the array if the array is full. 
  private void doubleSize(){
    //create a temporary array that is twice of the original webpage array and copy
    //everything from original array to the new temp array.
    Webpage[] temp=new Webpage[(collection.length)*2];
    for (int webpage=0;webpage<collection.length;webpage++){
      temp[webpage]=collection[webpage]; 
    }
    //make the original collection array's content to be the temp array's content.
    collection=temp;
  }
  
  
  //takes in Webpage object and add it into the array that is already sorted.
  private void addToArray(Webpage webpage){
  
    //if the current collection is an empty, we just add one Website object.
    if (countOfWeb==0){
      collection[0]=webpage;
      countOfWeb++; 
    }
    else{
      //act as a road block to make sure that the array is not full, if it not full, 
      //keep going. If it is full, increase space, then keep going. checking this.
      if (collection.length==countOfWeb){
        doubleSize();
      }
      //compare the last element to the input webpage
      //if the number of lines of webpage is greater than that of the last element 
      //add the webpage at the end of the array
      if (collection[countOfWeb-1].compareTo(webpage)<0){
        collection[countOfWeb]=webpage;
        countOfWeb++;  
      // if the input line count of Wepage object is less than each element in the array,
        //keep checking to the right. Once we found one spot 
        //that the input webpage line count is greater than, we save that index. 
      }else{
        int index=0;
        while (collection[index].compareTo(webpage)<0){
          index++;
        } 
        // Then move everything to the right of that index one spot to the right. 
        //Make sure we do it from the back, so that we don't replace
        //any numbers while moving.
        for (int i=countOfWeb;index<i;i--){
          collection[i]=collection[i-1]; 
        }
        //add the webpage to its appropriate spot. 
        collection[index]=webpage;
        countOfWeb++;
      }
             
    }
  }
//get the line count and content of the url and store the info in a new Webpage object.
  public Webpage fetchContent(String urlName){
    String content="";
    int count=0;
    try {
      URL u = new URL(urlName);
      Scanner urlScan = new Scanner( u.openStream() ); // will throw exception, so need to have a catch.
      //as long there is next line, keep adding the content. 
      while (urlScan.hasNext()) {
        content+=urlScan.nextLine();
        count++; 
      }
    } catch (IOException ex) {
      System.out.println(ex);
    }
    //store the information in a Webpage object and return that Webpage object.
    Webpage newweb=new Webpage(urlName,count,content); 
    return newweb;
  }

  //toString methods to print out everything, use the toString in 
  //webpage to print out each individual and use a for loop to loop through it.
  public String toString(){
    String result="";
    for (int i=0;i<countOfWeb;i++){
      result+=collection[i] + "\n";
    }
    return "results from visiting " + countOfWeb + " webpages \n" + result;
  }
  
  

  public static void main(String[] args){
    if (args.length==0){
      System.out.println("Please enter URLs (without spaces) below; end your list with ctrl-D:");
      //create a new Cyberspace arrawy and fill in each slot through readFromKey.
      Cyberspace newarray=new Cyberspace();
      newarray.readFromKey();
      System.out.println(newarray);
    }
    else{
     //create a new Cyberspace array and fill in each slot through readFromFile.
      Cyberspace newarray=new Cyberspace();
      newarray.readFromFile(args[0]);
      System.out.println(newarray);
    }   
  }
}
