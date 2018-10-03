package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PixyCam_Analog  {
    private DigitalChannel        digIn_detected_pixy;                // Device Object
    private AnalogInput           analogIn_pixy;

    public PixyCam_Analog(HardwareMap hardwareMap) {
        digIn_detected_pixy = hardwareMap.get(DigitalChannel.class, "pixy_digital");     //  Use generic form of device mapping
        analogIn_pixy       = hardwareMap.get(AnalogInput.class, "pixy_analog");
    }

    public boolean isObjectDetected()  {      // if object is detected
        return digIn_detected_pixy.getState();
    }
    public double getAnalogRead() {
        return  analogIn_pixy.getVoltage();
    }
}
