package com.gryffingear.y2016.season;
import com.gryffingear.y2016.config.Ports;
import com.gryffingear.y2016.systems.LedStrips;




public class SuperSystem {
private static SuperSystem instance= null;
public LedStrips led = null;


private SuperSystem() {
led = new LedStrips(Ports.Leds.LED_STRIP_1_PORT,Ports.Leds.LED_STRIP_2_PORT,Ports.Leds.LED_STRIP_3_PORT,Ports.Leds.LED_STRIP_4_PORT);

}

public static SuperSystem getInstance() {

  if (instance == null) {
    instance = new SuperSystem();
  }
  return instance;
}



}