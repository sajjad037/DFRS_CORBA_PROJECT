package Testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import FE.TimeOutTask;
import Models.UDPMessage;
import ReliableUDP.Reciever;
import ReliableUDP.Sender;
import StaticContent.StaticContent;

public class TempSequencer {

	public static void main(String[] args) {

		System.out.println("Temp sequencer is up and running.");

		boolean isWaiting = true;

		while (isWaiting) {

			Reciever r = new Reciever(StaticContent.SEQUENCER_lISTENING_PORT,
					StaticContent.FRONT_END_ACK_PORT);

			System.out.println("the data received is : "
					+ r.getData().getServerName());
			
		//	r.getData().setFrontEndPort(frontEndPort)

			try {
			sendDoc(r.getData(), StaticContent.REPLICA_ULAN_IP_ADDRESS, StaticContent.REPLICA_ULAN_lISTENING_PORT);
			
			
			sendDoc(r.getData(), StaticContent.REPLICA_SAJJAD_IP_ADDRESS, StaticContent.REPLICA_SAJJAD_lISTENING_PORT);
			
			sendDoc(r.getData(), StaticContent.REPLICA_UMER_IP_ADDRESS, StaticContent.REPLICA_UMER_lISTENING_PORT);
			
			sendDoc(r.getData(), StaticContent.REPLICA_FERAS_IP_ADDRESS, StaticContent.REPLICA_FERAS_lISTENING_PORT);
			
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	public static void sendDoc(UDPMessage udpMsg, String aHost, int aPort) throws SocketException{
					
			Sender s = new Sender(aHost, aPort, StaticContent.SEQUENCER_ACK_PORT, false, new DatagramSocket());
			
			s.send(udpMsg);
			
			

	}

}
