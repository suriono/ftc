package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Use this one when setting the PixyMon to "analog/digital x"

public class PixyCam_Analog  {
    private DigitalChannel        digIn_detected_pixy = null;                // Device Object
    private AnalogInput           analogIn_pixy = null;

    public PixyCam_Analog(HardwareMap hardwareMap) {
        //digIn_detected_pixy = hardwareMap.get(DigitalChannel.class, "pixy_digital");     //  Use generic form of device mapping
        digIn_detected_pixy = hardwareMap.digitalChannel.get("pixy_digital");  // if it doesn't work, try the above
        //analogIn_pixy       = hardwareMap.get(AnalogInput.class, "pixy_analog");
        analogIn_pixy       = hardwareMap.analogInput.get("pixy_analog");       // if it doesn't work, try the above
    }

    // reading from pin 1 (SPI MISO, UART Rx) from Pixy
    public boolean isObjectDetected()  {      // true if object is detected
        return digIn_detected_pixy.getState();
    }

    // reading from pin 3 (Analog out) from Pixy
    public double getAnalogRead() {   // from 0 to 3.3V where 1.15V=middle, higher means object is to the right
        return  analogIn_pixy.getVoltage();
    }
}
