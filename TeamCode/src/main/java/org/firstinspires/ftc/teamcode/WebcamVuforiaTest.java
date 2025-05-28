/* import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@TeleOp(name="Webcam Vuforia Test", group="Vision")
public class WebcamVuforiaTest extends LinearOpMode {

    private VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {
        // Setup parameters for Vuforia
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        // Set your Vuforia license key here
        parameters.vuforiaLicenseKey = "YOUR-VUFORIA-LICENSE-KEY";

        // Use webcam, not phone camera
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        // Create Vuforia instance
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        telemetry.addLine("Webcam initialized via Vuforia");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("Webcam running...");
            telemetry.update();
        }
    }
}*/