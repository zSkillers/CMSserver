package com.euler.zam.commands;

import com.euler.zam.Command;
import com.euler.zam.CommandInput;
import com.euler.zam.Logger;
import com.euler.zam.NoParamExistException;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Count implements Command {

    private Connection con;
    private CommandInput cmdInput;

    public Count(Connection con, CommandInput commandParameter) {
        this.con = con;
        this.cmdInput = commandParameter;
    }

    public int totalUsers() {
        return gettotal("users");
    }

    public int totalCategories() {
        return gettotal("categories");
    }

    public int totalForums() {
        return gettotal("forums");
    }

    public int totalPosts() {
        return gettotal("posts");
    }

    public int totalThreads() {
        return gettotal("threads");
    }

    private int gettotal(String tableName) {
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from larastart." + tableName);

            while (rs.next()) {
                return rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            Logger.log(e.toString());
        }
        return 0;
    }

    @Override
    public void execute() {
        String paramValue = null;

        try {
            paramValue = cmdInput.getParam('a');

            switch (paramValue) {
                case "users":
                    Logger.log(totalUsers());
                    break;
                case "categories":
                    Logger.log(totalCategories());
                    break;
                case "posts":
                    Logger.log(totalPosts());
                    break;
                case "threads":
                    Logger.log(totalThreads());
                    break;
                case "forums":
                    Logger.log(totalForums());
                    break;
            }
        }
        catch (NoParamExistException e) {
            Logger.log("Categories: " + totalCategories());
            Logger.log("Forums: " + totalForums());
            Logger.log("Threads: " + totalThreads());
            Logger.log("Posts: " + totalPosts());
            Logger.log("Users: " + totalUsers());
        }
    }
}
