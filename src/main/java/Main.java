//javafx,event handling,interaction of all classes,billing booking logic
import java.util.Iterator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    HotelManager manager = new HotelManager();

    @Override
    public void init() {
        manager.loadFromFile();
    }

    @Override
    public void start(Stage stage) {

        Label title = new Label("Hotel Management System");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");

        Label roomLabel = new Label("Enter Room Number:");
        TextField roomField = new TextField();
        roomField.setPromptText("Enter Room Number");

        Label daysLabel = new Label("Enter Number of Days:");
        TextField daysField = new TextField();
        daysField.setPromptText("Enter number of days");

        daysField.setVisible(false);
        daysLabel.setVisible(false);

        ComboBox<RoomType> roomTypeBox = new ComboBox<>();
        roomTypeBox.getItems().addAll(RoomType.values());
        roomTypeBox.setPromptText("Select Room Type");

        Label section = new Label("Room Operations");
        section.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Button addBtn = new Button("Add Room");
        Button removeBtn = new Button("Remove Room");
        Button showBtn = new Button("Show Rooms");
        Button bookBtn = new Button("Book Room");
        Button checkoutBtn = new Button("Customer Checkout");

        String btnStyle = "-fx-background-radius: 10; -fx-font-weight: bold; -fx-padding: 8 15;";

        addBtn.setStyle(btnStyle + "-fx-background-color: #4CAF50; -fx-text-fill: white;");
        showBtn.setStyle(btnStyle + "-fx-background-color: #2196F3; -fx-text-fill: white;");
        bookBtn.setStyle(btnStyle + "-fx-background-color: #FF9800; -fx-text-fill: white;");
        checkoutBtn.setStyle(btnStyle + "-fx-background-color: #E91E63; -fx-text-fill: white;");
        removeBtn.setStyle(btnStyle + "-fx-background-color: #F44336; -fx-text-fill: white;");

        addBtn.setOnMouseEntered(e -> addBtn.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-background-radius: 10;"));
        addBtn.setOnMouseExited(e -> addBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 10;"));

        showBtn.setOnMouseEntered(e -> showBtn.setStyle("-fx-background-color: #1976D2; -fx-text-fill: white; -fx-background-radius: 10;"));
        showBtn.setOnMouseExited(e -> showBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 10;"));

        bookBtn.setOnMouseEntered(e -> bookBtn.setStyle("-fx-background-color: #FB8C00; -fx-text-fill: white; -fx-background-radius: 10;"));
        bookBtn.setOnMouseExited(e -> bookBtn.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-background-radius: 10;"));

        checkoutBtn.setOnMouseEntered(e -> checkoutBtn.setStyle("-fx-background-color: #C2185B; -fx-text-fill: white; -fx-background-radius: 10;"));
        checkoutBtn.setOnMouseExited(e -> checkoutBtn.setStyle("-fx-background-color: #E91E63; -fx-text-fill: white; -fx-background-radius: 10;"));

        removeBtn.setOnMouseEntered(e -> removeBtn.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white; -fx-background-radius: 10;"));
        removeBtn.setOnMouseExited(e -> removeBtn.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-background-radius: 10;"));

        TextArea output = new TextArea();
        output.setStyle(
        "-fx-font-size: 14px;" +
        "-fx-background-color: white;" +
        "-fx-background-radius: 10;" +
        "-fx-border-radius: 10;" +
        "-fx-border-color: #ccc;" +
        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);"
        );
        output.setEditable(false);

        // 🔹 Add Room
        addBtn.setOnAction(e -> {

            daysField.setVisible(false);
            daysLabel.setVisible(false);

            try {
                int roomNo = Integer.parseInt(roomField.getText());

                RoomType type = roomTypeBox.getValue();
                if (type == null) {
                    output.setText("Please select room type!");
                    return;
                }

                for (Room existing : manager.rooms) {
                    if (existing.getRoomNumber() == roomNo) {
                        new Alert(Alert.AlertType.ERROR, "Room already exists!").showAndWait();
                        return;
                    }
                }

                Room r;

                double price = type.getPricePerNight();

                if (type == RoomType.DELUXE) {
                 r = new DeluxeRoom(roomNo, price, true, true);
                 } else if (type == RoomType.SUITE) {
                  r = new SuiteRoom(roomNo, price, true, true, true, true);
                 } else {
                  r = new StandardRoom(roomNo, price, true);
                }

                manager.addRoom(r);
                manager.saveToFile();

                roomField.clear();
                roomTypeBox.setValue(null);

                new Alert(Alert.AlertType.INFORMATION, "Room Added: " + roomNo).showAndWait();

            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, "Invalid input!").showAndWait();
            }
        });

        // 🔹 Remove Room
        removeBtn.setOnAction(e -> {

            daysField.setVisible(false);
            daysLabel.setVisible(false);

            try {
                int roomNo = Integer.parseInt(roomField.getText());

                Iterator<Room> iterator = manager.rooms.iterator();

                while (iterator.hasNext()) {
                    Room r = iterator.next();

                    if (r.getRoomNumber() == roomNo) {
                        iterator.remove();
                        manager.saveToFile();

                        new Alert(Alert.AlertType.INFORMATION,
                                "Room " + roomNo + " removed").showAndWait();

                        roomField.clear();
                        return;
                    }
                }

                output.setText("Room not found!");

            } catch (Exception ex) {
                output.setText("Enter valid room number!");
            }
        });

        // 🔹 Show Rooms
showBtn.setOnAction(e -> {

    daysField.setVisible(false);
    daysLabel.setVisible(false);

    StringBuilder result = new StringBuilder();

    for (Room r : manager.rooms) {

        String type;

        if (r instanceof SuiteRoom) {
            type = "SUITE";
        } else if (r instanceof DeluxeRoom) {
            type = "DELUXE";
        } else {
            type = "STANDARD";
        }

        String status = r.isBooked() ? "BOOKED" : "AVAILABLE";

        result.append("Room ")
                .append(r.getRoomNumber())
                .append(" | Type: ")
                .append(type)
                .append(" | Price: ")
                .append(r.calculateTariff())
                .append(" | Status: ")
                .append(status);

        // 🔥 SHOW AMENITIES
        result.append(" | Amenities: ");

        if (r instanceof SuiteRoom) {
            result.append("WiFi, Meals, Laundry, Gym, Pool");
        } else if (r instanceof DeluxeRoom) {
            result.append("WiFi, Meals, Laundry");
        } else {
            result.append("WiFi");
        }

        result.append("\n");
    }

    output.setText(result.toString());
});

        // 🔹 Book Room
        bookBtn.setOnAction(e -> {

            if (!daysField.isVisible()) {
                daysField.setVisible(true);
                daysLabel.setVisible(true);
                output.setText("Enter days and click Book again");
                return;
            }

            try {
                int roomNo = Integer.parseInt(roomField.getText());
                int days = Integer.parseInt(daysField.getText());

                for (Room r : manager.rooms) {

                    if (r.getRoomNumber() == roomNo) {

                        if (r.isBooked()) {
                            output.setText("Room already booked!");
                            return;
                        }

                        new BookingThread("Customer", r).start();
                        r.setBooked(true);

                        double total = r.calculateTariff() * days;

                        new Alert(Alert.AlertType.INFORMATION,
                                "Room " + roomNo +
                                        "\nDays: " + days +
                                        "\nTotal Bill: " + total).showAndWait();

                        roomField.clear();
                        daysField.clear();
                        daysField.setVisible(false);
                        daysLabel.setVisible(false);

                        return;
                    }
                }

                output.setText("Room not found!");

            } catch (Exception ex) {
                output.setText("Enter valid input!");
            }
        });

        // 🔹 Checkout
        checkoutBtn.setOnAction(e -> {

            daysField.setVisible(false);
            daysLabel.setVisible(false);

            try {
                int roomNo = Integer.parseInt(roomField.getText());

                for (Room r : manager.rooms) {

                    if (r.getRoomNumber() == roomNo) {

                        if (!r.isBooked()) {
                            output.setText("Room already available!");
                            return;
                        }

                        r.setBooked(false);
                        manager.saveToFile();

                        new Alert(Alert.AlertType.INFORMATION,
                                "Checkout successful").showAndWait();

                        roomField.clear();
                        return;
                    }
                }

                output.setText("Room not found!");

            } catch (Exception ex) {
                output.setText("Enter valid room number!");
            }
        });

        VBox layout = new VBox(15,
                title,
                roomLabel,
                roomField,
                daysLabel,
                daysField,
                roomTypeBox,
                section,
                addBtn,
                removeBtn,
                showBtn,
                bookBtn,
                checkoutBtn,
                output
        );

       layout.setStyle(
       "-fx-padding: 20;" +
       "-fx-background-color: linear-gradient(to bottom, #e0c3fc, #8ec5fc);"
       );

        Scene scene = new Scene(layout, 400, 450);

        stage.setTitle("Hotel System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}