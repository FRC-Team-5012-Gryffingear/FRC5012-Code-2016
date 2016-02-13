package com.gryffingear.y2016.systems;
import com.gryffingear.y2016.config.Ports;




public class SuperSystem {
private static SuperSystem instance= null;
public LedStrips led = null;
public Drivetrain drive = null;

private SuperSystem() {
	drive = new Drivetrain(Ports.Drivetrain.DRIVE_LEFT_A_PORT, Ports.Drivetrain.DRIVE_LEFT_B_PORT,
	        Ports.Drivetrain.DRIVE_RIGHT_A_PORT, Ports.Drivetrain.DRIVE_RIGHT_B_PORT);
	
	led = new LedStrips(Ports.Leds.LED_STRIP_1_PORT,Ports.Leds.LED_STRIP_2_PORT,Ports.Leds.LED_STRIP_3_PORT,Ports.Leds.LED_STRIP_4_PORT);

}

public static SuperSystem getInstance() {

  if (instance == null) {
    instance = new SuperSystem();
  }
  return instance;
}



}