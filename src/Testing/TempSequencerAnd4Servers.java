package Testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import FE.TimeOutTask;

public class TempSequencerAnd4Servers {
	
	static String addressFE = "";
	static int portFE = 0;
	
	public static void main(String[] args){
		
		
		int port = 5555;
		DatagramSocket socket;
		try {
			socket = new DatagramSocket(port);
			System.out.println("Temp sequencer is up and running.");
		boolean isWaiting = true;
		
		
		
		while(isWaiting){
			
			try {
				byte[] buffer2 = new byte[1000];
				DatagramPacket requestPacket = new DatagramPacket(buffer2, buffer2.length);
				socket.receive(requestPacket); 
				String message = new String(requestPacket.getData());				
				System.out.println("Message received: "+message+" address: "+requestPacket.getAddress()+" portNumber: "+requestPacket.getPort());
				addressFE = requestPacket.getAddress().toString();
				portFE = requestPacket.getPort();
							
				String msgACK = "Ack:0";
				DatagramPacket replyPacket2 = new DatagramPacket(msgACK.getBytes(), msgACK.length(), requestPacket.getAddress(), requestPacket.getPort());
				socket.send(replyPacket2);
				System.out.println("Acknowledgement sent to "+requestPacket.getAddress()+" "+requestPacket.getPort());
				isWaiting = false;
			} catch (IOException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		socket.close();
		
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		send("Sajjad");
		send("Ulan");
		send("Ulan");
		send("Ulan");
		
		
		
		
		
	}
	
	public static void send(String new_msg){
		
		try {
			
		DatagramSocket socket = new DatagramSocket();
		System.out.println("my port:"+ socket.getLocalPort());
		
		InetAddress aHost = InetAddress.getByName("127.0.0.1");
		
		
		DatagramPacket requestPacket = new DatagramPacket(new_msg.getBytes(), new_msg.length(), aHost, portFE);
		socket.send(requestPacket);
		System.out.println("Request sent to FE: " + new String(requestPacket.getData()));
		
		byte[] buffer1 = new byte[1000];
		DatagramPacket replyPacket = new DatagramPacket(buffer1, buffer1.length);	
		socket.receive(replyPacket);
		System.out.println("Reply Ack received from FE: " + new String(replyPacket.getData()));	
		
		
		
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
