   
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
      LinkedList<Integer, Integer> event = new LinkedList<Integer, Integer>();
      String number1 = JOptionPane.showInputDialog("Please enter a start Date");
      String number2 = JOptionPane.showInputDialog("Please enter a end Date");
      event.add(Integer.parseInt(number1),Integer.parseInt(number2));
      number1 = JOptionPane.showInputDialog("Please enter another start Date");
      number2 = JOptionPane.showInputDialog("Please enter another end Date");
      event.add(Integer.parseInt(number1),Integer.parseInt(number2));
      JOptionPane.showMessageDialog(null,".ics file would like this\n" + event.toString());
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

   private eventNode<Integer, Integer> head = null;
   protected Integer size = new Integer(0);

   public LinkedList(){
   }
   public void add(Integer start, Integer end){
   
   if (head == null)
   {//if there is nothing in the list
   head = new eventNode<Integer, Integer>(start, end, null);
   }
   else 
   {//starts the add at the next node
   eventNode<Integer, Integer> previous = head;
   eventNode<Integer, Integer> current = head.getNext();
   
   while(current != null)
   {//if it's not at the end of the list it goes to the next object
   previous = current;
   current = current.getNext();
   }
   //adds to the end of the list
   eventNode<Integer, Integer> event = new eventNode<Integer, Integer>(start, end, null);
   previous.setNext(event);
   
   } 
   size++;  
   }//end of add
   
   /*
   * used to display the event stored in the list
   *
   *  @return a string "output" for the event
   */
   public String toString()
   {
   String output = new String("");
   eventNode<Integer, Integer> current = head;
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
   public eventNode<Integer, Integer> get(Integer start) throws ListException
   {
   if (head == null)
   {
   throw new ListException("Cannot get an item from an emtpy list!");
   }
   //find node
   //counter tracks the #of loops
   Integer counter = new Integer(1);
   eventNode<Integer, Integer> current = head;
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
   public void remove(Integer start) throws ListException
   {
   if (head == null)
   {//check if empty list
   throw new ListException("cannot remove from an empty list!");
   }
   //if at begining of list
   eventNode<Integer, Integer> current = head;
   if(current.getStart().equals(start))
   {//remove 1st node
   head = head.getNext();
   }
   else
   {//if not at the begining
   eventNode<Integer, Integer> previous = head;
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
