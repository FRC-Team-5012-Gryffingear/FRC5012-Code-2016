package com.gryffingear.y2016.systems;

import edu.wpi.first.wpilibj.CANTalon;

	public class Stager {
		private CANTalon stagerMotorA = null;
		
		
		public Stager (int ma) {
			
			stagerMotorA = new CANTalon(ma);
			// where is configureTalon ?????
			
		}
		
		public void runStager(double stagerv){
			
			stagerMotorA.set(stagerv);
		}
	}


