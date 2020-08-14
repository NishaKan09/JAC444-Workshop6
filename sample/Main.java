/********************************************************************************
 Workshop # 6
 Course: JAC444 - Semester 4
 Last Name: Kanagasapabathy
 First Name: Nishantha (Nisha)
 ID: 135015162
 Section: NB
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: July 13th/2020
 *********************************************************************************/

package sample;   //IntelliJ keeps giving me issues when I try to change the package name
                 // so I had to go with the default when using Java FX

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private static String[] results = new String[5];

    Scene scene, scene2;
    Stage secondaryStage = new Stage();
    Label lbl = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //layout1
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        //Labels
        Label fileName = new Label("Select the Year:");
        GridPane.setConstraints(fileName, 0, 0);
        Label gender = new Label("Select the Gender:");
        GridPane.setConstraints(gender, 0, 1);
        Label name = new Label("Enter the Name:");
        GridPane.setConstraints(name, 0, 2);

        //Text fields
        ChoiceBox<String> yr = new ChoiceBox<>();
        GridPane.setConstraints(yr, 1, 0);
        yr.getItems().addAll(" ", "2009", "2010", "2011", "2012",
                "2013", "2014", "2015",
                "2016", "2017", "2018");

        ChoiceBox<String> gen = new ChoiceBox<>();
        GridPane.setConstraints(gen, 1, 1);
        gen.getItems().addAll(" ", "M", "F");

        TextField n = new TextField();
        GridPane.setConstraints(n, 1, 2);
        gen.setPrefWidth(40);

        //Buttons
        Button yesBtn = new Button("Yes");
        GridPane.setConstraints(yesBtn, 0, 3);
        yesBtn.setOnAction(ex -> {
            secondaryStage.close();
            primaryStage.setScene(scene);
            yr.setValue(" ");
            gen.setValue(" ");
            n.clear();
        });

        Button noBtn = new Button("No");
        GridPane.setConstraints(noBtn, 1, 3);
        noBtn.setOnAction(ev -> {
            secondaryStage.close();
            primaryStage.close();
        });

        Button submitQueryBtn = new Button("Submit Query");
        submitQueryBtn.setOnAction(e -> {
            try {
                String y = getYear(yr);
                String g = getGender(gen);
                search(y, g, n.getText());

                if(g == "F"){
                    lbl = new Label("Girl name " + results[3] + " is ranked #" + results[0] + " in " + yr.getValue() + " year.");
                    GridPane.setConstraints(lbl, 0, 0);
                }
                else if(g == "M"){
                    lbl = new Label("Boy name " + results[1] + " is ranked #" + results[0] + " in " + yr.getValue() + " year.");
                    GridPane.setConstraints(lbl, 0, 0);
                }

                //layout2
                GridPane grid2 = new GridPane();
                grid2.setPadding(new Insets(20, 20, 20, 20));
                grid2.setVgap(8);
                grid2.setHgap(10);

                Label txt = new Label("Do you want to search for another name?");
                GridPane.setConstraints(txt, 0, 1);
                grid2.getChildren().addAll(lbl, txt, yesBtn, noBtn);
                grid2.setAlignment(Pos.CENTER);
                scene2 = new Scene(grid2, 400, 200);
                secondaryStage.setTitle("Results");
                secondaryStage.setScene(scene2);
                secondaryStage.show();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> {
            System.out.println("Closing Program");
            primaryStage.close();
        });

        //First scene
        HBox buttons = new HBox();
        buttons.getChildren().addAll(submitQueryBtn, exitBtn);
        grid.add(buttons, 0, 3, 1, 1);
        grid.getChildren().addAll(year, yr, gender, gen, name, n);
        grid.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Search Name Ranking Application");
        scene = new Scene(grid,400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static String[] search(String year, String gender, String name) throws IOException{
        BufferedReader br = null;
        File sourceFile = new File("babynamesranking" + year + ".txt");
        String line;
        try{
            br = new BufferedReader(new FileReader(sourceFile));
        } catch (Exception e){
            System.out.println("Cannot read file");
            System.exit(0);
        }

        try{
            while((line = br.readLine()) != null) {
                String[] columns = line.split("\t");
                String rank = columns[0].replaceAll("\\s+", "");
                String boyName = columns[1].replaceAll("\\s+", "");
                String girlName = columns[3].replaceAll("\\s+", "");
                if (gender == "F") {
                    if (girlName.toLowerCase().startsWith(name.toLowerCase())) {
                        results[0] = rank;
                        results[3] = girlName;
                        return results;
                    }
                } else if (gender == "M") {
                    if (boyName.toLowerCase().startsWith(name.toLowerCase())) {
                        results[0] = rank;
                        results[1] = boyName;
                        return results;
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage() + " Error Reading File");
        }
        return results;
    }

    private String getYear(ChoiceBox<String> yr){
        String year = yr.getValue();
        return year;
    }

    private String getGender(ChoiceBox<String> gen){
        String gender = gen.getValue();
        return gender;
    }
}

