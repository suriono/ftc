package org.firstinspires.ftc.teamcode.Competition;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Library.Autonomous_Codes;

@Autonomous(name = "Blue Alliance Left Side", group = "Competition")

public class Autonomous_Blue extends Autonomous_Codes {
    @Override

    protected void Autonomous_Codes() { // using Autonomous_Codes

        run_left_motor(10, 0.2); // use Autonomous_Codes here

    }
}
