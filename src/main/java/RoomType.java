//enum,constructor inside enum
public enum RoomType {

    STANDARD(2000),
    DELUXE(3500),
    SUITE(5000);

    private double pricePerNight;

    // Constructor
    RoomType(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    // Getter
    public double getPricePerNight() {
        return pricePerNight;
    }

    // Method to calculate cost
    public double calculateCost(int days) {
        return pricePerNight * days;
    }
}