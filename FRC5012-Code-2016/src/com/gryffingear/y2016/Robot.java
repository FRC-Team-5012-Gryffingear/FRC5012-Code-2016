package com.gryffingear.y2016;

import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.systems.SuperSystem;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	Joystick driverL = new Joystick(Ports.Controls.DRIVER_PORT_A);
	Joystick driverR = new Joystick(Ports.Controls.DRIVER_PORT_B);
	Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

	SuperSystem bot = SuperSystem.getInstance();

	public void teleopPeriodic() {
		
		bot.poke();

		bot.drive(driverL.getRawAxis(1), driverR.getRawAxis(1));

		bot.magicshot(operator.getRawButton(6), operator.getRawButton(2), operator.getRawButton(7),
				operator.getRawButton(8));
		
		bot.updateSmartDashboard();

	}
}
