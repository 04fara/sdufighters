import javafx.geometry.Rectangle2D;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

class Arena extends Pane {

    int chosen = 0;

    Arena() {
        Image arenaspic = new Image(MortalKombat.class.getResource("res/ui/arenas.png").toString());
        ImageView[] arenaspics = new ImageView[5];
        final int width = 400, height = 300;
        for (int i = 0; i < 5; i++) {
            arenaspics[i] = new ImageView(arenaspic);
            if (i > 0) arenaspics[i].setOpacity(0.7);
            arenaspics[i].setViewport(new Rectangle2D(400 * i, 0, width, height));
            arenaspics[i].setFitWidth(200);
            arenaspics[i].setFitHeight(150);
            arenaspics[i].setTranslateX(i % 5 < 3 ? (150 + 250 * i) : (i == 3 ? 275 : 525));
            arenaspics[i].setTranslateY(i % 5 < 3 ? 150 : 350);
        }
        int i = 0;
        for (ImageView iv : arenaspics) {
            int finalI = i;
            iv.setOnMouseClicked(value -> {
                chosen = finalI;
                iv.setOpacity(1.0);
                for (ImageView iv0 : arenaspics) {
                    if (iv0 != iv) iv0.setOpacity(0.7);
                }
            });
            iv.setOnMouseEntered(value -> iv.setOpacity(1.0));
            iv.setOnMouseExited((MouseEvent value) -> {
                if (chosen != finalI) iv.setOpacity(0.7);
            });
            ++i;
        }
        getChildren().addAll(arenaspics[0], arenaspics[1], arenaspics[2], arenaspics[3], arenaspics[4]);
    }
}
