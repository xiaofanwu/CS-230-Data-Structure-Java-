/* Assignment 8
 * MyGenealogy.java
 * Task 2
 * Xiaofan Wu, Yuanzhen Pan
 */
//driver class that uses GenealogyTree
public class MyGenealogy{
  
  public static void main(String[]args){
    //test cases that are the same as the gien test cases.
    GenealogyTree<String> a = new GenealogyTree<String>("T");

    a.setPater("T","Y");
    a.setMater("T","E");
    a.setPater("Y","P");
    a.setMater("Y","M");
    a.setPater("E","B");
    a.setMater("E","D");
    a.setPater("P","A");
    a.setMater("P","C");
    a.setPater("M","F");
    a.setMater("M","G");
    a.setPater("B","H");
    a.setMater("B","I");
    a.setPater("D","J");
    a.setMater("D","K");
    
    System.out.println(a);
    
    System.out.println("T was found: true " + a.appears("T"));
    System.out.println("Offspring of Y is: T "+ a.getOffspring("Y"));
    System.out.println("You appear at the root T " + a.getCoTU());
    
    
    System.out.println("E was found: true"+ a.appears("E"));
    System.out.println( "Your paternal grandfather is: P "+ a.getPater(a.getPater("T")));
    System.out.println( "Your maternal grandmother is: D "+ a.getMater(a.getMater("T")));
    System.out.println( "Your paternal great-grandfather is: A "+ a.getPater(a.getPater(a.getPater("T"))));
    System.out.println( "Your paternal great-grandmother is: K "+ a.getMater(a.getMater(a.getMater("T"))));
    System.out.println("P and B are in-laws: true " +a.inLaws("P","B"));
    System.out.println("P and E are NOT in-laws: false " +a.inLaws("P","E"));
    
    //create another family tree
    GenealogyTree<String> xf=new GenealogyTree<String>("xiaofan");
    xf.setPater("xiaofan","chunlin");
    xf.setMater("xiaofan","wangyan");
    xf.setMater("chunlin","yaqing");
    xf.setPater("chunlin","yeye");
    xf.setPater("wangyan","laoying");
    xf.setMater("wangyan","laolao");
    
    System.out.println(xf);
    System.out.println("size should be 7: " + xf.size());
    System.out.println("should be null: " + xf.getOffspring("xiaofan"));
    
    //checked all the exceptions one by one
//    System.out.println("should throw exception: " + xf.getOffspring("dfd"));
//    System.out.println("should throw exception: " + xf.getPater("dsfd"));
//    System.out.println("should throw exception: " + xf.getMater("qew"));
//                       
//    System.out.println("should throw exception");           
//    xf.setPater("ds","burens");     
//    xf.setMater("ds","burens");                                 
    
  }
}