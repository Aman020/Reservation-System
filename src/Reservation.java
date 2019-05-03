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
        JFrame frame = new JFrame("Reservation");
        frame.setContentPane(new Reservation().reservationPanel);
        frame.setVisible(true);

    }


    private List<ReservationData> getDummyReservations( int noOfReservations)
    {
        Random random = new Random();
        int randomNo = random.nextInt((4-0) +1) + 0;

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


}


