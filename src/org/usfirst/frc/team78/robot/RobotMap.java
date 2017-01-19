package org.usfirst.frc.team78.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//CAN talons
	public static final int LEFT_FRONT = 0;
	public static final int LEFT_BACK = 1;
	public static final int LEFT_TOP = 2;
	public static final int RIGHT_FRONT = 3;
	public static final int RIGHT_BACK = 4;
	public static final int RIGHT_TOP = 5;
	
	//VictorSP
	public static final int TOP_LrFT = 0; 
	public static final int TOP_RIGHT = 1;
	public static final int BOTTOM_LEFT = 2;
	public static final int BOTTOM_RIGHT = 3;
	
	//Sensors
	public static final int SHOOTER_ENC_A = 0;
	public static final int SHOOTER_ENC_B = 1;
	public static final int PULSES_PER_ROTATION = 12; //# of encoder pulses per revolution
	public static final int ENC_GEARING = 3;  //Gear ratio; ex. 3 means three revolutions of
											  //motor/encoder per 1 revolution of the wheel(s).
											  //This is multiplied by the pulses per rotation.
}
