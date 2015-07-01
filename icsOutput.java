   
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
import java.util.*;
import java.io.*;

public class icsOutput{
	
   public static void main(String[] args){
      LinkedList<Long, Long> event = new LinkedList<Long, Long>();
      String [] choices = {"add event", "output .ics file" , 
            "retrieve event" , "exit"};
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
               event.add(input(1),input(0));
               break;
            case 1: 
               JOptionPane.showMessageDialog(null,".ics file would like this\n" + event.toString());
               break;
            case 2:
               JOptionPane.showMessageDialog(null,"doesn't work yet comeback later\n"); 
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
      input = input+input2+input3;
      //makes it into a long and returns to main
      long inputNum = Long.parseLong(input);
      return inputNum;
   }
}//end main

//creation of a single event
class eventNode<Start, End>{
	
   protected Start start;
   protected End end;
   protected eventNode<Start, End> next;
	
   public eventNode(Start start2, End end2, eventNode<Start, End> next2){
      start = start2;
      end = end2;
      next = next2;
   	
   }//end constructor
   public String toString(){
      String event = "DTSTART:" + start.toString() + "\nDTEND:" + end.toString();
      return event;
   }//end toString()
   
   public Start getStart(){
      return start;
   }
   
   public End getEnd(){
      return end;
   }
   
   public eventNode<Start, End> getNext(){
      return next;
   }
   
   public void setNext(eventNode<Start, End> next2){
      next = next2;
   }
}//end of class eventNode

class LinkedList<Start, End>{

   private eventNode<Long, Long> head = null;
   protected Integer size = new Integer(0);

   public LinkedList(){
   }
   /* this adds a node to the list
   * also this sorts from descending order as you add to the list
   */
   public void add(Long start, Long end){//starts add
      eventNode<Long, Long> event = new eventNode<Long, Long>(start, end, null);
      if (event.getStart() < event.getEnd()){//checks if end is smaller than start
         if (head == null){//if there is nothing in the list
            head = new eventNode<Long, Long>(start, end, null);
         }
         else 
         {//starts the add at the next node
            eventNode<Long, Long> current = head;
            System.out.println("number: " + current.getStart() + '\n'+ current.toString()+'\n');
            eventNode<Long, Long> previous =  head.getNext();
         
         // System.out.println("number: " + event.getStart() + '\n'+event.toString()+'\n');
            eventNode<Long, Long> temp = new eventNode<Long, Long>(null, null, null);
            try{
               while(event != null)
               {//if it's at the head and compares puts best on top
                  if((current.getStart() < event.getStart()) && current == head){
                     temp = current;
                     current = event;
                     current.setNext(temp);
                     head = current;
                     System.out.println("number: " + current.getStart() +'\n'+ current.toString()+'\n');
                     previous = current;
                     current = current.getNext();
                     event = null;
                  }
                  else if(current.getStart() < event.getStart() && current != head && current != null){
                     System.out.println("it's greater but no tthe top");
                     temp = current;
                     previous.setNext(event);
                     current = event;
                     current.setNext(temp);
                     System.out.println("number: " + current.getStart() +'\n'+ current.toString()+'\n');
                     current = current.getNext();
                     event = null;
                  }
                  //adds to the end of the list
                  else{
                     previous = current;
                     current = previous.getNext();
                  
                  }
               } System.out.println("outside the loop" );
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
      eventNode<Long, Long> current = head;
      while(current != null)
      {
         output = output + current.toString() + "\n";
         current = current.getNext();
      }
      return output;
   }//end of toString
   
   /** gets an event based on teh date
   * we can probably change this to something else mebbe month?
   *
   *
   */
   public eventNode<Long, Long> get(Long start) throws ListException
   {
      if (head == null)
      {
         throw new ListException("Cannot get an item from an emtpy list!");
      }
   //find node
   //counter tracks the #of loops
      Integer counter = new Integer(1);
      eventNode<Long, Long> current = head;
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
      eventNode<Long, Long> current = head;
      if(current.getStart().equals(start))
      {//remove 1st node
         head = head.getNext();
      }
      else
      {//if not at the begining
         eventNode<Long, Long> previous = head;
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
