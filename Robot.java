package org.usfirst.frc.team6184.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

//Simple drive program for robot
//Allows rotation and basic backward forward movement

public class Robot extends SampleRobot {
	
	Joystick stick = new Joystick(0); // set to ID 1 in DriverStation

	Talon mFL = new Talon(0), mFR = new Talon(3), mBL = new Talon(2), mBR = new Talon(1); //All the motor controllers 
	
	Solenoid tshirtSolenoid = new Solenoid(0); //Assumes 0 is the ID of the Pnemuatic Controller and that we are using the 1st output
	
	public Robot() {
		
	}
	
	@Override
	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			
			solenoidController(); //Run solenoid function
			
			double m = .7;
			if(stick.getRawButton(3)) m = 1; //Boost power
			else m = .7; 
			double x = stick.getX()*m; //How much  "X" to apply to motors
			double y = -stick.getY()*m; //How much "y"
			if (y > .1 || y <= -.1) { //If joystick is not at default state
				if (x > .005) {
//					double m = 1 - x / 2;
					mFR.setSpeed(-y * m);
					mBR.setSpeed(-y * m);
					mFL.setSpeed(y);
					mBL.setSpeed(y);
				} else if (x < -.005) {
					x = -x;
//					float m = 1 - x / 2;
					mFR.setSpeed(-y);
					mBR.setSpeed(-y);
					mFL.setSpeed(y * m);
					mBL.setSpeed(y * m);
				} else if (x < .005 && x > -.005) {
					mFR.setSpeed(-y);
					mBR.setSpeed(-y);
					mFL.setSpeed(y);
					mBL.setSpeed(y);
				} else {
					mFR.setSpeed(0);
					mBR.setSpeed(0);
					mFL.setSpeed(0);
					mBL.setSpeed(0);
				}
			}

			else {
				double z = stick.getZ();
				mFR.setSpeed(z);
				mBR.setSpeed(z);
				mFL.setSpeed(z);
				mBL.setSpeed(z);
	}
		}
	}
	@Override
    public void autonomous() {   //This runs the moment autonmous is switched into
        	mFR.setSpeed(-.1);
    		mBR.setSpeed(-.1);
    		mFL.setSpeed(.1);
    		mBL.setSpeed(.1);
            Timer.delay(0.1); //Wait 0.5 seconds
            mFR.setSpeed(0);
    		mBR.setSpeed(0);
    		mFL.setSpeed(0);
    		mBL.setSpeed(0);
    }
	
	
	private void solenoidController() {
		if(stick.getRawButton(1)) tshirtSolenoid.set(true);
		else tshirtSolenoid.set(false);
	}
}
