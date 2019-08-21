package com.euler.zam.commands;

import com.euler.zam.Command;
import com.euler.zam.Logger;

/**
 * No command.
 * @author Cody Wofford (zamight)
 * @version 1.0
 */
public class NoCommandFound implements Command {
    @Override
    public void execute() {
        Logger.log("No command found.");
    }
}
