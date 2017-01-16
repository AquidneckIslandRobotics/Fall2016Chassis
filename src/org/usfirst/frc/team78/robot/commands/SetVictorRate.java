package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetVictorRate extends Command {
	
	private int motor;
//	private double targetRate;
//	private double ff;
	private double power;
	private double powerAdd;
	private double scale = 0.05;
	
    public SetVictorRate(double Power, int Motor) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	motor = Motor;
    	power = Power;
    	requires(Robot.proto);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.proto.shooterEnc.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.proto.setVictorRate(targetRate, motor);
    	double rate = Robot.proto.getEncRate();
    	
    	if(rate > -17000){
    		powerAdd = -((rate - -17000) * scale);
    	}else if(rate < -18500){
    		powerAdd = (-18500 - rate) * scale;
    	}else{
    		//add nothing
    	}
    	Robot.proto.setVictorSpeed(0, (power + powerAdd));
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
