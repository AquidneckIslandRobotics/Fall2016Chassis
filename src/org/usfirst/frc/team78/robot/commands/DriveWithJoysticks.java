package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoysticks extends Command {
	
	public static boolean heading_correction_toggled = false;
	
    public DriveWithJoysticks() {
    	requires(Robot.chassis);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.ahrs.reset();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left = Robot.oi.getDriverLeftStick();
    	double right = Robot.oi.getDriverRightStick();
    	
    	/*if(Math.abs(left - right) <= 0.1 && Math.abs(left) >= 0.85 && Math.abs(right) >= 0.85) {
    		//Uncomment next line to enable heading correction (doesn't work as of 1-19-17)
    		//heading_correction_toggled = true;
    	} else {
    		heading_correction_toggled = false;
    	}
    	
    	if(heading_correction_toggled) {
    		Robot.chassis.correctHeading(Robot.chassis.ahrs.getAngle());
    	} else {
    		Robot.chassis.driveWithJoysticks();		
    	}
    	
    	SmartDashboard.putBoolean("toggle", heading_correction_toggled);*/
    	
    	Robot.chassis.driveWithJoysticks();
    	
    }
    	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopAllDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
