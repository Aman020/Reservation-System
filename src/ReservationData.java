import java.util.*;

enum Status
{
    ABSENT, PRESENT
}

public class ReservationData {

    public int reservationId;
    public Date startTime;
    public Student student;
    public String question;
    public Status status;
    public boolean isPresent;

}
