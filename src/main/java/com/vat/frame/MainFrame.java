package com.vat.frame;

import com.vat.frame.data.LoadShapes;
import com.vat.frame.data.SaveShapes;
import com.vat.frame.shape.EditShape;
import com.vat.frame.shape.NewShape;
import com.vat.shape.Shape;
import com.vat.shape.ShapeService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainFrame extends Application {

    public static ShapeService shapeService = new ShapeService();
    private Stage window;
    private ComboBox<String> shapeTypeComboBox;
    private ListView<String> shapeList;
    private TextField volumeText;
    private TextField totalVolumeText;
    private Button deleteButton;
    private Shape previousSelectedShape = null;
    private String previousSelectedItem = null;

    private static final int shadowSize = 50;

    @Override
    public void start(Stage primaryStage) {
        //window.initStyle(StageStyle.TRANSPARENT);
//
        //StackPane stackPane = new StackPane(createShadowPane());
        //stackPane.setStyle(
        //        "-fx-background-color:rgba(255, 255, 255, 0.5):" +
        //                "-fx-background-insets: " + shadowSize + ";"
        //);

        window = primaryStage;
        window.setTitle("Vorm Analyse Tool");

        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = this.createMenu();
        GridPane editor = this.createEditor();

        borderPane.setTop(menuBar);
        borderPane.setCenter(editor);

        Scene scene = new Scene(borderPane, 600, 350);
        //scene.setFill(Color.TRANSPARENT);
        //window.setOpacity(0.70);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
        // Create a shadow effect as a halo around the pane and not within
        // the pane's content area.
        //private Pane createShadowPane() {
        //    Pane shadowPane = new Pane();
        //    // Moet dit niet in css stylesheet?
        //    shadowPane.setStyle(
        //        "-fx-background-color: white;" +
        //                "-fx-effect: dropshadow(gaussian, red, " + shadowSize + ",0,0,0,0);" +
        //                "-fx-background-insets: " + shadowSize + ";"
        //);
        //    Rectangle innerRect = new Rectangle();
        //    Rectangle outerRect = new Rectangle();
        //    shadowPane.layoutBoundsProperty().addListener(
        //            (observable, oldBounds, newBounds) -> {
        //                innerRect.relocate(
        //                        newBounds.getMinX() + shadowSize,
        //                        newBounds.getMinY() + shadowSize
        //                );
        //                innerRect.setWidth(newBounds.getWidth() - shadowSize * 2);
        //                innerRect.setHeight(newBounds.getHeight() - shadowSize * 2);
//
        //                outerRect.setWidth(newBounds.getWidth());
        //                outerRect.setHeight(newBounds.getHeight());
//
        //                javafx.scene.shape.Shape clip = javafx.scene.shape.Shape.subtract(outerRect, innerRect);
        //                shadowPane.setClip(clip);
        //            }
        //    );
//
        //    return shadowPane;

        //}
    public static void main(String[] args) {
        launch(args);
    }

    private GridPane createEditor() {
        GridPane editor = new GridPane();
        ColumnConstraints leftColumnConstraint = new ColumnConstraints();
        leftColumnConstraint.setPercentWidth(40);
        ColumnConstraints rightColumnConstraint = new ColumnConstraints();
        rightColumnConstraint.setPercentWidth(60);
        editor.getColumnConstraints().addAll(leftColumnConstraint, rightColumnConstraint);

        editor.setPadding(new Insets(20));
        editor.setVgap(8);
        editor.setHgap(20);
        GridPane leftPane = new GridPane();
        leftPane.setVgap(10);
        GridPane rightPane = new GridPane();
        rightPane.setVgap(10);

        editor.add(leftPane, 0, 0);
        editor.add(rightPane, 1, 0);

        // Shape Selector
        VBox shapeTypeBox = this.createShapeTypeBox();

        // Volume
        VBox volumeBox = new VBox();
        Label volumeLabel = new Label("Volume: (m³)");
        volumeText = new TextField();
        volumeText.setPrefWidth(200.0);
        volumeText.setText("0.0");
        volumeText.setDisable(true);
        volumeBox.getChildren().addAll(volumeLabel, volumeText);

        // Total Volume
        VBox totalVolumeBox = new VBox();
        Label totalVolumeLabel = new Label("Total Volume: (m³)");
        totalVolumeText = new TextField();
        totalVolumeText.setPrefWidth(200.0);
        totalVolumeText.setText("0.0");
        totalVolumeText.setDisable(true);
        totalVolumeBox.getChildren().addAll(totalVolumeLabel, totalVolumeText);

        leftPane.add(shapeTypeBox, 0, 0);
        leftPane.add(volumeBox, 0, 1);
        leftPane.add(totalVolumeBox, 0, 2);

        // Shape List Box
        VBox shapeListBox = new VBox();
        Label shapeListHeader = new Label("Figures:");

        shapeList = new ListView<>();
        shapeList.setPrefWidth(300.0);
        shapeList.setOnMouseClicked(click -> {
            String selectedItem = shapeList.getSelectionModel()
                    .getSelectedItem();
            int selectedIndex = shapeList.getSelectionModel()
                    .getSelectedIndex();

            if (selectedItem == null) {
                return;
            }

            if (click.getClickCount() == 1) {
                System.out.println("selectedItem: " + selectedItem);
                System.out.println("this.previousSelectedItem: " + this.previousSelectedItem);
                if (this.previousSelectedItem != null && this.previousSelectedItem.equals(selectedItem)) {
                    this.previousSelectedItem = null;
                    this.previousSelectedShape = null;
                    volumeText.setText("0.0");
                    shapeListHeader.setText("Figures:");
                    deleteButton.setDisable(true);
                    Platform.runLater(() -> shapeList.getSelectionModel().select(null));
                } else {
                    Shape shape = shapeService.getShapes().get(selectedIndex);
                    volumeText.setText(String.format("%s", shape.calculateVolume()));
                    this.previousSelectedItem = selectedItem;
                    this.previousSelectedShape = shape;
                    deleteButton.setDisable(false);
                    shapeListHeader.setText("Tip: Double tab to edit.");
                }
            } else if (click.getClickCount() == 2) {
                window.hide();
                EditShape.display(shapeService.getShapes().get(selectedIndex));
                this.previousSelectedItem = null;
                this.previousSelectedShape = null;
                deleteButton.setDisable(true);
                shapeListHeader.setText("Figures:");
                window.show();
                updateView();
            }
        });

        shapeListBox.getChildren().addAll(shapeListHeader, shapeList);

        deleteButton = new Button("Delete selected shape");
        deleteButton.setDisable(true);
        deleteButton.setOnAction(e -> {
            if (previousSelectedItem != null) {
                System.out.println(previousSelectedShape);
                System.out.println(shapeService.getShapes().indexOf(previousSelectedShape));
                shapeService.getShapes().remove(previousSelectedShape);
                previousSelectedShape = null;
                previousSelectedItem = null;
                deleteButton.setDisable(false);
                shapeListHeader.setText("Figures:");

                this.updateView();
            }
        });

        rightPane.add(shapeListBox, 0, 0);
        rightPane.add(deleteButton, 0, 1);

        this.updateView();

        return editor;
    }

    private void updateView() {
        shapeList.getItems()
                .clear();

        ArrayList<String> shapes = new ArrayList<>();

        for (Shape shape :
                shapeService.getShapes()) {
            shapes.add(shape.toString());
        }

        volumeText.setText("0.0");
        totalVolumeText.setText(String.format("%s", shapeService.calculateTotalVolume()));

        shapeList.getItems().addAll(shapes);
    }

    private VBox createShapeTypeBox() {
        VBox shapeTypeBox = new VBox();
        Label shapeTypeLabel = new Label("Create shape:");
        shapeTypeComboBox = new ComboBox<>();
        shapeTypeComboBox.setPrefWidth(200.0);
        shapeTypeComboBox.setPromptText("Select...");
        shapeTypeComboBox.getItems().addAll("Cuboid", "Sphere", "Cylinder", "Cone", "Cube", "Pyramid");
        shapeTypeComboBox
                .valueProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        System.out.println(newValue);
                        window.hide();

                        NewShape.display(newValue);
                        window.show();
                        this.updateView();
                        Platform.runLater(() -> shapeTypeComboBox.setValue(null));
                    }
                });

        shapeTypeBox.getChildren().addAll(shapeTypeLabel, shapeTypeComboBox);
        return shapeTypeBox;
    }

    private MenuBar createMenu() {
        Menu fileMenu = new Menu("File");

        MenuItem save = new MenuItem("Save");
        save.setOnAction(e -> {
            window.hide();
            if (SaveShapes.display()) {
                window.show();
                this.updateView();
            }
        });

        MenuItem load = new MenuItem("Load");
        load.setOnAction(e -> {
            window.hide();
            if (LoadShapes.display()) {
                window.show();
                this.updateView();
            }
        });

        MenuItem close = new MenuItem("Shutdown");
        close.setOnAction(e -> window.close());

        fileMenu.getItems()
                .addAll(
                        save,
                        load,
                        new SeparatorMenuItem(),
                        close
                );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }
}
