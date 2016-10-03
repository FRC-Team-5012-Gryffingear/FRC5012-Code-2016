package com.gryffingear.y2016.utilities;

import com.gryffingear.y2016.config.Constants;

import edu.wpi.first.wpilibj.AnalogInput;

public class Pixy {
	
	private AnalogInput in = null;
	
	public Pixy(int port) {
		in = new AnalogInput(port);
	}
	
	public double get() {
		double answer = ((Constants.SuperSystem.AUTO_AIM_TARGET) - in.getVoltage());
		
		//if(answer >= 3.25) answer = 3.3/2.0;
		
		return answer;
	}
	

}
