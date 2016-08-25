package org.usfirst.frc.team2339.robot;

public class Ports {

	public static class Controls {
		 
		public static int DRIVER_LEFT_PORT = 0;
		public static int DRIVER_RIGHT_PORT = 1;
		public static int OPERATOR_PORT = 2;
		
	}
	
	public static class Drivetrain {
		
		public static int DRIVE_MOTOR_LEFT_A = 2;
		public static int DRIVE_MOTOR_LEFT_B = 3;
		public static int DRIVE_MOTOR_RIGHT_A = 4;
		public static int DRIVE_MOTOR_RIGHT_B = 5;
	}
	
	public static class Shooter {
		
		public static int SHOOTER_MOTOR_A = 1;
		public static int SHOOTER_MOTOR_B = 6;
		public static int SHOOTER_SOLENOID = 3;
	}
	
	public static class Intake {
		public static int INTAKE_MOTOR = 0;
		public static int INTAKE_SOLENOID = 1;
	}
	
	public static class Pneumatics {

		public static int PCM_CAN_ID = 0;

	}
}
