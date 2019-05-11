package edu.buffalo;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class ReservationDriverTest {

	ReservationDriver driver = new ReservationDriver();
	Queue<Reservation> reservationQueue;

	
	/**
	 * This test case will test the queue if there are multiple entries when the student is banned. 
	 * 
	 */
	
	@Test
	public void testQueueWithMultipleEntriesWhenStudentIsBanned() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(11), generateStudent(1), generateQuestion(1));
		Reservation reservation2 = new Reservation(2, getTimeBefore(5), generateStudent(2), generateQuestion(2));
		Reservation reservation3 = new Reservation(3, getTimeBefore(11), generateStudent(3), generateQuestion(3));

		reservationQueue.add(reservation1);
		reservationQueue.add(reservation2);
		reservationQueue.add(reservation3);
		
		int previousQueueSize = reservationQueue.size();

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue, false);

		assertEquals(returnQueue.contains(reservation1), false);
		assertEquals(returnQueue.size(), previousQueueSize-1);

	}
	
	/**
	 * This test case will test the queue if there is Single entry when the student is banned. 
	 * 
	 */

	@Test
	public void testQueueWithSingleEntryWhenStudentIsBanned() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(11), generateStudent(1), generateQuestion(1));

		reservationQueue.add(reservation1);

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue, false);

		assertEquals(returnQueue.size(), 0);

	}

	/**
	 * This test case will test the queue if there is single entry when the student is re-added at the end
	 * 
	 */
	
	
	@Test
	public void testQueueWithSingleEntryWhenStudentReaddedAtEnd() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(5), generateStudent(1), generateQuestion(1));

		reservationQueue.add(reservation1);

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue, false);

		assertEquals(returnQueue.contains(reservation1), true);
		assertEquals(returnQueue.size(), 1);

	}

	/**
	 * This test case will test the queue if there are single entry when the student is re-added at the end
	 * 
	 */
	
	@Test
	public void testQueueWithMultipleEntriesWhenStudentReaddedAtEnd() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(5), generateStudent(1), generateQuestion(1));
		Reservation reservation2 = new Reservation(2, getTimeBefore(11), generateStudent(2), generateQuestion(2));

		reservationQueue.add(reservation1);
		reservationQueue.add(reservation2);
		
		Reservation headOfOriginalQueue = reservationQueue.peek();

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue, false);
		
		
		Reservation resAtTail = new Reservation();
		
		for(Reservation res: returnQueue) {
			resAtTail = res;
			
		}
		
		assertEquals(returnQueue.size(), 2);
		assertEquals(resAtTail, headOfOriginalQueue);


	}

	/**
	 * This method generates student Object
	 * 
	 * @param an integer to distinguish between students
	 * @return the generated student
	 * 
	 */
	private Student generateStudent(int i) {

		return new Student("student_" + i + "@buffalo.edu", null);

	}

	/**
	 * This method generates a sample question
	 * 
	 * @param an integer to distinguish between students
	 * @return the generated question
	 * 
	 */
	private String generateQuestion(int i) {

		return "this is a question " + i;

	}

	/**
	 * This method generates a start time of a reservation
	 * 
	 * @param an integer to substract minutes from current time
	 * @return the calculated time
	 * 
	 */
	private Date getTimeBefore(int minutes) {

		Calendar reservationTime = Calendar.getInstance();
		reservationTime.add(Calendar.MINUTE, (minutes * -1));

		return reservationTime.getTime();

	}

}
