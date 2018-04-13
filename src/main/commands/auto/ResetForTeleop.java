package main.commands.auto;

import interfacesAndAbstracts.ImprovedCommandGroup;
import main.commands.drivetrain.DistanceDriveStraight;
import main.commands.elevator.MoveToBottom;
import main.commands.intake.SpinOff;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.TiltDown;
import main.commands.pneumatics.tilt.TiltUp;

public class ResetForTeleop extends ImprovedCommandGroup {
	public ResetForTeleop(boolean moveDown) {
		addSequential(new ArmOpen());
		addSequential(new SpinOff());
		if(moveDown) {
			//addSequential(new TiltDown());
			addSequential(new DistanceDriveStraight(-30));//Drive backwards
			addSequential(new MoveToBottom(1.5));//Let Elevator Down
		}
		addSequential(new TiltUp());
	}
}
