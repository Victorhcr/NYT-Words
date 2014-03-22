/*
 * This class is still on construction. Please do not evaluate this.
 */
package com.victor.nytwords.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Victorhcr
 */
public class Chart extends Application {

    private String word;
    private int beg;
    private int end;
    private FileReader fileNorm;
    private FileReader filePred;
    private Stage stage;
    
    public Chart() {

    }

    public Chart(String word, int beg, int end,String[] args) {
        this.word = word;
        this.beg = beg;
        this.end = end;
        System.out.println("1");
        System.out.println(this.word);
        System.out.println(this.end);
        System.out.println(this.beg);
        
        main(args);
    }
    

    
    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        FileReader main = new FileReader("files/main.txt");
        getData(main);
        
        this.fileNorm = new FileReader("files/" + this.word + "-" + this.beg + "-" + this.end + ".txt");
        this.filePred = new FileReader("files/prediction-" + this.word + "-" + this.beg + "-" + this.end + ".txt");

        
        System.out.println("2");

        stage.setTitle("Article Hits Chart");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("New York Times Articles");

        lineChart.setCreateSymbols(false);
        lineChart.setAlternativeRowFillVisible(false);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Word: " + this.word);

        addData(series1,this.fileNorm);

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Prediction");
        addData(series2,this.filePred);
        
        Scene scene = new Scene(lineChart);
        lineChart.getData().addAll(series1,series2);
        //scene.getStylesheets().add("linechartsample/Chart.css");                      
        stage.setScene(scene);
        stage.show();
    }
    
    public void getData(FileReader main){
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(main);
            
            this.word = br.readLine();
            this.beg = Integer.parseInt(br.readLine());
            this.end = Integer.parseInt(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addData(XYChart.Series series,FileReader file) {
        BufferedReader br = null;
        try {
            String sCurrentLine;
            String[] parts;

            br = new BufferedReader(file);

            while (!(sCurrentLine = br.readLine()).equals("-")) {
                System.out.println(sCurrentLine);
                parts = sCurrentLine.split(": ");
                series.getData().add(new XYChart.Data(parts[0], Double.parseDouble(parts[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("3");
    }
}