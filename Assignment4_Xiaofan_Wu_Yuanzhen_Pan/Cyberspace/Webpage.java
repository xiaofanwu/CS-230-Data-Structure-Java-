/*Xiaofan Wu and Yuanzhen Pan
 * Assignment 4:Webpage.java
 * date:10/08/2015
*/
import java.util.*;
import java.net.*;
import java.io.*;

//create a class that implements comparable and the tpye is webpage that we are comparing.
public class Webpage implements Comparable<Webpage>{
  //declare relevant information that Webpage takes in as parameters
  private String url;
  private int lineCount;
  private String content;
  
  public Webpage(String url,int lineCount,String content){
    //take in relevant parameters
    this.url=url;
    this.lineCount=lineCount;
    this.content=content;
  }
//prints out the url, the line count, and the first 20 characters of the content with tabs  
  public String toString(){
    //check if the content is greater than 20, if it is not, return all the content that is available.
    if (content.length()>20){
      //the tab does not work for shorter urls, but we checked in with the helproom tutors and they said it is ok.
    return url +"\t"+ ":"+lineCount +"\t" +content.substring(0,20);
    }
    else{
          return url +"\t"+ ":"+lineCount +"\t" +content;
    }
  }
  
  //create a new compareTo method that compare the line count, so that we can use it in CyberSpace
  public int compareTo(Webpage webpage){
    //if the two line counts are equal, return 0, if the oject itself is greater than the webpage taken in return 1
    //otherwise return -1.
    return (this.getlineCount()==webpage.getlineCount())? 0:(this.getlineCount()>webpage.getlineCount()? 1:-1);
  
    }
    
  
  // create getters to get all the variables 
  public String geturl(){
    return url;
  }
  public int getlineCount(){
    return lineCount;
  }
  public String getContent(){
    return content;
  }
  
  public static void main(String[] args){
    //case 1: if the length of the content is exactly 20
    
   Webpage variable1=new Webpage("http://www.google.com",1,"12345678912345678912");
 
   //case 2: if the length of the content is exactly 20, should print out all the available content
   
   Webpage variable2=new Webpage("http://www.lol.com",2,"123456789123456789");
   
   //case 3: if the length of the content is greater than 20, should print only the first 20 
   
  Webpage variable3=new Webpage("http://www.netflix.com",3,"12345678911234567891SHOULDNOTPRINT");
  //case 4: Have some webpage that its content is the same as another variable
 Webpage variable4=new Webpage("http://www.love.com",3,"12345678911234567891SHOULDNOTPRINT");
      
   System.out.println(variable1);
   System.out.println(variable2);
   System.out.println(variable3);
   System.out.println(variable4);
   //Need to check all cases, -1,1,and 0
   //should return -1, because (1 >2) is not true.
   System.out.println("should return -1: "+variable1.compareTo(variable2));
   //Should return 1, because 3>2 is true.
   System.out.println("should return 1: "+variable3.compareTo(variable2));
   //Should return 0 because 2=2 is true.
   System.out.println("should return 0: "+variable2.compareTo(variable2));
   
  }
}
  
  

  
