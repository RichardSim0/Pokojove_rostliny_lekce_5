package com.engeto.rostliny;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfPlants {
    private List<Plant> plantList = new ArrayList<>();

   public void loadDataFromFile (String fileName, String delimiter) throws PlantException {
       String[] items = new String[0];
       String line = "";
       int lineNumber = 1;
       try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
           while (scanner.hasNextLine()) {
               line = scanner.nextLine();
               items = line.split(delimiter);
               if (items.length != 5) throw new PlantException("Nesprávny počet položiek v riadku: "+lineNumber+": "+line);
               String name = items[0];
               String description = items[1];
               BigDecimal frequencyOfWatering = new BigDecimal(items[2]);
               LocalDate watering = LocalDate.parse(items[3]);
               LocalDate planted = LocalDate.parse(items[4]);
               Plant newPlant = new Plant(name, description, frequencyOfWatering, watering, planted);
               plantList.add(newPlant);
               lineNumber++;
           }
       } catch (FileNotFoundException e) {
           throw new PlantException("Súbor " + fileName + " sa nenašiel! " + e.getLocalizedMessage());
       }catch (NumberFormatException e){
           throw new PlantException("Nesprávne zadané číslo \""+items[2]+"\" na riadku "+lineNumber+":\n"+line);
       }catch (DateTimeParseException e){
           throw new PlantException("Nesprávne zadaný dátum na riadku "+lineNumber+":\n"+line);
       }
   }

    public void saveDataToFile (String fileName, String delimiter) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plantList){
                writer.println(
                        plant.getName() + delimiter
                                +plant.getNotes() + delimiter
                                +plant.getFrequencyOfWatering() + delimiter
                                +plant.getWatering() + delimiter
                                +plant.getPlanted() + delimiter

                );
            }
        } catch (IOException e) {
            throw new PlantException("Chyba pri zápise do súboru: "+fileName+": "+e.getLocalizedMessage());
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