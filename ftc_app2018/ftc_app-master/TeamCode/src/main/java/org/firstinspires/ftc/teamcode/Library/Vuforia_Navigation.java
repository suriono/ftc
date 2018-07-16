package org.firstinspires.ftc.teamcode.Library;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

public class Vuforia_Navigation {

    public static final String TAG = "Vuforia VuMark Sample";
    OpenGLMatrix lastRobotLocation = null;
    VuforiaLocalizer vuforia;
    VuforiaTrackables targets = null;
    VuforiaTrackable relicTemplate  = null;

    List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
    VuforiaLocalizer.Parameters parameters;

    VectorF trans=null;
    Orientation rot=null;
    // private String      vumark;         // L M or R side
    private RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
    public double X_coordinate_mm, Y_coordinate_mm;


    public Vuforia_Navigation(boolean enableExtendedTracking) {        // constructor
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AdksQ3j/////AAAAGVB9GUsSEE0BlMaVB7HcRZRM4Sv74bxusFbCpn3gwnUkr3GuOtSWhrTCHnTU/93+Im+JlrYI6///bytu1igZT48xQ6182nSTpVzJ2ZP+Q/sNzSg3qvIOMnjEptutngqB+e3mQ1+YTiDa9aZod1e8X7UvGsAJ3cfV+X/S3E4M/81d1IRSMPRPEaLpKFdMqN3AcbDpBHoqp82fAp7XWVN3qd/BRe0CAAoNsr26scPBAxvm9cizRG1WeRSFms3XkwFN6eGpH7VpNAdPPXep9RQ3lLZMTFQGOfiV/vRQXq/Tlaj/b7dkA12zBSW81MfBiXRxp06NGieFe7KvXNuu2aDyyXoaPFsI44FEGp1z/SVSEVR4"; // Insert your own key here
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        parameters.useExtendedTracking = enableExtendedTracking;        // true by default,whether to use extended tracking
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        targets = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = targets.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary since only one target
    }


    public void activate() {   // start Vuforia
        this.targets.activate();
    }

    public void deactivate() {  // stop Vuforia, it is power consumming to keep it running
        this.targets.deactivate();
    }

    public boolean isTarget_visible() {
        return ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).isVisible();
    }


    public boolean updateRobotLocation()  {

        vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
            if (pose != null) {     // target location found
                trans = pose.getTranslation();
                rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                double orient = getOrientation();
                double signofX = Math.signum(orient);
                double anglerad = Math.toRadians(orient);
                double xloc = getX();

                X_coordinate_mm = signofX*(getY()*Math.sin(anglerad) + signofX * xloc*Math.cos(anglerad));
                Y_coordinate_mm = getY()*Math.cos(anglerad) - signofX * xloc*Math.sin(anglerad);

                getCrytoboxColumn_inch();

                return true;
            }
        }
        return false;  // target not found
    }


    // distance the robot needs to go to a destination X-Y coordinate
    private double getDestinationDistance_mm(double destination_X, double destination_Y) {
        return Math.sqrt(Math.pow(getX()-destination_X,2) + Math.pow(getY()-destination_Y,2));
    }


    private double getCrytoboxColumn_inch() {
        if (vuMark == RelicRecoveryVuMark.LEFT) {
            return -7.63;
        } else if (vuMark == RelicRecoveryVuMark.RIGHT) {
            return 7.63;
        }
        return 0.0;   // when it is in the center
    }


    public double getX() { // robot x location
        return trans.get(0);
    }

    public double getY() { // robot y location actually
        return -trans.get(2);
    }

    // angle > 0 need to turn to left
    public double getAngleTowardPicture() {
        return Math.toDegrees( Math.atan2(getX(), getY()));
    }

    public float getOrientation() {  // 1st, 2nd, and 3rd angle
        return -rot.secondAngle;
    }

}
