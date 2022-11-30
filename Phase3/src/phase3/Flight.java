package phase3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class Flight {
	// initializing the seats array
	private static Seat[][] seats = {
			{ new Seat("A1"), new Seat("C1") }, 
			{ new Seat("A2"), new Seat("C2") },
			{ new Seat("A3"), new Seat("C3") }, 
			{ new Seat("A4"), new Seat("B4"), new Seat("C4"), new Seat("D4") },
			{ new Seat("A5"), new Seat("B5"), new Seat("C5"), new Seat("D5") },
			{ new Seat("A6"), new Seat("B6"), new Seat("C6"), new Seat("D6") },
			{ new Seat("A7"), new Seat("B7"), new Seat("C7"), new Seat("D7") },
			{ new Seat("A8"), new Seat("B8"), new Seat("C8"), new Seat("D8") },
			{ new Seat("A9"), new Seat("B9"), new Seat("C9"), new Seat("D9") },
			{ new Seat("A10"), new Seat("B10"), new Seat("C10"), new Seat("D10") },
			{ new Seat("A11"), new Seat("B11"), new Seat("C11"), new Seat("D11") },
			{ new Seat("A12"), new Seat("B12"), new Seat("C12"), new Seat("D12") }

	};

	static Scanner sc = new Scanner(System.in);
	static boolean end = false;
	static int reservedCounter = 0;

	public static void main(String[] args) {
		System.out.println("---------------- PHASE 3 ----------------"); // print selection menu and entering selection.
		while (end != true) {
			System.out.println("1. Read passengers file. ");
			System.out.println("2. Reserve a new empty seat. ");
			System.out.println("3. Delete a reserved seat. ");
			System.out.println("4. Delete all reserved seats. ");
			System.out.println("5. Update passengers file. ");
			System.out.println("6. Quit. ");
			System.out.println("7. Print flight seats map. (Extra)");
			System.out.print("\nEnter selection: ");
				String x = sc.nextLine();	// avoiding any input mismatch error by using string input instead of integer.
				selection(x);
	}
	}

	public static void selection(String x) { // selection menu leading to requested methods using Switch.
		switch (x) {  // not converting to int to make use of switch default message.
		case "1":
			readFile();
			break;
		case "2":
			reserve();
			break;
		case "3":
			emptySeat();
			break;
		case "4":
			emptyAll();
			break;
		case "5":
			updateFile();
			break;
		case "6":
			quit();
			break;
		case "7":
			printer();
			break;
		default:
			System.out.print("\nError, Enter a valid selection: ");
			x = sc.nextLine();
			selection(x);
			break;
		}

	}

	public static void readFile() { // reserves seats from file following this format: (Seat Number: Passenger name)
		String name = "";
		String seatNumber = "";
		try {
			File file = new File("passengers.txt");
			Scanner scan = new Scanner(file);
			Scanner scan2 = new Scanner(file);
			System.out.println();
			if (reservedCounter < 42) {
				while (scan.hasNextLine() || scan2.hasNextLine()) {
					seatNumber = scan.nextLine();
					seatNumber = seatNumber.substring(0, seatNumber.indexOf(':')); // get the correct substring, stop when you reach ':' .
					name = scan2.nextLine();
					name = name.substring(name.indexOf(':') + 2, name.length()); // begin from ':', skip its index and	the whitespace, get the substring till the end of string.
					if (Seat.isValid(seatNumber) == true) {
						int column = Seat.getColumn(seatNumber);
						int row = Seat.getRow(seatNumber);
								if (seats[row][column].isEmpty() == true) {
										seats[row][column].setPassengerName(name);
										System.out.println("Seat " + seatNumber + " has been reserved for: " + name);
										System.out.println();
										reservedCounter++;
									} else {
										System.out.println("Seat " + seatNumber + " is already reserved.");
										System.out.println();
									}
								
					}

					else {
						System.out.println("Seat " + seatNumber + " is invalid.");
						System.out.println();

					}

				}
			} else {
				System.out.println("\nAll Seats are already reserved!\n");

			}
		}

		catch (FileNotFoundException e) {
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
		}

	}

	public static void reserve() { // reserves selected seat if its empty.
		Scanner input = new Scanner(System.in);
		if (reservedCounter < 42) {
			System.out.print("\nEnter a valid seat number: ");
			String x = input.nextLine();
			if (Seat.isValid(x) == true) {
				int column = Seat.getColumn(x);
				int row = Seat.getRow(x);
				if (seats[row][column].isEmpty()) {
								System.out.print("\nEnter passenger name: ");	// if seat is valid and not reserved, it prompts the user to enter a passenger name.
								String name = input.nextLine();
								seats[row][column].setPassengerName(name);
								System.out.println();
								System.out.println("Seat " + x + " has been reserved for: " + name);
								System.out.println();
								reservedCounter++;
				} else {
					System.out.println();
					System.out.println("Seat " + x + " is already reserved, Please reserve a different seat. ");
					reserve();
							}

			} else {
					System.out.println("\nError, Please enter a valid seat number.");
					reserve();
						}

		} else {
			System.out.println("\nAll Seats are already reserved!\n");
				}
	}

	public static void emptySeat() { // empties selected seat if it's not already empty.
		Scanner empty = new Scanner(System.in);
		if (reservedCounter != 0) {
			System.out.print("\nEnter a valid seat to empty: ");
			String x = empty.nextLine();
			if (Seat.isValid(x) == true) {
				int column = Seat.getColumn(x);
				int row = Seat.getRow(x);
				if (seats[row][column].isEmpty() == false) { // Empties seat if its reserved, by setting the passenger name to null.
					seats[row][column].setPassengerName(null);
					System.out.println("\n" + "Seat " + x + " has been emptied!\n");
					reservedCounter--;
				} else {
					System.out.println();
					System.out.println("Seat " + x + " is already empty.");
					System.out.println("\nEnter a different seat. ");
					emptySeat();
				}

			} else {
				System.out.println("\nError, Please enter a valid seat number. ");
				emptySeat();
			}

		} else {
			System.out.println("\nAll seats are already empty!\n");
		}

	}

	public static void emptyAll() { // sets all passenger names to null
		for (int i = 0; i < seats.length; i++)
			for (int j = 0; j < seats[i].length; j++)
				seats[i][j].setPassengerName(null);

		reservedCounter = 0;
		System.out.println("\nAll seats have been emptied!\n");

	}

	public static void updateFile() { // Updates current file with newly reserved seats.
		try {
			File file = new File("passengers.txt");
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			for (int i = 0; i < seats.length; i++)
				for (int j = 0; j < seats[i].length; j++)
					if (seats[i][j].getPassengerName() != null)
						writer.println(seats[i][j]);

			System.out.println("\nFile has been updated!");
			System.out.println();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println();
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	public static void printer() { // prints info map of all seats. to make reviewing my code easier.
		System.out.println("\n---------------------- FLIGHT SEATS MAP ----------------------");
		System.out.println();
		for (int i = 0; i < seats.length; i++)
			for (int j = 0; j < seats[i].length; j++) {
				System.out.println(seats[i][j].getSeatNumber()+": "+seats[i][j].getPassengerName());

			}
		System.out.println();
	}

	public static void quit() { // Quit method, if called, sends the quit signal to while loop.
		System.out.println("\nQuitted.");
		end = true;
	}

}
