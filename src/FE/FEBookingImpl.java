package FE;

import org.omg.CORBA.ORB;

public class FEBookingImpl extends FEBookingIntPOA {
	
	private ORB orb;
	
	public FEBookingImpl(){}

	@Override
	public String bookFlight(String firstName, String lastName, String address,
			String phone, String destination, String date, String classFlight) {
		// TODO Auto-generated method stub
		System.out.println("inside bookFlight");
		return "";
	}

	@Override
	public String getBookedFlightCount(String recordType) {
		// TODO Auto-generated method stub
		System.out.println("inside getBookedFlightCount");
		return "";
	}

	@Override
	public String editFlightRecord(String recordID, String fieldName, String newValue) {
		// TODO Auto-generated method stub
		System.out.println("inside editFlightRecord");
		return "";
	}

	@Override
	public String transferReservation(String passengerID, String currentCity, String otherCity) {
		// TODO Auto-generated method stub
		System.out.println("inside transferReservation");
		return "";
	}
	
	/**
	 * This method sets the orb
	 * @param new_orb
	 */
	public void setORB(ORB new_orb){
		orb = new_orb;
	}
	
	/**
	 * This method shut downs the orb
	 */
	public void shutdown(){
		orb.shutdown(false);
	}

}
