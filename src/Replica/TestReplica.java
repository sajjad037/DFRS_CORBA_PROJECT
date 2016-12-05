package Replica;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;

import Models.Enums;
import Models.UDPMessage;
import ReliableUDP.Sender;
import StaticContent.*;
public class TestReplica {
	
	public static void main(String[] args) {		
		try {
			
		//Booking Flight ...	
		UDPMessage udpMsg = new UDPMessage(Enums.UDPSender.Sequencer, 1, Enums.getFlightCitiesFromString("Montreal"),
				Enums.Operations.bookFlight, Enums.UDPMessageType.Request);
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("firstName", "Ulan");
		parameterMap.put("lastName", "Baitassov");
		parameterMap.put("address", "Verdun");
		parameterMap.put("phone", "5145606164");
		parameterMap.put("destination", "Washington");
		parameterMap.put("date", "02/12/2016");
		parameterMap.put("classFlight", "economy");
		udpMsg.setParamters(parameterMap);		
		udpMsg.setManagerID("-1");		
		udpMsg.setFrontEndPort(-1);	
		
		DatagramSocket socket;
	
			socket = new DatagramSocket();
	
		System.out.println("try to Send .... ");
		//Sender s = new Sender(destinationIP, destinationPort, acknowledgementPort, false, socket);
		Sender s = new Sender(StaticContent.REPLICA_SAJJAD_IP_ADDRESS, StaticContent.REPLICA_SAJJAD_lISTENING_PORT, false, socket);
		if(s.send(udpMsg))
		{
			//release Port
			if(socket != null && !socket.isClosed())
				socket.close();
			
			System.out.println("Successfully Send ..");
		}		
		else
		{
			System.out.println("Fail to Send ..");
		}
		
		if(socket != null && !socket.isClosed())
			socket.close();
	
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}

