package edu.wpi.first.wpilibj.templates.commands;

public class RaiseShooter extends CommandBase {

    public RaiseShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.resetV();
        shooter.forward = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!feeder.isIn) {
            shooter.manageUp();
            shooter.equalizeVelocity();
            shooter.limitVelocity();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (shooter.topSwitch.get() || shooter.left.getD() >= shooter.height.fetch() || shooter.right.getD() >= shooter.height.fetch() || feeder.isIn);
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.set(0.0);
    }
}