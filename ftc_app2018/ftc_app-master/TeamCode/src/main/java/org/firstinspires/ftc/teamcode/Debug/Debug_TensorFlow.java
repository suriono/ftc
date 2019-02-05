package org.firstinspires.ftc.teamcode.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Library.PixyCam_Analog;
import org.firstinspires.ftc.teamcode.Library.TensorFlow;

@TeleOp(name="Debug: TensorFlow", group="Debugging")
//@Disabled
public class Debug_TensorFlow extends LinearOpMode {

    TensorFlow tensorflow = null;
    ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        tensorflow = new TensorFlow(hardwareMap);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if (elapsedTime.milliseconds() > 100) // Update every tenth of a second.
            {
                elapsedTime.reset();

                telemetry.addData("is phone compatible?:", tensorflow.isPhoneCompatible());
                if (tensorflow.isPhoneCompatible()) {
                    int location_direction = tensorflow.runTensorFlow();
                    telemetry.addData("Location:", location_direction);
                    if (location_direction < 2) { // only -1, 0, 1 for left, middle and right
                        telemetry.addData("Estimate angle: ", tensorflow.angle_gold);
                        if (location_direction < 0) {
                            telemetry.addData("Gold location: ", "Left");
                        } else if( location_direction<1) {
                            telemetry.addData("Gold location: ", "Center");
                        } else if( location_direction<2) {
                            telemetry.addData("Gold location: ", "Right");
                        }
                    }
                }
                telemetry.update(); // to actually send to the phone message for debugging purpose
            }

            idle();
        }


        tensorflow.stopTensorFlow();  // stop it at the end of autonomous
    }
}
