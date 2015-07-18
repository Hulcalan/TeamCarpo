


/**
 * purpose of the program is to collect input for an Ical event
 * and output a .ics file
 * 
 * @author William Alan Ulch
 * @author Stephani Diep
 * @author Michael Moorefield
 * 
 * @Assignment ICS 314 Team Carpo Project (Ical)
 *  @Date:  7/11/2015
 *
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.Math; 

public class icsOutput {

	public static void main(String[] args) {

		new gui();

	}
}

class gui {
	private static BufferedImage bg;

	public gui() {
		UIManager.put("OptionPane.background", Color.black);
		UIManager.put("Label.foreground", Color.white);
		UIManager.put("Panel.background", Color.black);
        UIManager.put("OptionPane.messageForeground", Color.white);
		LinkedList<Long, Long, String, String, String, String, String, String> event = new LinkedList<Long, Long, String, String, String, String, String, String>();
		String[] choices = { "add event", "output .ics file", "retrieve list",
				"exit" };

		int choice = 0;
		while (choice != choices.length) {

			choice = JOptionPane
					.showOptionDialog(null, // put in center of
							// screen
							"<html><font color = \"white\">Welcome to the event Creator, Select a Command</font></html>", // message
							"ICal event creator", // title of window
							JOptionPane.YES_NO_CANCEL_OPTION, // type of option
							JOptionPane.PLAIN_MESSAGE,
							// type of message
							null, // icon
							choices, // array of strings
							choices[choices.length - 1]); // default choice
															// (last one)
			switch (choice) {
			case 0:

				try {

					gui.input(event);

				} catch (NumberFormatException e) {
					JOptionPane
							.showConfirmDialog(
									null,
									"<html><font color = \"white\">\tYou did not enter a start/end date!</font>",
									"NOTICE!", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.PLAIN_MESSAGE, null);
				}
				break;
			case 1:
				try {
					JOptionPane.showConfirmDialog(null,
							"<html><font color = \"white\">.ics file would look like this\n"
									+ event.toString(), "NOTICE!",
							JOptionPane.PLAIN_MESSAGE,
							JOptionPane.PLAIN_MESSAGE, null);
					outputFile("teamcarpo", event);
					JOptionPane
							.showConfirmDialog(
									null,
									"<html><font color = \"white\">File: \"teamcarpo.ics\" outputted successfully!</font>",
									"NOTICE!", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.PLAIN_MESSAGE, null);

				} catch (NullPointerException e) {
					JOptionPane
							.showConfirmDialog(
									null,
									"<html><font color = \"white\">There is nothing to output, calender is empty</font>",
									"NOTICE!", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.PLAIN_MESSAGE, null);
				}
				break;
			case 2:
				try {
					JOptionPane.showConfirmDialog(null,
							"<html><font color = \"white\">.ics file would look like this\n"
									+ event.toString() , "NOTICE!",
							JOptionPane.PLAIN_MESSAGE,
							JOptionPane.PLAIN_MESSAGE, null);
				} catch (NullPointerException e) {
					JOptionPane
							.showConfirmDialog(
									null,
									"<html><font color = \"white\">There is nothing to output, calender is empty</font>",
									"NOTICE!", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.PLAIN_MESSAGE, null);
				}

				break;
			case 3:
				choice = choices.length;
				JOptionPane
						.showConfirmDialog(
								null,
								"<html><font color = \"white\">Exiting, have a nice day!</font>",
								"GoodBye!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.PLAIN_MESSAGE, null);
				break;

			default:
				// exits if red x is clicked
				choice = choices.length;
				break;
			}// end of switch
		}// end of while
	}

	/*
	 * method for the input for add event so our main isn't super cluddered
	 */

	public static void input(
			LinkedList<Long, Long, String, String, String, String, String, String> event)

	{// the menus the construct a start or end long type integer

		int check = 1;

		String timezone = "";
		String startIn = "";
		String endIn = "";
		String gloc = "";
		String classif = "";
		String location = "";
		String description = "";
		String summary = "";
		String secs = "00";

		String[] tzchoices = { "Eastern", "Central", "Mountain", "Pacific",
				"Alaska", "Hawaii" };
		String[] time = { "00", "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
				"39", "40", "41", "42", "43", "44", "45", "46", "47", "48",
				"49", "50", "51", "52", "53", "54", "55", "56", "57", "58",
				"59" };

		// choose a year... should think about making the choices longer but meh
		String[] years = { "2015", "2016", "2017", "2018", "2019", "2020" };

		// choose a month... loops through time to put together the months
		String[] month = new String[12];
		for (int i = 0; i < 12; i++) {
			month[i] = time[i + 1];
		}
         //day strings
		final String[] day31 = new String[31];
		for (int i = 0; i < 31; i++) {
			day31[i] = time[i + 1];
		}

		final String[] dayL = new String[29];
		for (int i = 0; i < 29; i++) {
			dayL[i] = time[i + 1];
		}

		final String[] daynL = new String[28];
		for (int i = 0; i < 28; i++) {
			daynL[i] = time[i + 1];
		}

		final String[] day30 = new String[30];
		for (int i = 0; i < 30; i++) {
			day30[i] = time[i + 1];
		}
		
        //strings for hours
		String[] hours = new String[24];
		for (int i = 0; i < 24; i++) {
			hours[i] = time[i];
		}
		// strings for locations
		String[] locations = { " ", "Uh Manoa", "Uh Bookstore", "Ka Leo", "POST"};
		// strings for classifications
		String[] classifc = { " ", "PUBLIC", "PRIVATE", "CONFIDENTIAL" };
		
		final DefaultComboBoxModel<String> treeone = new DefaultComboBoxModel<>(
				day31);
		final DefaultComboBoxModel<String> twonine = new DefaultComboBoxModel<>(
				dayL);
		final DefaultComboBoxModel<String> twoeight = new DefaultComboBoxModel<>(
				daynL);
		final DefaultComboBoxModel<String> thirty = new DefaultComboBoxModel<>(
				day30);
		final JComboBox<String> yearb = new JComboBox<>(years);
		final JComboBox<String> monthb = new JComboBox<>(month);
		final JComboBox<String> dayb = new JComboBox<>(day31);
		final JComboBox<String> tzbox = new JComboBox<>(tzchoices);
		final JComboBox<String> yearbe = new JComboBox<>(years);
		final JComboBox<String> monthbe = new JComboBox<>(month);
		final JComboBox<String> daybe = new JComboBox<>(day31);
		JComboBox<String> hoursb = new JComboBox<>(hours);
		JComboBox<String> minsb = new JComboBox<>(time);
		JComboBox<String> hoursbe = new JComboBox<>(hours);
		JComboBox<String> minsbe = new JComboBox<>(time);
		JComboBox<String> geob = new JComboBox<>(locations);
		JComboBox<String> classifb = new JComboBox<>(classifc);

		try {
			bg = ImageIO.read(gui.class.getResource("/src/test.jpg"));
		} catch (NullPointerException e) {
		} catch (IOException ex) {
		} catch (IllegalArgumentException e) {
		}

		JPanel startPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(560, 250);
			}
		};
		startPanel.setPreferredSize(new Dimension(560, 250));
		startPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel title = new JLabel("Enter an event title:");
		startPanel.add(title);
		title.setPreferredSize(new Dimension(110, 20));
		JTextField titlet = new JTextField("");
		titlet.setEditable(true);
		titlet.setPreferredSize(new Dimension(440, 20));
		startPanel.add(titlet);
		JLabel alan = new JLabel("Enter a start date:");
		startPanel.add(alan);

		JLabel year = new JLabel("Year:");
		startPanel.add(year);
		startPanel.add(yearb);
		yearb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (yearb.getSelectedItem() != "2015") {
					monthb.setSelectedIndex(0);
					dayb.setSelectedIndex(0);
				}
			}
		});
		JLabel mon = new JLabel("Month:");
		startPanel.add(mon);
		startPanel.add(monthb);
		JLabel day = new JLabel("Day:");
		startPanel.add(day);
		startPanel.add(dayb);
		monthb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Integer.parseInt(yearb.getSelectedItem().toString()) % 4 == 0)
						&& (monthb.getSelectedItem() == "02")) {
					dayb.setModel(twonine);
				}
				if (monthb.getSelectedItem() == "04"
						|| monthb.getSelectedItem() == "06"
						|| monthb.getSelectedItem() == "09"
						|| monthb.getSelectedItem() == "11") {
					dayb.setModel(thirty);
				}
				if ((Integer.parseInt(yearb.getSelectedItem().toString()) % 4 != 0)
						&& (monthb.getSelectedItem() == "02")) {
					dayb.setModel(twoeight);
				}
				if (monthb.getSelectedItem() == "01"
						|| monthb.getSelectedItem() == "03"
						|| monthb.getSelectedItem() == "05"
						|| monthb.getSelectedItem() == "07"
						|| monthb.getSelectedItem() == "08"
						|| monthb.getSelectedItem() == "12") {
				  dayb.setModel(treeone);
				}
			}
		});

		JLabel hour = new JLabel("Hours:");
		startPanel.add(hour);
		startPanel.add(hoursb);
		JLabel min = new JLabel("Mins:");
		startPanel.add(min);
		startPanel.add(minsb);

		// end part of the pane
		JLabel endl = new JLabel("Enter an end date:");
		startPanel.add(endl);
		endl.setOpaque(true);
		endl.setHorizontalAlignment(JLabel.CENTER);
		endl.setBackground(Color.black);
		year = new JLabel("Year:");
		startPanel.add(year);
		startPanel.add(yearbe);
		yearb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (yearbe.getSelectedItem() != "2015") {
					monthbe.setSelectedIndex(0);
					daybe.setSelectedIndex(0);
				}
			}
		});
		mon = new JLabel("Month:");
		startPanel.add(mon);
		startPanel.add(monthbe);
		day = new JLabel("Day:");
		startPanel.add(day);
		startPanel.add(daybe);
		monthbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Integer.parseInt(yearbe.getSelectedItem().toString()) % 4 == 0)
						&& (monthbe.getSelectedItem() == "02")) {
					daybe.setModel(twonine);
				}
				if (monthbe.getSelectedItem() == "04"
						|| monthbe.getSelectedItem() == "06"
						|| monthbe.getSelectedItem() == "09"
						|| monthbe.getSelectedItem() == "11") {
					daybe.setModel(thirty);
				}
				if ((Integer.parseInt(yearbe.getSelectedItem().toString()) % 4 != 0)
						&& (monthbe.getSelectedItem() == "02")) {
					daybe.setModel(twoeight);
				}
				if (monthbe.getSelectedItem() == "01"
						|| monthbe.getSelectedItem() == "03"
						|| monthbe.getSelectedItem() == "05"
						|| monthbe.getSelectedItem() == "07"
						|| monthbe.getSelectedItem() == "08"
						|| monthbe.getSelectedItem() == "12") {
					daybe.setModel(treeone);
				}
			}
		});

		hour = new JLabel("Hours:");
		startPanel.add(hour);
		startPanel.add(hoursbe);

		min = new JLabel("Mins:");
		startPanel.add(min);
		startPanel.add(minsbe);

		min = new JLabel("Timezone:");
		startPanel.add(min);
		startPanel.add(tzbox);
		min = new JLabel("Closest City:");
		startPanel.add(min);
		startPanel.add(geob);
		min = new JLabel("Classification:");
		startPanel.add(min);
		startPanel.add(classifb);
		JLabel descrips = new JLabel("<html><p>Description:</p> <p> "
				+ "Enter address or anything "
				+ "about your event here!</html><p>", SwingConstants.CENTER);
		startPanel.add(descrips);
		descrips.setPreferredSize(new Dimension(110, 100));

		JTextArea descrip = new JTextArea();
		DefaultCaret caret = (DefaultCaret) descrip.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		descrip.setEditable(true);
		descrip.setLineWrap(true);
		descrip.setWrapStyleWord(true);
		descrip.setCaretPosition(descrip.getDocument().getLength());
		descrip.setMargin(new Insets(5, 5, 5, 5));
		JScrollPane scrollPane = new JScrollPane(descrip);
		scrollPane.setPreferredSize(new Dimension(430, 100));
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.add(descrip);
		scrollPane.setViewportView(descrip);
		startPanel.add(scrollPane);
		while (check != 0) {
			int result = JOptionPane.showConfirmDialog(null, startPanel,
					"Event Creator", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			startIn = yearb.getSelectedItem().toString()
					+ monthb.getSelectedItem().toString()
					+ dayb.getSelectedItem().toString()
					+ hoursb.getSelectedItem().toString()
					+ minsb.getSelectedItem().toString() + secs;

			endIn = yearbe.getSelectedItem().toString()
					+ monthbe.getSelectedItem().toString()
					+ daybe.getSelectedItem().toString()
					+ hoursbe.getSelectedItem().toString()
					+ minsbe.getSelectedItem().toString() + secs;

			if (Long.parseLong(endIn) > Long.parseLong(startIn)
					&& result == JOptionPane.OK_OPTION) {

				if (tzbox.getSelectedItem() == "Eastern") {
					timezone = "TZID=America/New_York:";
				} else if (tzbox.getSelectedItem() == "Central") {
					timezone = "TZID=America/Chicago:";
				} else if (tzbox.getSelectedItem() == "Mountain") {
					timezone = "TZID=America/Denver:";
				} else if (tzbox.getSelectedItem() == "Pacific") {
					timezone = "TZID=America/Los_Angeles:";
				} else if (tzbox.getSelectedItem() == "Hawaii") {
					timezone = "TZID=Pacific/Honolulu:";
				} else{
					timezone = "TZID=America/Ancorage:";
				}
				if (geob.getSelectedItem().toString() == "Uh Manoa") {
					gloc = "21.299816;-157.817579";
					location = geob.getSelectedItem().toString();
				} else if (geob.getSelectedItem().toString() == "Uh Bookstore"){
					gloc = "21.2980320;-157.8185900";
					location = geob.getSelectedItem().toString();
				} else if (geob.getSelectedItem().toString() == "Ka Leo"){
					gloc = "21.2971940;-157.8151930";
					location = geob.getSelectedItem().toString();
				} else if (geob.getSelectedItem().toString() == "POST"){
					gloc = "21.297410;-157.816244";
					location = geob.getSelectedItem().toString();
				} else {
					gloc = "00.000000;00.000000";
					location = geob.getSelectedItem().toString();
				}
				
				classif = classifb.getSelectedItem().toString();
				description = descrip.getText();
				summary = titlet.getText();
				event.add(Long.parseLong(startIn), Long.parseLong(endIn),
						timezone, classif, gloc, location, description, summary);

				check = 0;
			} else if (result == JOptionPane.CANCEL_OPTION) {
				check = 0;
			} else if (result == JOptionPane.CLOSED_OPTION) {
				check = 0;
			} else {
				JOptionPane
						.showConfirmDialog(
								null,
								"<html><font color = \"white\">End date must be longer than your start date!</font>",
								"NOTICE!", JOptionPane.PLAIN_MESSAGE,
								JOptionPane.PLAIN_MESSAGE, null);
				check = 1;
			}

		}

	}// else

	private static void outputFile(
			String filename,
			LinkedList<Long, Long, String, String, String, String, String, String> event) {
		try {
			PrintWriter fileWriter = new PrintWriter(filename + ".ics");
			fileWriter.append(event.toString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {

		}
	}
}

// creation of a single event
class eventNode<Start, End, Tzone, Clasf, Loc, City, Des, Sum> {

	protected Start start;
	protected End end;
	protected Tzone zone;
	protected Clasf clasf;
	protected Loc loc;
	protected City city;
	protected Des des;
	protected Sum sum;
	protected eventNode<Start, End, Tzone, Clasf, Loc, City, Des, Sum> next;

	public eventNode(Start start2, End end2, Tzone zone2, Clasf clasf2,
			Loc loc2, City city2, Des des2, Sum sum2,
			eventNode<Start, End, Tzone, Clasf, Loc, City, Des, Sum> next2) {
		start = start2;
		end = end2;
		next = next2;
		clasf = clasf2;
		loc = loc2;
		zone = zone2;
		city = city2;
		des = des2;
		sum = sum2;
	}// end constructor

	public String toString() {
		// these add the necessary T and Zulu components to the date lines
		String startD = zone.toString() + start.toString().substring(0, 8)
				+ "T"
				+ start.toString().substring(8, start.toString().length());
		String endD = zone.toString() + end.toString().substring(0, 8) + "T"
				+ end.toString().substring(8, start.toString().length());
		String classif = clasf.toString();
		String summary = sum.toString();
		String event = "BEGIN:VEVENT\n" + "DTSTART;" + startD + "\nDTEND;"
				+ endD + "\nCLASS:" + classif + "\nSUMMARY:" + summary
				+ "\nDESCRIPTION:" + des.toString() + "\nGEO:" + loc.toString()
				+ "\nLOCATION:" + city.toString() + "\nEND:VEVENT";
		return event;
	}// end toString()

	public Start getStart() {
		return start;
	}

	public End getEnd() {
		return end;
	}

	public Loc getLoc() {
		return loc;
	}
	
	public void setDes(Des data) {
		des = data;
	}
	
	public Des getDescrip() {
		return des; 
	}

	public Clasf getClasf() {
		return clasf;
	}

	public eventNode<Start, End, Tzone, Clasf, Loc, City, Des, Sum> getNext() {
		return next;
	}

	public void setNext(
			eventNode<Start, End, Tzone, Clasf, Loc, City, Des, Sum> next2) {
		next = next2;
	}
}// end of class eventNode

class LinkedList<Start, End, Tzone, Clasf, Loc, City, Des, Sum> {

	private eventNode<Long, Long, String, String, String, String, String, String> head = null;
	protected Integer size = new Integer(0);

	public LinkedList() {
	}

	/*
	 * this adds a node to the list also this sorts from descending order as you
	 * add to the list
	 */
	public void add(Long start, Long end, String Tzone, String clasf,
			String loc, String city, String des, String sum) {// starts
		// add
		eventNode<Long, Long, String, String, String, String, String, String> event = new eventNode<Long, Long, String, String, String, String, String, String>(
				start, end, Tzone, clasf, loc, city, des, sum, null);
		if (event.getStart() < event.getEnd()) {// checks if end is smaller than
												// start
			if (head == null) {// if there is nothing in the list
				head = new eventNode<Long, Long, String, String, String, String, String, String>(
						start, end, Tzone, clasf, loc, city, des, sum, null);
			} else {// starts the add at the next node
				eventNode<Long, Long, String, String, String, String, String, String> current = head;
				eventNode<Long, Long, String, String, String, String, String, String> previous = head
						.getNext();
				eventNode<Long, Long, String, String, String, String, String, String> temp = new eventNode<Long, Long, String, String, String, String, String, String>(
						null, null, null, null, null, null, null, null, null);
				eventNode<Long, Long, String, String, String, String, String, String> temp2 = new eventNode<Long, Long, String, String, String, String, String, String>(
						null, null, null, null, null, null, null, null, null);
				try {
					while (event != null) {// if it's at the head and compares
											// puts best on top
						if ((head.getStart() > event.getStart())
								&& (current.getStart() != event.getStart())) {

							temp = current;
							temp2 = current.getNext();
							current = event;
							current.setNext(temp2);
							head = current;
							previous = head;
							event = temp;
							event.setNext(null);

						}
						// compares and puts the least before the greatest
						else if ((current.getStart() > event.getStart())
								&& (current.getStart() != event.getStart())
								&& (current.getStart() != head.getStart())) {

							temp = current;
							temp2 = current.getNext();
							current = event;
							current.setNext(temp2);
							previous.setNext(current);

							event = temp;
							event.setNext(null);
						}
						// if the current is smaller than the addition moves to
						// the next one
						else if (current.getStart() < event.getStart()
								&& (current.getNext() != null)) {

							current = current.getNext();

						}

						// adds to the end of the list
						else {
							current.setNext(event);
							event = null;
						}
					}
				} catch (NullPointerException e) {
					previous.setNext(event);
					event = null;
					System.out.println(e);

				}
				size++;
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Your End date must be later than your Start date");
		}
	}// end of add

	/*
	 * used to display the event stored in the list
	 * 
	 * @return a string "output" for the event
	 */
	public String toString() {

		String output = new String("");
		eventNode<Long, Long, String, String, String, String, String, String> current = head;
		output = "BEGIN:VCALENDAR\nCALSCALE:GREGORIAN\nX-WR-CALNAME:Team Carpo\n";
		if (current.getStart().toString() == "") {
			output = "nothing here boss!";
		} else {
			while (current != null) {
				output = output + current.toString() + "\n";
				current = current.getNext();
			}
			output = output + "END:VCALENDAR";
		}
		return output;
	}// end of toString

	/**
	 * gets an event based on teh date we can probably change this to something
	 * else mebbe month?
	 * 
	 * 
	 */
	public eventNode<Long, Long, String, String, String, String, String, String> get(
			Long start) throws ListException {
		if (head == null) {
			throw new ListException("Cannot get an item from an emtpy list!");
		}
		// find node
		// counter tracks the #of loops
		Integer counter = new Integer(1);
		eventNode<Long, Long, String, String, String, String, String, String> current = head;
		while (!current.getStart().equals(start)) {
			// /goes to the next node
			current = current.getNext();
			counter++;
		}
		return current;
	}// end of get

	/*
	 * removes an event from the list
	 * 
	 * @param start is teh start date of the node
	 * 
	 * @excetion itemexception if a node doesnt exist with that date
	 */
	public void remove(Long start) throws ListException {
		if (head == null) {// check if empty list
			throw new ListException("cannot remove from an empty list!");
		}
		// if at begining of list
		eventNode<Long, Long, String, String, String, String, String, String> current = head;
		if (current.getStart().equals(start)) {// remove 1st node
			head = head.getNext();
		} else {// if not at the begining
			eventNode<Long, Long, String, String, String, String, String, String> previous = head;
			while (!current.getStart().equals(start)) {// goes to the next node
														// until found
				previous = current;
				current = current.getNext();
			}
			// skips thus removing it from the list
			previous.setNext(current.getNext());
		}
		size--;
	}// end of remove
	
	/*Greater circle distance using Haversine formula*/
	public double geoCalc(String location_1, String location_2) {
		//Format strings into respective x and y doubles
		String delims = "[;]";
		String[] loc1 = location_1.split(delims);
		String[] loc2 = location_2.split(delims);
		
		//Create 4 variables for x1, x2, y1, y2 for formula
		double x1 = Double.parseDouble(loc1[0]);
		double y1 = Double.parseDouble(loc1[1]);
		double x2 = Double.parseDouble(loc2[0]);
		double y2 = Double.parseDouble(loc2[1]);
		
		/*Haversine formula:	
		a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
		c = 2 ⋅ asin( √a, √(1−a) )
		d = R ⋅ c
		where φ is latitude, λ is longitude, R is earth’s radius
		each degree on a great circle of Earth is 69.1105 miles
		note that angles need to be in radians to pass to trig functions*/
		
		double difference_x = Math.toRadians(x2 - x1);
		x1 = Math.toRadians(x1);
		x2 = Math.toRadians(x2);
		double difference_y = Math.toRadians(y2 - y1);
		double radius = 69.1105; 
		
		double a = Math.pow(Math.sin((difference_x)/2), 2) + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((difference_y)/2), 2);		
		double angle = 2 * Math.asin(Math.min(1, Math.sqrt(a)));
		angle = Math.toDegrees(angle);
		double distance = radius * angle;
		
		return distance; //in miles  
	}
}// end of linkedlist class

/**
 * Used for all List exceptions
 */
class ListException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 *            describes the exact cause of the error.
	 */
	public ListException(String message) {
		super(message);
	}// end of constructor
}// end of class