package com.euler.zam.commands;

import com.euler.zam.Command;

public class Help implements Command {


    @Override
    public void execute() {
        System.out.println("updateuserhighscore [-u username] Update user highscore.");
        System.out.println("count [-a users | categories | posts | threads | forums] Count item.");
        System.out.println("changeusername [-i user id, -u new username] Change username.");
        System.out.println("changersname [-i user id, -u new username] Change rs name.");
        System.out.println("exit Close program.");
    }
}
