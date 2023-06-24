package com.engeto.rostliny;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfPlants {
    private List<Plant> plantList = new ArrayList<>();

   public void loadDataFromFile (String fileName, String delimiter) throws PlantException {
       try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] items = line.split(delimiter);
                String name = items[0];
                String description = items[1];
                int frequencyOfWatering = Integer.parseInt(items[2]);
                LocalDate watering = LocalDate.parse(items[3]);
                LocalDate planted = LocalDate.parse(items[4]);
                Plant newPlant = new Plant(name, description, frequencyOfWatering, watering, planted);
                plantList.add(newPlant);
            }
       } catch (FileNotFoundException e) {
           throw new PlantException("Súbor"+fileName+"sa nenašiel"+e.getLocalizedMessage());
       }
   }

    public void add(Plant newPlant){
        plantList.add(newPlant);
    }

    public void remove(Plant plant){
        plantList.remove(plant);
    }

    public Plant get(int index){
        return plantList.get(index);
    }

    public List<Plant> getPlantList(){
       return new ArrayList<>(plantList);
    }
}
