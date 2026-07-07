//MULTITHREADING
public class BookingThread extends Thread {

    private String customerName;
    private Room room;

    public BookingThread(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
    }

    @Override
    public void run() {
        System.out.println(customerName + " is booking Room " + room.getRoomNumber());

        try {
            Thread.sleep(2000); // simulate delay
        } catch (InterruptedException e) {
            System.out.println("Booking interrupted");
        }

        System.out.println(customerName + " successfully booked Room " + room.getRoomNumber());
    }
}