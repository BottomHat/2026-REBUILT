package frc.robot.Subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants;

public class Shooter {
    private TalonFX m_shoot_motor;

    public final int SHOOTER_MOTOR_VOLTAGE = 1;

    public Shooter() {
        m_shoot_motor = new TalonFX(Constants.ShooterConstants.SHOOTER_MOTOR_PORT);
    }

    public void setShooterVoltage(double voltage) {
        m_shoot_motor.setControl(new VoltageOut(voltage));
    }

    public void setShooterToggle(boolean on) {
        if (on) setShooterVoltage(SHOOTER_MOTOR_VOLTAGE);
        else setShooterVoltage(0);
    }
    
}
