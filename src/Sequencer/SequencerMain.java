package Sequencer;

import java.util.logging.Logger;

import ReliableUDP.Reciever;
import StaticContent.StaticContent;
import Utilities.CLogger;

public class SequencerMain {

	private static CLogger clogger;
	private final static Logger LOGGER = Logger.getLogger(SequencerMain.class.getName());
	

	public static void main(String[] args) {
		String msg ="";
		try {
			// initialize logger
			clogger = new CLogger(LOGGER, "Sequencer/Sequencer.log");
			msg = "Sequencer is UP!";
			clogger.log(msg);
			System.out.println(msg);

//			// Start UDP Server
//			SequencerListner server = new SequencerListner(clogger);
//			server.start();
//			//server.executeTestMessage();
//			server.join();
			
			while(true){
				Reciever r = new Reciever(StaticContent.SEQUENCER_lISTENING_PORT, StaticContent.SEQUENCER_ACK_PORT);
				
				//Thread.sleep(1000);
				//System.err.println("isTransferComplete : "+ s.isTransferComplete); 
				
				System.out.println("data recieved is : "+ r.getData().getSequencerNumber());				
			}

			

		} catch (Exception e) {
			System.out.println("Sequencer Exception: " + e.getMessage());
		}
	}
}
