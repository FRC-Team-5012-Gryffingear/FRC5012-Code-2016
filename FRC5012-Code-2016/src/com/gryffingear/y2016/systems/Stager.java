package com.gryffingear.y2016.systems;

import com.gryffingear.y2016.config.Constants;

import edu.wpi.first.wpilibj.CANTalon;

	public class Stager {
		private CANTalon stagerMotorA = null;
		
		
		public Stager (int ma) {
			
			stagerMotorA = configureTalon(new CANTalon(ma));
			
			
		}
		private CANTalon configureTalon(CANTalon in) {

			in.clearStickyFaults();
			in.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			in.setVoltageRampRate(Constants.Shooter.RAMP_RATE);
			in.enableBrakeMode(true);
			in.enableControl();
			System.out.println("[CANTalon]" + in.getDescription() + 
					" Initialized at device ID: " + in.getDeviceID());
			return in;
		}
		
		public void runStager(double stagerv){
			
			stagerMotorA.set(stagerv);
		}
	}


