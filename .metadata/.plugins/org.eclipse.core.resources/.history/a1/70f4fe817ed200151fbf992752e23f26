package com.gryffingear.y2016;

import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.systems.SuperSystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
public class Robot extends IterativeRobot {
   
    


Joystick joy1 = new Joystick(Ports.Controls.DRIVER_PORT_A);
Joystick joy2 = new Joystick(Ports.Controls.DRIVER_PORT_B);
Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

SuperSystem bot = SuperSystem.getInstance();


public void teleopPeriodic() {
	
	
	bot.drive.tankDrive(joy1.getRawAxis(1), joy2.getRawAxis(1));
	
	bot.led.setA(operator.getRawButton(1));
	bot.led.setB(operator.getRawButton(2));
	bot.led.setC(operator.getRawButton(3));
	bot.led.setD(operator.getRawButton(4));

}
}
