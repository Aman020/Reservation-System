package edu.buffalo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.junit.Test;

import edu.buffalo.Reservation;
import edu.buffalo.ReservationDriver;
import edu.buffalo.Student;

public class ReservationDriverTest {
	
	ReservationDriver driver = new ReservationDriver();
	Queue<Reservation> reservationQueue;
	

	@Test
	public void testQueueWhenStudentIsBanned() {
		
		reservationQueue = new LinkedList<Reservation>();

		Reservation reservation1 = new Reservation(1, getTimeBefore(11), generateStudent(1) , generateQuestion(1));
		Reservation reservation2 = new Reservation(2, getTimeBefore(5), generateStudent(2) , generateQuestion(2));
		Reservation reservation3 = new Reservation(3, getTimeBefore(11), generateStudent(3) , generateQuestion(3));
		
		reservationQueue.add(reservation1);
		reservationQueue.add(reservation2);
		reservationQueue.add(reservation3);
		
		
		Queue<Reservation> returnQueue = driver.markHeadAsAbsent(reservationQueue);
		
		assertEquals(returnQueue.contains(reservation1), false);
		

	}
	
	
	
	
	private Student generateStudent(int i) {
		
		return new Student("student_" + i + "@buffalo.edu", null);
		
	}
	
	private String generateQuestion(int i) {
		
		return "this is a question " + i;
		
	}
	
	private Date getTimeBefore(int minutes) {
		
		Calendar reservationTime = Calendar.getInstance();
		reservationTime.add(Calendar.MINUTE, (minutes*-1));

		return reservationTime.getTime();
		
	}

}
