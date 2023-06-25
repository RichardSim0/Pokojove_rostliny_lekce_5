import com.engeto.rostliny.LoadingSettings;
import com.engeto.rostliny.Plant;
import com.engeto.rostliny.PlantException;
import com.engeto.rostliny.RegisterOfPlants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RegisterOfPlants registerOfPlants = new RegisterOfPlants();
//        18. ÚLOHA
        try {
            registerOfPlants.loadDataFromFile(LoadingSettings.fileName3(),LoadingSettings.delimiter());
        } catch (PlantException e) {
            System.err.println("Nepodarilo sa načítať dáta zo súboru: \""+LoadingSettings.fileName3()+"\" "+e.getLocalizedMessage());
        }
//        17.ÚLOHA
        try {
            registerOfPlants.loadDataFromFile(LoadingSettings.fileName2(),LoadingSettings.delimiter());
        } catch (PlantException e) {
            System.err.println("Nepodarilo sa načítať dáta zo súboru: \""+LoadingSettings.fileName2()+"\" "+e.getLocalizedMessage());
        }
        try {
            registerOfPlants.loadDataFromFile(LoadingSettings.fileName(),LoadingSettings.delimiter());
        } catch (PlantException e) {
            System.err.println("Nepodarilo sa načítať dáta zo súboru: \""+LoadingSettings.fileName()+"\" "+e.getLocalizedMessage());
        }
//        13. ÚLOHA
        List<Plant> plantList = registerOfPlants.getPlantList();
        for (Plant plant : plantList){
            System.out.println(plant.getWatering());
        }
        try {
            plantList.add(new Plant("Bazalka","v kuchyni", BigDecimal.TWO,LocalDate.of(2021,9,4),LocalDate.of(2021,9,4)));
        } catch (PlantException e) {
            System.err.println("Chyba pri zadávaní novej rastliny! "+e.getLocalizedMessage());
        }
        try {
            plantList.add(new Plant("Koriander","nechutí dobre",BigDecimal.ONE,LocalDate.now(),LocalDate.now()));
        } catch (PlantException e) {
            System.err.println("Chyba pri zadávaní novej rastliny! "+e.getLocalizedMessage());
        }
        plantList.remove(2);
        for (Plant plant : plantList){
            System.out.println(plant);
        }
    }
}