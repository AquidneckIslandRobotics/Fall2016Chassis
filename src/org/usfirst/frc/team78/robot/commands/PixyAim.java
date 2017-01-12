package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import java.lang.Object;
/**
 *
 */
public class PixyAim extends Command {

	double speed;// = 0.07;
	double scale = 0.005;
	double output;
	

	
    public PixyAim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	requires(Robot.vision);
    	requires(Robot.pid);
    	
    	Robot.pid.setPID(0.5, 0.02, 0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.pid.setOutputLimits(0, 0.7);
    	Robot.pid.setSetpointRange(5);
    	output = Robot.pid.getOutput(Vision.xPosition, 160);	
    	
    	
    	//Robot.chassis.setSpeed(output, output);
    	
    	
    	
    	if(Vision.xPosition > 170){
    		speed = (Vision.xPosition - 170) * scale;
    		Robot.chassis.setSpeed(-speed, -speed);
    	}else if(Vision.xPosition < 150){
    		speed = (150 - Vision.xPosition) * scale;
    		Robot.chassis.setSpeed(speed, speed);
    	}else{
    		Robot.chassis.stopAllDrive();
    	}
    	
    }
    
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	
}
