/* GradSchoolsGUI.java
 * CS230 Assignment 5 Task3
 * Written by: Shiping Xu & Xiaofan Wu
 * 10/25/2015
 */

import javax.swing.*;

public class GradSchoolsGUI {
  // main method
  public static void main (String[] args) {
  // creates and shows a Frame
  JFrame frame = new JFrame("Grad Schools");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  //create an instance of the GradSchools called schoolList
  GradSchools schoolList = new GradSchools();
    
  //create a tabbed pane
  JTabbedPane tp = new JTabbedPane();
  // create three panels
  tp.addTab ("About", new GSAboutPanel());
  tp.addTab ("Add School", new GSAddSchoolPanel(schoolList));
  tp.addTab ("Evaluate", new GSEvaluatePanel(schoolList));
  
  frame.getContentPane().add(tp); // add it to the frame
    
  frame.pack();
  frame.setVisible(true); //causes the frame to be displayed on the screen
  }
}
