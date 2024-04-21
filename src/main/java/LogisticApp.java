import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
public class LogisticApp {
    public static void main(String[] args) {
        String filePath = "src/main/resources/test_file.txt";
        List<Package> packages = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] input = line.split(","); //splitting the lines based on the ","
                if (input.length == 4) { //making sure there are only 4 values on each line
                    Package randompackage = new Package(input[0].trim(),
                            Integer.parseInt(input[1].trim()),
                            Double.parseDouble(input[2].trim()),
                            LocalDate.parse(input[3].trim(), formatter));
                    packages.add(randompackage);
                }
            }
        } catch (IOException e) {
            System.out.println("File handling problem. Please solve!");
            e.printStackTrace();
        }

        Map<String, Map<LocalDate, List<Package>>> deliveries = new HashMap<>();
        for (Package pack : packages) {
            Map<LocalDate, List<Package>> dateMap = deliveries.get(pack.getTargetLocation());
            if (dateMap == null) {
                dateMap = new HashMap<>();
                deliveries.put(pack.getTargetLocation(), dateMap);
            }
            List<Package> packageList = dateMap.get(pack.getDeliveryDate()); // Check if the location map already has an entry for the delivery date
            if (packageList == null) {
                // If not, create a new list for the date
                packageList = new ArrayList<>();
                dateMap.put(pack.getDeliveryDate(), packageList);
            }
            packageList.add(pack);
        }

        for (String location : deliveries.keySet()) { //surely I could have used lambda streams here but due to lack of time went the old classic way
            Map<LocalDate, List<Package>> dateMap = deliveries.get(location);
            for (LocalDate date : dateMap.keySet()) {
                List<Package> dates = dateMap.get(date);
                Thread deliveryThread = new Thread(new Delivery(dates, location, date));
                deliveryThread.start();
            }
        }
    }
}
