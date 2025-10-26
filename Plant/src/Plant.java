import java.time.LocalDate;

public class Plant {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    //constructors

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering)
        throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;

        // frequency control
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být větší než 0.");
        }
        this.frequencyOfWatering = frequencyOfWatering;

        // watering control
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky (" + watering +
                    ") nemůže být před datem zasazení (" + planted + ").");
        }
        this.watering = watering;
    }

    public Plant(String name, int frequencyOfWatering) {
        this.name = name;
        this.frequencyOfWatering = frequencyOfWatering;
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.notes = "";
    }

    public Plant(String name) {
        this.name = name;
        this.frequencyOfWatering = 7;
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.notes = "";
    }

    //Geterrs and Setters

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
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky nemůže být před datem zasazení.");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být větší než 0.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //methods
    public String getWateringInfo() {
        return "Název Květiny: "+name+
                "\nDatum poslední zálivky: "+watering+
                "\nDatum doporučené další zálivky: "+watering.plusDays(frequencyOfWatering);
    }
    public LocalDate doWateringNow() {
        this.watering = LocalDate.now();
        return this.watering;
    }
}
