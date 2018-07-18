package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Autonomous_Codes extends LinearOpMode { // sequence run by autonomous
    protected abstract void Autonomous_Mode();  // All autonomous mode declaration

    // --------------- Declaration to be edited -------------------------
    Chassis_Motors LeftMotor = null;
    ElapsedTime autonomous_elapsetime = new ElapsedTime();
    // ------------------------------------------------------------------

    @Override
    
    // ------------------- below is for all autonomous common to all scenarios -------------
    public void runOpMode() throws InterruptedException {
        
        // ------- create objects to be edited ------------------
        LeftMotor = new Chassis_Motors(hardwareMap);
        //
        // ------------------------------------------------------

        waitForStart();    // wait until the Start button is pressed

        // Add sequence common to all autonomous at the beginning here, Jewel for example
        //
        //

        Autonomous_Mode();       // where you put autonomous sequence for each scenaria
        // You can add a line to stop the robot here if necessary
        while (opModeIsActive()) // after autonomous is done wait for manual stop or stop after the timer
        {
            idle();
        }
    }


    
    
    // ========================All autonomous codes below to be edited =========================

    private void run_left_motor(double timer_sec, double motor_power) {

        LeftMotor.move_left(motor_power);

        autonomous_elapsetime.reset();
        while(autonomous_elapsetime.seconds() < timer_sec && opModeIsActive()) { // until it passes 5 seconds
            sleep(1);
            idle();
        }
        LeftMotor.move_left(0.0); // zero power to stop
    }
}
