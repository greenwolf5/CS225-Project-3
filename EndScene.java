// Team effort
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EndScene{
    private final Label lblWinner = new Label();
    private final Label lblCarOne = new Label();
    private final Label lblCarTwo = new Label();
    private final Label lblCarThree = new Label();
    private final GridPane carOneTable;
    private final GridPane carTwoTable;
    private final GridPane carThreeTable;
    private final Car carOne;
    private final Car carTwo;
    private final Car carThree;

    // Default constructor. Was used for testing but not used otherwise by the program
    public EndScene(){
        carOne = new Car();
        carTwo = new Car();
        carThree = new Car();

        lblWinner.setFont(Font.font("Bauhaus 93", 45));

        lblCarOne.setText("Car 1:");
        lblCarOne.setFont(Font.font("Gill Sans MT Condensed", 35));

        lblCarTwo.setText("Car 2:");
        lblCarTwo.setFont(Font.font("Gill Sans MT Condensed", 35));

        lblCarThree.setText("Car 3:");
        lblCarThree.setFont(Font.font("Gill Sans MT Condensed", 35));

        this.checkWinner();

        carOneTable = createTable(carOne);
        carTwoTable = createTable(carTwo);
        carThreeTable = createTable(carThree);
    }

    // This constructor takes the three car objects in and creates their tables.
    public EndScene(Car car1, Car car2, Car car3){
        carOne = car1;
        carTwo = car2;
        carThree = car3;

        lblWinner.setFont(Font.font("Bauhaus 93", 40));

        lblCarOne.setText("Car 1:");
        lblCarOne.setFont(Font.font("Gill Sans MT Condensed", 30));

        lblCarTwo.setText("Car 2:");
        lblCarTwo.setFont(Font.font("Gill Sans MT Condensed", 30));

        lblCarThree.setText("Car 3:");
        lblCarThree.setFont(Font.font("Gill Sans MT Condensed", 30));

        this.checkWinner();

        carOneTable = createTable(carOne);
        carTwoTable = createTable(carTwo);
        carThreeTable = createTable(carThree);
    }

    // Creates the table for one car displaying all of the information from the race
    private GridPane createTable(Car car){

        String timeString;
        // This checks if the car broke down (9999999L means it broke down which is elaborated upon in the next comment)
        // and if it does its time string for the table reads did not finish, otherwise its time is shown normally
        if(car.getFinalTime().get() == 9999999L){
            timeString = "Did Not Finish";
        }
        else {
            timeString = car.getTime() + " seconds";
        }

        Label[] labels = {new Label("Time"), new Label(timeString),
                new Label("Route"), new Label(car.getPathString()),
                new Label("Car Type"), new Label(car.getTypeString()),
                new Label("Color"), new Label(car.getColorString()),
                new Label("Tires"), new Label(car.getTireString()),
                new Label("Engine"), new Label(car.getEngineString()),};

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        gridPane.setPadding(new Insets(10.0, 10.0, 20.0, 10.0));

        int j = 0, k = 0;
        for(int i = 0; i < 12; i++) {
            if(j == 0){
                labels[i].setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 25.0));
            }
            else{
                labels[i].setFont(Font.font("Gill Sans MT Condensed", 20.0));
            }
            labels[i].setPadding(new Insets(10, 16, 10 , 10));

            gridPane.add(labels[i], j++, k);
            if(j == 2) {
                j = 0;
                k++;
            }
        }

        return gridPane;
    }

    // This checks if there is a winner, multiple winners, or if no cars survived, and changes the label at the top of
    // the scene accordingly
    //
    // ** If a car breaks down we needed to set its time to an arbitrarily large number (hence 9999999L) solely
    // ** for this method. We at first had a broken down car's time set to zero but obviously zero would be less
    // ** then the other times and it would declare that broken down car the winner. Of course we could have
    // ** complicated this method further by, after the first if statement, checking if any of the cars have a zero
    // ** time value and making sure to ignore those but we found what we have now to be a much simpler alternative.
    private void checkWinner(){
        if(carOne.getFinalTime().get() == 9999999L && carTwo.getFinalTime().get() == 9999999L
                && carThree.getFinalTime().get() == 9999999L){
            lblWinner.setText("No Cars Finished");
            return;
        }
        if(carOne.getTime() < carTwo.getTime() && carOne.getTime() < carThree.getTime()){
            lblWinner.setText("Car One Wins!");
            return;
        }
        if(carTwo.getTime() < carOne.getTime() && carTwo.getTime() < carThree.getTime()){
            lblWinner.setText("Car Two Wins!");
            return;
        }
        if(carThree.getTime() < carTwo.getTime() && carThree.getTime() < carOne.getTime()){
            lblWinner.setText("Car Three Wins!");
            return;
        }
        lblWinner.setText("Multiple Winners");
    }

    // This organizes the scene that will be displayed in the GameGui class
    public Scene scene(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));

        AnchorPane.setTopAnchor(lblWinner, 20.0);
        AnchorPane.setLeftAnchor(lblWinner, 340.0);

        AnchorPane.setTopAnchor(lblCarOne, 100.0);
        AnchorPane.setLeftAnchor(lblCarOne, 130.0);

        AnchorPane.setTopAnchor(lblCarTwo, 100.0);
        AnchorPane.setLeftAnchor(lblCarTwo, 460.0);

        AnchorPane.setTopAnchor(lblCarThree, 100.0);
        AnchorPane.setLeftAnchor(lblCarThree, 800.0);

        AnchorPane.setTopAnchor(carOneTable, 150.0);
        AnchorPane.setLeftAnchor(carOneTable, 60.0);

        AnchorPane.setTopAnchor(carTwoTable, 150.0);
        AnchorPane.setLeftAnchor(carTwoTable, 395.0);

        AnchorPane.setTopAnchor(carThreeTable, 150.0);
        AnchorPane.setLeftAnchor(carThreeTable, 730.0);

        anchorPane.getChildren().addAll(lblWinner, lblCarOne, lblCarTwo, lblCarThree, carOneTable, carTwoTable,
                carThreeTable);

        Scene scene = new Scene(anchorPane, 1005, 550);

        return scene;
    }

    @Override
    public String toString() {
        return "EndScene{" +
                "lblWinner=" + lblWinner +
                ", lblCarOne=" + lblCarOne +
                ", lblCarTwo=" + lblCarTwo +
                ", lblCarThree=" + lblCarThree +
                ", carOneTable=" + carOneTable +
                ", carTwoTable=" + carTwoTable +
                ", carThreeTable=" + carThreeTable +
                ", carOne=" + carOne +
                ", carTwo=" + carTwo +
                ", carThree=" + carThree +
                '}';
    }
}