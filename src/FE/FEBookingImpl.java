package FE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.omg.CORBA.ORB;

public class FEBookingImpl extends FEBookingIntPOA {
	
	private ORB orb;
	private int portSequencer = 5555;
	private String addressSequencer = "127.0.0.1";
	
	public FEBookingImpl(){}
	

	@Override
	public String bookFlight(String firstName, String lastName, String address,String phone, String destination, String date, String classFlight) {
		// TODO Auto-generated method stub
		System.out.println("inside bookFlight");
		
		String msg ="bookFlight:"+firstName+":"+lastName+":"+address+":"+phone+":"+destination+":"+date+":"+classFlight;
		
		
		send(msg);
	
		
		
		return "";
	}

	@Override
	public String getBookedFlightCount(String recordType) {
		// TODO Auto-generated method stub
		System.out.println("inside getBookedFlightCount");
		
		String msg = "getBookedFlightCount:"+recordType;
		
		send(msg);
		
		
		return "";
	}

	@Override
	public String editFlightRecord(String recordID, String fieldName, String newValue) {
		// TODO Auto-generated method stub
		System.out.println("inside editFlightRecord");
		
		String msg = "editFlightRecord:"+recordID+":"+fieldName+":"+newValue;
		
		send(msg);
		
		return "";
	}

	@Override
	public String transferReservation(String passengerID, String currentCity, String otherCity) {
		// TODO Auto-generated method stub
		System.out.println("inside transferReservation");
		
		String msg = "transferReservation:"+passengerID+":"+currentCity+":"+otherCity;
		send(msg);
		
		
		
		return "";
	}
	
	/**
	 * This method sets the orb
	 * @param new_orb
	 */
	public void setORB(ORB new_orb){
		orb = new_orb;
	}
	
	/**
	 * This method shut downs the orb
	 */
	public void shutdown(){
		orb.shutdown(false);
	}
	
	
	public void send(String message){
		try{
			DatagramSocket socket = new DatagramSocket();
			System.out.println("my port:"+ socket.getLocalPort());
			
	/*		InetAddress aHost = InetAddress.getByName(addressSequencer);
			
			DatagramPacket requestPacket = new DatagramPacket(message.getBytes(), message.length(), aHost, portSequencer);
			socket.send(requestPacket);
			System.out.println("Request: " + new String(requestPacket.getData()));
			
			//receive UDP reply message from other server containing number of passenger on the flights
			byte[] buffer = new byte[1000];
			DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);	
			socket.receive(replyPacket);
			System.out.println("Reply: " + new String(replyPacket.getData()));	
	*/		
			boolean isWaiting = true;
			while(isWaiting){
				
				System.out.println("hi");
				
				
			}
			socket.close();
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("socket error");
	/*	} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	*/	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
