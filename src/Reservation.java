import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;


public class Reservation extends JFrame implements ActionListener {

    //Declaring private variables
    private  JButton btnPresent;
    private   JPanel reservationPanel;
    private  JButton btnAbsent;
    private  JTextArea reservationText;
    private  Container reservationContainer;
    private  static Queue<ReservationData> reservationQueue;
    private  JScrollPane jsp;
    private static ReservationData currentReservation;


    @Override
    public void actionPerformed(ActionEvent e) {
        Component currentButton =  (JButton)e.getSource();
       // Date reservationTime = currentReservation.startTime;

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime reservationTime = LocalDateTime.ofInstant(  currentReservation.startTime.toInstant(), ZoneId.systemDefault());

        Duration duration = Duration.between(currentTime, reservationTime);
        long difference = Math.abs(duration.toMinutes());

        try {
            if (currentReservation != null) {
                if (currentButton == btnAbsent) {

                    if (difference >=5 && difference <11) {
                        reservationQueue.add(currentReservation);
                        btnAbsent.setEnabled(false);
                        btnPresent.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Reservation added to the back the queue");
                        this.setVisible(false);
                        PrintQueue(reservationQueue);
                    } else {
                        JOptionPane.showMessageDialog(btnAbsent, " The current student is blocked");
                        this.setVisible(false);
                        PrintQueue(reservationQueue);
                    }

                } else {

                    if(difference >=5 && difference <11) {
                    // reservationQueue.add(currentReservation);
                        btnPresent.setEnabled(false);
                        btnAbsent.setEnabled(false);
                        this.setVisible(false);
                        PrintQueue(reservationQueue);
                    } else {

                      //  JOptionPane.showMessageDialog(btnAbsent, " The current student is blocked");
                        btnPresent.setEnabled(false);
                        btnAbsent.setEnabled(false);
                        this.setVisible(false);

                        PrintQueue(reservationQueue);
                    }

                }

            } else {
                JOptionPane.showMessageDialog(null, " There are not appointments at present");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    //Constructor
    public Reservation() {
        reservationQueue = new LinkedList<>();
        reservationText = new JTextArea(10,20);
        reservationText.setEditable(false);
        jsp = new JScrollPane(reservationText);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        reservationContainer = getContentPane();
        reservationContainer.setLayout(new FlowLayout());
        reservationContainer.add(btnAbsent);
        reservationContainer.add(btnPresent);
        reservationContainer.add(reservationText);
        reservationContainer.add(jsp);
        btnAbsent.addActionListener(this);
        btnPresent.addActionListener(this);

    }

    public static void main(String [] args)
    {
        Reservation reservationFrame = new Reservation();
        List<ReservationData> resData = reservationFrame.getDummyReservations(0,4);
        populateQueue(resData);

        StringBuilder sb = new StringBuilder();
        currentReservation = reservationQueue.poll();
        if( currentReservation != null) {
            sb.append("Reservation Id - " + currentReservation.reservationId + "\n " + "Start Time -" + currentReservation.startTime + "\n" + "Student Id-" + currentReservation.student.emailId + "\n" + "Question-" + currentReservation.question);
            reservationFrame.UpdateReservationText(sb.toString());
            reservationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            reservationFrame.setBounds(300, 100, 300, 150);
            reservationFrame.setSize(600, 600);
            reservationFrame.setVisible(true);
        }
        else
        {
            reservationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            reservationFrame.setBounds(300, 100, 300, 150);
            reservationFrame.setSize(600, 600);
            reservationFrame.setVisible(true);
          JOptionPane.showMessageDialog(null," Currently there are no appointments!");
          System.exit(0);
        }
    }


    //UTILITY FUNCTIONS START HERE


    /* This function sets the text of the TextArea.
       @param text - The message to be set.
     */
    private void UpdateReservationText(String text)
    {
     reservationText.setText(text);
    }

    /*
     This function generates the random number of dummy reservations.
     @param minimum - The minimum number of reservations to be generated.
     @param maximum - The maximum number of reservations to be generated.
     */
    private List<ReservationData> getDummyReservations(int minimum, int maximum)
    {
        Random random = new Random();
        int randomNo = random.nextInt((maximum-minimum) +1) + minimum;

        List<ReservationData> reservationList = new ArrayList<>();
        for(int i =1; i <= randomNo ;i++)
        {
            ReservationData rs = new ReservationData();
            rs.isPresent = true;
            rs.reservationId = i;
            rs.question = "This is the sample question" + i ;
            rs.status = Status.PRESENT;
            rs.student = new Student("student_" + i + "@buffalo.edu", null);
            rs.startTime = getReservationTime();
            reservationList.add(rs);

        }


        return reservationList;
    }

    /*
      This function populates the queue with dummy reservations.
     @param reservationList - The list of reservations.
    */
    private static void populateQueue(List<ReservationData> reservationList)
    {
        reservationQueue.addAll(reservationList);
    }


    /*
      This function generates reservation times for 2 following cases.
         1. 5 minutes before the current time.
         2. 11 minutes before the current time.
      @param - NA
     */
    private Date getReservationTime() {
        Calendar reservationTime = Calendar.getInstance();
        Random random = new Random();
        // reservation time 5 minustes before current time
        if(!random.nextBoolean())
            reservationTime.add(Calendar.MINUTE, -5);
        // reservation time 11 minustes before current time
        else
         reservationTime.add(Calendar.MINUTE, -11);
        return reservationTime.getTime();

    }

    /*
      This function iterates over the elements of queue and print it in a specific order.
      @param - Queue whose eleents are to be printed.
     */
    private void PrintQueue( Queue<ReservationData> toBePrintedQueue)
     {
         JFrame queueFrame = new JFrame();
         JTextArea queueText = new JTextArea();
         queueText.setEditable(false);
         queueFrame.add(queueText);
         queueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         queueFrame.setBounds(400, 400, 300, 150);
         queueFrame.setSize(600, 600);
         queueFrame.setVisible(true);
         StringBuilder sb = new StringBuilder();
         if( toBePrintedQueue.size() == 0)
         {
             queueText.setText("There was only 1 appointment which was removed recently");

         }
         else {
             while (!toBePrintedQueue.isEmpty()) {
                 ReservationData currentReservation = toBePrintedQueue.poll();
                 sb.append("Reservation Id - " + currentReservation.reservationId + "\n " + "Start Time -" + currentReservation.startTime + "\n" + "Student Id-" + currentReservation.student.emailId + "\n" + "Question-" + currentReservation.question);
                 sb.append("\n\n");
             }
             queueText.setText(sb.toString());
         }



     }


}


