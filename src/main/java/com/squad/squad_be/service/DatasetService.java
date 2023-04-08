package com.squad.squad_be.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.squad.squad_be.dto.*;
import com.squad.squad_be.dto.enums.Rank;
import com.squad.squad_be.dto.enums.Role;
import com.squad.squad_be.dto.enums.Tier;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DatasetService {

    private final String[] fields = {"email", "name", "summonerLevel"};

    public void addUser(User user) throws Exception {
        // create a FileWriter object with the append flag set to true
        //FileWriter fileWriter = new FileWriter("./src/main/dataset/lol_b5p.csv", true);
        ICSVWriter writer = new CSVWriterBuilder(new FileWriter("./src/main/dataset/lol_b5p.csv", true)).withSeparator(',').withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).build();

        // create a new row to append
        String[] newRow = new String[] {
                user.getName() != null ? user.getName() : "", //Name
                user.getEmail() != null ? user.getEmail() : "", //Email
                user.getAccount() != null && user.getAccount().getName() != null ? user.getAccount().getName() : "", //Account name
                user.getAccount() != null && user.getAccount().getId() != null ? user.getAccount().getId() : "", //Account ID
                user.getAccount() != null && user.getAccount().getSummonerLevel() != null ? user.getAccount().getSummonerLevel().toString() : "", //Account level
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        Integer.toString(user.getStats().getQueueStats()[0].getWins()) : "", //Wins
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        Integer.toString(user.getStats().getQueueStats()[0].getLosses()) : "", //Losses
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        Integer.toString(user.getStats().getQueueStats()[0].getLeaguePoints()) : "", //League points
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        String.valueOf(user.getStats().getQueueStats()[0].getRank().ordinal()) : "", //League rank
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        String.valueOf(user.getStats().getQueueStats()[0].getTier().ordinal()) : "", //League tier
                user.getRole() != null ? String.valueOf(user.getRole().ordinal()) : "", //Role
                user.getSchedule_start() != null ? user.getSchedule_start().toString() : "", //Schedule start
                user.getSchedule_end() != null ? user.getSchedule_end().toString() : "", //Schedule end
                user.getTraits() != null && user.getTraits().getAgreeableness() != null ? user.getTraits().getAgreeableness().toString() : "", //Agreeableness
                user.getTraits() != null && user.getTraits().getExtraversion() != null ? user.getTraits().getExtraversion().toString() : "", //Extraversion
                user.getTraits() != null && user.getTraits().getConscientiousness() != null ? user.getTraits().getConscientiousness().toString() : "", 	//Conscientiousness
                user.getTraits() != null && user.getTraits().getOpenness() != null ? user.getTraits().getOpenness().toString() : "", //Openness
                user.getTraits() != null && user.getTraits().getNeuroticism() != null ? user.getTraits().getNeuroticism().toString() : "", //Neuroticism
                user.getGroup() != null ? user.getGroup().toString() :"", //Group
        };

        // append the new row to the CSV file
        writer.writeNext(newRow);

        // close the CSVWriter and FileWriter objects
        writer.close();

    }

    public void fillValues() throws Exception {
        // Read the CSV file and create a list of row objects
        CSVReader reader = new CSVReader(new FileReader("./src/main/dataset/lol_raw.csv"));
        List<String[]> rows = reader.readAll();
        List<User> users = new ArrayList<>();
        for (String[] row : rows) {
            User user = new User();

            Traits traits = new Traits();
            user.setTraits(traits);

            // Set properties from the row array
            QueueStat queueStat = new QueueStat();
            queueStat.setWins(Integer.parseInt(row[1]));
            queueStat.setLosses(Integer.parseInt(row[2]));
            queueStat.setLeaguePoints(Integer.parseInt(row[3]));
            //queueStat.setRank(Rank.valueOf(row[4]));
            //queueStat.setTier(Tier.valueOf(row[5]));

            Account account = new Account();
            //account.setName(row[9]);
            //account.setId(row[10]);
            //account.setSummonerLevel(Integer.parseInt(row[11]));
            user.setAccount(account);

            // Assuming the username is in column 7
            //user.setName(row[7]);

            // Assuming the email is in column 8
            //user.setEmail(row[8]);

            // Assuming the role is in column 14
            //user.setRole(Role.valueOf(row[14]));

            GameStats stats = new GameStats();
            stats.setQueueStats(new QueueStat[] {queueStat});
            user.setStats(stats);

            // Set other properties...
            users.add(user);
        }

        // Generate random values for the RANK column
        Random rand = new Random();
        for (User user : users) {
            //Traits
            user.getTraits().setAgreeableness(rand.nextInt(1, 5));
            user.getTraits().setExtraversion(rand.nextInt(1, 5));
            user.getTraits().setConscientiousness(rand.nextInt(1, 5));
            user.getTraits().setNeuroticism(rand.nextInt(1, 5));
            user.getTraits().setOpenness(rand.nextInt(1, 5));

            //Account level
            user.getAccount().setSummonerLevel(rand.nextInt(1, 700));

            //Rank
            Rank[] ranks = Rank.values();
            Rank randomRank = ranks[rand.nextInt(ranks.length)];
            user.getStats().getQueueStats()[0].setRank(randomRank);

            //Tier
            Tier[] tiers = Tier.values();
            Tier randomTier = tiers[rand.nextInt(tiers.length)];
            user.getStats().getQueueStats()[0].setTier(randomTier);

            //Role
            Role[] roles = Role.values();
            Role randomRole = roles[rand.nextInt(roles.length)];
            user.setRole(randomRole);

            //Schedule time start/end
            int start = rand.nextInt(0, 24);
            int duration = rand.nextInt(1, 5);
            int end = start + duration;
            if(end >= 24)
                end -= 24;

            user.setSchedule_start(start);
            user.setSchedule_end(end);
        }

        // Write the updated row objects back to the CSV file
        //CSVWriter writer = new CSVWriter(new FileWriter("./src/main/dataset/lol_b5p.csv"));
        //CSVWriter writer = new CSVWriter(new FileWriter("./src/main/dataset/lol_b5p.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);
        ICSVWriter writer = new CSVWriterBuilder(new FileWriter("./src/main/dataset/lol_b5p.csv")).withSeparator(',').withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).build();

        for (User user : users) {
            String[] row = new String[] {
                    "", //Name
                    "", //Email
                    "", //Account name
                    "", //Account ID
                    user.getAccount().getSummonerLevel().toString(),
                    Integer.toString(user.getStats().getQueueStats()[0].getWins()),
                    Integer.toString(user.getStats().getQueueStats()[0].getLosses()),
                    Integer.toString(user.getStats().getQueueStats()[0].getLeaguePoints()),
                    String.valueOf(user.getStats().getQueueStats()[0].getRank().ordinal()),
                    String.valueOf(user.getStats().getQueueStats()[0].getTier().ordinal()),
                    String.valueOf(user.getRole().ordinal()),
                    user.getSchedule_start().toString(),
                    user.getSchedule_end().toString(),
                    user.getTraits().getAgreeableness().toString(),
                    user.getTraits().getExtraversion().toString(),
                    user.getTraits().getConscientiousness().toString(),
                    user.getTraits().getOpenness().toString(),
                    user.getTraits().getNeuroticism().toString(),
                    ""
            };
            writer.writeNext(row);
        }
        writer.close();

    }

}

