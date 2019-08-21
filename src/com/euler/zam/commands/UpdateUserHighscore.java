package com.euler.zam.commands;

import com.euler.zam.Command;
import com.euler.zam.CommandInput;
import com.euler.zam.Logger;
import com.euler.zam.NoParamExistException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.LinkedList;

/**
 * Update user highscore stats command.
 * @author Cody Wofford
 * @version 1.0
 */
public class UpdateUserHighscore implements Command {

    private String username;
    private String name = "updateuserhighscore";
    private CommandInput cmdInput;
    private Connection con;

    public UpdateUserHighscore(Connection con, CommandInput commandline) {
        this.con = con;
        cmdInput = commandline;
    }

    private String getUserData(String game_username) {
        String data = "";
        try {
            URL url = new URL("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + game_username);

            URLConnection con = url.openConnection();
            InputStream inputStream = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;

            while ((line = br.readLine()) != null) {
                data += line + "\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private boolean insertNewUSerStat(int userID) {
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from larastart.stats where user_id = " + userID);

            while (rs.next()) {
                int i = rs.getInt("count(*)");
                return (i == 0);
            }
        } catch (SQLException e) {
            Logger.log(e.toString());
        }
        return true;
    }

    private PreparedStatement prepareUserData(String s, int userId) throws SQLException {

        PreparedStatement preparedStatement;
        String query = null;
        boolean insertData = insertNewUSerStat(userId);

        if (insertData) {
            query = "INSERT INTO `stats` (user_id, " +
                    "overall_rank, " +
                    "overall_level, " +
                    "overall_xp, " +
                    "attack_rank, " +
                    "attack_level, " +
                    "attack_xp, " +
                    "defence_rank, " +
                    "defence_level, " +
                    "defence_xp, " +
                    "strength_rank, " +
                    "strength_level, " +
                    "strength_xp, " +
                    "hitpoints_rank, " +
                    "hitpoints_level, " +
                    "hitpoints_xp, " +
                    "ranged_rank, " +
                    "ranged_level, " +
                    "ranged_xp, " +
                    "prayer_rank, " +
                    "prayer_level, " +
                    "prayer_xp, " +
                    "magic_rank, " +
                    "magic_level, " +
                    "magic_xp, " +
                    "cooking_rank, " +
                    "cooking_level, " +
                    "cooking_xp, " +
                    "woodcutting_rank, " +
                    "woodcutting_level, " +
                    "woodcutting_xp,  " +
                    "fletching_rank," +
                    "fletching_level, " +
                    "fletching_xp, " +
                    "fishing_rank, " +
                    "fishing_level, " +
                    "fishing_xp, " +
                    "firemaking_rank, " +
                    "firemaking_level, " +
                    "firemaking_xp, " +
                    "crafting_rank, " +
                    "crafting_level, " +
                    "crafting_xp, " +
                    "smithing_rank, " +
                    "smithing_level, " +
                    "smithing_xp, " +
                    "mining_rank, " +
                    "mining_level, " +
                    "mining_xp, " +
                    "herlore_rank, " +
                    "herlore_level, " +
                    "herlore_xp, " +
                    "agility_rank, " +
                    "agility_level, " +
                    "agility_xp, " +
                    "thieving_rank, " +
                    "thieving_level, " +
                    "thieving_xp, " +
                    "slayer_rank, " +
                    "slayer_level, " +
                    "slayer_xp, " +
                    "farming_rank, " +
                    "farming_level, " +
                    "farming_xp, " +
                    "runecraft_rank, " +
                    "runecraft_level, " +
                    "runecraft_xp, " +
                    "hunter_rank, " +
                    "hunter_level, " +
                    "hunter_xp, " +
                    "construction_rank, " +
                    "construction_level, " +
                    "construction_xp, " +
                    "clue_scroll_all_score, " +
                    "clue_scroll_beginner_score, " +
                    "clue_scroll_easy_score, " +
                    "clue_scroll_medium_score, " +
                    "clue_scroll_hard_score, " +
                    "clue_scroll_elite_score, " +
                    "clue_scroll_master_score, " +
                    "created_at, " +
                    "updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        else {
            query = "UPDATE `stats` SET overall_rank=?, " +
                    "overall_level=?, " +
                    "overall_xp=?, " +
                    "attack_rank=?, " +
                    "attack_level=?, " +
                    "attack_xp=?, " +
                    "defence_rank=?, " +
                    "defence_level=?, " +
                    "defence_xp=?, " +
                    "strength_rank=?, " +
                    "strength_level=?, " +
                    "strength_xp=?, " +
                    "hitpoints_rank=?, " +
                    "hitpoints_level=?, " +
                    "hitpoints_xp=?, " +
                    "ranged_rank=?, " +
                    "ranged_level=?, " +
                    "ranged_xp=?, " +
                    "prayer_rank=?, " +
                    "prayer_level=?, " +
                    "prayer_xp=?, " +
                    "magic_rank=?, " +
                    "magic_level=?, " +
                    "magic_xp=?, " +
                    "cooking_rank=?, " +
                    "cooking_level=?, " +
                    "cooking_xp=?, " +
                    "woodcutting_rank=?, " +
                    "woodcutting_level=?, " +
                    "woodcutting_xp=?, " +
                    "fletching_rank=?, " +
                    "fletching_level=?, " +
                    "fletching_xp=?, " +
                    "fishing_rank=?, " +
                    "fishing_level=?, " +
                    "fishing_xp=?, " +
                    "firemaking_rank=?, " +
                    "firemaking_level=?, " +
                    "firemaking_xp=?, " +
                    "crafting_rank=?, " +
                    "crafting_level=?, " +
                    "crafting_xp=?, " +
                    "smithing_rank=?, " +
                    "smithing_level=?, " +
                    "smithing_xp=?, " +
                    "mining_rank=?, " +
                    "mining_level=?, " +
                    "mining_xp=?, " +
                    "herlore_rank=?, " +
                    "herlore_level=?, " +
                    "herlore_xp=?, " +
                    "agility_rank=?, " +
                    "agility_level=?, " +
                    "agility_xp=?, " +
                    "thieving_rank=?, " +
                    "thieving_level=?, " +
                    "thieving_xp=?, " +
                    "slayer_rank=?, " +
                    "slayer_level=?, " +
                    "slayer_xp=?, " +
                    "farming_rank=?, " +
                    "farming_level=?, " +
                    "farming_xp=?, " +
                    "runecraft_rank=?, " +
                    "runecraft_level=?, " +
                    "runecraft_xp=?, " +
                    "hunter_rank=?, " +
                    "hunter_level=?, " +
                    "hunter_xp=?, " +
                    "construction_rank=?, " +
                    "construction_level=?, " +
                    "construction_xp=?, " +
                    "clue_scroll_all_score=?, " +
                    "clue_scroll_easy_score=?, " +
                    "clue_scroll_beginner_score=?, " +
                    "clue_scroll_medium_score=?, " +
                    "clue_scroll_hard_score=?, " +
                    "clue_scroll_elite_score=?, " +
                    "clue_scroll_master_score=?, " +
                    "updated_at=?" +
                    " WHERE user_id = ?";
        }

        preparedStatement = con.prepareStatement(query);

        String[] splitLones = s.split("\n");

        String[] skills = {"overall",
                "attack",
                "defence",
                "strength",
                "hitpoints",
                "ranged",
                "prayer",
                "magic",
                "cooking",
                "woodcutting",
                "fletching",
                "fishing",
                "firemaking",
                "crafting",
                "smithing",
                "mining",
                "herblore",
                "agility",
                "thieving",
                "slayer",
                "farming",
                "runecrafting",
                "hunter",
                "construction",
                "bounty",
                "bounty",
                "bounty",
                "clue_scroll_all",
                "clue_scroll_beginner",
                "clue_scroll_easy",
                "clue_scroll_medium",
                "clue_scroll_hard",
                "clue_scroll_elite",
                "clue_scroll_master"
        };

        int queryPosition = 1;

        if (insertData) {
            preparedStatement.setInt(queryPosition++, userId);
        }

        for (int x = 0; x < skills.length; x++) {
            if (!skills[x].equals("bounty") && !skills[x].contains("clue")) {
                String[] stats = splitLones[x].split(",");
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[0]));
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[1]));
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[2]));
            } else if (!skills[x].equals("bounty")) {
                String[] stats = splitLones[x].split(",");
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[1]));
            }
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        preparedStatement.setTimestamp(queryPosition++, ts);

        if (insertData) {
            preparedStatement.setTimestamp(queryPosition++, ts);
        }
        else {
            preparedStatement.setInt(queryPosition++, userId);
        }


        return preparedStatement;
    }

    private PreparedStatement prepareDataHistory(String s, int userId) throws SQLException {
        String query = "INSERT INTO `stathistorys` (user_id, " +
                "overall_rank, " +
                "overall_level, " +
                "overall_xp, " +
                "attack_rank, " +
                "attack_level, " +
                "attack_xp, " +
                "defence_rank, " +
                "defence_level, " +
                "defence_xp, " +
                "strength_rank, " +
                "strength_level, " +
                "strength_xp, " +
                "hitpoints_rank, " +
                "hitpoints_level, " +
                "hitpoints_xp, " +
                "ranged_rank, " +
                "ranged_level, " +
                "ranged_xp, " +
                "prayer_rank, " +
                "prayer_level, " +
                "prayer_xp, " +
                "magic_rank, " +
                "magic_level, " +
                "magic_xp, " +
                "cooking_rank, " +
                "cooking_level, " +
                "cooking_xp, " +
                "woodcutting_rank, " +
                "woodcutting_level, " +
                "woodcutting_xp,  " +
                "fletching_rank," +
                "fletching_level, " +
                "fletching_xp, " +
                "fishing_rank, " +
                "fishing_level, " +
                "fishing_xp, " +
                "firemaking_rank, " +
                "firemaking_level, " +
                "firemaking_xp, " +
                "crafting_rank, " +
                "crafting_level, " +
                "crafting_xp, " +
                "smithing_rank, " +
                "smithing_level, " +
                "smithing_xp, " +
                "mining_rank, " +
                "mining_level, " +
                "mining_xp, " +
                "herlore_rank, " +
                "herlore_level, " +
                "herlore_xp, " +
                "agility_rank, " +
                "agility_level, " +
                "agility_xp, " +
                "thieving_rank, " +
                "thieving_level, " +
                "thieving_xp, " +
                "slayer_rank, " +
                "slayer_level, " +
                "slayer_xp, " +
                "farming_rank, " +
                "farming_level, " +
                "farming_xp, " +
                "runecraft_rank, " +
                "runecraft_level, " +
                "runecraft_xp, " +
                "hunter_rank, " +
                "hunter_level, " +
                "hunter_xp, " +
                "construction_rank, " +
                "construction_level, " +
                "construction_xp, " +
                "clue_scroll_all_score, " +
                "clue_scroll_beginner_score, " +
                "clue_scroll_easy_score, " +
                "clue_scroll_medium_score, " +
                "clue_scroll_hard_score, " +
                "clue_scroll_elite_score, " +
                "clue_scroll_master_score, " +
                "created_at, " +
                "updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = con.prepareStatement(query);

        String[] splitLones = s.split("\n");

        String[] skills = {"overall",
                "attack",
                "defence",
                "strength",
                "hitpoints",
                "ranged",
                "prayer",
                "magic",
                "cooking",
                "woodcutting",
                "fletching",
                "fishing",
                "firemaking",
                "crafting",
                "smithing",
                "mining",
                "herblore",
                "agility",
                "thieving",
                "slayer",
                "farming",
                "runecrafting",
                "hunter",
                "construction",
                "bounty",
                "bounty",
                "bounty",
                "clue_scroll_all",
                "clue_scroll_beginner",
                "clue_scroll_easy",
                "clue_scroll_medium",
                "clue_scroll_hard",
                "clue_scroll_elite",
                "clue_scroll_master"
        };

        int queryPosition = 1;

        preparedStatement.setInt(queryPosition++, userId);

        for (int x = 0; x < skills.length; x++) {
            if (!skills[x].equals("bounty") && !skills[x].contains("clue")) {
                String[] stats = splitLones[x].split(",");
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[0]));
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[1]));
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[2]));
            } else if (!skills[x].equals("bounty")) {
                String[] stats = splitLones[x].split(",");
                preparedStatement.setInt(queryPosition++, Integer.parseInt(stats[1]));
            }
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        preparedStatement.setTimestamp(queryPosition++, ts);
        preparedStatement.setTimestamp(queryPosition++, ts);

        return preparedStatement;
    }

    @Override
    public void execute() {
        try {
            Logger.log("User:" + cmdInput.getParam('u') + " updated.");
            String s = getUserData(cmdInput.getParam('u'));
            try (PreparedStatement preparedStatement = prepareDataHistory(s, 1)) {
                preparedStatement.execute();
            } catch (SQLException e) {
                Logger.log("480: " + e.toString());
            }

            try (PreparedStatement preparedStatement1 = prepareUserData(s, 1)) {
                preparedStatement1.execute();
            } catch (SQLException e) {
                Logger.log("486: " + e.toString());
            }
        } catch (NoParamExistException e) {
            Logger.log("Require parameter -u.");
        }
    }
}
