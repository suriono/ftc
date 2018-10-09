package org.firstinspires.ftc.teamcode.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Library.Chassis_Motors;
import org.firstinspires.ftc.teamcode.Library.Rev_IMU;

import java.util.Locale;


@TeleOp(name="Debug: IMU", group="Debugging")
//@Disabled
public class Debug_IMU extends LinearOpMode {
    Rev_IMU imu_robot = null;

    @Override
    public void runOpMode() {

        imu_robot = new Rev_IMU(hardwareMap);

        imu_robot.initialize();     // for competition you may want to initialize after landing for zero Yaw

        waitForStart();

        imu_robot.start();          // start measuring non stop

        while (opModeIsActive()) {

            if (imu_robot.measure()) { // if successfully measured/refreshed
                telemetry.addData("Yaw: ", imu_robot.yaw_value);
                telemetry.update();
            }
            // Pace this loop so jaw action is reasonable speed.
            sleep(10);
        }
    }
}
