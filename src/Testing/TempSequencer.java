package Testing;

import java.net.DatagramSocket;
import java.net.SocketException;

import Models.UDPMessage;
import ReliableUDP.Reciever;
import ReliableUDP.Sender;
import StaticContent.StaticContent;

public class TempSequencer {

	public static void main(String[] args) throws SocketException {

		System.out.println("Temp sequencer is up and running.");

		boolean isWaiting = true;
		DatagramSocket scoketReceiver = null;
		scoketReceiver = new DatagramSocket(StaticContent.SEQUENCER_lISTENING_PORT);
		while (isWaiting) {

			Reciever r = new Reciever(scoketReceiver);

			System.out.println("the data received is : "
					+ r.getData().getServerName());
			
		//	r.getData().setFrontEndPort(frontEndPort)

			try {
			sendDoc(r.getData(), StaticContent.REPLICA_ULAN_IP_ADDRESS, StaticContent.REPLICA_ULAN_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_ULAN);
			
			sendDoc(r.getData(), StaticContent.REPLICA_SAJJAD_IP_ADDRESS, StaticContent.REPLICA_SAJJAD_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_SAJJAD);
			
			sendDoc(r.getData(), StaticContent.REPLICA_UMER_IP_ADDRESS, StaticContent.REPLICA_UMER_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_UMER);
			
			sendDoc(r.getData(), StaticContent.REPLICA_FERAS_IP_ADDRESS, StaticContent.REPLICA_FERAS_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_FERAS);
			
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		scoketReceiver.close();

	}
	
	
	public static void sendDoc(UDPMessage udpMsg, String aHost, int aPort, int ackPort) throws SocketException{
					
			Sender s = new Sender(aHost, aPort, false, new DatagramSocket());
			
			s.send(udpMsg);
			
			

	}

}
