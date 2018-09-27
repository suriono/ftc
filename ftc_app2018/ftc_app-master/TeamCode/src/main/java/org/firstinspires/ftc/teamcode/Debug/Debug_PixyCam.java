package org.firstinspires.ftc.teamcode.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Library.Chassis_Motors;
import org.firstinspires.ftc.teamcode.Library.PixyCam;

@TeleOp(name="Debug: Pixy Camera", group="Debugging")
//@Disabled
public class Debug_PixyCam extends LinearOpMode {

    PixyCam pixyCam;
    PixyCam.Block block1;
    ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        pixyCam = hardwareMap.get(PixyCam.class, "pixycam");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            if (elapsedTime.milliseconds() > 100) // Update every tenth of a second.
            {
                elapsedTime.reset();
                block1 = pixyCam.GetBiggestBlock(1);
                telemetry.addData("Block 1:", block1.toString());
                telemetry.addData("X :", block1.x);
                telemetry.addData("Y :", block1.y);
                telemetry.addData("Width :", block1.width);
                telemetry.addData("Height :", block1.height);

                telemetry.update(); // to actually send to the phone message for debugging purpose
            }
        }
    }
}
