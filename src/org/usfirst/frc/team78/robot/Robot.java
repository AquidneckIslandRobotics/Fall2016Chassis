
package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team78.robot.commands.SetVictorRate;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.PID;
import org.usfirst.frc.team78.robot.subsystems.Prototypes;
import org.usfirst.frc.team78.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static OI oi;
	public static final Chassis chassis = new Chassis();
	public static final Vision vision = new Vision();
	public static final PID pid = new PID();
	public static final Prototypes proto = new Prototypes();

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        
        Compressor c = new Compressor(0);
    	c.setClosedLoopControl(true);
    	
    	proto.encInit();
    }
	
    
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	vision.printPixyStuff();
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	vision.printPixyStuff();
        if (autonomousCommand != null) autonomousCommand.cancel();
    
        SmartDashboard.putNumber("Top Lrft", 0.0);
        SmartDashboard.putNumber("Top Right", 0.0);
        SmartDashboard.putNumber("Bottom Left", 0.0);
        SmartDashboard.putNumber("Bottom Right", 0.0);
    }
    
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//Robot.proto.setVictorSpeed(0, SmartDashboard.getNumber("Top Lrft", 0.0));
    	Robot.proto.setVictorSpeed(1, SmartDashboard.getNumber("Top Right", 0.0));
    	Robot.proto.setVictorSpeed(2, SmartDashboard.getNumber("Bottom Left", 0.0));
    	Robot.proto.setVictorSpeed(3, SmartDashboard.getNumber("Bottom Right", 0.0));

    	SmartDashboard.putNumber("Enc", proto.getEncRate());
    	//SmartDashboard.putNumber("count", proto.shooterEnc.getRaw());
    	
    	double val = SmartDashboard.getNumber("Top Lrft", 0.0);
    	Robot.proto.takeBackHalf(val, 0);
    	
    	vision.printPixyStuff();
    	
    	SmartDashboard.putBoolean("Bumper Switch", !proto.bumperSwitch());
    	
    	Scheduler.getInstance().run();      
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
