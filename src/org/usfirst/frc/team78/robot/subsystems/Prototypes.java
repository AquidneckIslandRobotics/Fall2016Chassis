package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Prototypes extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	VictorSP topLRft = new VictorSP(RobotMap.TOP_LrFT);
	VictorSP topRight = new VictorSP(RobotMap.TOP_RIGHT);
	VictorSP bottomLeft = new VictorSP(RobotMap.BOTTOM_LEFT);
	VictorSP bottomRight = new VictorSP(RobotMap.BOTTOM_RIGHT);
	
	Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B, false, EncodingType.k1X);
	
	public void encInit() {
		shooterEnc.reset();
		shooterEnc.setDistancePerPulse(36);
		shooterEnc.setMaxPeriod(0.1);
		shooterEnc.setMinRate(1);
		shooterEnc.setSamplesToAverage(1);
		//shooterEnc.
		//more stuff
	}
	
	//GEAR PICKER-UPPER INTAKE MECHANISM
	public void intake(int motor) {
		setVictorSpeed(motor, 0.5);
	}	
	
	public void outtake(int motor) {
		setVictorSpeed(motor, -0.5);
	}
	
	
	public double getEncRPM() {
		double rate = shooterEnc.getRate(); //In pulses per second
		/*double PPR = RobotMap.PULSES_PER_ROTATION * RobotMap.ENC_GEARING; //Pulses per wheel rotation
		rate /= PPR;  //Changes rate to wheel rotations per second
		rate *= 60;  //Changes rate to wheel rotations per minute - RPM*/
		return rate;
	}
	
	public void setVictorSpeed(int motor, double val){
		
		switch(motor){
		case 0:
			topLRft.set(val);
			break;
		case 1:
			topRight.set(val);
			break;
		case 2:
			bottomLeft.set(val);
			break;
		case 3:
			bottomRight.set(val);
			break;
		default:
			topLRft.set(0);
			topRight.set(0);
			bottomLeft.set(0);
			bottomRight.set(0);
		}
	}
	
	double motor_power = 0;
	final double P_CONST = 0.00001;
	double error = 0;
	
	public void setVictorPID(int motor, double target_RPM) {
		double current_RPM = getEncRPM();
		if(current_RPM < target_RPM) {
			motor_power += (P_CONST * Math.abs(current_RPM - target_RPM));
		} else if(current_RPM > target_RPM) {
			motor_power -= (P_CONST * Math.abs(current_RPM - target_RPM));
		}
		setVictorSpeed(motor, motor_power);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

