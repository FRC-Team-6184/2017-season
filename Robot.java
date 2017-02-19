package org.usfirst.frc.team6184.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

//Simple drive program for robot
//Allows rotation and basic backward forward movement

public class Robot extends SampleRobot {

	Joystick stick = new Joystick(0); // set to ID 1 in DriverStation

	Talon mR = new Talon(0), mL = new Talon(1), shoot = new Talon(3), climb = new Talon(4), actu = new Talon(2);
																							// the
																							// motor
																						// controllers

	// Solenoid tshirtSolenoid = new Solenoid(0); //Assumes 0 is the ID of the
	// Pnemuatic Controller and that we are using the 1st output

	// AnalogInput ultraSonic = new AnalogInput(0); //Assumes 0 for the Analog
	// Input on RoboRIO
	// final double ultraInputVoltage = 5.0f; //What's the voltage input for
	// HRLV-MaxSonar-EZ1 relative to Pin 7 (GND)
	// final double ultraScaling = ultraInputVoltage/5120; //(about 0.977mV per
	// 1mm at 5V input voltage) Scaling specs for HRLV-MaxSonar-EZ1

	// DigitalOutput ultraOut = new DigitalOutput(0);

	public Robot() {

	}

	@Override
	public void operatorControl() {
		climb.setSpeed(0);
		while (isOperatorControl() && isEnabled()) {
			// ultraOut.set(true);
			// System.out.println(getDistanceUltra()); //Print distance in mm
			double m;
			if (stick.getRawButton(3))
				m = 1; // Boost power
			else
				m = .7;
			double y = stick.getY(); // How much "y"
			double z = stick.getZ(); // How much "z"
			if (y > .1 || y <= -.1) {
				mR.setSpeed(-y * m);
				mL.setSpeed(y * m);
			} else if (z > .1 || z <= -.1) {
				mR.setSpeed(z*m);
				mL.setSpeed(z*m);
			} else {
				mR.setSpeed(0);
				mL.setSpeed(0);
			}
			if(stick.getRawButton(1)){
				shoot.setSpeed(1);
				actu.setSpeed(.1);
			} else {
				shoot.setSpeed(0);
				actu.setSpeed(0);
			}
			if(stick.getRawButton(8)){
				climb.setSpeed(.5);
			} else {
				climb.setSpeed(0);
			}
		}
	}

	@Override
	public void autonomous() {
	}

	// private void solenoidController() {
	// if(stick.getRawButton(1)) tshirtSolenoid.set(true);
	// else tshirtSolenoid.set(false);
	// }

	// Uses Ultrasonic sensor to find distance in mm
	// private double getDistanceUltra() {
	// double volts = ultraSonic.getVoltage();
	// ultraOut.set(false);
	// return volts/ultraScaling;
	// }
}
