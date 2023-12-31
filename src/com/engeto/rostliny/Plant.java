package com.engeto.rostliny;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private BigDecimal frequencyOfWatering;

    public Plant(String name, String notes, BigDecimal frequencyOfWatering, LocalDate watering, LocalDate planted) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.setFrequencyOfWatering(frequencyOfWatering);
        this.watering = watering;
        this.planted = planted;
    }

    public Plant(String name, String notes, LocalDate watering) {
        this.name = name;
        this.notes = "";
        this.watering = LocalDate.now();
    }

    public Plant(String name) throws PlantException {
        this.name = name;
        this.notes = null;
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.setFrequencyOfWatering(BigDecimal.valueOf(7));
    }

    public String getWateringInfo(){
        return "Názov: "+name+"; deň polievania{"+watering+"}; doporučený deň ďaľšieho polievania("+frequencyOfWatering+" dní)";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(getPlanted())){
            throw new PlantException(
                    "Dátum polievnia nesmie byť skôr, než dátum zasadenia rastliny (zadal si:" +watering+")");
        }

        this.watering = watering;
    }

    public BigDecimal getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(BigDecimal frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering.compareTo(BigDecimal.ZERO) <= 0){
                throw new PlantException (
                        "Frekvencia polievania nesmie byť 0 a menej" + "(zadal si: " + frequencyOfWatering + ")");
            }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    @Override
    public String toString() {
        return name+" "+notes+"\t"+frequencyOfWatering+"\t"+watering+"\t"+planted;
    }

    @Override
    public int compareTo(Plant o) {
        return this.name.compareTo(o.name);
    }
}