//INHERITANCE,override method,polymorphism
public class StandardRoom extends Room {

    private boolean breakfast;

    // Constructor
    public StandardRoom(int roomNumber, double basePrice, boolean breakfast) {
        super(roomNumber, basePrice); // using super
        this.breakfast = breakfast;
    }

    // Implement abstract method
    @Override
    public double calculateTariff() {
        double cost = getBasePrice();

        if (breakfast)
            cost += 300;

        return cost;
    }

    // Optional method
    public void showBreakfast() {
        System.out.println("Breakfast available: " + breakfast);
    }
}