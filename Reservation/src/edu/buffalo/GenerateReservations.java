package edu.buffalo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GenerateReservations {
	
	/**
	 * This method generates the random number of dummy reservations.
	 * 
	 * @param minimum - The minimum number of reservations to be generated.
	 * 
	 * @param maximum - The maximum number of reservations to be generated.
	 * @return - This method returns Reservation List
	 */
	
	public List<Reservation> getGeneratedData(int minimum, int maximum) {
		
		Random random = new Random();
		int randomNo = random.nextInt((maximum - minimum) + 1) + minimum;

		List<Reservation> reservationList = new ArrayList<>();
		for (int i = 1; i <= randomNo; i++) {
			
			
			//TODO: fix it
			Reservation rs = new Reservation();

			rs.setReservationId(i);
			if (i % 2 == 1) {
				rs.setQuestion("This is the sample question" + i);
			}

			rs.setStatus(Status.ABSENT);
			rs.setStudent(new Student("student_" + i + "@buffalo.edu", null));
			rs.setStartTime(generateReservationTime());
			
			reservationList.add(rs);

		}

		return reservationList;
		
	}
	
	
	/**
	 * This function generates reservation times for 2 following cases. 1. 5 minutes
	 * before the current time. 2. 11 minutes before the current time.
	 * 
	 * @param - NA
	 * @return - Time which is 5 mins before the current time or 11 mins before the current time. 
	 */
	private Date generateReservationTime() {
		Calendar reservationTime = Calendar.getInstance();
		Random random = new Random();
		// reservation time 5 minustes before current time
		if (!random.nextBoolean())
			reservationTime.add(Calendar.MINUTE, -5);
		// reservation time 11 minustes before current time
		else
			reservationTime.add(Calendar.MINUTE, -11);
		return reservationTime.getTime();

	}

}
