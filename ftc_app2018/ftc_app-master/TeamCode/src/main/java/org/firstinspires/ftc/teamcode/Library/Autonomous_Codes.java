package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Autonomous_Codes extends LinearOpMode {
    Chassis_Motors LeftMotor = null;
    ElapsedTime autonomous_elapsetime = new ElapsedTime();

    @Override
    public void runOpMode() {
        // create objects
        LeftMotor = new Chassis_Motors(hardwareMap);

        waitForStart();
        Autonomous_Codes();
        while (opModeIsActive()) // after autonomous is done wait for manual stop or stop after the timer
        {
            idle();
        }
    }
    protected abstract void Autonomous_Codes();


    // All autonomous codes below

    public void run_left_motor(double timer_sec, double motor_power) {

        LeftMotor.move_left(motor_power);

        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < timer_sec) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        LeftMotor.move_left(0.0); // zero power to stop
    }
}
