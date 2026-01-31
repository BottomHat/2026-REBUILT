package frc.robot.Subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
public class Shooter extends SubsystemBase {
    private TalonFX m_shoot_motor;

    public final int SHOOTER_MOTOR_VOLTAGE = 1;

    public Shooter() {
        m_shoot_motor = new TalonFX(Constants.ShooterConstants.SHOOTER_MOTOR_PORT);

        TalonFXConfiguration config = new TalonFXConfiguration();

        // some voltage limiting configs here
        config.Voltage.PeakForwardVoltage = 0;

        m_shoot_motor.getConfigurator().apply(config);
    }

    public void setShooterVoltage(double voltage) {
        m_shoot_motor.setControl(new VoltageOut(voltage));
    }

    public void setShooterToggle(boolean on) {
        if (on) setShooterVoltage(SHOOTER_MOTOR_VOLTAGE);
        else setShooterVoltage(0);
    }

    @Override
    public void periodic() {
        double voltageUsed = m_shoot_motor.getMotorVoltage().getValueAsDouble();
        SmartDashboard.putNumber("Shooter Voltage", voltageUsed);
    }
}
