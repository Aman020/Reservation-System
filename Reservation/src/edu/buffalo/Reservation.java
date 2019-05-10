package edu.buffalo;
import java.util.*;

enum Status
{
    ABSENT, PRESENT
}

public class Reservation {

    private int reservationId;
    private Date startTime;
    private Student student;
    private String question;
    private Status status;
    private boolean isPresent;
    
    
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public boolean isPresent() {
		return isPresent;
	}
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	
	public String showReservation() {
		
		String res = "ReservationId: " + reservationId + "\n"
				+ "Start Time: " + startTime + "\n"
				+ "Student: " + student.getEmailId() + "\n";
		
		if(question!=null) {
			res += "Question: " + question;
		}
		
		return res;
	}
    

}
