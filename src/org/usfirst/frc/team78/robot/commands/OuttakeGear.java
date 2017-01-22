package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OuttakeGear extends Command {
	
	private int motor;
	
    public OuttakeGear(int Motor) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.proto);
    	motor = Motor;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.proto.setVictorSpeed(motor, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.oi.btn6.get()) {
    		Robot.proto.setVictorSpeed(motor, -0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	if(!Robot.oi.btn5.get()) return true;
//        else return false;
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