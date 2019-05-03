import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Reservation {
    private JButton btnPresent;
    private JPanel reservationPanel;
    private JButton btnAbsent;

    public Reservation() {
        btnPresent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello world");
            }
        });
    }

    public static void main(String [] args)
    {
        Reservation reservation = new Reservation();
       List<ReservationData> resData = reservation.getDummyReservations(0,4);
        JFrame frame = new JFrame("Reservation");
        frame.setContentPane(new Reservation().reservationPanel);
        frame.setVisible(true);

    }


    private List<ReservationData> getDummyReservations(int minimum, int maximum)
    {
        Random random = new Random();
        int randomNo = random.nextInt((maximum-minimum) +1) + minimum;

        List<ReservationData> reservationList = new ArrayList<>();
        for(int i =0; i < randomNo ;i++)
        {
            ReservationData rs = new ReservationData();
            rs.isPresent = true;
            rs.reservationId = i;
            rs.question = "This is the sample question" + i ;
            rs.status = Status.PRESENT;
            rs.student = new Student("student_" + i + "@buffalo.edu", null);


        }


        return reservationList;
    }

    private Date getReservationTime() {
        
        
        Calendar reservationTime = Calendar.getInstance();
        
        Random random = new Random();
        
        // reservation time 5 minustes before current time
        if(random.nextBoolean()) reservationTime.add(Calendar.MINUTE, -5);
        // reservation time 11 minustes before current time
        else reservationTime.add(Calendar.MINUTE, -11);

        
        return reservationTime.getTime();
    }


}


