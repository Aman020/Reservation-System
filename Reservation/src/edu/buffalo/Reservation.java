package edu.buffalo;
import java.util.Date;



//Enum to mark the reservations's status as either ABSENT and PRESENT
enum Status
{
    ABSENT, PRESENT
}


/**
 *The Reservation class is a worker class that holds and provides the information of a specific reservation.
 * @author 	Aman,Amlan,Deboshree, Meghana, Prabhleen
 * @version	1.0
 * @since	2019-05-04
 */

public class Reservation {

    private int reservationId;
    private Date startTime;
    private Student student;
    private String question;
    private Status status;

    public Reservation() {}
    
	public Reservation(int reservationId, Date startTime, Student student, String question) {
		super();
		this.reservationId = reservationId;
		this.startTime = startTime;
		this.student = student;
		this.question = question;
		this.status = Status.ABSENT;
	}
	//Get the reservation id.
	public int getReservationId() {
		return reservationId;
	}
	//Set the reservation id.
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId; }
	//Return the start time of the reservation
	public Date getStartTime() {
		return startTime;
	}
	//Set the start time of the reservation
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	//Get the student's information
	public Student getStudent() {
		return student;
	}
	//Set the student's information
	public void setStudent(Student student) {
		this.student = student;
	}
	//Get the question
	public String getQuestion() {
		return question;
	}
	//Set the question
	public void setQuestion(String question) {
		this.question = question;
	}
	//Get the status of the appointment
	public Status getStatus() {
		return status;
	}
	//Set the status of the appointment
	public void setStatus(Status status) {
		this.status = status;
	}
	

	//Overridden method to customize the predefined function
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (reservationId != other.reservationId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
	
	/**
	 * This function showReservation will show the reservation details like reservation ID,Start time and student's Email ID.
	 * 
	 * @param - NA	 
	 * @return - This returns a string which contains the reservation details*/

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
