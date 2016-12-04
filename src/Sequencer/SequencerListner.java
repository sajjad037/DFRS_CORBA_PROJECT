package Sequencer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import Models.Enums;
import Models.UDPMessage;
import ReliableUDP.Reciever;
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
			//serverSocket = new DatagramSocket(StaticContent.SEQUENCER_lISTENING_PORT);
			byte[] receiveData = new byte[StaticContent.UDP_REQUEST_BUFFER_SIZE];
			//byte[] sendData = new byte[SIZE_BUFFER_REQUEST];
			String msg = Enums.UDPSender.Sequencer.toString() + " UDP Server Is UP!";

			System.out.println(msg);
			clogger.log(msg);
			while (continueUDP) {

//				// Read request
//				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//				serverSocket.receive(receivePacket);
//				
//				byte[] message = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
//		        UDPMessage udpMessage = Serializer.deserialize(message);
//				// Clear received buffer
//				receiveData = new byte[StaticContent.UDP_REQUEST_BUFFER_SIZE];
				
				Reciever r = new Reciever(StaticContent.SEQUENCER_lISTENING_PORT, StaticContent.FRONT_END_ACK_PORT);
				UDPMessage udpMessage =  r.getData();
				//System.out.println("data recieved is : "+ r.getData().getSequencerNumber());				
				
				switch (udpMessage.getSender()) {
				case FrontEnd:
					increaseSequenceNumber();
					//udpMessage.setFrontEndPort(receivePacket.getPort());
					//udpMessage.setFrontEndIP(receivePacket.getAddress());
					udpMessage.setSequencerNumber(sequencerNumber);
					
					// Multicast  Message in independent thread.
//					final UDPMessage fUDPMessage = udpMessage; // Or whatever
//					Thread t = new Thread(new Runnable() {
//					    public void run() {
//					    	sequencerMulticaster.multicatMessage(fUDPMessage);
//					    }
//					});
//					t.start();
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
				
//				//Send Acknowledge.
//				UDPMessage ackMessage = new UDPMessage(Enums.UDPSender.Sequencer, sequencerNumber, udpMessage.getServerName(), udpMessage.getOpernation(), Enums.UDPMessageType.Reply);
//				ackMessage.setStatus(true);
//				byte[] sendData = Serializer.serialize(ackMessage);
//				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
//				serverSocket.send(sendPacket);
//
//				// Clear Send buffer
//				sendData = new byte[StaticContent.UDP_REQUEST_BUFFER_SIZE];
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

