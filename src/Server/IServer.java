package Server;

import java.rmi.RemoteException;

public interface IServer {

	/**
	 * Book Flight for User.
	 * Also updated Flight seat count in Flights data 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 * @param destination
	 * @param date
	 * @param _class
	 * @return
	 * @throws RemoteException
	 */
	public String bookFlight(String firstName, String lastName, String address, String phone, String destination,
			String date, String _class);

	/**
	 * Get the Count of Booked Flight.
	 * 
	 * @param recordType equal to (FlightClass:ManagerID)
	 * @return
	 * @throws RemoteException
	 */
	public String getBookedFlightCount(String recordType);

	/**
	 * Update a particular field for  new value in Flight.
	 * In case of delete operation also deleted the all linked user booking 
	 * @param recordID
	 *            equals to (RecordID is have other wise -1:ManagerID otherwise
	 *            -1)
	 * @param fieldName
	 *            Operation to Perform
	 * @param newValue
	 *            values for that operations.
	 * @return
	 * @throws RemoteException
	 */
	public String editFlightRecord(String recordID, String fieldName, String newValue) ;
	
    /**
     * Operation transferReservation
     */
    public String transferReservation(String passengerID, String currentCity, String otherCity);

}
