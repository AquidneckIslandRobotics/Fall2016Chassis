package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.SetVictorRate;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

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
	
	public Encoder shooterEnc = new Encoder(RobotMap.SHOOTER_ENC_A, RobotMap.SHOOTER_ENC_B, false, EncodingType.k4X);
	
	public DigitalInput bumber = new DigitalInput(RobotMap.BUMPER_SWITCH);
		
	public void encInit() {
		shooterEnc.reset();
		shooterEnc.setDistancePerPulse(6.55);//6.55 single shot wheel rpm (21.6 motor rpm)
		shooterEnc.setSamplesToAverage(5);
		
	}
	public double getEncRate(){
		return shooterEnc.getRate();
	}
	
	public void setVictorRate(double targetRate, int motor){
		double powerAdd = 0;
		double scale = 28000;
		
		double rate = getEncRate();
    	
    	powerAdd = (targetRate - rate) / scale;
    	
    	setVictorSpeed(motor, ((targetRate/scale) + powerAdd));
	}
	
	public void bangBang(double targetRate, int motor){
		double rate = getEncRate();
		if(rate < targetRate){
			setVictorSpeed(motor, 1);
		}else{
			setVictorSpeed(motor, 0);
		}
	}
	
	double gain = 0.000225;
	double current = 0;
	double actual = getEncRate();
	double previous = 0;
	public void takeBackHalf(double desired, int motor, int motor2){//, double gain){
		switch(motor){
		case 0:
			current = topLRft.get();
			break;
		case 1:
			current = topRight.get();
			break;
		case 2:
			current = bottomLeft.get();
			break;
		case 3:
			current = bottomRight.get();
			break;
		}	
		current = (current - previous)/2;
		current = current + gain*(desired - actual);
		//current = (current - previous)/2;
		
		setVictorSpeed(motor, current);
		setVictorSpeed(motor2, current);
		
		previous = current;
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
	
	public boolean bumperSwitch(){
		if(bumber.get()){
			return true;
		}else{
			return false;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

