import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlantManagement manager = new PlantManagement();

        // load the list of plants from file kvetiny.txt
        try {
            manager.loadFromFile("kvetiny.txt");
        } catch (PlantException e) {
            System.out.println(e.getMessage());
        }

        // print watering info for all plants
        System.out.println("=== Informace o zálivce (všechny květiny) ===");
        List<Plant> all = manager.getAllPlants();
        for (Plant p : all) {
            System.out.println(p.getWateringInfo());
            System.out.println("----------------------------");
        }

        // add a new plant (custom data)
        try {
            Plant nova = new Plant(
                    "Monstera",
                    "v rohu obýváku",
                    LocalDate.now(),
                    LocalDate.now(),
                    7
            );
            manager.addPlant(nova);
        } catch (PlantException e) {
            System.out.println("Chyba při vytváření nové květiny: " + e.getMessage());
        }

        // add 10 plants with index from 1 to 10
        for (int i = 1; i <= 10; i++) {
            try {
                Plant t = new Plant(
                        "Tulipán na prodej " + i,
                        "čerstvý kus",
                        LocalDate.now(),
                        LocalDate.now(),
                        14
                );
                manager.addPlant(t);
            } catch (PlantException e) {
                System.out.println("Chyba při vytváření tulipánu " + i + ": " + e.getMessage());
            }
        }
        System.out.println("\nPo přidání: " + manager.size() + " květin celkem.");

        //remove the plant at the 3rd position
        if (manager.size() >= 3) {
            manager.removePlant(2);
            System.out.println("Odebrána květina na indexu 2 (3. pozice).");
        } else {
            System.out.println("Nelze odebrat 3. položku – seznam má méně než 3 květiny.");
        }
        System.out.println("Aktuálně v seznamu: " + manager.size() + " květin.");

        // save the list to a new file and verify its content by reloading it
        String outFile = "kvetiny_export.txt";
        try {
            manager.saveToFile(outFile);
            System.out.println("\nSeznam uložen do souboru: " + outFile);
        } catch (PlantException e) {
            System.out.println("Chyba při ukládání: " + e.getMessage());
        }

        // verify the content
        PlantManagement verify = new PlantManagement();
        try {
            verify.loadFromFile(outFile);
            System.out.println("Znovu načteno z " + outFile + ": " + verify.size() + " květin.");
        } catch (PlantException e) {
            System.out.println("Chyba při opětovném načítání exportovaného souboru: " + e.getMessage());
        }

        // sorting the list
        System.out.println("\n=== Seřazeno podle jména (výchozí) ===");
        manager.sortPlantsByName();
        for (Plant p : manager.getAllPlants()) {
            System.out.println(p.getName() + " | poslední zálivka: " + p.getWatering());
        }

        System.out.println("\n=== Seřazeno podle data poslední zálivky (od nejstarší) ===");
        manager.sortPlantsByWatering();
        for (Plant p : manager.getAllPlants()) {
            System.out.println(p.getWatering() + " | " + p.getName());
        }

        System.out.println("\nHotovo");
    }
}
