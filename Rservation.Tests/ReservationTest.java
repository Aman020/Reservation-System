import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ReservationTest {


    @Before
    public void SetupQueue()
    {
        Queue<ReservationData> reservationQueue = new LinkedList<>();
        for(int i =0; i < 4 ; i++)
        {
            ReservationData rs = new ReservationData();
            rs.isPresent = true;
            rs.reservationId = i;
            rs.question = "This is the sample question" + i ;
            rs.status = Status.PRESENT;
            rs.student = new Student("student_" + i + "@buffalo.edu", null);
           // rs.startTime = getReservationTime();
            //reservationList.add(rs);
        }
    }


    @Test
    public void actionPerformed() {


    }
}