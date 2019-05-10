package edu.buffalo;
import java.util.Date;

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

    public Reservation() {}
    
	public Reservation(int reservationId, Date startTime, Student student, String question) {
		super();
		this.reservationId = reservationId;
		this.startTime = startTime;
		this.student = student;
		this.question = question;
		this.status = Status.ABSENT;
	}
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
