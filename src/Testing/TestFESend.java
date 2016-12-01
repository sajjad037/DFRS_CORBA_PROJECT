package Testing;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import FE.FEBookingImpl;


public class TestFESend {
	
	FEBookingImpl bookingObject = null;
	
	@BeforeClass 
	public static void beforeClass() {
		System.out.println("Before Class Test FE Send");		
	}
	@AfterClass 
	public static void  afterClass() {
		System.out.println("After Class Test FE Send");
	}
	
	@Before 
	public void before() {
		System.out.print("inside ");
		bookingObject = new FEBookingImpl();
	}
	
	@After
	public void after() {
		System.out.println("outside test ");
	}
	
	//Test when 3 server sends the result, and 1 doesn't
	@Test
	public void testSend() {
		System.out.println("testSend");	
		
		String result = bookingObject.send("Ulan").trim();
		
		System.out.println("result:" +result);
		assertTrue(result.equalsIgnoreCase("Ulan"));
	}
	
}
