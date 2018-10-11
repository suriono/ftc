package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;

/**
 * Created by Michael Vierra, FTC 8461 on 9/13/2017.
 */

/*
Bytes    16-bit word    Description
        ----------------------------------------------------------------
        0, 1     y              sync: 0xaa55=normal object, 0xaa56=color code object
        2, 3     y              checksum (sum of all 16-bit words 2-6, that is, bytes 4-13)
        4, 5     y              signature number
        6, 7     y              x center of object
        8, 9     y              y center of object
        10, 11   y              width of object
        12, 13   y              height of object
        */

public abstract class PixyCam_Byte  {

    I2cDeviceSynch pixy = null;

    public PixyCam_Byte (HardwareMap hardwareMap) {
        pixy = hardwareMap.i2cDeviceSynch.get("pixy");
        //setting Pixy's I2C Address
        pixy.setI2cAddress(I2cAddr.create7bit(0x54));
        I2cDeviceSynch.ReadWindow readWindow = new I2cDeviceSynch.ReadWindow (1, 26,
                I2cDeviceSynch.ReadMode.REPEAT);
        pixy.setReadWindow(readWindow);
        pixy.engage();
    }

    public int get_Width() {
        return pixy.read8(10) << 8 | pixy.read8(11);
    }

    public int get_Height() {
        return pixy.read8(12) << 8 | pixy.read8(13);
    }

    private int get_X() {
        return pixy.read8(6) << 8 | pixy.read8(7);
    }

    public int get_Signature() {
        return pixy.read8(4) << 8 | pixy.read8(5);
    }

    public int get_dX() {  // how far from the orientation of the robot in pixel
        // X pixel from 0 - 319 where 160 is about the center
        return get_X() - 160; // positive = object is to the right side of the robot
    }
}
