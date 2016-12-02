package ReplicaManager;

import java.util.logging.Logger;

import Models.Enums;
import Replica.ReplicaListner;
import StaticContent.StaticContent;
import Utilities.CLogger;

public class ReplicaManagerMain {
	private static CLogger clogger;
	private final static Logger LOGGER = Logger.getLogger(ReplicaManagerMain.class.getName());
	public static void main(String[] args) {
		String msg ="";
		try {
			// initialize logger
			clogger = new CLogger(LOGGER, "ReplicaManager/ReplicaManager.log");
			msg = "Replica is UP!";
			clogger.log(msg);
			System.out.println(msg);

			// Start UDP Server
			ReplicaListner server = new ReplicaListner(clogger, StaticContent.REPLICA_ULAN_lISTENING_PORT, Enums.UDPSender.ReplicaUlan);
			server.start();
			//server.executeTestMessage();
			server.join();
			

		} catch (Exception e) {
			System.out.println("Sequencer Exception: " + e.getMessage());
		}
	}
}
