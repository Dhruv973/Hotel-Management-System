//ABSTRACT CLASS,encapsulation,base class for inheritance
public abstract class Room {

    private int roomNumber;
    private double basePrice;

    protected boolean isBooked = false;
    protected boolean wifi = true; // common for all rooms

    // Constructor
    public Room(int roomNumber, double basePrice) {
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public double getBasePrice() {
        return basePrice;
    }

    // Booking status
    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }

    // WiFi (common for all rooms)
    public void provideWifi() {
        System.out.println("WiFi available: " + wifi);
    }

    // Abstract method (must be implemented by child classes)
    public abstract double calculateTariff();
}