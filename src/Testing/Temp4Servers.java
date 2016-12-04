package Testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import FE.TimeOutTask;
import ReliableUDP.Reciever;
import ReliableUDP.Sender;
import StaticContent.StaticContent;

public class Temp4Servers {

	public static void main(String[] args) {

		send(StaticContent.REPLICA_ULAN_IP_ADDRESS, StaticContent.REPLICA_ULAN_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_ULAN, "Ulan");
		send(StaticContent.REPLICA_SAJJAD_IP_ADDRESS, StaticContent.REPLICA_SAJJAD_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_SAJJAD, "Sajjad");
		send(StaticContent.REPLICA_UMER_IP_ADDRESS, StaticContent.REPLICA_UMER_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_UMER,"Ulan");
		send(StaticContent.REPLICA_FERAS_IP_ADDRESS, StaticContent.REPLICA_FERAS_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT_FOR_REPLICA_FERAS, "Ulan");

	}

	public static void send(final String ip_address, final int port, final int acknowledgementPort, final String new_ans) {
		System.out.println("server is up: "+ ip_address+" , port: "+ port);
			
			
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
						
						Reciever r = new Reciever(port, acknowledgementPort);

						System.out.println("the data received is : "
								+ r.getData().getServerName());
						
						System.out.println("the data received is : "
								+ r.getData().getSender());
						
						
						
						
						InetAddress aHostFE;
						try {
							aHostFE = InetAddress.getByName(StaticContent.FRONT_END_IP_ADDRESS);
						
						int portFE = r.getData().getFrontEndPort();
						System.out.println("abc port: "+ portFE);
						
			//			DatagramSocket s = new DatagramSocket(portFE);
						DatagramSocket socket = new DatagramSocket();
						System.out.println("my socket is : "+ socket.getLocalPort());
						
						DatagramPacket requestPacket1 = new DatagramPacket(new_ans.getBytes(), new_ans.length(), aHostFE, portFE);
						
						if(port!= StaticContent.REPLICA_FERAS_lISTENING_PORT)
						socket.send(requestPacket1);
						
				//		Sender s = new Sender(StaticContent.FRONT_END_IP_ADDRESS, portFE, 19091, true, new DatagramSocket());
				//		s.send(r.getData());
						if(socket!=null){
							socket.close();
						}
						
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});
			t2.start();
			
	

			
	}

}
