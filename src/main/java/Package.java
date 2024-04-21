import java.time.LocalDate;
public class Package {
    private String targetLocation;
    private int targetDistance;
    private double packageValue;
    private LocalDate deliveryDate;
    public Package(String targetLocation, int targetDistance, double packageValue, LocalDate deliveryDate) { //Constructor
        this.targetLocation = targetLocation;
        this.targetDistance = targetDistance;
        this.packageValue = packageValue;
        this.deliveryDate = deliveryDate;
    }
    public Package() { //Empty constructor. You never know.
        System.out.println("Please enter valid data");
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public int getTargetDistance() {
        return targetDistance;
    }

    public double getPackageValue() {
        return packageValue;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
}

