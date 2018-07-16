package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Autonomous_Codes extends LinearOpMode { // sequence run by autonomous
    
    // --------------- Declaration to be edited -------------------------
    Chassis_Motors LeftMotor = null;
    ElapsedTime autonomous_elapsetime = new ElapsedTime();
    // ------------------------------------------------------------------

    @Override
    
    // ------------------- below is for all autonomous initialization -------------------
    public void runOpMode() throws InterruptedException {
        
        // ------- create objects to be edited ------------------
        LeftMotor = new Chassis_Motors(hardwareMap);
        
        waitForStart();
        // ------------------------------------------------------
        
        Autonomous_Mode();       // where you put autonomous sequence
        
        while (opModeIsActive()) // after autonomous is done wait for manual stop or stop after the timer
        {
            idle();
        }
        // You could add a line to stop the robot just in case after Op Mode is not active
    }
    protected abstract void Autonomous_Mode();  // All autonomous mode declaration

    
    
    // ========================All autonomous codes below to be edited =========================

    public void run_left_motor(double timer_sec, double motor_power) {

        LeftMotor.move_left(motor_power);

        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        LeftMotor.move_left(0.0); // zero power to stop
    }
}
