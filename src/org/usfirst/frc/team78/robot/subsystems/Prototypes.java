package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.SetVictorRate;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Prototypes extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public VictorSP topLRft = new VictorSP(RobotMap.TOP_LrFT);
	public VictorSP topRight = new VictorSP(RobotMap.TOP_RIGHT);
	public VictorSP bottomLeft = new VictorSP(RobotMap.BOTTOM_LEFT);
	public VictorSP bottomRight = new VictorSP(RobotMap.BOTTOM_RIGHT);
	
	public Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B);
	
	public void setEncParam(double pulsePerRot, double maxPeriod){
		shooterEnc.reset();
		shooterEnc.setDistancePerPulse(pulsePerRot);
		shooterEnc.setMaxPeriod(maxPeriod);		
	}
	
	public double getEncRate(){
		return shooterEnc.getRate();
	}
	
	public void setVictorRate(double targetRate, int motor){
		//double power = targetRate;
		double powerAdd = 0;
		double scale = 28000;
		
		double rate = getEncRate();
    	
    	powerAdd = (targetRate - rate) / scale;
    	
    	Robot.proto.setVictorSpeed(0, ((targetRate/scale) + powerAdd));
	}
	
	public void setVictorSpeed(int motor, double val){
		
		if(val > 1){
			val = 1;
		}else if(val < -1){
			val = -1;
		}else{
			//do nothing
		}
		
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

