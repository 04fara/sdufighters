import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

class Options extends Pane {

    Label sfxvp = new Label("70");
    Label musicvp = new Label("70");

    Options() {
        HBox sfx = new HBox();
        HBox music = new HBox();
        Image sfxpic = new Image(MortalKombat.class.getResource("res/ui/sfxvolume.png").toString());
        ImageView sfxv = new ImageView(sfxpic);
        sfxv.setFitHeight(33);
        sfxv.setFitWidth(200);
        Slider sfxvol = new Slider();
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
        sfx.getChildren().addAll(sfxv, sfxvol, sfxvp);
        Image musicpic = new Image(MortalKombat.class.getResource("res/ui/musicvolume.png").toString());
        ImageView musicv = new ImageView(musicpic);
        musicv.setFitHeight(33);
        musicv.setFitWidth(200);
        Slider musicvol = new Slider();
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
        sfxvol.valueProperty().addListener((ov, old_val, new_val) -> sfxvp.setText(String.format("%.0f", new_val)));
        musicvol.valueProperty().addListener((ov, old_val, new_val) -> musicvp.setText(String.format("%.0f", new_val)));
        music.getChildren().addAll(musicv, musicvol, musicvp);
        sfxvol.setTranslateX(20);
        musicvol.setTranslateX(20);
        sfxvp.setTranslateX(20);
        musicvp.setTranslateX(20);
        sfx.setTranslateY(250);
        sfx.setTranslateX(200);
        music.setTranslateY(350);
        music.setTranslateX(200);
        getChildren().addAll(sfx, music);
    }
}
