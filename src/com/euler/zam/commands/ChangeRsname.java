package com.euler.zam.commands;

import com.euler.zam.Command;
import com.euler.zam.CommandInput;
import com.euler.zam.Logger;
import com.euler.zam.NoParamExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ChangeRsname implements Command {
    Connection con;
    CommandInput cmd;

    public ChangeRsname(Connection con, CommandInput cmd) {
        this.con = con;
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        String query = "UPDATE `users` SET rsname=?, updated_at=? " +
                " WHERE id=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, cmd.getParam('u'));

            Timestamp ts = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(2, ts);

            preparedStatement.setInt(3, Integer.parseInt(cmd.getParam('i')));
            preparedStatement.execute();
        } catch (SQLException e) {
            Logger.log(e.toString());
        } catch (NoParamExistException e) {
            Logger.log(e.toString());
        }
    }
}
