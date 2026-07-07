//INHERITANCE,full services,billing
public class SuiteRoom extends Room implements Amenities {

    private boolean meals;
    private boolean laundry;
    private boolean gym;
    private boolean swimmingPool;

    // Constructor
    public SuiteRoom(int roomNumber, double basePrice,
                     boolean meals, boolean laundry,
                     boolean gym, boolean swimmingPool) {

        super(roomNumber, basePrice); // using super
        this.meals = meals;
        this.laundry = laundry;
        this.gym = gym;
        this.swimmingPool = swimmingPool;
    }

    // Override method (Billing)
    @Override
    public double calculateTariff() {

        double cost = getBasePrice();

        if (meals)
            cost += 1000;
        if (laundry)
            cost += 500;
        if (gym)
            cost += 800;
        if (swimmingPool)
            cost += 1200;

        return cost;
    }

    // 🔹 Amenities implementation

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
        System.out.println("Gym access available");
    }

    @Override
    public void provideSwimmingPool() {
        System.out.println("Swimming pool access available");
    }
}