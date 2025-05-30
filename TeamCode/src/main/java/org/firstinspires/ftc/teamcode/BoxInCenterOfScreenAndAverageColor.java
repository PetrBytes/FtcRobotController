package org.firstinspires.ftc.teamcode;

import com.sun.tools.javac.util.RawDiagnosticFormatter;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.*;

@TeleOp(name = "Box Average Color", group = "Testing")
public class BoxInCenterOfScreenAndAverageColor extends LinearOpMode{
    // any variables
    OpenCvCamera webcam;
    String allianceColour = "Blue";
    boolean cameraOpened = false;

    Rect roi1 = new Rect(230, 390, 80, 80);

    @Override
    public void runOpMode() {
        // initialisation

        if (gamepad1.x) allianceColour = "Blue";
        if (gamepad1.b) allianceColour = "Red";
        waitForStart();

        // teleop
        while (opModeIsActive()) {

        }
    }
}

