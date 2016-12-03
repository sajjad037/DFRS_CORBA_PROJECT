package Testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import FE.TimeOutTask;
import StaticContent.StaticContent;

public class Temp4Servers {

	public static void main(String[] args) {

		send(StaticContent.REPLICA_ULAN_IP_ADDRESS, StaticContent.REPLICA_ULAN_lISTENING_PORT, "Ulan");
		send(StaticContent.REPLICA_SAJJAD_IP_ADDRESS, StaticContent.REPLICA_SAJJAD_lISTENING_PORT, "Sajjad");
		send(StaticContent.REPLICA_UMER_IP_ADDRESS, StaticContent.REPLICA_UMER_lISTENING_PORT, "Ulan");
		send(StaticContent.REPLICA_FERAS_IP_ADDRESS, StaticContent.REPLICA_FERAS_lISTENING_PORT, "Ulan");

	}

	public static void send(String ip_address, int port, final String new_ans) {
		System.out.println("server is up.");

		final DatagramSocket socket;
		try {
			socket = new DatagramSocket(port);

			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							byte[] buffer2 = new byte[1000];
							DatagramPacket requestPacket2 = new DatagramPacket(buffer2, buffer2.length);
							socket.receive(requestPacket2);
							String message = new String(requestPacket2.getData());
							
							//UdpMessage udpMsg = 
							
							System.out.println("Result message received: " + message + " address: "
									+ requestPacket2.getAddress() + " portNumber: " + requestPacket2.getPort());
							
							String msgACK = new_ans;
							DatagramPacket replyPacket2 = new DatagramPacket(msgACK.getBytes(), msgACK.length(),
									requestPacket2.getAddress(), requestPacket2.getPort());
							socket.send(replyPacket2);
							System.out.println(msgACK);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			t2.start();

		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
