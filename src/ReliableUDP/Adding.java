package ReliableUDP;

import java.net.DatagramSocket;
import java.util.HashMap;

import FE.FEBookingImpl;
import Models.Enums;
import Models.UDPMessage;

public class Adding {

	public int addingTwoNymber(int x, int y) {
		return x + y;
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
//		// String msg = "Hello ; ";
//
//		// Fornt End Send Request
//		UDPMessage udpMsg = new UDPMessage(Enums.UDPSender.FrontEnd, -1, Enums.FlightCities.Montreal,
//				Enums.Operations.bookFlight, Enums.UDPMessageType.Request);
//		HashMap<String, String> parameterMap = new HashMap<String, String>();
//		parameterMap.put("firstName", "abc");
//		parameterMap.put("lastName", "asdasd");
//		parameterMap.put("address", "asdasd");
//		parameterMap.put("phone", "1234567890");
//		parameterMap.put("destination", Enums.FlightCities.Montreal.toString());
//		parameterMap.put("date", "2016/12/1");
//		parameterMap.put("classFlight", Enums.Class.Economy.toString());
//		udpMsg.setParamters(parameterMap);
//
//		Sender s = new Sender("127.0.0.2", 1000, 2000, false);
//		Boolean status = s.send(udpMsg);
//		System.err.println("isTransferComplete : " + s.isTransferComplete);
//		System.err.println("status : " + status);
//
//		DatagramSocket socket = s.getOutGoingSocket();
//
//		// do FE staff here etc etc
//		System.out.println("Get Port # used : " + socket.getLocalPort());
//
//		socket.close();
//
//		// System.err.println("isTransferComplete : "+ s.isTransferComplete);
//		// Reciever r = new Reciever(1000,2000);
//
//		// Thread.sleep(1000);
//
//		// System.out.print("data recieved is : "+ r.getData());
		
		UDPMessage udpMsg = new UDPMessage(Enums.UDPSender.FrontEnd, -1, Enums.getFlightCitiesFromString("Montreal"),
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
		
		FEBookingImpl bookingObject = null;
		bookingObject = new FEBookingImpl();
		
		String result = bookingObject.send(udpMsg).trim();

	}

}
