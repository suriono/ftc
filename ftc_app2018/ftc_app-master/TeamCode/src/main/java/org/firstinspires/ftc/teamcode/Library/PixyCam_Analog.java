package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

// Use this one when setting the PixyMon to "analog/digital x"

public class PixyCam_Analog  {
    private DigitalChannel        digIn_detected_pixy;                // Device Object
    private AnalogInput           analogIn_pixy;

    public PixyCam_Analog(HardwareMap hardwareMap) {
        digIn_detected_pixy = hardwareMap.get(DigitalChannel.class, "pixy_digital");     //  Use generic form of device mapping
        analogIn_pixy       = hardwareMap.get(AnalogInput.class, "pixy_analog");
    }

    // reading from pin 1 (SPI MISO, UART Rx) from Pixy
    public boolean isObjectDetected()  {      // true if object is detected
        return digIn_detected_pixy.getState();
    }

    // reading from pin 3 (Analog out) from Pixy
    public double getAnalogRead() {   // from 0 to 3.3V where 1.15V when the object is right in front of it
        return  analogIn_pixy.getVoltage();
    }
}
