package src;

   
/**
 * purpose of the program is to collect input for an Ical event
 * and output a .ics file
 * 
 * @author William Alan Ulch
 * @author Stehpani Diep
 * @author Michael Moorefield
 * 
 * @Assignment ICS 314 Team Carpo Project (Ical)
 *  @Date:  7/11/2015
 *
 */
import javax.swing.JOptionPane;
//import java.util.*;
import java.io.*;

public class icsOutput{
	
   public static void main(String[] args){
      LinkedList<Long, Long, String, String> event = new LinkedList<Long, Long, String, String>();
      String [] choices = {"add event", "output .ics file" , 
            "retrieve list" , "exit"};
      String loc, clasf;
      loc ="";
      clasf="";
      int choice = 0;
      while(choice != choices.length){
         choice = JOptionPane.showOptionDialog(null, // put in center of
               // screen
               "Select a Command", // message to user
               "ICal", // title of window
               JOptionPane.YES_NO_CANCEL_OPTION, // type of option
               JOptionPane.QUESTION_MESSAGE, // type of message
               null, // icon
               choices, // array of strings
               choices[choices.length - 1]); // default choice (last one)
         switch (choice) {
            case 0:
               event.add(input(1),input(0),inputText(clasf, 0),inputText(loc, 1));
               break;
            case 1: 
               JOptionPane.showMessageDialog(null,".ics file would like this\n" + event.toString());
               outputFile("teamcarpo", event);
               JOptionPane.showMessageDialog(null, "File: \"teamcarpo.ics\" outputted successfully!");
               break;
            case 2:
               JOptionPane.showMessageDialog(null,".ics file would like this\n" + event.toString());
               
               break;
            case 3:
               choice = choices.length;
               JOptionPane.showMessageDialog(null, "Exiting, have a nice day!");
               break;
            
            default:
                  //exits if red x is clicked
               choice = choices.length;
               JOptionPane.showMessageDialog(null, "Exiting, have a nice day!");
               break;
         }//end of switch
      }//end of while
   /*
   *method for the input for add event so our main isn't super cluddered
   */
      
   }
   public static Long input(Integer start)
   
   {//the menus the construct a start or end long type integer
      String input3 = "";
      String toggle = "";
      if (start == 1){
         toggle = "Please add a Start Date";
      }
      else{
         toggle = "Please add an End Date";
      }
      String[] time ={ 
         "01","02","03","04","05","06","07","08","09", 
         "10","11", "12", "13","14","15","16","17","18","19",
         "20","21","22","23","24","25","26","27","28","29",
         "30","31","32","33","34","35","36","37","38","39",
         "40","41","42","43","44","45","46","47","48","49",
         "50","51","52","53","54","55","56","57","58","59","60"};   
      
      
      //choose a year... should think about making the choices longer but meh
      String[] choices = { "2015", "2016", "2017", "2018", "2019", "2020" };
      String input = (String) JOptionPane.showInputDialog(null, "chose a year...",
         toggle , JOptionPane.QUESTION_MESSAGE, null, 
         choices, // Array of choices
         choices[0]); // Initial choice
    
     //choose a month... loops through time to put together the months
      String[] month = new String[12];
      for(int i=0; i < 12; i++){
         month[i] = time[i];
      }
      String input2 = (String) JOptionPane.showInputDialog(null, "Choose a month...",
         toggle, JOptionPane.QUESTION_MESSAGE, null, 
         month, // Array of choices
         month[0]); // Initial choice
      
      //31 day months
      if (input2 != "04" && input2 != "06" && input2 != "09" && input2 != "11" && input2 != "02" ){
         String[] day = new String[31];
         for(int i=0; i < 31; i++){
            day[i] = time[i];
         }
         input3 = (String) JOptionPane.showInputDialog(null, "Choose a day...",
            toggle, JOptionPane.QUESTION_MESSAGE, null, 
            day, // Array of choices
            day[0]); // Initial choice
      }
      
      //30 days months
      else if(input2 == "04" || input2 == "06" || input2 == "09" || input2 == "11"){
         String[] day = new String[30];
         for(int i=0; i < 30; i++){
            day[i] = time[i];
         }
         input3 = (String) JOptionPane.showInputDialog(null, "Choose a day...",
            toggle, JOptionPane.QUESTION_MESSAGE, null, 
            day, // Array of choices
            day[0]); // Initial choice
      }
       
      //leap year feb
      else if ((Integer.parseInt(input)%4 == 0) && input2 == "02"){
         String[] day = new String[29];
         for(int i=0; i < 29; i++){
            day[i] = time[i];
         }
         input3 = (String) JOptionPane.showInputDialog(null, "Choose a day...",
            toggle, JOptionPane.QUESTION_MESSAGE, null, 
            day, // Array of choices
            day[0]); // Initial choice
      }
      //no leap year february
      else {
         String[] day = new String[28];
         for(int i=0; i < 28; i++){
            day[i] = time[i];
         }
         input3 = (String) JOptionPane.showInputDialog(null, "Choose a day...",
            toggle, JOptionPane.QUESTION_MESSAGE, null, 
            day, // Array of choices
            day[0]); // Initial choice
      }
      //puts them all together
      if (start == 1){
         input = input+ input2+ input3 + "083500";
      }
      else{
         input = input+input2+input3+ "141000";
      }
      
      //makes it into a long and returns to main
      long inputNum = Long.parseLong(input);
      return inputNum;
   }
   public static String inputText(String here, Integer check){
      if(check == 1){//if location
         String[] choices = {" ","Uh Manoa"};
         here = (String) JOptionPane.showInputDialog(null, "chose a classification...",
                  "Please choose from the following" , JOptionPane.QUESTION_MESSAGE, null, 
                  choices, // Array of choices
                  choices[0]); // Initial choice
         if (here == "Uh Manoa"){
            here = "21.299816;-157.817579";
         }         
      }
      if(check == 0){//if classification  "PUBLIC" / "PRIVATE" / "CONFIDENTIAL"
         //choose a year... should think about making the choices longer but meh
         String[] choices = {" ","PUBLIC", "PRIVATE", "CONFIDENTIAL"};
         here = (String) JOptionPane.showInputDialog(null, "chose a classification...",
               "Please choose from the following" , JOptionPane.QUESTION_MESSAGE, null, 
               choices, // Array of choices
               choices[0]); // Initial choice
         
      }
      return here;
   }
   private static void outputFile(String filename,  LinkedList<Long, Long, String, String> event ){
      try{
         PrintWriter fileWriter = new PrintWriter(filename + ".ics");
         fileWriter.append(event.toString());
         fileWriter.flush();fileWriter.close();
      }
      catch(IOException e){
         System.out.println("put a file name there dummy");
      }
   }
   
}//end main

//creation of a single event
class eventNode<Start, End, Clasf, Loc>{
	
   protected Start start;
   protected End end;
   protected Clasf clasf;
   protected Loc loc;
   protected eventNode<Start, End, Clasf, Loc> next;
	
   public eventNode(Start start2, End end2, Clasf clasf2, Loc loc2, eventNode<Start, End, Clasf, Loc> next2){
      start = start2;
      end = end2;
      next = next2;
      clasf = clasf2;
      loc = loc2;
   	  
   }//end constructor
   public String toString(){
      //these add the necessary T and Zulu components to the date lines
      String startD = start.toString().substring(0,8)+ "T" + start.toString().substring(8,start.toString().length()) + "Z";
      String endD = end.toString().substring(0,8)+ "T" + end.toString().substring(8,start.toString().length()) + "Z";
      String classif = clasf.toString();
      String event = "BEGIN:VEVENT\n" + "DTSTART:" + startD + "\nDTEND:" + endD +  "\nCLASS:" + classif + "\nDESCRIPTION:"
                      + "\nGEO:" + loc.toString() + "\nEND:VEVENT";
      return event;
   }//end toString()
   
   public Start getStart(){
      return start;
   }
   
   public End getEnd(){
      return end;
   }
   public Loc getLoc(){
      return loc;
   }
   public Clasf getClasf(){
      return clasf;
   }
   
   public eventNode<Start, End, Clasf, Loc> getNext(){
      return next;
   }
   
   public void setNext(eventNode<Start, End, Clasf, Loc> next2){
      next = next2;
   }
}//end of class eventNode

class LinkedList<Start, End, Clasf, Loc>{

   private eventNode<Long, Long, String, String> head = null;
   protected Integer size = new Integer(0);

   public LinkedList(){
   }
   /* this adds a node to the list
   * also this sorts from descending order as you add to the list
   */
   public void add(Long start, Long end, String clasf, String loc){//starts add
      eventNode<Long, Long, String, String> event = new eventNode<Long, Long, String, String>(start, end, clasf, loc, null);
      if (event.getStart() < event.getEnd()){//checks if end is smaller than start
         if (head == null){//if there is nothing in the list
            head = new eventNode<Long, Long, String, String>(start, end, clasf, loc, null);
         }
         else 
         {//starts the add at the next node
            eventNode<Long, Long, String, String> current = head;
            eventNode<Long, Long, String, String> previous =  head.getNext();
            eventNode<Long, Long, String, String> temp = new eventNode<Long, Long, String, String>(null, null, null, null, null);
            eventNode<Long, Long, String, String> temp2 = new eventNode<Long, Long, String, String>(null, null, null, null, null);
            try{
               while(event != null)
               {//if it's at the head and compares puts best on top
                  if((head.getStart() > event.getStart()) && (current.getStart() != event.getStart())){
                     
                     temp = current;
                     temp2 = current.getNext();
                     current = event;
                     current.setNext(temp2);
                     head = current;
                     previous = head;
                     event = temp;
                     event.setNext(null);
                     
                  }
                  //compares and puts the least before the greatest
                  else if((current.getStart() > event.getStart()) && (current.getStart() != event.getStart()) && (current.getStart() != head.getStart())){
                    
                     temp = current;
                     temp2 = current.getNext();
                     current = event;
                     current.setNext(temp2);
                     previous.setNext(current);
                     
                     event = temp;
                     event.setNext(null);
                  }
                  //if the current is smaller than the addition moves to the next one
                  else if( current.getStart() < event.getStart() && (current.getNext() != null))
                  {
                  
                     current = current.getNext();
                  
                  
                  }
                  
                  //adds to the end of the list
                  else{
                     current.setNext(event);
                     event = null;
                  }
               } 
            }
            catch(NullPointerException e){
               previous.setNext(event);
               event = null;
               System.out.println(e);
            
            }
            size++;  
         }
      }
      else
      {
         JOptionPane.showMessageDialog(null,"Your End date must be later than your Start date");
      }
   }//end of add
   
   /*
   * used to display the event stored in the list
   *
   *  @return a string "output" for the event
   */
   public String toString()
   {
      String output = new String("");
      eventNode<Long, Long, String, String> current = head;
      output = "BEGIN:VCALENDAR\nCALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\nX-WR-TIMEZONE:Pacific/Honolulu\n"; 
    		 
      while(current != null)
      {
         output = output + current.toString() + "\n";
         current = current.getNext();
      }
      output = output + "END:VCALENDAR";
      return output;
   }//end of toString
   
   /** gets an event based on teh date
   * we can probably change this to something else mebbe month?
   *
   *
   */
   public eventNode<Long, Long, String, String> get(Long start) throws ListException
   {
      if (head == null)
      {
         throw new ListException("Cannot get an item from an emtpy list!");
      }
   //find node
   //counter tracks the #of loops
      Integer counter = new Integer(1);
      eventNode<Long, Long, String, String> current = head;
      while(!current.getStart().equals(start))
      {
      ///goes to the next node
         current = current.getNext();
         counter++;
      }
      return current;
   }//end of get
   
   /* removes an event from the list
   *@param start is teh start date of the node
   * @excetion itemexception if a node doesnt exist with that date
   */
   public void remove(Long start) throws ListException
   {
      if (head == null)
      {//check if empty list
         throw new ListException("cannot remove from an empty list!");
      }
   //if at begining of list
      eventNode<Long, Long, String, String> current = head;
      if(current.getStart().equals(start))
      {//remove 1st node
         head = head.getNext();
      }
      else
      {//if not at the begining
         eventNode<Long, Long, String, String> previous = head;
         while(!current.getStart().equals(start))
         {//goes to the next node until foun
            previous = current;
            current = current.getNext();
         }
      //skips thus removing it from the list
         previous.setNext(current.getNext());
      }
      size--;
   }//end of remove
}//end of linkedlist class
/**
 * Used for all List exceptions
 */
class ListException extends RuntimeException {
	/**
	 * @param message describes the exact cause of the error.
	 */
   public ListException(String message) {
      super(message);
   }// end of constructor
}// end of class
