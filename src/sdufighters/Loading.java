import javafx.scene.control.ProgressBar;
import javafx.scene.image.*;
import javafx.scene.layout.*;

class Loading extends Pane {

    private Image logopic = new Image(MortalKombat.class.getResource("res/ui/loadinglogo.png").toString());
    ImageView logo = new ImageView(logopic);
    ProgressBar progress = new ProgressBar();

    Loading() {
        HBox hb = new HBox();
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
        getChildren().addAll(hb, progress);
    }
}