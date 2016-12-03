package Replica;

import java.util.logging.Logger;

import Models.Enums;
import ReplicaManager.ReplicaManagerListner;
import StaticContent.StaticContent;
import Utilities.CLogger;

public class ReplicaMain {
	private static CLogger clogger;
	private final static Logger LOGGER = Logger.getLogger(ReplicaMain.class.getName());
	
	/*
	 * Reason of this class:
	 * 1) Create MTL, WSL, NDH server objects using ORB.
	 * 2) Listen to all incoming messages from sequencer and respective RM.
	 * 3) Parse the incoming UDP request and call it in the actual server object.
	 * 4) Replica always reply to FrontEnd port that is in UDP Object.
	 * 5) Any request that comes from Sequencer should be saved in a file as backup so that it can be restored when the replica is started again.
	 */
	
	public static void main(String[] args) {
		String msg ="";
		try {
			// initialize logger
			clogger = new CLogger(LOGGER, "Replica/Replica.log");
			msg = "Replica is UP!";
			clogger.log(msg);
			System.out.println(msg);

			// Start UDP Server
			ReplicaListner server = new ReplicaListner(clogger, StaticContent.RM1_lISTENING_PORT, Enums.UDPSender.ReplicaUlan);
			server.start();
			//server.executeTestMessage();
			server.join();
			

		} catch (Exception e) {
			System.out.println("Sequencer Exception: " + e.getMessage());
		}
	}
}
