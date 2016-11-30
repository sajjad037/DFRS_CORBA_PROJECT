package FE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

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
		
		String[] arr = firstName.split(":");
//					server		manager		cmd			firstName
		String msg =arr[0]+"|"+arr[1]+":"+"bookFlight:"+arr[3]+":"+lastName+":"+address+":"+phone+":"+destination+":"+date+":"+classFlight;
		
		String result = send(msg);
		
		return result;
	}

	@Override
	public String getBookedFlightCount(String recordType) {
		// TODO Auto-generated method stub
		System.out.println("inside getBookedFlightCount");
		
		String msg = "getBookedFlightCount:"+recordType;
		
	//	send("2");
		
		
		return "";
	}

	@Override
	public String editFlightRecord(String recordID, String fieldName, String newValue) {
		// TODO Auto-generated method stub
		System.out.println("inside editFlightRecord");
		
		String msg = "editFlightRecord:"+recordID+":"+fieldName+":"+newValue;
		
	//	send("3");
		
		return "";
	}

	@Override
	public String transferReservation(String passengerID, String currentCity, String otherCity) {
		// TODO Auto-generated method stub
		System.out.println("inside transferReservation");
		
		String msg = "transferReservation:"+passengerID+":"+currentCity+":"+otherCity;
//		send("4");
		
		
		
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
	
	
	public String send(String new_msg){
		String result = "0";
		String [][] resultInfo = null;
		try{
			DatagramSocket socket = new DatagramSocket();
			System.out.println("my port:"+ socket.getLocalPort());
			
			InetAddress aHost = InetAddress.getByName(addressSequencer);
			
			DatagramPacket requestPacket1 = new DatagramPacket(new_msg.getBytes(), new_msg.length(), aHost, portSequencer);
			socket.send(requestPacket1);
			System.out.println("Request sent to Sequencer: " + new String(requestPacket1.getData()));
			
			byte[] buffer1 = new byte[1000];
			DatagramPacket replyPacket1 = new DatagramPacket(buffer1, buffer1.length);	
			socket.receive(replyPacket1);
			System.out.println("Reply Ack received from Sequencer: " + new String(replyPacket1.getData()));	
			
			resultInfo = new String[4][4];
			int i=0;
			Timer timer = new Timer();
			TimeOutTask timeOutTask = null;
			boolean isWaiting = true;
			while(isWaiting){
				System.out.println("waiting for UDP message i: "+i);
				
				try {
					byte[] buffer2 = new byte[1000];
					DatagramPacket requestPacket2 = new DatagramPacket(buffer2, buffer2.length);
					socket.receive(requestPacket2); 
					String message = new String(requestPacket2.getData());				
					System.out.println("Result message received: "+message+" address: "+requestPacket2.getAddress()+" portNumber: "+requestPacket2.getPort());
					resultInfo[i][0] = "0";
					resultInfo[i][1] = message;
					resultInfo[i][2] = requestPacket2.getAddress().toString();
					resultInfo[i][3] = Integer.toString(requestPacket2.getPort());
					if(i==0){
						timer.schedule(timeOutTask = new TimeOutTask(),1000);
					}
					i++;
					
					String msgACK = "Ack:0";
					DatagramPacket replyPacket2 = new DatagramPacket(msgACK.getBytes(), msgACK.length(), requestPacket2.getAddress(), requestPacket2.getPort());
					socket.send(replyPacket2);
					System.out.println("Acknowledgement sent to "+requestPacket2.getAddress()+" "+requestPacket2.getPort());
				} catch (IOException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(i==4){
					isWaiting = false;
					System.out.println("i = "+i+", isWaiting set to: "+isWaiting);
				}
				if(timeOutTask.getTimeOut()){
					System.out.println("time out has occured:");
					for(int k=0; k<4; k++){
						if(resultInfo[k][1] == null){
							resultInfo[k][0] = "0";
							resultInfo[k][1] = "X";
							resultInfo[k][2] = "X";
							resultInfo[k][3] = "X";
						}
						System.out.println("resultInfo["+k+"][0] = "+resultInfo[k][0]);
						System.out.println("resultInfo["+k+"][1] = "+resultInfo[k][1]);
						System.out.println("resultInfo["+k+"][2] = "+resultInfo[k][2]);
						System.out.println("resultInfo["+k+"][3] = "+resultInfo[k][3]);
					}
				}
				
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
	
		result = compareResults(resultInfo);
		System.out.println("final result: "+result);
	
		return result;
	}
	
	
	public String compareResults(String[][] new_resultInfo){
		
		String finalResult = "";
		int j = 0;
		if(!new_resultInfo[0][1].equalsIgnoreCase("X")){
			finalResult = new_resultInfo[j][1];
		}else{
			j++;
			finalResult = new_resultInfo[j][1];
		}
		
		int count=0;
		for(int i=j+1; i<4; i++){
			if(finalResult.equalsIgnoreCase(new_resultInfo[i][1])){
				count++;
			}			
		}
		
		if(count==0){
			if(!new_resultInfo[j+1][1].equalsIgnoreCase("X")){
				finalResult = new_resultInfo[j+1][1];
			}else{
				finalResult = new_resultInfo[j+2][1];
			}
		}
		
		return finalResult;
	}
	

}
