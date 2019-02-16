/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6520.robot.commands;

import org.usfirst.frc.team6520.robot.Robot;
import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class C_PauseVision extends Command {
  public C_PauseVision() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    RobotMap.ss_vision.paused=true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // RobotMap.ss_vision.paused = true;
    // RobotMap.ss_vision.m_visionThread.interrupt();
    // RobotMap.m_left.stopMotor();
    // RobotMap.m_right.stopMotor();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.m_oi.gamepad.getRawButton(1)){
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
