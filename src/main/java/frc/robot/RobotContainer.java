// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Subsystems.ExampleSubsystem;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Shooter;
import frc.robot.Subsystems.DriveTrain.DriveTrain;
import frc.robot.Subsystems.DriveTrain.DriveTrainRealIO;
import frc.robot.Subsystems.DriveTrain.DriveTrainSimIO;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

    public final int RIGHT_MIDDLE_BUTTON = 8;

    public record JoystickInputs(double drive_x, double drive_y, double drive_a) {}
    //check is need joystick inputs or not

    // private Joystick main_stick = new Joystick(Constants.IO.MAIN_PORT);
    // private Joystick second_stick = new Joystick(Constants.IO.COPILOT_PORT);
    // private Joystick driver_controller = new Joystick(2);
    // private Joystick codriver_controller = new Joystick(3);

    private Joystick simp_stick = new Joystick(4);

    //

    public final DriveTrain m_drive = Robot.isReal() ? new DriveTrainRealIO() : new DriveTrainSimIO();

    public final Intake m_intake = new Intake();

    public final Shooter m_shooter = new Shooter();

    /*

    //The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

    // Replace with CommandPS4Controller or CommandJoystick if needed
    private final CommandXboxController m_driverController =
        new CommandXboxController(OperatorConstants.kDriverControllerPort);

    */

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    // auto commands here
    public RobotContainer() {
        NamedCommands.registerCommand("Intake On Command", new InstantCommand());

        // Configure the trigger bindings
        configureBindings();
    }

    // update the swerve
    public void updateSwerve() {
     double rightStickUpDown = simp_stick.getRawAxis(5);
    SmartDashboard.putNumber("joystick_axis_5", rightStickUpDown);

    double x_metersPerSecond = (Math.abs(simp_stick.getRawAxis(5)) < 0.1) ? 0 : 1.5 * simp_stick.getRawAxis(5);
    SmartDashboard.putNumber("x_mps", x_metersPerSecond);

    double rightStickLeftRight = simp_stick.getRawAxis(4);
    SmartDashboard.putNumber("joystick_axis_4", rightStickLeftRight);

    double y_metersPerSecond = (Math.abs(simp_stick.getRawAxis(4)) < 0.1) ? 0 : 1.5 * simp_stick.getRawAxis(4);
    SmartDashboard.putNumber("y_mps", y_metersPerSecond);

    double leftStickLeftRight = simp_stick.getRawAxis(0);
    double angle_radiansPerSecond =  (Math.abs(simp_stick.getRawAxis(0)) < 0.2) ? 0 : Math.signum(simp_stick.getRawAxis(0)) * -1.5
    * Math.pow(simp_stick.getRawAxis(0), 2);
    SmartDashboard.putNumber("axis_0", leftStickLeftRight);
    SmartDashboard.putNumber("angle", angle_radiansPerSecond);

    m_drive.setSwerveDrive(
      x_metersPerSecond, 
      y_metersPerSecond, 
      angle_radiansPerSecond
      );
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {

        // intake
        // note to self don't use .onTrue with StartEndCommand
        new JoystickButton(simp_stick, 3).whileTrue(
            new StartEndCommand(
                () -> m_intake.setIntakeToggle(true),
                () -> m_intake.setIntakeToggle(false)
            )
        );

        // shooter
        // will probably have to change
        /* 
        new JoystickButton(simp_stick, 8).whileTrue(
            new StartEndCommand(
                () -> m_shooter.setShooterToggle(true),
                () -> m_shooter.setShooterToggle(false)
            )
        );
        */

        /*
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
        new Trigger(m_exampleSubsystem::exampleCondition)
            .onTrue(new ExampleCommand(m_exampleSubsystem));

        // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
        // cancelling on release.
        m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
        */
    }

    /* 
    public void updateSubsystemManager() {
        SubsystemManager.instance.update(new JoystickInputs(
                        simp_stick.getRawAxis(1), 
                        simp_stick.getRawAxis(0), 
                        simp_stick.getRawAxis(4)));
    }
    */

    public Command getAutonomousCommand() {
        return new InstantCommand();
    }
}
