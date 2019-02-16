/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6520.robot;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team6520.robot.subsystems.SS_CargoIntake;
import org.usfirst.frc.team6520.robot.subsystems.SS_CargoMechanism;
import org.usfirst.frc.team6520.robot.subsystems.SS_Climber;
import org.usfirst.frc.team6520.robot.subsystems.SS_Drivebase;
import org.usfirst.frc.team6520.robot.subsystems.SS_Elevator;
import org.usfirst.frc.team6520.robot.subsystems.SS_HatchMechanism;
import org.usfirst.frc.team6520.robot.subsystems.SS_Vision;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// drivebase
	public static SS_Drivebase ss_drivebase = new SS_Drivebase();
	public static int M_LEFT_PORT = 0;
	public static int M_RIGHT_PORT = 1;
	public static VictorSP m_left = new VictorSP(M_LEFT_PORT);
	public static VictorSP m_right = new VictorSP(M_RIGHT_PORT);

	// vision
	public static SS_Vision ss_vision = new SS_Vision();
	
	
	
	public static AHRS navx = new AHRS(SPI.Port.kMXP);

	// cargo intake
	public static SS_CargoIntake ss_cargoIntake = new SS_CargoIntake();
	public static int CARGO_M_PORT = 2;
	public static int CARGO_ENC_M_PORT = 3;
	public static int CARGO_ENC_CHANNELA_PORT = 8;
	public static int CARGO_ENC_CHANNELB_PORT = 9;
	public static VictorSP m_cargo = new VictorSP(CARGO_M_PORT);
	public static VictorSP m_enc_cargo = new VictorSP(CARGO_ENC_M_PORT);
	public static Encoder enc_cargo = new Encoder(CARGO_ENC_CHANNELA_PORT,CARGO_ENC_CHANNELB_PORT,false,EncodingType.k4X);

	// cargo mechanism
	public static SS_CargoMechanism ss_cargoMechanism = new SS_CargoMechanism();
	public static int CARGO_MECHANISM_M_LEFT_PORT = 4;
	public static int CARGO_MECHANISM_M_RIGHT_PORT = 5;
	public static int CARGO_MECHANISM_PISTON_PUSH = 1;
	public static int CARGO_MECHANISM_PISTON_PULL = 2;
	public static int CARGO_LIMIT_SWITCH = 00;
	public static DigitalInput limit_switch = new DigitalInput(CARGO_LIMIT_SWITCH);
	public static VictorSP cargo_m_left = new VictorSP(CARGO_MECHANISM_M_LEFT_PORT);
	public static VictorSP cargo_m_right = new VictorSP(CARGO_MECHANISM_M_RIGHT_PORT);
	public static Solenoid cargo_piston_push = new Solenoid(CARGO_MECHANISM_PISTON_PUSH);
	public static Solenoid cargo_piston_pull = new Solenoid(CARGO_MECHANISM_PISTON_PULL);
	
	// elevator
	public static SS_Elevator ss_elevator = new SS_Elevator();
	public static int ELEVATOR_M_LEFT_PORT = 6;
	public static int ELEVATOR_M_RIGHT_PORT = 7;
	public static int ELEVATOR_ENC_LEFT_CHANNELA_PORT = 6;
	public static int ELEVATOR_ENC_LEFT_CHANNELB_PORT = 7;
	public static int ELEVATOR_ENC_RIGHT_CHANNELA_PORT = 4;
	public static int ELEVATOR_ENC_RIGHT_CHANNELB_PORT = 5;
	public static int ELEVATOR_PISTON_PORT = 3;
	public static VictorSP elevator_m_left = new VictorSP(ELEVATOR_M_LEFT_PORT);
	public static VictorSP elevator_m_right = new VictorSP(ELEVATOR_M_RIGHT_PORT);
	public static Encoder enc_left_elevator = new Encoder(ELEVATOR_ENC_LEFT_CHANNELA_PORT,ELEVATOR_ENC_LEFT_CHANNELB_PORT,false,EncodingType.k4X);
	public static Encoder enc_right_elevator = new Encoder(ELEVATOR_ENC_RIGHT_CHANNELA_PORT,ELEVATOR_ENC_RIGHT_CHANNELB_PORT,false,EncodingType.k4X);
	public static Solenoid elevator_piston = new Solenoid(ELEVATOR_PISTON_PORT);

	// climber
	public static SS_Climber ss_climber = new SS_Climber();
	static int CLIMBER_LEFT_PISTON_PORT = 0;
	static int CLIMBER_RIGHT_PISTON_PORT = 0;
	static int CLIMBER_MOTOR_PORT = 0;
	public static Solenoid climber_left_piston = new Solenoid(CLIMBER_LEFT_PISTON_PORT);
	public static Solenoid climber_right_piston = new Solenoid(CLIMBER_RIGHT_PISTON_PORT);
	public static VictorSP climber_motor = new VictorSP(CLIMBER_MOTOR_PORT);
	
	// hatch mechanism
	public static SS_HatchMechanism ss_hatchmechanism = new SS_HatchMechanism();
	static int HATCH_PISTON_PORT = 0;
	static int CARGO_PISTON_PORT = 0;
	public static Solenoid hatch_piston = new Solenoid(HATCH_PISTON_PORT);
	public static Solenoid cargo_piston = new Solenoid (CARGO_PISTON_PORT);
	public static void update(){
		// SmartDashboard.putNumber("encoder", RobotMap.encoder.getRaw());
	}
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}