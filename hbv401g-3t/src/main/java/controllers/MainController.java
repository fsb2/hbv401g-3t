package controllers;

import entities.FlightEntity;
import entities.HotelEntity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * This is the controller for the main window.
 */
public class MainController {

    @FXML
    private Button btnSignIn, btnSignUp;

    @FXML
    private Label lblSearch, lblFromDate, lblToDate, lblHotels, lblFlights;

    @FXML
    private TableView tvFlights, tvHotels;

    public MainController() {
    }

    @FXML
    public void initialize() {
        btnSignUp.setText("Sign Up");
        btnSignIn.setText("Sign In");

        lblSearch.setText("Search engine");
        lblFromDate.setText("From");
        lblToDate.setText("To");
        lblHotels.setText("Hotel list");
        lblFlights.setText("Flight list");

        ObservableList<FlightEntity> flights = null;
        ObservableList<HotelEntity> hotels = null;

        String fH = "/files/flightHeaders.txt";
        String fL = "/files/flights.txt";
        String hH =  "/files/hotelHeaders.txt";
        String hL =  "/files/hotels.txt";
        
        listing(tvFlights, flights, fH, fL);
        listing(tvHotels, hotels, hH, hL);
        
        signInButtonHandler();
        signUpButtonHandler();
    }

    // A handler for the sign in button.
    private void signInButtonHandler() {
        btnSignIn.setOnAction((event) -> {
            try {
                Parent p = FXMLLoader.load(getClass()
                        .getResource("/fxml/FXMLSignIn.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getCause());
            }
        });
    }

    // A handler for the sign up button.
    private void signUpButtonHandler() {
        btnSignUp.setOnAction((event) -> {
            try {
                Parent p = FXMLLoader.load(getClass()
                        .getResource("/fxml/FXMLSignUp.fxml"));

                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getCause());
            }
        });
    }

    private void listing(TableView tv, ObservableList entities,
            String header, String list) {
        
        entities = FXCollections.observableArrayList();
        List<TableColumn> headers = new ArrayList<>();

        try (InputStreamReader inputReader = new InputStreamReader(getClass()
                .getResourceAsStream(header));
                Scanner input = new Scanner(inputReader)) {

            input.useDelimiter(";|\n");

            while (input.hasNext()) {
                headers.add(new TableColumn(input.next()));
            }

            input.close();
            inputReader.close();

        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }

        if (!headers.isEmpty()) {
            try (InputStreamReader inputReader = new InputStreamReader(getClass()
                    .getResourceAsStream(list));
                    Scanner input = new Scanner(inputReader)) {

                input.useDelimiter(";|\n");

                if (list.contains("flights")) {
                    while (input.hasNext()) {
                        entities.add(new FlightEntity(
                                input.next(), input.next(),
                                input.next(), input.next(),
                                input.next(), input.next()));
                    }
                } else if (list.contains("hotels")) {
                    while (input.hasNext()) {
                        entities.add(new HotelEntity(input.next(),
                                input.next()));
                    }
                }

                input.close();
                inputReader.close();

            } catch (IOException ex) {
                System.out.println(ex.getCause());
            }
        }

        tv.getColumns().addAll(headers);

        for (int i = 0; i < headers.size(); i++) {
            headers.get(i).setCellValueFactory(
                    new PropertyValueFactory<FlightEntity, String>(
                            headers.get(i).getText()));
        }

        tv.setItems(entities);
    }
}
