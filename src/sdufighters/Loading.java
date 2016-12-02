package sdufighters;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Loading extends Pane {
    
    Image logopic = new Image(MortalKombat.class.getResource("res\ui\loadinglogo.png").toString());
    ImageView logo = new ImageView(logopic);
    ProgressBar progress = new ProgressBar();
    HBox hb = new HBox();
        
    public Loading() {
        logo.setFitHeight(300);
        logo.setFitWidth(300);
        progress.setPrefSize(800, 10);
        progress.setProgress(0);
        progress.setStyle("-fx-accent: white; -fx-control-inner-background: black;");
        logo.setTranslateY(150);
        logo.setTranslateX(350);
        progress.setTranslateY(600);
        progress.setTranslateX(100);
        hb.getChildren().add(logo);
        getChildren().addAll(hb,progress);
    }
}