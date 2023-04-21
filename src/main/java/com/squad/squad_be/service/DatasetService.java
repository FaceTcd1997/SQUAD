package com.squad.squad_be.service;

import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.squad.squad_be.dto.*;
import com.squad.squad_be.dto.enums.Rank;
import com.squad.squad_be.dto.enums.Role;
import com.squad.squad_be.dto.enums.Tier;
import com.squad.squad_be.dto.ml.UserEntry;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DatasetService {

    public UserEntry mapUser(User user){
        UserEntry entry = new UserEntry();

        entry.setUSERNAME(user.getName());
        entry.setEMAIL(user.getEmail());
        entry.setACCOUNT_NAME(user.getAccount().getName());
        entry.setACCOUNT_ID(user.getAccount().getAccountId());
        entry.setACCOUNT_LEVEL(user.getAccount().getSummonerLevel());
        entry.setWINS(user.getStats().getQueueStats()[0].getWins());
        entry.setLOSSES(user.getStats().getQueueStats()[0].getLosses());
        entry.setWIN_RATE(calculateWinRate(user.getStats().getQueueStats()[0].getWins(), user.getStats().getQueueStats()[0].getLosses()));
        entry.setLEAGUE_POINTS(user.getStats().getQueueStats()[0].getLeaguePoints());
        entry.setRANK(user.getStats().getQueueStats()[0].getRank());
        entry.setTIER(user.getStats().getQueueStats()[0].getTier());
        entry.setOVERALL_SCORE(calculateRank(user.getStats().getQueueStats()[0].getLeaguePoints(), user.getStats().getQueueStats()[0].getRank(), user.getStats().getQueueStats()[0].getTier()));
        entry.setROLE(user.getRole());
        entry.setSCHEDULE_TIME_START(user.getSchedule_start());
        entry.setSCHEDULE_TIME_END(user.getSchedule_end());
        entry.setAGREEABLENESS(user.getTraits().getAgreeableness());
        entry.setCONSCIENTIOUSNESS(user.getTraits().getConscientiousness());
        entry.setEXTRAVERSION(user.getTraits().getExtraversion());
        entry.setNEUROTICISM(user.getTraits().getNeuroticism());
        entry.setOPENNESS(user.getTraits().getOpenness());
        entry.setREQUEST_REGION("eur");

        return entry;
    }

    public void addUser(User user) throws Exception {
        // create a FileWriter object with the append flag set to true
        ICSVWriter writer = new CSVWriterBuilder(new FileWriter("./src/main/dataset/users.csv", true)).withSeparator(',').withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).build();

        // create a new row to append
        String[] newRow = new String[] {
                user.getName() != null ? user.getName() : "", //Name
                user.getEmail() != null ? user.getEmail() : "", //Email
                user.getAccount() != null && user.getAccount().getName() != null ? user.getAccount().getName() : "", //Account name
                user.getAccount() != null && user.getAccount().getId() != null ? user.getAccount().getId() : "" //Account ID
        };
        // append the new row to the CSV file
        writer.writeNext(newRow);
        // close the CSVWriter and FileWriter objects
        writer.close();
    }

    private Double calculateWinRate(Integer wins, Integer losses){
        double winRate;
        if(!(wins != null && losses != null))
            return null;

        double total = wins + losses;
        winRate = (double)wins * 100 / total;

        DecimalFormat df = new DecimalFormat("#.#");
        return Double.valueOf(df.format(winRate).replace(",", "."));
    }

    private Integer calculateRank(Integer leaguePoints, Rank rank, Tier tier){
        int score;
        // Null check
        if(!(leaguePoints != null && rank != null && tier != null))
            return null;
        // Master Grandmaster and Challenger are defined by the percentage of players, don't have an upper limit
        if(tier.equals(Tier.MASTER) || tier.equals(Tier.GRANDMASTER) || tier.equals(Tier.CHALLENGER)){
            score = leaguePoints + rank.ordinal() * 100 + Tier.MASTER.ordinal() * 400;
        }else{
            score = leaguePoints + rank.ordinal() * 100 + tier.ordinal() * 400;
        }
        return score;
    }

    public void boh() {
        try {
            File inputFile = new File("./src/main/dataset/lol_b5p_v3.csv");
            File outputFile = new File("./src/main/dataset/lol_b5p_v2.csv");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String header = reader.readLine();
            writer.write(header + ",REQUEST_REGION\n");
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + ",eur\n");
            }
            reader.close();
            writer.close();
            System.out.println("Column added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** ---------------------------------------------------------------------------------------------------------------------------------------------------------------* */
    /*public void addUser2(User user) throws Exception {
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
    }    */

    public void createDataset() throws IOException {
        // Read the CSV file and create a list of row objects
        List<User> users = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 15000; i++) {
            User user = new User();
            Faker faker = new Faker();
            // Generate a random name
            String name = faker.name().fullName();
            // Generate a random email address
            String email = faker.internet().emailAddress();
            // Generate a random account name
            String accountName = faker.funnyName().name();
            // Generate a random accound ID
            String accoundId = faker.internet().uuid();

            QueueStat queueStat = new QueueStat();
            queueStat.setWins(rand.nextInt(0, 200));
            queueStat.setLosses(rand.nextInt(0, 200));
            queueStat.setTier(Tier.values()[rand.nextInt(0,9)]);
            if(queueStat.getTier().equals(Tier.MASTER)){
                queueStat.setLeaguePoints(rand.nextInt(0, 200));
            }else if(queueStat.getTier().equals(Tier.GRANDMASTER)){
                queueStat.setLeaguePoints(rand.nextInt(200, 400));
            }else if(queueStat.getTier().equals(Tier.CHALLENGER)){
                queueStat.setLeaguePoints(rand.nextInt(400, 600));
            }else{
                queueStat.setLeaguePoints(rand.nextInt(0, 100));
            }
            GameStats stats = new GameStats();
            stats.setQueueStats(new QueueStat[] {queueStat});
            user.setStats(stats);
            //Rank
            if(user.getStats().getQueueStats()[0].getTier().equals(Tier.MASTER) || user.getStats().getQueueStats()[0].getTier().equals(Tier.GRANDMASTER) || user.getStats().getQueueStats()[0].getTier().equals(Tier.CHALLENGER)){
                user.getStats().getQueueStats()[0].setRank(Rank.I);
            }else{
                Rank[] ranks = Rank.values();
                Rank randomRank = ranks[rand.nextInt(ranks.length)];
                user.getStats().getQueueStats()[0].setRank(randomRank);
            }

            Account account = new Account();
            account.setName(accountName);
            account.setId(accoundId);
            account.setSummonerLevel(rand.nextInt(0, 700));
            user.setAccount(account);

            Traits traits = new Traits();
            user.setTraits(traits);
            user.getTraits().setAgreeableness(rand.nextDouble(1, 5));
            user.getTraits().setExtraversion(rand.nextDouble(1, 5));
            user.getTraits().setConscientiousness(rand.nextDouble(1, 5));
            user.getTraits().setNeuroticism(rand.nextDouble(1, 5));
            user.getTraits().setOpenness(rand.nextDouble(1, 5));

            Role randomRole = Role.values()[rand.nextInt(0,5)];
            user.setRole(randomRole);

            user.setName(name);
            user.setEmail(email);

            int start = rand.nextInt(0, 24);
            int duration = rand.nextInt(3, 6);
            int end = start + duration;
            if(end >= 24)
                end -= 24;

            user.setSchedule_start(start);
            user.setSchedule_end(end);

            users.add(user);
        }

        ICSVWriter writer = new CSVWriterBuilder(new FileWriter("./src/main/dataset/lol_b5p.csv")).withSeparator(',').withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER).build();
        String[] header = {"USERNAME","EMAIL","ACCOUNT_NAME","ACCOUNT_ID","ACCOUNT_LEVEL","WINS","LOSSES","WIN_RATE","LEAGUE_POINTS","RANK","TIER","OVERALL_SCORE","ROLE","SCHEDULE_TIME_START","SCHEDULE_TIME_END","AGREEABLENESS","CONSCIENTIOUSNESS","EXTRAVERSION","NEUROTICISM","OPENNESS","REQUEST_REGION"};
        writer.writeNext(header);

        DecimalFormat df = new DecimalFormat("#.#");

        for (User user : users) {
            String[] row = new String[] {
                    user.getName(), //Name
                    user.getEmail(), //Email
                    user.getAccount().getName(), //Account name
                    user.getAccount().getId(), //Account ID
                    user.getAccount().getSummonerLevel().toString(),
                    Integer.toString(user.getStats().getQueueStats()[0].getWins()),
                    Integer.toString(user.getStats().getQueueStats()[0].getLosses()),
                    df.format(calculateWinRate(user.getStats().getQueueStats()[0].getWins(), user.getStats().getQueueStats()[0].getLosses())).replace(',','.'),
                    Integer.toString(user.getStats().getQueueStats()[0].getLeaguePoints()),
                    String.valueOf(user.getStats().getQueueStats()[0].getRank()),
                    String.valueOf(user.getStats().getQueueStats()[0].getTier()),
                    String.valueOf(calculateRank(user.getStats().getQueueStats()[0].getLeaguePoints(), user.getStats().getQueueStats()[0].getRank(), user.getStats().getQueueStats()[0].getTier())),
                    String.valueOf(user.getRole()),
                    user.getSchedule_start().toString(),
                    user.getSchedule_end().toString(),
                    df.format(user.getTraits().getAgreeableness()).replace(',','.'),
                    df.format(user.getTraits().getConscientiousness()).replace(',','.'),
                    df.format(user.getTraits().getExtraversion()).replace(',','.'),
                    df.format(user.getTraits().getNeuroticism()).replace(',','.'),
                    df.format(user.getTraits().getOpenness()).replace(',','.'),
                    "eur"
            };
            writer.writeNext(row);
        }
        writer.close();
    }

}

