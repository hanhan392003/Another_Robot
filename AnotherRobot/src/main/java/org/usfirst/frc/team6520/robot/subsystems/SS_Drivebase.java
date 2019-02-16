package org.usfirst.frc.team6520.robot.subsystems;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.RobotMap;

// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.PIDController;
// import edu.wpi.first.wpilibj.PIDOutput;
// import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Drivebase extends Subsystem {
// implements PIDOutput {
	// public void pidWrite(double output){
		
	// }
	// ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
	
	// PIDController pid = new PIDController(0.013, 0, 0.027, gyro, this, 50);
	
	// public SS_Drivebase(){
	// 	pid.reset();
	// 	pid.setInputRange(0, 360);
	// 	pid.setContinuous(true);
	// 	pid.setOutputRange(-0.5, 0.5);
	// 	pid.setAbsoluteTolerance(2);
	// }
	
	// public void turn(double angle){
	// 	gyro.reset();
	// 	pid.setSetpoint(angle);
	// 	pid.enable();
	// 	while (pid.onTarget() == false){
	// 		RobotMap.m_left.set(pid.get());
	// 		RobotMap.m_right.set(pid.get());
	// }

	public void drive(){
		if (RobotMap.ss_vision.paused){
		RobotMap.m_left.set(Robot.m_oi.gamepad.getRawAxis(1)*0.6);
		RobotMap.m_right.set(Robot.m_oi.gamepad.getRawAxis(5)*0.6);
		}
	}

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}