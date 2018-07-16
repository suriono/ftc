package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Chassis_Motors {

    private DcMotor leftMotor = null;  // set null if no value to prevent unpredictability

    // Constructor specifies what to initialize when creating an object
    public Chassis_Motors(HardwareMap hardwareMap) {    // constructor to create object
        leftMotor = hardwareMap.dcMotor.get("chassis_left_motor");  // the name assigned on the phone
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //also works when encoder is not used
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void move_left(double mot_power) {
        leftMotor.setPower(mot_power);

        motor_adjustment_example(); // private is an example for internal class use only
    }

    private void motor_adjustment_example() {
        // this one is not visible
    }
}
