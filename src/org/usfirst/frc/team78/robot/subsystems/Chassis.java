package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;
import com.kauailabs.navx.frc.*;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Chassis extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//MOTORS
		CANTalon leftFront = new CANTalon(RobotMap.LEFT_FRONT);
		CANTalon leftBack = new CANTalon(RobotMap.LEFT_BACK);
		CANTalon leftTop = new CANTalon(RobotMap.LEFT_TOP);
		CANTalon rightFront = new CANTalon(RobotMap.RIGHT_FRONT);
		CANTalon rightBack = new CANTalon(RobotMap.RIGHT_BACK);
		CANTalon rightTop = new CANTalon(RobotMap.RIGHT_TOP);
		
		
		
	
		
		//sensors
		public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
		
		//constants
		final double GYRO_P = (.017);
		
		
		public void setSpeed(double left, double right){
	    	leftFront.set(left);
	    	leftBack.set(left);
	    	leftTop.set(left);
	    	rightFront.set(right);
	    	rightBack.set(right);
	    	rightTop.set(right);
	    }
		
		
		 
		
		 double startHeading;
		 double rightSpeed;
		 double leftSpeed;
		 
		 public void correctHeading(double start){
		 	leftSpeed = Robot.oi.getDriverLeftStick();
	    	rightSpeed = Robot.oi.getDriverRightStick();
	    	startHeading = start;
	    	
	    	leftSpeed = leftSpeed - Robot.chassis.headingCorrection(start);
	    	rightSpeed = rightSpeed + Robot.chassis.headingCorrection(start);
	    	
	    	
	    	Robot.chassis.setSpeed(leftSpeed, rightSpeed);
		 }
		 public double headingCorrection (double heading){
			 //Spencer's bad code - disregard	
			 /*double driftError = heading - getAngle();
			 double abs_driftError = Math.abs(driftError);
			 
			 if(driftError < 180) {
				 return abs_driftError * GYRO_P;
			 } else if(driftError > 180) {
				 return -1 * abs_driftError * GYRO_P;
			 }
			 
			 return 0;*/
			 
		    	double driftError = heading - getAngle();
		    	
		    	if (driftError < -180){
		    		driftError = driftError + 360;
		    	}
		    	else if (driftError > 180){
		    		driftError = driftError - 360;
		    	}	    	
		    	
		    	return ((GYRO_P)*driftError);
		    	//setSpeed(((GYRO_P)*driftError), -((GYRO_P)*driftError));
		  }
		 
		 public void driveWithJoysticks(){
		    	
		    	double left = Robot.oi.getDriverLeftStick();
		    	double right = Robot.oi.getDriverRightStick();
		    	
		    	setSpeed(-left,right);
		    }
		
	    public void stopAllDrive(){
	    	setSpeed(0,0);
	    }

	    
	    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoysticks());
    	  	
    }
    
    public double getAngle(){
    	return ahrs.getAngle();
    }
    
    public double getPitch(){
    	return ahrs.getPitch();//just look at all the different gets, figure out what is going on
    }
    
    public double getRoll(){
    	return ahrs.getRoll();//just look at all the different gets, figure out what is going on
    }
}

