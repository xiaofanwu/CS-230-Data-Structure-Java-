/* GSAddSchoolPanel.java
 * CS230 Assignment5 Task3
 * Written by: Shiping Xu & Xiaofan Wu
 * 10/25/2015
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;


public class GSAddSchoolPanel extends JPanel{
  // instance vars
  private GradSchools schoolList;
  private JPanel promptPanel, chooseSchoolPanel, schoolListPanel;
  private JButton addSchoolButton;
  private JLabel promptLabel, infoLabel, schoolLabel, academicsLabel, researchLabel, pubsLabel,scannerLabel;
  private JTextField schoolName,scannerFile;
  private JComboBox academicsCombo, researchCombo, pubsCombo;
   
  // constructor
  public GSAddSchoolPanel(GradSchools gs) {
    this.schoolList = gs;
    // set the layout and color of the panel
    setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
    setBackground(Color.yellow);
    
    // prompt panel, prompts user what to do
    promptPanel = new JPanel();
    promptPanel.setPreferredSize(new Dimension(300,40));
    promptPanel.setBackground(Color.blue);
    promptLabel = new JLabel("Fill in the information to add a school, then click Add School");
    promptLabel.setFont(new Font("Serif", Font.ITALIC, 22));
    promptLabel.setForeground(Color.white);
    promptPanel.add(promptLabel);
    
    // put in school's name and academic, research and pubs scores
    chooseSchoolPanel = new JPanel();
    chooseSchoolPanel.setPreferredSize(new Dimension(300,40));
    chooseSchoolPanel.setBackground(Color.green);
  
    // combo boxes
    String[] scores = {"...", "1", "2", "3", "4", "5"};
    academicsCombo = new JComboBox(scores);
    researchCombo = new JComboBox(scores);
    pubsCombo = new JComboBox(scores);
    
    // labels
    schoolLabel = new JLabel("School: ");
    academicsLabel = new JLabel("Academics: ");
    researchLabel = new JLabel("Research: ");
    pubsLabel = new JLabel("Pubs: ");
    scannerLabel=new JLabel("Or, please enter the file name that you want to input:"); //promps the user to enter a file name
    
    // text field: School name and file
    schoolName = new JTextField(5);
    scannerFile=new JTextField(10);
    
    // add labels and combo boxes to the choose school panel
    chooseSchoolPanel.add(schoolLabel);
    chooseSchoolPanel.add(schoolName);
    chooseSchoolPanel.add(academicsLabel);
    chooseSchoolPanel.add(academicsCombo);
    chooseSchoolPanel.add(researchLabel);
    chooseSchoolPanel.add(researchCombo);
    chooseSchoolPanel.add(pubsLabel);
    chooseSchoolPanel.add(pubsCombo);
    chooseSchoolPanel.add(scannerLabel);
    chooseSchoolPanel.add(scannerFile);
    
    // create and add the "add school" button to the panel
    addSchoolButton = new JButton("Add School");
    chooseSchoolPanel.add(addSchoolButton);
    
    
    // add in school's information into this panel
    schoolListPanel = new JPanel();
    schoolListPanel.setPreferredSize(new Dimension(300,200));
    schoolListPanel.setBackground(Color.white);
    infoLabel = new JLabel("Information on the new school will appear here.");
    infoLabel.setFont(new Font("Arial", Font.ITALIC, 16));
    infoLabel.setForeground(Color.magenta);
    schoolListPanel.add(infoLabel);

    // add three panels into the big panel
    add(promptPanel);
    add(Box.createRigidArea(new Dimension(0,10)));
    add(chooseSchoolPanel);
    add(Box.createRigidArea(new Dimension(0,10)));
    add(schoolListPanel);
    
    // action listners
    addSchoolButton.addActionListener(new ButtonListener());
  }
    
 // ButtonListener is a private class for responding to button push events
private class ButtonListener implements ActionListener {    
    public void actionPerformed (ActionEvent event) {
      //get all inputs that the user enters, some might be empty, since user will not enter all the information
      String name = (schoolName.getText()).toUpperCase();   
      int acds = academicsCombo.getSelectedIndex();
      int res = researchCombo.getSelectedIndex();
      int pubs = pubsCombo.getSelectedIndex();
      String inputFile=scannerFile.getText();
      //create a boolean to check if the user enters an invalid text file name
      boolean exception=false;
      
      // if either the file input or the manual add input has some value, want to either add the manual input school
      //to the schoolList or the add each file line input to the schoolList
      if ((!name.equals("") && acds!=0 && res!=0 && pubs!=0) || (!inputFile.equals(""))){
        
        if (!name.equals("") && acds!=0 && res!=0 && pubs!=0)  {
          schoolList.addSchool(name, acds, res, pubs);
        }//end of checking if the user manually input each school
        
        else if (!inputFile.equals("")) {
          try {
            Scanner fileScan=new Scanner(new File(inputFile));
            //for each line in the file, get the name and scores of the schools. Each line represent one school and its scores
            while (fileScan.hasNextLine()){             
              String schoolName=fileScan.nextLine();              
              String[] eachLine=schoolName.split(" ");           
              schoolList.addSchool((eachLine[0]).toUpperCase(), Integer.parseInt(eachLine[1]), 
                                   Integer.parseInt(eachLine[2]), Integer.parseInt(eachLine[3]));
            }//end of while loop of file scan
            fileScan.close();
          }catch (IOException ex) { //set the exception boolean true if there is a file is invalid
            exception=true;     
          } catch (NumberFormatException ex) {  // set exceptio boolean to true if the file has been entered
            exception=true;
          }
        }//end of checking if user entered a vallid file name, or if the numbers are invalid
   
        //if there is an exception (aka file input name is not right), let the user know.
      if (exception)
          infoLabel.setText("Please enter a valid file name or make sure that the file is correctly formatted");
        
        //if there is no file exception, we then set the info text to the 10 most recently added schools
        else {   
          School[] gradSchoolArray = schoolList.getSchoolArray();
          String s = "<html><CENTER><TABLE BORDER=2 CELLSPACING=2 CELLPADDING=2><TR><TD>  School Name  </TD>" +
            "<TD>  Academics  </TD> <TD>  Research  </TD><TD>  Publications  </TD><TD>  Overall  </TD><TD>  Current Rank  </TD></TR>";
          int size = schoolList.getSize();         
          int endingIndex = 0; // set the ending index to be 0 for cases when there are less than 10 schools in the list
          
          if (size>10) { // if the size is greater than 10
            endingIndex = size-10; // set the ending Index to be size-10
          }
          for (int i=(size-1); i>=endingIndex; i--) 
          s += ("<TR><TD>" + gradSchoolArray[i].getName() + "</TD>" +
                  "<TD>" + gradSchoolArray[i].getAcademics() + "</TD> <TD>" + gradSchoolArray[i].getResearch() + "</TD><TD>" + 
                  gradSchoolArray[i].getPublications() + "</TD><TD>" + gradSchoolArray[i].getRating() + "</TD><TD>" + 
                  gradSchoolArray[i].getRank() + "</TD></TR>");

          infoLabel.setText(s +" </TABLE> </CENTER>");
        }//end of else statement after making sure that the ioexception is not thrown and then will print out the table of schools.
      } //end of checking if the user inputed any values      
      else {
        // prompts the user a message if the user doesn't enter the 3 evaluation scores
        infoLabel.setText("<html><b><font color='red'>Please enter a school name with 3 valid<br>" +
                          "evaluation scores for Academics, research and publications.</font></b></html>");        
      }  
  }//end of method
  
}//end of private class ButtonListener
}//end of entire class


