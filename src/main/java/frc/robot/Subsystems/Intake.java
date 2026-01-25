package frc.robot.Subsystems;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class Intake {
    private TalonFX m_intake = new TalonFX(0);

    public final int INTAKE_VOLTAGE = 1;

    // idk how theyre going to deploy the intake
    // will probably have to change this later

    /*
    private DutyCycleEncoder deploy_encoder = new DutyCycleEncoder(0);
    private TalonFX m_deploy_intake = new TalonFX(0);
    */

    public Intake() {}

    public void setIntakeVoltage(double voltage) {
        m_intake.setControl(new VoltageOut(voltage));
    }

    // would be better for commands to use this method instead
    public void setIntakeToggle(boolean on) {
        if (on) setIntakeVoltage(0);
        else setIntakeVoltage(INTAKE_VOLTAGE);
    }

    /*
    // would have to put this in a RunCommand like on reefscape
    // or maybe a FunctionalCommand
    public void setDeployVoltage(double voltage) {
        // move to constants later
        double deployed_pos = 0;
        double undeployed_pos = 0;
        
        // will have to change these later
        if (deploy_encoder.get() > deployed_pos && voltage > 0) {
            m_deploy_intake.setControl(new VoltageOut(0));
            return;
        }

        if (deploy_encoder.get() < undeployed_pos && voltage < 0) {
            m_deploy_intake.setControl(new VoltageOut(0));
            return;
        }

        m_deploy_intake.setControl(new VoltageOut(voltage));
    }
    */

    /*
    public boolean getDeployInBounds() {
        // should move these to constants later
        double deployed_pos = 0;
        double undeployed_pos = 0;

        // will need to switch around inequality signs later
        return deploy_encoder.get() > deployed_pos && 
            deploy_encoder.get() < undeployed_pos;
    }
    */

    /*
     * for a functional command
     *
     * on start: set voltage
     * while running:
     * on end: set voltage to 0
     * is finished: intake out of bounds or button is unpressed
     */
}
