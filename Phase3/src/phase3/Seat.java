package phase3;

public class Seat {

	private String seatNumber;
	private String passengerName;
	
	 Seat(String seatNumber){
		this.setSeatNumber(seatNumber);
	}
	 
	 // ------- setters and getters -------

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	 
	// -------------- methods -------------- 
	
	public static int getRow(String seatNumber) { // method is called only when seat is valid.
		String row = seatNumber.substring(1, seatNumber.length()); // row starts from index 1 to the end of the string.
		int x = Integer.parseInt(row);
		return x - 1; // -1 to make sure it fits array index length.
	}

	public static int getColumn(String seatNumber) { // method is only called if seat is valid.
		int x = 0;
		char column = seatNumber.charAt(0); // in a valid seat number, the column is at index zero.
		if (Seat.getRow(seatNumber) > 3) {
			switch (column) {
			case 'A':
				x = 0;
				break;
			case 'B':
				x = 1;
				break;
			case 'C':
				x = 2;
				break;
			case 'D':
				x = 3;
				break;
			}
		} else {
			if (column == 'C') // in the first 3 rows we only have 2 columns, 0 and 1 ( A , C)
				x = 1;
		}
		return x;
	}
	
	public static boolean isValid(String seatNumber) {
		
		String[][] validSeats = { // Ragged array containing all possible valid seat numbers.
				{"A1","C1"},		// we could have used seats array in flight class but this way is more convenient.
				{"A2","C2"},
				{"A3","C3"},
			{"A4","B4","C4","D4"},
			{"A5","B5","C5","D5"},
			{"A6","B6","C6","D6"},
			{"A7","B7","C7","D7"},
			{"A8","B8","C8","D8"},
			{"A9","B9","C9","D9"},
			{"A10","B10","C10","D10"},
			{"A11","B11","C11","D11"},
			{"A12","B12","C12","D12"}
								
		};

		for (int i = 0; i < validSeats.length; i++)
			for(int j = 0 ; j<validSeats[i].length ; j++) 
			if (seatNumber.equals(validSeats[i][j])) // goes through array to check if seat is valid or not.
				return true;
		
				return false;
	}
	
	public boolean isEmpty() {
		if(this.passengerName == null) // if passengerName is null then seat is unreserved.
			return true;
		
		return false;
	}
	
	
	public String toString(){
	
		return this.seatNumber + ": " + this.passengerName; // prints seat objects in supported formating: (seatNumber: passengerName).
	}
	
	
	
}
