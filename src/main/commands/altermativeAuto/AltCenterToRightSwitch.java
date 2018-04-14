package main.commands.altermativeAuto;

import edu.wpi.first.wpilibj.command.WaitCommand;
import interfacesAndAbstracts.ImprovedCommandGroup;
import main.commands.commandGroups.cubeManipulator.DropCube;
import main.commands.commandGroups.cubeManipulator.DropCubeOff;
import main.commands.drivetrain.DistanceDriveStraight;
import main.commands.drivetrain.TurnToAngle;
import main.commands.elevator.MoveToBottom;
import main.commands.elevator.MoveToSwitch;
import main.commands.intake.SpinIn;
import main.commands.intake.SpinOff;
import main.commands.pneumatics.arm.ArmClose;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.TiltDown;
import main.commands.pneumatics.tilt.TiltUp;

public class AltCenterToRightSwitch extends ImprovedCommandGroup {
	public AltCenterToRightSwitch() {
		addSequential(new DistanceDriveStraight(30.375)); //(Break away from wall so there is no resistance on the first turn)
		/*addSequential(new TurnToAngle(90));
		addSequential(new DistanceDriveStraight(60.5));
		addSequential(new TurnToAngle(-90));
		addSequential(new WaitCommand(0.25));*/
		addSequential(new TurnToAngle(30));
		addParallel(new MoveToSwitch(1.5));
		addSequential(new DistanceDriveStraight(85));
		//addSequential(new DistanceDriveStraight(90.375));
		addSequential(new WaitCommand(0.1));
		addSequential(new DropCube());
		addSequential(new WaitCommand(1));
		addSequential(new DropCubeOff());
		addSequential(new WaitCommand(0.2));
		addSequential(new DistanceDriveStraight(-60));
		addSequential(new MoveToBottom(1.5));
		addSequential(new ArmOpen());
		addSequential(new TiltDown());
		addSequential(new SpinIn());
		addSequential(new TurnToAngle(-40));
		//addSequential(new DistanceDriveStraight(38));
		//addSequential(new TurnToAngle(90));
		addSequential(new DistanceDriveStraight(35));
		addSequential(new WaitCommand(0.5));
		addSequential(new ArmClose());
		//addParallel(new WaitCommand(0.5));
		addSequential(new TiltUp());
		addSequential(new DistanceDriveStraight(-35));
		addSequential(new SpinOff());
		addSequential(new TurnToAngle(50));
		addParallel(new MoveToSwitch(1.5));
		addSequential(new DistanceDriveStraight(80));
		addSequential(new DropCube());
		addSequential(new WaitCommand(1));
		addSequential(new DropCubeOff());
		addSequential(new WaitCommand(0.2));
	}
}
