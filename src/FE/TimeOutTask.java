package FE;

import java.util.TimerTask;

public class TimeOutTask extends TimerTask{
	
	boolean timeOut = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		timeOut = true;
	}
	
	public boolean getTimeOut(){
		return timeOut;
	}

}
