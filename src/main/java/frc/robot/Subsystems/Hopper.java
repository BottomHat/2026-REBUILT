package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.HopperConstants;

public class Hopper extends SubsystemBase {
    private TalonFX m_agitator = new TalonFX(0);

    public Hopper() {

    }

    public void runAgitator(boolean onOff) {
        // if (onOff) m_agitator.setControl(HopperConstants.AGITATOR_VOLTAGE);
    }
}
