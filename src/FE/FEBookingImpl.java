package FE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.omg.CORBA.ORB;

import Models.Enums;
import Models.UDPMessage;
import ReliableUDP.Reciever;
import ReliableUDP.Sender;
import StaticContent.StaticContent;

public class FEBookingImpl extends FEBookingIntPOA {

	private ORB orb;
	// private int portSequencer = 5555;
	// private String addressSequencer = "127.0.0.1";

	public FEBookingImpl() {
	}

	@Override
	public String bookFlight(String firstName, String lastName, String address, String phone, String destination,
			String date, String classFlight) {
		// TODO Auto-generated method stub
		System.out.println("inside bookFlight");

		String[] arr = firstName.split(":");
		// server manager cmd firstName
		// String msg = arr[0] + "|" + arr[1] + ":" + "bookFlight:" + arr[3] +
		// ":" + lastName + ":" + address + ":" + phone
		// + ":" + destination + ":" + date + ":" + classFlight;

		// Fornt End Send Request
		UDPMessage udpMsg = new UDPMessage(Enums.UDPSender.FrontEnd, -1, Enums.getFlightCitiesFromString(arr[0]),
				Enums.Operations.bookFlight, Enums.UDPMessageType.Request);
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("firstName", arr[2]);
		parameterMap.put("lastName", lastName);
		parameterMap.put("address", address);
		parameterMap.put("phone", phone);
		parameterMap.put("destination", destination);
		parameterMap.put("date", date);
		parameterMap.put("classFlight", classFlight);
		udpMsg.setParamters(parameterMap);
		udpMsg.setManagerID(arr[1]);
		udpMsg.setFrontEndPort(-1);

		return send(udpMsg);
	}

	@Override
	public String getBookedFlightCount(String recordType) {
		// TODO Auto-generated method stub
		System.out.println("inside getBookedFlightCount");
		// String msg = "getBookedFlightCount:" + recordType;
		String[] arr = recordType.split(":");

		UDPMessage udpMsg = new UDPMessage(Enums.UDPSender.FrontEnd, -1, Enums.getFlightCitiesFromString(arr[0]),
				Enums.Operations.getBookedFlightCount, Enums.UDPMessageType.Request);
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("recordType", arr[2]);
		udpMsg.setParamters(parameterMap);
		udpMsg.setManagerID(arr[1]);
		udpMsg.setFrontEndPort(-1);

		return send(udpMsg);
	}

	@Override
	public String editFlightRecord(String recordID, String fieldName, String newValue) {
		// TODO Auto-generated method stub
		System.out.println("inside editFlightRecord");

		// String msg = "editFlightRecord:" + recordID + ":" + fieldName + ":" +
		// newValue;
		String[] arr = recordID.split(":");

		UDPMessage udpMsg = new UDPMessage(Enums.UDPSender.FrontEnd, -1, Enums.getFlightCitiesFromString(arr[0]),
				Enums.Operations.editFlightRecord, Enums.UDPMessageType.Request);
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("recordID", arr[2]);
		parameterMap.put("fieldName", fieldName);
		parameterMap.put("newValue", newValue);
		udpMsg.setParamters(parameterMap);
		udpMsg.setManagerID(arr[1]);
		udpMsg.setFrontEndPort(-1);

		return send(udpMsg);
	}

	@Override
	public String transferReservation(String passengerID, String currentCity, String otherCity) {
		// TODO Auto-generated method stub
		System.out.println("inside transferReservation");

		// String msg = "transferReservation:" + passengerID + ":" + currentCity
		// + ":" + otherCity;
		String[] arr = passengerID.split(":");
		UDPMessage udpMsg = new UDPMessage(Enums.UDPSender.FrontEnd, -1, Enums.getFlightCitiesFromString(arr[0]),
				Enums.Operations.transferReservation, Enums.UDPMessageType.Request);
		HashMap<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("passengerID", arr[2]);
		parameterMap.put("currentCity", currentCity);
		parameterMap.put("otherCity", otherCity);
		udpMsg.setParamters(parameterMap);
		udpMsg.setManagerID(arr[1]);
		udpMsg.setFrontEndPort(-1);

		return send(udpMsg);
	}

	/**
	 * This method sets the orb
	 * 
	 * @param new_orb
	 */
	public void setORB(ORB new_orb) {
		orb = new_orb;
	}

	/**
	 * This method shut downs the orb
	 */
	public void shutdown() {
		orb.shutdown(false);
	}

	public String send(UDPMessage new_msg) {

		String result = "0";
		final String[][] resultInfo = new String[4][4];
		System.out.println("a");
		final DatagramSocket socket;
		try {
			socket = new DatagramSocket();

			Sender s = new Sender(StaticContent.SEQUENCER_IP_ADDRESS, StaticContent.SEQUENCER_lISTENING_PORT,
					StaticContent.FRONT_END_ACK_PORT, false, socket);

			new_msg.setFrontEndPort(socket.getLocalPort());
			try {
				new_msg.setFrontEndIP(InetAddress.getByName(StaticContent.FRONT_END_IP_ADDRESS));
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Boolean status = s.send(new_msg);

			System.out.println(status);

			/*
			 * while(true){
			 * 
			 * Reciever r = new Reciever(a, StaticContent.FRONT_END_ACK_PORT);
			 * 
			 * System.out.println("the data received is : " +
			 * r.getData().getServerName());
			 * 
			 * System.out.println("the data received is : " +
			 * r.getData().getSender()); }
			 */

			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					int i = 0;
					boolean isWaiting2 = true;
					while (isWaiting2) {
						try {

							System.out.println("waiting for UDP message i: " + i);
							byte[] buffer2 = new byte[1000];
							DatagramPacket requestPacket2 = new DatagramPacket(buffer2, buffer2.length);
							socket.receive(requestPacket2);

							String message = new String(requestPacket2.getData());
							System.out.println("Result message received: " + message + " address: "
									+ requestPacket2.getAddress() + " portNumber: " + requestPacket2.getPort());
							resultInfo[i][0] = "0";
							resultInfo[i][1] = message;
							resultInfo[i][2] = requestPacket2.getAddress().toString();
							resultInfo[i][3] = Integer.toString(requestPacket2.getPort());

							i++;

							if (i == 4) {
								isWaiting2 = false;
								System.out.println("i = " + i + ", isWaiting2 set to: " + isWaiting2);
								break;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			t2.start();
			// t2.join();

			Timer timer = new Timer();
			TimeOutTask timeOutTask = null;
			boolean isWaiting = true;

			timer.schedule(timeOutTask = new TimeOutTask(), StaticContent.FRONT_END_SERVER_REPLY_TIME_OUT);

			int count = 0;
			while (isWaiting) {
				count++;
				System.out.print(count + " ");
				if (count % 20 == 0) {
					System.out.println();
				}

				if (timeOutTask.getTimeOut() || !t2.isAlive()) {
					isWaiting = false;
					t2.stop();

					if (timeOutTask.getTimeOut()) {
						System.out.println("time out has occured:");
					}
					else if(!t2.isAlive())
					{
						System.out.println("All Messages arrived.");
					}

					for (int k = 0; k < 4; k++) {
						if (resultInfo[k][1] == null) {
							resultInfo[k][0] = "0";
							resultInfo[k][1] = "X";
							resultInfo[k][2] = "X";
							resultInfo[k][3] = "X";
						}
						System.out.println("resultInfo[" + k + "][0] = " + resultInfo[k][0]);
						System.out.println("resultInfo[" + k + "][1] = " + resultInfo[k][1]);
						System.out.println("resultInfo[" + k + "][2] = " + resultInfo[k][2]);
						System.out.println("resultInfo[" + k + "][3] = " + resultInfo[k][3]);
					}
				}

			}
			socket.close();

		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		result = compareResults(resultInfo);
		System.out.println("final result: " + result);

		return result;
	}

	/**
	 * This method compares 4 results that was sent by replicas to verify the
	 * correct one even if one is incorrect and another is lost.
	 * 
	 * @param new_resultInfo
	 *            information of replicas
	 * @return correct result
	 */
	public String compareResults(String[][] new_resultInfo) {

		String finalResult = "";
		int j = 0;
		if (!new_resultInfo[0][1].equalsIgnoreCase("X")) {
			finalResult = new_resultInfo[j][1];
		} else {
			j++;
			finalResult = new_resultInfo[j][1];
		}

		int count = 0;
		for (int i = j + 1; i < 4; i++) {
			if (finalResult.equalsIgnoreCase(new_resultInfo[i][1])) {
				count++;
			}
		}

		if (count == 0) {
			if (!new_resultInfo[j + 1][1].equalsIgnoreCase("X")) {
				finalResult = new_resultInfo[j + 1][1];
			} else {
				finalResult = new_resultInfo[j + 2][1];
			}
		}

		return finalResult;
	}

}
