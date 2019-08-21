package com.euler.zam;

import com.euler.zam.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static boolean serverIsOn = true;

    public Main() {

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Logger.log("Main Class Start.");

        Logger.log("Starting Database Connection..");
        String urlDB = "jdbc:mysql://localhost:3306/larastart?useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPass = "password";

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(urlDB, dbUser, dbPass);
        } catch (ClassNotFoundException e) {
            Logger.log("Could not load com.mysql.jdbc.Driver");
        } catch (SQLException e) {
            Logger.log("Could not connect to DB");
            Logger.log(e.toString());
        }

        if (con == null) {
            serverIsOn = false;
            Logger.log("Turning off.");
        }

        String command;
        CommandInput commandInput;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (serverIsOn) {
            Logger.log("Input Command: ");
            command = bufferedReader.readLine();

            Command c = new NoCommandFound();
            commandInput = new CommandInput(command);

            switch (commandInput.getCommand()) {
                case "updateuserhighscore":
                    c = new UpdateUserHighscore(con, commandInput);
                    break;
                case "count":
                    c = new Count(con, commandInput);
                    break;
                case "help":
                    c = new Help();
                    break;
                case "changersname":
                    c = new ChangeRsname(con, commandInput);
                    break;
                case "exit":
                    c = new Exit();
                    serverIsOn = false;
                    break;
            }

            c.execute();
        }
    }

}
