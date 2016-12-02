package Replica;

import java.util.logging.Logger;

import Models.Enums;
import ReplicaManager.ReplicaManagerListner;
import StaticContent.StaticContent;
import Utilities.CLogger;

public class ReplicaMain {
	private static CLogger clogger;
	private final static Logger LOGGER = Logger.getLogger(ReplicaMain.class.getName());
	
	public static void main(String[] args) {
		String msg ="";
		try {
			// initialize logger
			clogger = new CLogger(LOGGER, "Replica/Replica.log");
			msg = "Replica is UP!";
			clogger.log(msg);
			System.out.println(msg);

			// Start UDP Server
			ReplicaManagerListner server = new ReplicaManagerListner(clogger, StaticContent.RM1_lISTENING_PORT, Enums.UDPSender.ReplicaUlan);
			server.start();
			//server.executeTestMessage();
			server.join();
			

		} catch (Exception e) {
			System.out.println("Sequencer Exception: " + e.getMessage());
		}
	}
}
