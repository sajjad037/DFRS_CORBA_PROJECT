package Models;

import java.io.Serializable;

import Models.Enums.FlightCities;
import Models.Enums.Operations;
import Models.Enums.UDPMessageType;
import Models.Enums.UDPSender;

/**
 * 
 * @author SajjadAshrafCan
 *
 */
public class UDPMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long sequencerNumber = -1;
	Enums.FlightCities serverName;
	Enums.Operations opernation;
	String concatenatedInputs;
	Enums.UDPSender sender;
	Enums.UDPMessageType messageType;
	Boolean status;
	int returnID;
	String replyMsg;
	
	/**
	 * 
	 * @param sequencerNumber
	 * @param serverName
	 * @param opernation
	 * @param messageType
	 */
	public UDPMessage(UDPSender sender, long sequencerNumber, FlightCities serverName, Operations opernation, UDPMessageType messageType) {
		super();
		this.sender = sender;
		this.sequencerNumber = sequencerNumber;
		this.serverName = serverName;
		this.opernation = opernation;
		this.messageType = messageType;
	}

	/**
	 * @return the sender
	 */
	public Enums.UDPSender getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(Enums.UDPSender sender) {
		this.sender = sender;
	}

	/**
	 * @return the sequencerNumber
	 */
	public long getSequencerNumber() {
		return sequencerNumber;
	}

	/**
	 * @param sequencerNumber the sequencerNumber to set
	 */
	public void setSequencerNumber(long sequencerNumber) {
		this.sequencerNumber = sequencerNumber;
	}

	/**
	 * @return the serverName
	 */
	public Enums.FlightCities getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(Enums.FlightCities serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the opernation
	 */
	public Enums.Operations getOpernation() {
		return opernation;
	}

	/**
	 * @param opernation the opernation to set
	 */
	public void setOpernation(Enums.Operations opernation) {
		this.opernation = opernation;
	}

	/**
	 * @return the concatenatedInputs
	 */
	public String getConcatenatedInputs() {
		return concatenatedInputs;
	}

	/**
	 * @param concatenatedInputs the concatenatedInputs to set
	 */
	public void setConcatenatedInputs(String concatenatedInputs) {
		this.concatenatedInputs = concatenatedInputs;
	}

	/**
	 * @return the messageType
	 */
	public Enums.UDPMessageType getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(Enums.UDPMessageType messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the returnID
	 */
	public int getReturnID() {
		return returnID;
	}

	/**
	 * @param returnID the returnID to set
	 */
	public void setReturnID(int returnID) {
		this.returnID = returnID;
	}

	/**
	 * @return the replyMsg
	 */
	public String getReplyMsg() {
		return replyMsg;
	}

	/**
	 * @param replyMsg the replyMsg to set
	 */
	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}
	
	
	
	
	
	
}
