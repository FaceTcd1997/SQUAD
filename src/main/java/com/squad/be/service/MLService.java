package com.squad.be.service;

import com.squad.be.api.MlApi;
import com.squad.be.dto.ml.Teams;
import com.squad.be.dto.ml.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;

@Service
public class MLService {

    @Autowired
    private MlApi mlApi;

    public Teams retrieveMatches(UserEntry entry) {
        return mlApi.retrieveMatches(entry);
    }

    public Teams retrieveNewMatches(UserEntry entry) {
        return mlApi.retrieveNewMatches(entry);
    }

    public String updateEntry(UserEntry entry) {
        return mlApi.updateEntry(entry);
    }

    public String removeEntry(UserEntry entry) {
        return mlApi.removeEntry(entry);
    }

    public void clusterize() throws Exception {
        // Load CSV file into fullData
        CSVLoader fullLoader = new CSVLoader();
        fullLoader.setSource(new File("./src/main/dataset/lol_b5p.csv"));
        Instances fullData = fullLoader.getDataSet();

        // Load CSV file into data and remove attributes
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("./src/main/dataset/lol_b5p.csv"));
        Instances data = loader.getDataSet();

        data.deleteAttributeAt(12); //Remove Role
        data.deleteAttributeAt(10); //Remove Tier
        data.deleteAttributeAt(9); //Remove Rank
        data.deleteAttributeAt(6); //Remove Losses
        data.deleteAttributeAt(5); //Remove Wins
        data.deleteAttributeAt(0); //Remove AccountID
        data.deleteAttributeAt(0); //Remove Account name
        data.deleteAttributeAt(0); //Remove Email
        data.deleteAttributeAt(0); //Remove Name

        // Create a new attribute for the cluster information
        Attribute clusterAttribute = new Attribute("CLUSTER");
        // Add the new attribute to the fullData and data instances
        fullData.insertAttributeAt(clusterAttribute, fullData.numAttributes());
        data.insertAttributeAt(clusterAttribute, data.numAttributes());

        // Set the size of the clusters to 4
        int clusterSize = 4;

        // Set number of clusters to n rows divided into groups of 4
        int numClusters = (int) Math.ceil((double) fullData.numInstances() / clusterSize);

        // Initialize K-means clusterer
        SimpleKMeans kmeans = new SimpleKMeans();
        kmeans.setNumClusters(numClusters);
        kmeans.setPreserveInstancesOrder(true);
        kmeans.buildClusterer(data);

        // Assign cluster information to the fullData instances and populate the new attribute
        for (int i = 0; i < data.numInstances(); i++) {
            int cluster = kmeans.clusterInstance(data.instance(i));
            fullData.instance(i).setValue(fullData.attribute("CLUSTER"), cluster);
        }

        // Save the modified fullData to a new CSV file with missing values replaced with null
        weka.core.converters.CSVSaver saver = new weka.core.converters.CSVSaver();
        saver.setInstances(fullData);
        saver.setFile(new File("./src/main/dataset/lol_b5p_clustered.csv"));
        saver.setMissingValue("");
        saver.writeBatch();
    }

}
