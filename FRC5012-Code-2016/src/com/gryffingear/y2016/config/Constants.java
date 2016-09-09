package com.gryffingear.y2016.config;

public class Constants {
	public static class Controls {

	}

	public static class Drivetrain {
		public static final double RAMP_RATE = 48.0;

	}

	public static class Intake {
		public static final double RAMP_RATE = 96.0;
		public static final double BALL_SENSOR_THRESHOLD = 2.5;

		public static final double INTAKE_IN = 1.0;
		public static final double INTAKE_OUT = -1.0;

	}

	public static class Shooter {
		public static final double RAMP_RATE = 96.0;
		public static final double SHOOTING_VOLTAGE = 1.0;
		public static final double IDLE_VOLTAGE = 0.4;
		public static final double AT_SPEED_CURRENT_THRESHOLD = 5.0;

	}

	public static class Leds {

	}

	public static class Pneumatics {

	}
	
	public static class SuperSystem {
		public static final double AUTO_AIM_KP = 2.0;
		public static final double AUTO_AIM_TARGET_OFFSET = 0.15;
		public static final double AUTO_AIM_TARGET = (3.3/2.0) + AUTO_AIM_TARGET_OFFSET;
		public static final double AUTO_SPEED_KP = 2.0;
		public static final double AUTO_SPEED_KF = 5.2;
	}
}