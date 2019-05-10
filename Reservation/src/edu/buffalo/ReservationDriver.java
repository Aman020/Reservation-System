package edu.buffalo;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ReservationDriver extends JFrame implements ActionListener {

	// Declaring private variables
	private JButton btnPresent;
	private JButton btnAbsent;
	private static JTextArea reservationText;
	private Container reservationContainer;
	private static Queue<Reservation> reservationQueue;
	private JScrollPane jsp;

	public ReservationDriver() {

		btnPresent = new JButton();
		btnPresent.setText("Present");
		btnAbsent = new JButton();
		btnAbsent.setText("Absent");

		reservationText = new JTextArea(10, 20);
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

		reservationQueue = new LinkedList<>();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Component currentButton = (JButton) e.getSource();

		Reservation currentReservation = reservationQueue.peek();

		try {

			if (currentReservation != null) {

				if (currentButton == btnAbsent)
					reservationQueue = markHeadAsAbsent(reservationQueue);
				else
					reservationQueue = markHeadAsPresent(reservationQueue);
				PrintQueue(reservationQueue);

			} else {

				JOptionPane.showMessageDialog(null, " There are not appointments at present");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public Queue<Reservation> markHeadAsAbsent(Queue<Reservation> localReservationQueue) {

		Reservation currentReservation = localReservationQueue.poll();
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime reservationTime = LocalDateTime.ofInstant(currentReservation.getStartTime().toInstant(),
				ZoneId.systemDefault());

		Duration duration = Duration.between(currentTime, reservationTime);
		long difference = Math.abs(duration.toMinutes());

		if (difference >= 5 && difference < 11) {

			localReservationQueue.add(currentReservation);
			btnAbsent.setEnabled(false);
			btnPresent.setEnabled(false);
			JOptionPane.showMessageDialog(null, "Reservation added to the back the queue");
			this.setVisible(false);

		} else {

			JOptionPane.showMessageDialog(btnAbsent, " The current student is banned");
			this.setVisible(false);
		}

		return localReservationQueue;

	}

	public Queue<Reservation> markHeadAsPresent(Queue<Reservation> localReservationQueue) {

		Reservation currentReservation = localReservationQueue.poll();

		currentReservation.setStatus(Status.PRESENT);

		btnPresent.setEnabled(false);
		btnAbsent.setEnabled(false);
		this.setVisible(false);

		return localReservationQueue;

	}

	/*
	 * This function iterates over the elements of queue and print it in a specific
	 * order.
	 * 
	 * @param - Queue whose eleents are to be printed.
	 */
	private void PrintQueue(Queue<Reservation> toBePrintedQueue) {

		JFrame queueFrame = new JFrame();
		JTextArea queueText = new JTextArea();
		queueText.setEditable(false);
		queueFrame.add(queueText);
		queueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		queueFrame.setBounds(400, 400, 300, 150);
		queueFrame.setSize(600, 600);
		queueFrame.setVisible(true);

		StringBuilder sb = new StringBuilder();
		if (toBePrintedQueue.size() == 0) {
			queueText.setText("No Appointment in the Queue");

		} else {
			while (!toBePrintedQueue.isEmpty()) {
				Reservation currentReservation = toBePrintedQueue.poll();
				sb.append(currentReservation.showReservation());
				sb.append("\n\n");
			}

			queueText.setText(sb.toString());
		}

	}

	public static void main(String[] args) {

		ReservationDriver reservationDriver = new ReservationDriver();

		GenerateReservations geReservations = new GenerateReservations();

		reservationQueue.addAll(geReservations.getGeneratedData(0, 4));

		Reservation currentReservation = reservationQueue.peek();

		if (currentReservation != null) {

			// queue has at least one appointment

			reservationText.setText(currentReservation.showReservation());

			reservationDriver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			reservationDriver.setBounds(300, 100, 300, 150);
			reservationDriver.setSize(600, 600);
			reservationDriver.setVisible(true);
		} else {

			// no appointments in the queue

			reservationDriver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			reservationDriver.setBounds(300, 100, 300, 150);
			reservationDriver.setSize(600, 600);
			reservationDriver.setVisible(true);
			JOptionPane.showMessageDialog(null, " Currently there are no appointments!");
			System.exit(0);
		}
	}
}
