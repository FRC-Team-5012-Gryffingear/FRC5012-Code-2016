package org.usfirst.frc.team5012.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
	  Joystick driver = new Joystick(Ports.Controls.DRIVER_PORT);
	  Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);
	  SuperSystem bot = SuperSystem.getInstance();
  
    public void robotInit() {

    }
    
	
    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	}
    

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

        bot.drive.tankDrive(driver.getRawAxis(1), driver.getRawAxis(2));
    }
    
    
    public void testPeriodic() {
    
    }
    
}
