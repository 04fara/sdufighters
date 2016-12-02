package sdufighters;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Options extends Pane {
    
    Image sfxpic = new Image(MortalKombat.class.getResource("res\ui\sfxvolume.png").toString());
    Image musicpic = new Image(MortalKombat.class.getResource("res\ui\musicvolume.png").toString());
    Slider sfxvol = new Slider();
    Slider musicvol = new Slider();
    Label sfxvp = new Label("70");
    Label musicvp = new Label("70");
        
    public Options() {
        HBox sfx = new HBox();
        HBox music = new HBox();
        ImageView sfxv = new ImageView(sfxpic);
        sfxv.setFitHeight(33);
        sfxv.setFitWidth(200);
        sfxvol.setMin(0);
        sfxvol.setMax(100);
        sfxvol.setValue(70);
        sfxvol.setShowTickLabels(true);
        sfxvol.setShowTickMarks(true);
        sfxvol.setMajorTickUnit(50);
        sfxvol.setMinorTickCount(5);
        sfxvol.setBlockIncrement(10);
        sfxvol.setPrefSize(400, 50);
        sfxvp.setStyle("-fx-text-fill: white;");
        sfx.getChildren().addAll(sfxv,sfxvol,sfxvp);
        ImageView musicv = new ImageView(musicpic);
        musicv.setFitHeight(33);
        musicv.setFitWidth(200);
        musicvol.setMin(0);
        musicvol.setMax(100);
        musicvol.setValue(70);
        musicvol.setShowTickLabels(true);
        musicvol.setShowTickMarks(true);
        musicvol.setMajorTickUnit(50);
        musicvol.setMinorTickCount(5);
        musicvol.setBlockIncrement(10);
        musicvol.setPrefSize(400, 50);
        musicvp.setStyle("-fx-text-fill: white;");
        sfxvol.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    sfxvp.setText(String.format("%.0f", new_val));
            }
        });
        musicvol.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    musicvp.setText(String.format("%.0f", new_val));
            }
        });
        music.getChildren().addAll(musicv,musicvol,musicvp);
        sfxvol.setTranslateX(20);
        musicvol.setTranslateX(20);
        sfxvp.setTranslateX(20);
        musicvp.setTranslateX(20);
        sfx.setTranslateY(250);
        sfx.setTranslateX(200);
        music.setTranslateY(350);
        music.setTranslateX(200);
        getChildren().addAll(sfx,music);
    }
}
