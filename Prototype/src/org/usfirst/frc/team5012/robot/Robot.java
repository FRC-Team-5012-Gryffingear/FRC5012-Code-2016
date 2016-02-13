
package org.usfirst.frc.team5012.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team5012.robot.Ports;
import org.usfirst.frc.team5012.robot.Drivetrain;
import org.usfirst.frc.team5012.robot.SuperSystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//Joystick driver = new Joystick(Ports.Controls.DRIVER_PORT);
	Joystick joy1 = new Joystick(Ports.Controls.JOY1_PORT);
	Joystick joy2 = new Joystick(Ports.Controls.JOY2_PORT);
	  Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);
	  SuperSystem bot = SuperSystem.getInstance();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        
    }
    


        
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
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

        bot.drive.tankdrive(joy1.getRawAxis(1), joy2.getRawAxis(1));  
        bot.intake.intake(operator.getRawAxis(5));
        bot.shoot.shooter(operator.getRawAxis(3), operator.getRawAxis(2));
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
