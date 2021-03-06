package edu.wpi.first.wpilibj.templates.commands;

public class ResetShooter extends CommandBase {

    public ResetShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    ResetShooter(int i, int dynoVel) {
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.resetV();
        shooter.forward = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!feeder.isIn)
        {
            shooter.manageDown();
            shooter.equalizeVelocity();
            shooter.limitVelocity();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (shooter.bottomSwitch.get() || shooter.left.getD() <= 5 || shooter.right.getD() <= 5 || feeder.isIn);
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.set(0.0);
        if(shooter.bottomSwitch.get()) {
            shooter.reset();
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.set(0.0);
    }
}