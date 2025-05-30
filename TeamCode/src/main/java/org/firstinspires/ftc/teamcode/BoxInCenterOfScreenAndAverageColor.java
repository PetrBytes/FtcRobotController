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
    OpenCvCamera camera;
    String allianceColour = "Blue";
    boolean cameraOpened = false;
    final int RESOLUTION_WIDTH = 640;
    final int RESOLUTION_HEIGHT = 480;
    Rect centerbox = new Rect(280, 200, 80, 80);
    int[] zoneColourPercentage = new int[3];
    Mat hsvImage = new Mat();
    Mat mask = new Mat();

    @Override
    public void runOpMode() {
        // initialisation
        int viewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, viewId);

        if (gamepad1.x) allianceColour = "Blue";
        if (gamepad1.b) allianceColour = "Red";
        waitForStart();

        // teleop
        while (opModeIsActive()) {

        }
    }
}

