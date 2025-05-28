package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.*;

@TeleOp(name = "Detection Standalone", group = "Testing")
public class DetectionTest extends LinearOpMode {

    OpenCvCamera camera;
    String allianceColour = "Blue";

    // Detection zones
    Rect roi1 = new Rect(230, 390, 80, 80);
    Rect roi2 = new Rect(650, 376, 80, 80);
    Rect roi3 = new Rect(1080, 390, 80, 80);

    int[] zoneColourPercentage = new int[3];
    Mat hsvImage = new Mat();
    Mat mask = new Mat();

    boolean cameraOpened = false;

    @Override
    public void runOpMode() {
        int viewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, viewId);

        camera.setPipeline(new OpenCvPipeline() {
            @Override
            public Mat processFrame(Mat input) {
                try {
                    Imgproc.cvtColor(input, hsvImage, Imgproc.COLOR_RGB2HSV);

                    Scalar lowerRed = new Scalar(0, 100, 100);
                    Scalar upperRed = new Scalar(179, 255, 255);
                    Scalar lowerBlue = new Scalar(100, 200, 60);
                    Scalar upperBlue = new Scalar(120, 255, 100);

                    switch (allianceColour) {
                        case "Blue":
                            Core.inRange(hsvImage, lowerBlue, upperBlue, mask);
                            break;
                        case "Red":
                            Core.inRange(hsvImage, lowerRed, upperRed, mask);
                            break;
                    }

                    Imgproc.rectangle(input, roi1, new Scalar(0, 0, 255), 2);
                    Imgproc.rectangle(input, roi2, new Scalar(0, 255, 0), 2);
                    Imgproc.rectangle(input, roi3, new Scalar(255, 0, 0), 2);

                    double area1 = roi1.width * roi1.height;
                    double area2 = roi2.width * roi2.height;
                    double area3 = roi3.width * roi3.height;

                    zoneColourPercentage[0] = (int) (100.0 * Core.countNonZero(mask.submat(roi1)) / area1);
                    zoneColourPercentage[1] = (int) (100.0 * Core.countNonZero(mask.submat(roi2)) / area2);
                    zoneColourPercentage[2] = (int) (100.0 * Core.countNonZero(mask.submat(roi3)) / area3);

                } finally {
                    hsvImage.release();
                    mask.release();
                }
                return input;
            }
        });

        telemetry.addLine("Opening camera...");
        telemetry.update();

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                try {
                    camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
                    cameraOpened = true;
                } catch (Exception e) {
                    telemetry.addLine("1280x720 failed, trying 640x480...");
                    telemetry.update();
                    try {
                        camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
                        cameraOpened = true;
                    } catch (Exception fallbackError) {
                        telemetry.addLine("Fallback resolution also failed.");
                        telemetry.update();
                    }
                }
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addData("Camera error", errorCode);
                telemetry.update();
            }
        });

        waitForStart();

        while (opModeIsActive()) {
            if (!cameraOpened) {
                telemetry.addLine("Camera not opened");
                telemetry.update();
                continue;
            }

            if (gamepad1.x) allianceColour = "Blue";
            if (gamepad1.b) allianceColour = "Red";

            telemetry.addData("Alliance", allianceColour);
            telemetry.addData("Zone 1 %", zoneColourPercentage[0]);
            telemetry.addData("Zone 2 %", zoneColourPercentage[1]);
            telemetry.addData("Zone 3 %", zoneColourPercentage[2]);
            telemetry.addData("Detected Position", getPosition());
            telemetry.update();

            sleep(100);
        }

        camera.stopStreaming();
    }

    int getPosition() {
        if (zoneColourPercentage[0] > zoneColourPercentage[1] && zoneColourPercentage[0] > zoneColourPercentage[2]) {
            return 1;
        } else if (zoneColourPercentage[1] > zoneColourPercentage[2]) {
            return 2;
        } else {
            return 3;
        }
    }
}
