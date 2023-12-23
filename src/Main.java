import com.engeto.rostliny.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        RegisterOfPlants registerOfPlants = new RegisterOfPlants();

        try {
            registerOfPlants.loadDataFromFile(LoadingSettings.fileName(),LoadingSettings.delimiter());
        } catch (PlantException e) {
            System.err.println("Nepodarilo sa načítať dáta zo súboru: \""+LoadingSettings.fileName()+"\" "+e.getLocalizedMessage());
        }
        try {
            registerOfPlants.add(new Plant("Bazalka","v kuchyni", BigDecimal.TWO,LocalDate.of(2021,9,4),LocalDate.of(2021,9,4)));
        } catch (PlantException e) {
            System.err.println("Chyba pri zadávaní novej rastliny! "+e.getLocalizedMessage());
        }
        try {
            registerOfPlants.add(new Plant("Koriander","nechutí dobre",BigDecimal.ONE,LocalDate.now(),LocalDate.now()));
        } catch (PlantException e) {
            System.err.println("Chyba pri zadávaní novej rastliny! "+e.getLocalizedMessage());
        }

        try {
            registerOfPlants.saveDataToFile(LoadingSettings.fileName(),LoadingSettings.delimiter());
        } catch (PlantException e) {
            System.err.println("Chyba pri zápise do súboru: "+e.getLocalizedMessage());
        }

        Set<Plant> plantHashSet = new HashSet<>(registerOfPlants.getPlantList());
        for (Plant plant : plantHashSet){
            System.out.println(plant.getPlanted());
        }

    }
}