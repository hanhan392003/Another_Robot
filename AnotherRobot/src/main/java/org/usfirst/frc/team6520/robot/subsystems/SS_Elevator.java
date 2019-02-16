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
public class SS_Elevator extends Subsystem {

  boolean level0 = true;                    //giá trị thể hiện vị trí của elevator                      
  boolean level1,level2,level3 = false;     //same as above

  double distance,distance1,distance2 = 0;  //khoảng cách của các encoder và trung bình của chúng
  double speed = 0.4;                       //giá trị tốc độ của elevator

  public void Elevator_Level0(){            //đưa elevator đến vị trí ban đầu
    RobotMap.enc_left_elevator.reset();     
    RobotMap.enc_right_elevator.reset();
    if  (level1){                           //nếu thang ở level 1
      while (distance<00){
      distance1 = RobotMap.enc_left_elevator.get();   //lấy distance của encoder trái của elevator
      distance2 = RobotMap.enc_right_elevator.get();  //lấy distance của encoder phải của elevator
      distance  = (distance1+distance2)/2;            //giá trị trung bình của 2 encoder
      RobotMap.elevator_m_left.set(-speed);           //đưa elevator xuống
      RobotMap.elevator_m_right.set(speed);           //same as above
      }
    }
    else if (level2){                       //nếu thang ở level 2
      while (distance<00){
        distance1 = RobotMap.enc_left_elevator.get();   //same as level 1
        distance2 = RobotMap.enc_right_elevator.get();  //same as level 1
        distance  = (distance1+distance2)/2;            //same as level 1
        RobotMap.elevator_m_left.set(-speed);           //same as level 1
        RobotMap.elevator_m_right.set(speed);           //same as level 1
      }
    }
    else if (level3){                       //nếu thang ở level 3 
      while (distance<00){
        distance1 = RobotMap.enc_left_elevator.get();   
        distance2 = RobotMap.enc_right_elevator.get();
        distance  = (distance1+distance2)/2;
        RobotMap.elevator_m_left.set(-speed);
        RobotMap.elevator_m_right.set(speed);
      }
    }
    RobotMap.elevator_m_left.stopMotor();
    RobotMap.elevator_m_right.stopMotor();
    level0 = true;
    level1 = false;
    level2 = false;
    level3 = false;
  }
 
  public void Elevator_Level1(){                          //self-explainatory
    RobotMap.enc_left_elevator.reset();
    RobotMap.enc_right_elevator.reset();
    if  (level0){
      while (distance<00){
      distance1 = RobotMap.enc_left_elevator.get();
      distance2 = RobotMap.enc_right_elevator.get();
      distance  = (distance1+distance2)/2;
      RobotMap.elevator_m_left.set(speed);
      RobotMap.elevator_m_right.set(-speed);
      }
    }
    else if (level2){
      while (distance<00){
        distance1 = RobotMap.enc_left_elevator.get();
        distance2 = RobotMap.enc_right_elevator.get();
        distance  = (distance1+distance2)/2;
        RobotMap.elevator_m_left.set(-speed);
        RobotMap.elevator_m_right.set(speed);
      }
    }
    else if (level3){
      while (distance<00){
        distance1 = RobotMap.enc_left_elevator.get();
        distance2 = RobotMap.enc_right_elevator.get();
        distance  = (distance1+distance2)/2;
        RobotMap.elevator_m_left.set(-speed);
        RobotMap.elevator_m_right.set(speed);
      }
    }
    RobotMap.elevator_m_right.stopMotor();
    RobotMap.elevator_m_left.stopMotor();
    level0 = false;
    level1 = true;
    level2 = false;
    level3 = false;
  }

  public void Elevator_Level2(){
    RobotMap.enc_left_elevator.reset();
    RobotMap.enc_right_elevator.reset();
    if  (level0){
      while (distance<00){
      distance1 = RobotMap.enc_left_elevator.get();
      distance2 = RobotMap.enc_right_elevator.get();
      distance  = (distance1+distance2)/2;
      RobotMap.elevator_m_left.set(speed);
      RobotMap.elevator_m_right.set(-speed);
      }
    }
    else if (level1){
      while (distance<00){
        distance1 = RobotMap.enc_left_elevator.get();
        distance2 = RobotMap.enc_right_elevator.get();
        distance  = (distance1+distance2)/2;
        RobotMap.elevator_m_left.set(speed);
        RobotMap.elevator_m_right.set(-speed);
      }
    }
    else if (level3){
      while (distance<00){
        distance1 = RobotMap.enc_left_elevator.get();
        distance2 = RobotMap.enc_right_elevator.get();
        distance  = (distance1+distance2)/2;
        RobotMap.elevator_m_left.set(-speed);
        RobotMap.elevator_m_right.set(speed);
      }
    }
    RobotMap.elevator_m_left.stopMotor();
    RobotMap.elevator_m_right.stopMotor();
    level0 = false;
    level1 = false;
    level2 = true;
    level3 = false;
  }

  public void Elevator_Level3(){
    RobotMap.enc_left_elevator.reset();
    RobotMap.enc_right_elevator.reset();
    if  (level0){
    while (distance<00){
    distance1 = RobotMap.enc_left_elevator.get();
    distance2 = RobotMap.enc_right_elevator.get();
    distance  = (distance1+distance2)/2;
    RobotMap.elevator_m_left.set(speed);
    RobotMap.elevator_m_right.set(-speed);
    }
  }
  else if (level1){
    while (distance<00){
      distance1 = RobotMap.enc_left_elevator.get();
      distance2 = RobotMap.enc_right_elevator.get();
      distance  = (distance1+distance2)/2;
      RobotMap.elevator_m_left.set(speed);
      RobotMap.elevator_m_right.set(-speed);
    }
  }
  else if (level2){
    while (distance<00){
      distance1 = RobotMap.enc_left_elevator.get();
      distance2 = RobotMap.enc_right_elevator.get();
      distance  = (distance1+distance2)/2;
      RobotMap.elevator_m_left.set(speed);
      RobotMap.elevator_m_right.set(-speed);
    }
  }
  RobotMap.elevator_m_left.stopMotor();
  RobotMap.elevator_m_right.stopMotor();
  level0 = false;
  level1 = false;
  level2 = false;
  level3 = true;
}

  public void Wrist_Rotate(){
    RobotMap.elevator_piston.set(true);
  }
  public void Wrist_Return(){
    RobotMap.elevator_piston.set(false); 
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
