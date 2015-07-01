   
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
      
      String number1 = JOptionPane.showInputDialog("Please enter a start Date");
      String number2 = JOptionPane.showInputDialog("Please enter a end Date");
      event.add(Long.parseLong(number1),Long.parseLong(number2));
      number1 = JOptionPane.showInputDialog("Please enter another start Date");
      number2 = JOptionPane.showInputDialog("Please enter another end Date");
      event.add(Long.parseLong(number1),Long.parseLong(number2));
      number1 = JOptionPane.showInputDialog("Please enter another start Date");
      number2 = JOptionPane.showInputDialog("Please enter another end Date");
      event.add(Long.parseLong(number1),Long.parseLong(number2));
      number1 = JOptionPane.showInputDialog("Please enter another start Date");
      number2 = JOptionPane.showInputDialog("Please enter another end Date");
      event.add(Long.parseLong(number1),Long.parseLong(number2));
      number1 = JOptionPane.showInputDialog("Please enter another start Date");
      number2 = JOptionPane.showInputDialog("Please enter another end Date");
      event.add(Long.parseLong(number1),Long.parseLong(number2));
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

   private eventNode<Long, Long> head = null;
   protected Integer size = new Integer(0);

   public LinkedList(){
   }
   /* this adds a node to the list
   * also this sorts from descending order as you add to the list
   */
   public void add(Long start, Long end){
   
   if (head == null)
   {//if there is nothing in the list
   head = new eventNode<Long, Long>(start, end, null);
   }
   else 
   {//starts the add at the next node
   eventNode<Long, Long> current = head;
   System.out.println("number: " + current.getStart() + '\n'+ current.toString()+'\n');
   eventNode<Long, Long> previous =  head.getNext();
   eventNode<Long, Long> event = new eventNode<Long, Long>(start, end, null);
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
   }catch(NullPointerException e){
   previous.setNext(event);
   event = null;
   System.out.println(e);
   
  }
   size++;  
   }//end of add
   }
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
