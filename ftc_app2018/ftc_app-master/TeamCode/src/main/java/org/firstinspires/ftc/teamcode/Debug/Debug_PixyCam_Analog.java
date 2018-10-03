package org.firstinspires.ftc.teamcode.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Library.PixyCam_Analog;

@TeleOp(name="Debug: Analog Pixy", group="Debugging")
//@Disabled
public class Debug_PixyCam_Analog extends LinearOpMode {

    PixyCam_Analog pixyCamera = null;
    ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        pixyCamera = new PixyCam_Analog(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if (elapsedTime.milliseconds() > 100) // Update every tenth of a second.
            {
                elapsedTime.reset();
                telemetry.addData("Gold detected:", pixyCamera.isObjectDetected());
                telemetry.addData("Location (0-3.3) :", pixyCamera.getAnalogRead());
                telemetry.update(); // to actually send to the phone message for debugging purpose
            }
        }
    }
}
