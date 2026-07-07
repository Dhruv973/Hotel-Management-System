//ARRAYLIST
//file handling
//instanceof
import java.util.ArrayList;

public class HotelManager {

    public ArrayList<Room> rooms = new ArrayList<>();

    // Add room
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Display all rooms
    public void displayRooms() {
        for (Room r : rooms) {

            String type;

            if (r instanceof SuiteRoom) {
                type = "SUITE";
            } else if (r instanceof DeluxeRoom) {
                type = "DELUXE";
            } else {
                type = "STANDARD";
            }

            String status = r.isBooked() ? "BOOKED" : "AVAILABLE";

            System.out.println("Room No: " + r.getRoomNumber() +
                    " | Type: " + type +
                    " | Price: " + r.calculateTariff() +
                    " | Status: " + status);
        }
    }

    // Save rooms to file
    public void saveToFile() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("rooms.txt");

            for (Room r : rooms) {

                String type;

                if (r instanceof SuiteRoom) {
                    type = "SUITE";
                } else if (r instanceof DeluxeRoom) {
                    type = "DELUXE";
                } else {
                    type = "STANDARD";
                }

                fw.write(r.getRoomNumber() + "," + type + "\n");
            }

            fw.close();

        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    // Load rooms from file
    public void loadFromFile() {

        rooms.clear(); // 🔥 important (no duplicates)

        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("rooms.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int roomNo = Integer.parseInt(data[0]);
                String type = data[1];

                Room r;

                if (type.equals("SUITE")) {
                r = new SuiteRoom(roomNo, RoomType.SUITE.getPricePerNight(), true, true, true, true);
                } else if (type.equals("DELUXE")) {
                r = new DeluxeRoom(roomNo, RoomType.DELUXE.getPricePerNight(), true, true);
                } else {
                r = new StandardRoom(roomNo, RoomType.STANDARD.getPricePerNight(), true);
                }

                rooms.add(r);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("No previous data found");
        }
    }
}