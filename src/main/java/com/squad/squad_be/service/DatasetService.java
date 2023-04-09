package com.squad.squad_be.service;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.squad.squad_be.dto.*;
import com.squad.squad_be.dto.enums.Queue;
import com.squad.squad_be.dto.enums.Rank;
import com.squad.squad_be.dto.enums.Role;
import com.squad.squad_be.dto.enums.Tier;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DatasetService {

    public void addUser(User user) throws Exception {
        // create a FileWriter object with the append flag set to true
        ICSVWriter writer = new CSVWriterBuilder(new FileWriter("./src/main/dataset/lol_b5p.csv", true)).withSeparator(',').withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).build();

        DecimalFormat df = new DecimalFormat("#.#");

        // create a new row to append
        String[] newRow = new String[] {
                user.getName() != null ? user.getName().replace(" ", "") : "", //Name
                user.getEmail() != null ? user.getEmail().replace(" ", "") : "", //Email
                user.getAccount() != null && user.getAccount().getName() != null ? user.getAccount().getName().replace(" ", "") : "", //Account name
                user.getAccount() != null && user.getAccount().getId() != null ? user.getAccount().getId() : "", //Account ID
                user.getAccount() != null && user.getAccount().getSummonerLevel() != null ? user.getAccount().getSummonerLevel().toString() : "", //Account level
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        Integer.toString(user.getStats().getQueueStats()[0].getWins()) : "", //Wins
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        Integer.toString(user.getStats().getQueueStats()[0].getLosses()) : "", //Losses
                user.getStats() != null &&  user.getStats().getQueueStats().length > 0 && calculateWinRate(user.getStats().getQueueStats()[0].getWins(), user.getStats().getQueueStats()[0].getLosses()) != null ?
                        calculateWinRate(user.getStats().getQueueStats()[0].getWins(), user.getStats().getQueueStats()[0].getLosses()).replace(',','.') : "", //Win rate
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        Integer.toString(user.getStats().getQueueStats()[0].getLeaguePoints()) : "", //League points
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        String.valueOf(user.getStats().getQueueStats()[0].getRank()) : "", //League rank
                user.getStats() != null && user.getStats().getQueueStats() != null && user.getStats().getQueueStats().length > 0 ?
                        String.valueOf(user.getStats().getQueueStats()[0].getTier()) : "", //League tier
                user.getStats() != null &&  user.getStats().getQueueStats().length > 0 && calculateRank(user.getAccount().getSummonerLevel(), user.getStats().getQueueStats()[0].getRank(), user.getStats().getQueueStats()[0].getTier()) != null ?
                        String.valueOf(calculateRank(user.getAccount().getSummonerLevel(), user.getStats().getQueueStats()[0].getRank(), user.getStats().getQueueStats()[0].getTier())) : "", //Overall score
                user.getRole() != null ? String.valueOf(user.getRole()) : "", //Role
                user.getSchedule_start() != null ? user.getSchedule_start().toString() : "", //Schedule start
                user.getSchedule_end() != null ? user.getSchedule_end().toString() : "", //Schedule end
                user.getTraits() != null && user.getTraits().getAgreeableness() != null ? df.format(user.getTraits().getAgreeableness()).replace(',','.') : "", //Agreeableness
                user.getTraits() != null && user.getTraits().getConscientiousness() != null ? df.format(user.getTraits().getConscientiousness()).replace(',','.') : "", //Conscientiousness
                user.getTraits() != null && user.getTraits().getExtraversion() != null ? df.format(user.getTraits().getExtraversion()).replace(',','.') : "", //Extraversion
                user.getTraits() != null && user.getTraits().getNeuroticism() != null ? df.format(user.getTraits().getNeuroticism()).replace(',','.') : "", //Neuroticism
                user.getTraits() != null && user.getTraits().getOpenness() != null ? df.format(user.getTraits().getOpenness()).replace(',','.') : "" //Openness
        };

        // append the new row to the CSV file
        writer.writeNext(newRow);
        // close the CSVWriter and FileWriter objects
        writer.close();

    }

    private String calculateWinRate(Integer wins, Integer losses){
        double winRate;
        if(!(wins != null && losses != null))
            return null;

        double total = wins + losses;
        winRate = (double)wins * 100 / total;

        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(winRate);
    }

    private Integer calculateRank(Integer leaguePoints, Rank rank, Tier tier){
        int score;
        // Null check
        if(!(leaguePoints != null && rank != null && tier != null))
            return null;
        // Master Grandmaster and Challenger are defined by the percentage of players, don't have an upper limit
        if(tier.equals(Tier.MASTER) || tier.equals(Tier.GRANDMASTER) || tier.equals(Tier.CHALLENGER)){
            score = leaguePoints + (Tier.MASTER.ordinal() + 1) * 400;
        }else{
            score = leaguePoints + rank.ordinal() * 100 + tier.ordinal() * 400;
        }
        return score;
    }

    public User retrieveMatches(User user) throws Exception {
        //Retrieve matches
        List<String[]> matchingRows = getRowsByCluster(user);
        //Remove same role and user
        Role userRole = user.getRole();
        matchingRows.removeIf(row -> Role.valueOf(row[12]).equals(userRole));
        //Convert matches
        List<User> matches = convertRowsToUsers(matchingRows);
        //Store matches
        user.setMatches(matches.toArray(User[]::new));
        return user;
    }

    private List<String[]> getRowsByCluster(User user) throws Exception {
        List<String[]> rows;
        String name = user.getName().replace(" ", "");
        String email = user.getEmail().replace(" ", "");
        String accountName = user.getAccount().getName().replace(" ", "");

        // Read the CSV file into a list of arrays
        try (CSVReader reader = new CSVReader(new FileReader("./src/main/dataset/lol_b5p_clustered.csv"))) {
            rows = reader.readAll();
        }

        // Find the cluster value for the given account ID
        String cluster = "";
        for (String[] row : rows) {
            if (row[0].equals(name) && row[1].equals(email) && row[2].equals(accountName)) {
                cluster = row[20];
                break;
            }
        }

        // Find all rows with the same cluster value
        List<String[]> matchingRows = new ArrayList<>();
        for (String[] row : rows) {
            if (row[20].equals(cluster)) {
                matchingRows.add(row);
            }
        }

        return matchingRows;
    }

    private List<User> convertRowsToUsers(List<String[]> rows) {
        List<User> matches = new ArrayList<>();

        for(String[] row : rows){
            Account account = new Account(row[3],null,null,row[2],null,null,Integer.valueOf(row[4]));
            QueueStat queueStat = new QueueStat(null, Queue.RANKED_FLEX_SR,Tier.valueOf(row[10]),Rank.valueOf(row[9]),null,null,Integer.parseInt(row[8]),Integer.parseInt(row[5]),Integer.parseInt(row[6]),null,null,null,null);
            GameStats gameStats = new GameStats(new QueueStat[]{queueStat});
            Traits traits = new Traits(Double.valueOf(row[15]),Double.valueOf(row[16]),Double.valueOf(row[17]),Double.valueOf(row[18]),Double.valueOf(row[19]));

            User match = new User();
            match.setName(row[0]);
            match.setEmail(row[1]);
            match.setSchedule_start(Integer.parseInt(row[13]));
            match.setSchedule_end(Integer.parseInt(row[14]));
            match.setTraits(traits);
            match.setStats(gameStats);
            match.setAccount(account);
            match.setRole(Role.valueOf(row[12]));

            matches.add(match);
        }

        return matches;
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
            user.getTraits().setAgreeableness(rand.nextDouble(1, 5));
            user.getTraits().setExtraversion(rand.nextDouble(1, 5));
            user.getTraits().setConscientiousness(rand.nextDouble(1, 5));
            user.getTraits().setNeuroticism(rand.nextDouble(1, 5));
            user.getTraits().setOpenness(rand.nextDouble(1, 5));

            //Account level
            user.getAccount().setSummonerLevel(rand.nextInt(1, 700));

            //Tier
            Tier[] tiers = Tier.values();
            Tier randomTier = tiers[rand.nextInt(tiers.length)];
            user.getStats().getQueueStats()[0].setTier(randomTier);

            //Rank
            if(user.getStats().getQueueStats()[0].getTier().equals(Tier.MASTER) || user.getStats().getQueueStats()[0].getTier().equals(Tier.GRANDMASTER) || user.getStats().getQueueStats()[0].getTier().equals(Tier.CHALLENGER)){
                user.getStats().getQueueStats()[0].setRank(Rank.I);
            }else{
                Rank[] ranks = Rank.values();
                Rank randomRank = ranks[rand.nextInt(ranks.length)];
                user.getStats().getQueueStats()[0].setRank(randomRank);
            }

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
        //Header
        String[] header = {"USERNAME","EMAIL","ACCOUNT_NAME","ACCOUNT_ID","ACCOUNT_LEVEL","WINS","LOSSES","WIN_RATE","LEAGUE_POINTS","RANK","TIER","OVERALL_SCORE","ROLE","SCHEDULE_TIME_START","SCHEDULE_TIME_END","AGREEABLENESS","CONSCIENTIOUSNESS","EXTRAVERSION","NEUROTICISM","OPENNESS"};
        writer.writeNext(header);

        DecimalFormat df = new DecimalFormat("#.#");

        for (User user : users) {
            Faker faker = new Faker();
            // Generate a random name
            String name = faker.name().fullName();
            // Generate a random email address
            String email = faker.internet().emailAddress();
            // Generate a random account name
            String accountName = faker.funnyName().name();
            // Generate a random accound ID
            String accoundId = faker.internet().uuid();

            String[] row = new String[] {
                    name.replace(" ", ""), //Name
                    email.replace(" ", ""), //Email
                    accountName.replace(" ", ""), //Account name
                    accoundId, //Account ID
                    user.getAccount().getSummonerLevel().toString(),
                    Integer.toString(user.getStats().getQueueStats()[0].getWins()),
                    Integer.toString(user.getStats().getQueueStats()[0].getLosses()),
                    calculateWinRate(user.getStats().getQueueStats()[0].getWins(), user.getStats().getQueueStats()[0].getLosses()).replace(',','.'),
                    Integer.toString(user.getStats().getQueueStats()[0].getLeaguePoints()),
                    String.valueOf(user.getStats().getQueueStats()[0].getRank()),
                    String.valueOf(user.getStats().getQueueStats()[0].getTier()),
                    String.valueOf(calculateRank(user.getAccount().getSummonerLevel(), user.getStats().getQueueStats()[0].getRank(), user.getStats().getQueueStats()[0].getTier())),
                    String.valueOf(user.getRole()),
                    user.getSchedule_start().toString(),
                    user.getSchedule_end().toString(),
                    df.format(user.getTraits().getAgreeableness()).replace(',','.'),
                    df.format(user.getTraits().getConscientiousness()).replace(',','.'),
                    df.format(user.getTraits().getExtraversion()).replace(',','.'),
                    df.format(user.getTraits().getNeuroticism()).replace(',','.'),
                    df.format(user.getTraits().getOpenness()).replace(',','.')
            };
            writer.writeNext(row);
        }
        writer.close();

    }

}

