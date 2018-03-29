package main.commands.auto;

import edu.wpi.first.wpilibj.command.WaitCommand;
import interfacesAndAbstracts.ImprovedCommandGroup;
import main.commands.commandgroups.cubeManipulator.DropCube;
import main.commands.commandgroups.cubeManipulator.DropCubeOff;
import main.commands.drivetrain.TimedDrive;
import main.commands.drivetrain.TimedTurn;
import main.commands.elevator.MoveToTop;

public class CenterToRightSwitch extends ImprovedCommandGroup {
	//x, y & z will be the number of inches that the robot needs to drive/ alex will fill this in a couple of hours
	public CenterToRightSwitch() {
		addSequential(new TimedDrive(TIMED_DRIVE_PERCENT, 30.375/TIMED_DISTANCE_MULTIPLIER)); //(Break away from wall so there is no resistance on the first turn)
		addSequential(new TimedTurn(TurnMode.RIGHT, TIMED_TURN_PERCENT, TIMED_TURN_90_DEG_TIME));
		addSequential(new TimedDrive(TIMED_DRIVE_PERCENT, 60.5/TIMED_DISTANCE_MULTIPLIER));
		addSequential(new TimedTurn(TurnMode.LEFT, TIMED_TURN_PERCENT, TIMED_TURN_90_DEG_TIME));
		addSequential(new WaitCommand(0.25));
		addSequential(new MoveToTop(3));
		addSequential(new TimedDrive(TIMED_DRIVE_PERCENT, 90.375/TIMED_DISTANCE_MULTIPLIER));
		addSequential(new WaitCommand(0.1));
		addSequential(new DropCube());
		addSequential(new WaitCommand(1));
		addSequential(new DropCubeOff());
	}
}
