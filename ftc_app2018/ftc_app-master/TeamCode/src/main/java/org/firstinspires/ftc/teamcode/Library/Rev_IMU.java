package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class Rev_IMU {
    // The IMU sensor object
    private BNO055IMU imu;
    private BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    private ElapsedTime elapsed_time = new ElapsedTime();
    private Orientation angles;
    public double yaw_value;
    private double last_measured_time = -100.0;


    public Rev_IMU (HardwareMap hardwareMap) {
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        // I2C port named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");

    }

    public void initialize() {      // initialize and set to initial zero angle
        imu.initialize(parameters);
    }

    public void start ()  {  // Start the logging of measured acceleration
        imu.startAccelerationIntegration(new Position(), new Velocity(), 10);
        measure();           // get initial angles as the reference
        elapsed_time.reset();
    }

    public boolean measure () {   // measure angles
        if ((elapsed_time.milliseconds() - last_measured_time) > 10.0) {
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            yaw_value = getYaw(angles.angleUnit, angles.firstAngle);
            last_measured_time = elapsed_time.milliseconds();
            return true;
        } else {
            return false;
        }
    }

    private double getYaw(AngleUnit angleunit, double angle) {
        return AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angleunit, angle));
    }

}
