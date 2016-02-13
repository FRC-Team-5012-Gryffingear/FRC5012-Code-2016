
package com.gryffingear.y2016.season;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.IterativeRobot;
import com.gryffingear.y2016.season.SuperSystem;
import com.gryffingear.y2016.config.Ports;
public class Robot extends IterativeRobot {
   
    



Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

SuperSystem bot = SuperSystem.getInstance();


public void teleopPeriodic() {
	
	boolean resetting = false;
	
	if (!resetting){
	if (operator.getRawButton(1)){
		bot.led.setA(true);
	
	}
		
	if (operator.getRawButton(2)){
		bot.led.setB(true);
	}
	
	if (operator.getRawButton(3)){
		bot.led.setC(true);
	}
	
	if (operator.getRawButton(4)){
		bot.led.setD(true);
	}
	
}


}
}


























