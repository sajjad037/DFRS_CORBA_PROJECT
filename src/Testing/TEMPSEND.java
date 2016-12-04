package Testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TEMPSEND {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		
		

	}

}
