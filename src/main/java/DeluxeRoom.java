//INHERITANCE,additional services,billing,polymorphism
public class DeluxeRoom extends Room implements Amenities {

    private boolean breakfast;
    private boolean ac; // extra feature

    // Constructor
    public DeluxeRoom(int roomNumber, double basePrice, boolean breakfast, boolean ac) {
        super(roomNumber, basePrice); // using super
        this.breakfast = breakfast;
        this.ac = ac;
    }

    // Override method (Billing)
    @Override
    public double calculateTariff() {
        double cost = getBasePrice();

        // WiFi is already included in parent (no need to add again)

        if (breakfast)
            cost += 500;
        if (ac)
            cost += 700;

        return cost;
    }

    // 🔹 Amenities

    @Override
    public void provideMeals() {
        System.out.println("Meals service available");
    }

    @Override
    public void provideLaundry() {
        System.out.println("Laundry service available");
    }

    @Override
    public void provideGym() {
        System.out.println("Gym not available in Deluxe Room");
    }

    @Override
    public void provideSwimmingPool() {
        System.out.println("Swimming pool not available in Deluxe Room");
    }

    // Extra method
    public void showAC() {
        System.out.println("AC available: " + ac);
    }
}