package Sequencer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import Models.Enums;
import Models.UDPMessage;
import StaticContent.*;
import Utilities.CLogger;
import Utilities.Serializer;

public class SequencerListner implements Runnable {
	private CLogger clogger;
	private DatagramSocket serverSocket;
	private Thread t = null;
	private boolean continueUDP = true;
	private SequencerMulticaster sequencerMulticaster;
	private long sequencerNumber= 0;
	
	public SequencerListner(CLogger clogger) {
		this.clogger = clogger;
		sequencerMulticaster =  new  SequencerMulticaster(clogger);
	}

	@Override
	public void run() {
		try {
			serverSocket = new DatagramSocket(StaticContent.SEQUENCER_lISTENING_PORT);
			byte[] receiveData = new byte[StaticContent.UDP_REQUEST_BUFFER_SIZE];
			//byte[] sendData = new byte[SIZE_BUFFER_REQUEST];
			String msg = Enums.UDPSender.Sequencer.toString() + " UDP Server Is UP!";

			System.out.println(msg);
			clogger.log(msg);
			while (continueUDP) {

				// Read request
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				
				byte[] message = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
		        UDPMessage udpMessage = Serializer.deserialize(message);
				// Clear received buffer
				receiveData = new byte[StaticContent.UDP_REQUEST_BUFFER_SIZE];
				
				switch (udpMessage.getSender()) {
				case FrontEnd:
					increaseSequenceNumber();
					udpMessage.setSequencerNumber(sequencerNumber);
					sequencerMulticaster.multicatMessage(udpMessage);
					break;
					
//				case ReplicaUlan:
//				case ReplicaSajjad:
//				case ReplicaUmer:
//				case ReplicaFeras:
//					sequencerMulticaster.clearBuffer(udpMessage);
//					break;

				default:
					break;
				}
				
//
//				switch (opreation) {
//				case "getBookedFlightCount":
//					flightClass = requestArray[2];
//					capitalizedSentence = getLocalFlightCount(flightClass);
//					break;
//
//				case "transferReservation":
//					String firstName, lastName, address, phone, destination, date = "";
//					// oldBookingId = requestArray[2];
//					firstName = requestArray[3];
//					lastName = requestArray[4];
//					address = requestArray[5];
//					phone = requestArray[6];
//					destination = requestArray[7];
//					date = requestArray[8];
//					flightClass = requestArray[9];
//					capitalizedSentence = bookFlight(firstName, lastName, address, phone, destination, date,
//							flightClass);
//
//					break;
//
//				default:
//					break;
//				}

				//sendData = capitalizedSentence.getBytes();
				//DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				//serverSocket.send(sendPacket);

				// Clear Send buffer
				//sendData = new byte[SIZE_BUFFER_REQUEST];
			}
		} catch (Exception ex) {
			clogger.logException("on starting UDP Server", ex);
			ex.printStackTrace();
		}

	}
	
	/**
	 * Start the server thread
	 */
	public void start()
	{
		t = new Thread(this);
		t.start();
	}
	
	/**
	 * Execute a join on the thread
	 * @throws InterruptedException 
	 */
	public void join() throws InterruptedException 
	{
		if(t == null)
			return;
		
		t.join();
	}
	
	/**
	 * Stop the server thread
	 */
	public void stop()
	{
		continueUDP = false;
	}
	
	public long getNextSequenceNumber() {
		return this.sequencerNumber + 1;
	}

	public void increaseSequenceNumber() {
		this.sequencerNumber++;
	}

}
