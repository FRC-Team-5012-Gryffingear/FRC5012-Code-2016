package com.gryffingear.y2016.config;

public class Ports {

	public static class Controls {

		public static int DRIVER_PORT_A = 0;
		public static int DRIVER_PORT_B = 1;
		public static int OPERATOR_PORT = 2;
	}

	public static class Drivetrain {

		public static int DRIVE_LEFT_A_PORT = 0;
		public static int DRIVE_LEFT_B_PORT = 1;
		public static int DRIVE_RIGHT_A_PORT = 3;
		public static int DRIVE_RIGHT_B_PORT = 4;
	}

	public static class Intake {
		
		public static int INTAKE_MOTOR = 6;
		public static int INTAKE_SOLENOID = 5;
		public static int OUTER_STAGE_SENSOR = 1;
		public static int INNER_STAGE_SENSOR = 0;
	}
	
	public static class Shooter{
		
		public static int SHOOTER_MOTOR_A = 7;
		public static int SHOOTER_MOTOR_B = 8; 
	}
	
	public static class Leds {

		public static int LED_STRIP_1_PORT = 0;
		public static int LED_STRIP_2_PORT = 1;
		public static int LED_STRIP_3_PORT = 3;
		public static int LED_STRIP_4_PORT = 4;

	}

	public static class Pneumatics {
		
		public static int PCM_CAN_ID = 0;

	}
	
	

}