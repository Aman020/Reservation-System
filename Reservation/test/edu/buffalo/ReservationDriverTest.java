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

	@Test
	public void testQueueWithMultipleEntriesWhenStudentIsBanned() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(11), generateStudent(1), generateQuestion(1));
		Reservation reservation2 = new Reservation(2, getTimeBefore(5), generateStudent(2), generateQuestion(2));
		Reservation reservation3 = new Reservation(3, getTimeBefore(11), generateStudent(3), generateQuestion(3));

		reservationQueue.add(reservation1);
		reservationQueue.add(reservation2);
		reservationQueue.add(reservation3);

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue);

		assertEquals(returnQueue.contains(reservation1), false);

	}

	@Test
	public void testQueueWithSingleEntryWhenStudentIsBanned() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(11), generateStudent(1), generateQuestion(1));

		reservationQueue.add(reservation1);

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue);

		assertEquals(returnQueue.size(), 0);

	}

	@Test
	public void testQueueWithSingleEntryWhenStudentReaddedAtEnd() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(5), generateStudent(1), generateQuestion(1));

		reservationQueue.add(reservation1);

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue);

		assertEquals(returnQueue.contains(reservation1), true);

	}

	@Test
	public void testQueueWithMultipleEntriesWhenStudentReaddedAtEnd() {

		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(5), generateStudent(1), generateQuestion(1));
		Reservation reservation2 = new Reservation(2, getTimeBefore(11), generateStudent(2), generateQuestion(2));

		reservationQueue.add(reservation1);
		reservationQueue.add(reservation2);

		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue);

		assertEquals(returnQueue.size(), 2);

	}

	private Student generateStudent(int i) {

		return new Student("student_" + i + "@buffalo.edu", null);

	}

	private String generateQuestion(int i) {

		return "this is a question " + i;

	}

	private Date getTimeBefore(int minutes) {

		Calendar reservationTime = Calendar.getInstance();
		reservationTime.add(Calendar.MINUTE, (minutes * -1));

		return reservationTime.getTime();

	}

}
