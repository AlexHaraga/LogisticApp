import java.util.List;
import java.time.LocalDate;

public class Delivery implements Runnable {
    private List<Package> packages;
    private String location;
    private LocalDate date;

    public Delivery(List<Package> packages, String location, LocalDate date) {
        this.packages = packages;
        this.location = location;
        this.date = date;
    }

    @Override
    public void run() {
        double revenue =0;
        double totalValue = 0;
        int distance = packages.get(0).getTargetDistance();
        for (Package packages : packages) {
            totalValue += packages.getPackageValue(); // !!!!!!! += or was it =+ dont forget to check it later
            revenue += distance;
        }

        System.out.println("[Delivering for the following location: " + location + " on the " + date + " in " + distance + " seconds]");

        try {
            Thread.sleep(distance * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Delivery completed for the following location: " + location + " on " + date + " Total Value of Delivery was: " + totalValue + " and it generated the revenue: " + revenue);
    }
}
