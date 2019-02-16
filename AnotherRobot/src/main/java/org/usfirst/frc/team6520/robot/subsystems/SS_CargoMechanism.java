/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6520.robot.subsystems;

import java.util.concurrent.Delayed;

import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class SS_CargoMechanism extends Subsystem {
  public void Cargo_Pull(){
    double speed = 0.4;
    while (!RobotMap.limit_switch.get()) {
      RobotMap.cargo_m_left.set(speed);
      RobotMap.cargo_m_right.set(-speed);
    } 
    RobotMap.cargo_m_left.set(0);
    RobotMap.cargo_m_left.set(0);
  }
  public void Shoot(){
    double speed = 0.4;
    RobotMap.cargo_m_left.set(-speed);
    RobotMap.cargo_m_right.set(speed);
    RobotMap.cargo_piston_push.set(true);
    Timer.delay(0.4);
    RobotMap.cargo_piston_push.set(false);
  }
  public void Grabber_Retract(){
    RobotMap.cargo_piston_pull.set(true);
  }
  public void Grabber_Extend(){
    RobotMap.cargo_piston_pull.set(false);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
