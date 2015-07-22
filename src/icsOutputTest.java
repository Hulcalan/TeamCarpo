/**
 * purpose of the program is to test various features 
 * and methods of the icsOutput program for correct
 * output and behavior
 * 
 * @author William Alan Ulch
 * @author Stephani Diep
 * @author Michael Moorefield
 * 
 * @Assignment ICS 314 Team Carpo Project (Ical)
 *  @Date:  7/11/2015
 *
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class icsOutputTest {
	
	static eventNode testNode = new eventNode((long)2015071808, (long)2015071908, 
			"Pacific/Honolulu", "PRIVATE", "21.2971940;-157.8151930", "UH Manoa", "Description of event", "Event Title", null);
	static eventNode testNode2 = new eventNode((long)2015081808, (long)2015081908, 
			"Pacific/Honolulu", "PRIVATE", "21.2971940;-157.8151930", "UH Manoa", "Description of event", "Event Title", null);
	static LinkedList testLL = new LinkedList();
	
	// The following tests specifically test the eventNode class
	
	//Tests the method to retrieve the start date from an event node
	@Test
	public void test1() {
		assertEquals(testNode.getStart(), (long)2015071808);
	}
	
	//Tests the method to retrieve the end date from an event node
	@Test
	public void test2() {
		assertEquals(testNode.getEnd(), (long)2015071908);
	}
	
	//Tests the method to retrieve the geo location coordinates from an event node
	@Test
	public void test3() {
		assertEquals(testNode.getLoc(), "21.2971940;-157.8151930");
	}
	
	//Tests the method to retrieve the description from an event node
	@Test
	public void test4() {
		assertEquals(testNode.getDescrip(), "Description of event");
	}
	
	//Tests the method to retrieve the classification (public, private etc) from an event node
	@Test
	public void test5() {
		assertEquals(testNode.getClasf(), "PRIVATE");
	}
	
	//Tests the method to retrieve the reference to the next event from an event node
	@Test
	public void test6() {
		assertEquals(testNode.getNext(), null);
	}
	
	//Tests the method to set the value of the pointer to the next event
	@Test
	public void test7() {
		assertEquals(testNode.getNext(), null);
		testNode.setNext(testNode2);
		assertEquals(testNode2, testNode.getNext());
	}
	
	//Tests the method to modify the description of an event 
	@Test
	public void test8() {
		assertEquals(testNode.getDescrip(), "Description of event");
		testNode.setDes("Changed description");
		assertEquals(testNode.getDescrip(), "Changed description");
	}
	
	//Tests the toString method of event node and checks it against the expected format
	@Test
	public void test9() {
		assertEquals(testNode.toString(), "BEGIN:VEVENT\n" 
				+ "DTSTART;Pacific/Honolulu20150718T08\n"
				+ "DTEND;Pacific/Honolulu20150719T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Event Title\nDESCRIPTION:Changed description\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:UH Manoa\nEND:VEVENT"); 
	}
	//End testing of eventNode class
	
	
	
	
	//The following test the linkedList class
	
	//Tests the method for adding a new item to the linked list
	@Test
	public void test10() {
		//assertEquals(testLL.toString(), null);
		testLL.add((long)2015071808, (long)2015071908, 
				"Pacific/Honolulu", "PRIVATE", "21.2971940;-157.8151930",
				"UH Manoa", "Description of event", "Event Title");
		assertEquals(testLL.toString(), "BEGIN:VCALENDAR\n" + 
				"CALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\nBEGIN:VEVENT\n" 
				+ "DTSTART;Pacific/Honolulu20150718T08\n"
				+ "DTEND;Pacific/Honolulu20150719T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Event Title\nDESCRIPTION:Description of event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:UH Manoa\nEND:VEVENT\nEND:VCALENDAR");
	}
	
	//Tests just the toString method of the linked list class
	@Test
	public void test11(){
		assertEquals(testLL.toString(),"BEGIN:VCALENDAR\n" + 
				"CALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\nBEGIN:VEVENT\n" 
				+ "DTSTART;Pacific/Honolulu20150718T08\n"
				+ "DTEND;Pacific/Honolulu20150719T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Event Title\nDESCRIPTION:Description of event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:UH Manoa\nEND:VEVENT\nEND:VCALENDAR");
	}
	
	//Tests the ability of the linked list to accept a second event
	@Test
	public void test12() {
		testLL.add((long)2015081808, (long)2015081908, 
				"Pacific/Honolulu", "PRIVATE", "21.2971940;-157.8151930",
				"Kaleo", "Second event", "Two");
		assertEquals(testLL.toString(), "BEGIN:VCALENDAR\n" + 
				"CALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\nBEGIN:VEVENT\n" 
				+ "DTSTART;Pacific/Honolulu20150718T08\n"
				+ "DTEND;Pacific/Honolulu20150719T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Event Title\nDESCRIPTION:Description of event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:UH Manoa\nEND:VEVENT\nBEGIN:VEVENT\n"  
				+ "DTSTART;Pacific/Honolulu20150818T08\n"
				+ "DTEND;Pacific/Honolulu20150819T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Two\nDESCRIPTION:Second event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:Kaleo\nEND:VEVENT\nEND:VCALENDAR");
	}
	
	//Tests the add methods ability to sort events by date
	//A third event is added which occurs before the other events
	@Test
	public void test13() {
		testLL.add((long)2015061808, (long)2015061908, 
				"Pacific/Honolulu", "PRIVATE", "21.2971940;-157.8151930",
				"POST", "Third event", "Three");
		assertEquals(testLL.toString(), "BEGIN:VCALENDAR\n" + 
				"CALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\nBEGIN:VEVENT\n"  
				+ "DTSTART;Pacific/Honolulu20150618T08\n"
				+ "DTEND;Pacific/Honolulu20150619T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Three\nDESCRIPTION:Third event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:POST\nEND:VEVENT\nBEGIN:VEVENT\n" 
				+ "DTSTART;Pacific/Honolulu20150718T08\n"
				+ "DTEND;Pacific/Honolulu20150719T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Event Title\nDESCRIPTION:Description of event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:UH Manoa\nEND:VEVENT\nBEGIN:VEVENT\n"  
				+ "DTSTART;Pacific/Honolulu20150818T08\n"
				+ "DTEND;Pacific/Honolulu20150819T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Two\nDESCRIPTION:Second event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:Kaleo\nEND:VEVENT\nEND:VCALENDAR");
	}
	
	//Tests the method geocalc which will return the distance between the two coordinates entered
	@Test
	public void test14(){
		double temp = testLL.geoCalc("21.2971940;-157.8151930", "21.297410;-157.816244");
		assertEquals(temp, 0.06930161981156768, 0);
	}
	
	//Tests the ability to remove an even from the linked list based on its start date/time
	@Test
	public void test15(){
		testLL.remove((long)2015061808);
		assertEquals(testLL.toString(), "BEGIN:VCALENDAR\n" + 
				"CALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\nBEGIN:VEVENT\n" 
				+ "DTSTART;Pacific/Honolulu20150718T08\n"
				+ "DTEND;Pacific/Honolulu20150719T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Event Title\nDESCRIPTION:Description of event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:UH Manoa\nEND:VEVENT\nBEGIN:VEVENT\n"  
				+ "DTSTART;Pacific/Honolulu20150818T08\n"
				+ "DTEND;Pacific/Honolulu20150819T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Two\nDESCRIPTION:Second event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:Kaleo\nEND:VEVENT\nEND:VCALENDAR");
	}
	
	//Tests the method to retrieve a specific even based on start date/time
	//Also tests to ensure the linked list is not changed by this retrieve
	@Test
	public void test16(){
		eventNode temp = testLL.get((long)2015081808);
		String tempS = temp.toString();
		assertEquals(tempS, "BEGIN:VEVENT\n"  
				+ "DTSTART;Pacific/Honolulu20150818T08\n"
				+ "DTEND;Pacific/Honolulu20150819T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Two\nDESCRIPTION:Second event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:Kaleo\nEND:VEVENT");
		assertEquals(testLL.toString(), "BEGIN:VCALENDAR\n" + 
				"CALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\nBEGIN:VEVENT\n" 
				+ "DTSTART;Pacific/Honolulu20150718T08\n"
				+ "DTEND;Pacific/Honolulu20150719T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Event Title\nDESCRIPTION:Description of event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:UH Manoa\nEND:VEVENT\nBEGIN:VEVENT\n"  
				+ "DTSTART;Pacific/Honolulu20150818T08\n"
				+ "DTEND;Pacific/Honolulu20150819T08\nCLASS:PRIVATE\n"
				+ "SUMMARY:Two\nDESCRIPTION:Second event\nGEO:21.2971940;-157.8151930\n"
				+ "LOCATION:Kaleo\nEND:VEVENT\nEND:VCALENDAR");	
	}

	//end testing of linkedList class
}