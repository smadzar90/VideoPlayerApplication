/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package videoplayerapplication;


import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author stipanmadzar
 */
public class VideoPlayerApplication extends Application  {
    
    static MediaPlayer player;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        VBox root = new VBox(10);
        File videoFile = new File("/Users/stipanmadzar/Desktop/NetBeansProjects/VideoPlayerApplication/src/SampleVideo2.mp4");
        Media media = new Media(videoFile.toURI().toString());
        player = new MediaPlayer(media);
        MediaView view = new MediaView(player);
        Button open = new Button("Open");
        Button play = new Button("Play");
        Button pause = new Button("Pause");
        Button stop = new Button("Stop");
        Button mute = new Button("Mute");
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(open, play, pause, stop, mute);
        root.getChildren().addAll(view, box);
        view.setFitWidth(450);
        FileChooser fileChooser = new FileChooser();
        
        player.setOnEndOfMedia(() -> {
                
            player.stop();
            
        });
       
        open.setOnAction(e -> {
            
            File newFile = fileChooser.showOpenDialog(primaryStage);
            
            if(newFile != null) {
                
            player.stop();
            Media media2 = new Media(newFile.toURI().toString());
            player = new MediaPlayer(media2);
            view.setMediaPlayer(player);
            mute.setText("Mute");
            
            if(player.isMute()) {
                
                player.setMute(false);   
            }}  
            
        });
          
        play.setOnAction(e -> {
        
            player.play();
        });
        
        stop.setOnAction(e -> {
            
            player.stop();
        });
        
        pause.setOnAction(e -> {
            
            player.pause();
        });
        
        mute.setOnAction(e -> {
            
            if(player.isMute()) {
                
                player.setMute(false);
                mute.setText("Mute");
            }
            else {
                player.setMute(true);
                mute.setText("UnMute");
            }
        });
       
        Scene scene = new Scene(root, 450, 382);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
