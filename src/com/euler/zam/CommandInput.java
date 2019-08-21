package com.euler.zam;

public class CommandInput {

    String[] commands;

    public CommandInput(String cmd) {
        commands = cmd.split(" -");
    }

    
    public String getParam(char param) throws NoParamExistException {
        String paramValue = null;
        Boolean foundParam = false;
        
        for (int x = 1; x < commands.length && !foundParam; x++) {
            if (commands[x].charAt(0) == param) {
                paramValue = commands[x].substring(2);
                foundParam = true;
            }
        }
        
        if (!foundParam) {
            throw new NoParamExistException();
        }
        
        return paramValue;
    }

    public String getCommand() {
        return commands[0];
    }
}
