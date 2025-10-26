import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PlantManagement {
    private List<Plant> plants = new ArrayList<Plant>();

    public void addPlant(Plant plant) {
        plants.add(plant);
        sortPlantsByName();
    }

    public Plant getPlant(int index) {
        if (index < 0 || index >= plants.size()) {
            return null;
        }
        return plants.get(index);
    }

    public void removePlant(int index) {
        if (index >= 0 && index < plants.size()) {
            plants.remove(index);
        }
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<Plant>(plants);
    }

    public int size() {
        return plants.size();
    }

    public List<Plant> getPlantsToWater() {
        List<Plant> needWater = new ArrayList<Plant>();
        LocalDate today = LocalDate.now();

        for (Plant p : plants) {
            LocalDate lastWatering = p.getWatering();
            int frequency = p.getFrequencyOfWatering();

            LocalDate nextWateringDate = lastWatering.plusDays(frequency);

            if (!today.isAfter(nextWateringDate)) {
                needWater.add(p);
            }
        }
        return needWater;
    }
    public void sortPlantsByName() {
        Collections.sort(plants, new Comparator<Plant>() {
            @Override
            public int compare(Plant o1, Plant o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
    public void sortPlantsByWatering() {
        Collections.sort(plants, new Comparator<Plant>() {
            @Override
            public int compare(Plant o1, Plant o2) {
                return o1.getWatering().compareTo(o2.getWatering());
            }
        });
    }
    public void loadFromFile(String fileName) throws PlantException {
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            int lineNumber = 0;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lineNumber++;

                String[] parts = line.split("\t");
                if (parts.length != 5) {
                    throw new PlantException("Řádek " + lineNumber + ": očekáváno 5 hodnot oddělených tabulátorem.");
                }

                String name = parts[0];
                String notes = parts[1];
                int frequency = Integer.parseInt(parts[2]);
                LocalDate watering = LocalDate.parse(parts[3]);
                LocalDate planted = LocalDate.parse(parts[4]);

                if (frequency <= 0) {
                    throw new PlantException("Řádek " + lineNumber + ": frekvence musí být kladná.");
                }
                if (watering.isBefore(planted)) {
                    throw new PlantException("Řádek " + lineNumber + ": zálivka nemůže být dříve než zasazení.");
                }

                plants.add(new Plant(name, notes, planted, watering, frequency));
            }
            sortPlantsByName();
        }
        catch (IOException e) {
            throw new PlantException("Chyba při čtení zápisu " + e.getMessage());
        }
    }
    public void saveToFile(String fileName) throws PlantException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant p : plants) {
                writer.println(
                        p.getName() + "\t" +
                                p.getNotes() + "\t" +
                                p.getFrequencyOfWatering() + "\t" +
                                p.getWatering() + "\t" +
                                p.getPlanted()
                );
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru: " + e.getMessage());
        }
    }
}

