package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

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
	
	Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B);
	
	
	
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

