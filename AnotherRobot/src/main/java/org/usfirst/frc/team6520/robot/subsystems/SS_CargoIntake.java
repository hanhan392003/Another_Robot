/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6520.robot.subsystems;

import org.usfirst.frc.team6520.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class SS_CargoIntake extends Subsystem {
  public void roll(){
    double M_CARGO_POWER = 0.5;
    RobotMap.m_cargo.set(M_CARGO_POWER);
  }

  public void stop(){
    RobotMap.m_cargo.stopMotor();
  }

  public void in(){
    // mở
    int ENCODER_GETRAW = 180;
    double M_ENC_CARGO = 0.5;
    while(RobotMap.enc_cargo.getRaw() > ENCODER_GETRAW){
      RobotMap.m_enc_cargo.set(M_ENC_CARGO);
    }
    RobotMap.m_enc_cargo.stopMotor();
  }

  public void out(){
    // gập
    int ENCODER_GET_RAW = 0;
    double M_ENC_CARGO = -0.5;
    while(RobotMap.enc_cargo.getRaw() < ENCODER_GET_RAW){
      RobotMap.m_enc_cargo.set(M_ENC_CARGO);
    }
    RobotMap.m_enc_cargo.stopMotor();
  }

  public void initDefaultCommand() {
  
  }
}
