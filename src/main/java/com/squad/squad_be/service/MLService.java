package com.squad.squad_be.service;

import org.springframework.stereotype.Service;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;

@Service
public class MLService {

    public void clusterize() throws Exception {
        // Load CSV file into fullData
        CSVLoader fullLoader = new CSVLoader();
        fullLoader.setSource(new File("./src/main/dataset/lol_b5p.csv"));
        Instances fullData = fullLoader.getDataSet();

        // Load CSV file into data and remove attributes
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("./src/main/dataset/lol_b5p.csv"));
        Instances data = loader.getDataSet();
        data.deleteAttributeAt(0);
        data.deleteAttributeAt(0);
        data.deleteAttributeAt(0);
        data.deleteAttributeAt(0);
        //data.deleteAttributeAt(14);

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
