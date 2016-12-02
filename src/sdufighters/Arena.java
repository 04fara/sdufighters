package sdufighters;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Arena extends Pane {
    
    Image arenaspic = new Image(MortalKombat.class.getResource("res\ui\arenas.png").toString());
    int width=400;
    int height=300;
    int chosen=0;
    
    public Arena() {
        ImageView first = new ImageView(arenaspic);
        ImageView second = new ImageView(arenaspic);
        ImageView third = new ImageView(arenaspic);
        ImageView fourth = new ImageView(arenaspic);
        ImageView fifth = new ImageView(arenaspic);
        second.setOpacity(0.7);
        third.setOpacity(0.7);
        fourth.setOpacity(0.7);
        fifth.setOpacity(0.7);
        first.setViewport(new Rectangle2D(0, 0, width, height));
        second.setViewport(new Rectangle2D(400, 0, width, height));
        third.setViewport(new Rectangle2D(800, 0, width, height));
        fourth.setViewport(new Rectangle2D(1200, 0, width, height));
        fifth.setViewport(new Rectangle2D(1600, 0, width, height));
        first.setFitWidth(200);
        first.setFitHeight(150);
        second.setFitWidth(200);
        second.setFitHeight(150);
        third.setFitWidth(200);
        third.setFitHeight(150);
        fourth.setFitWidth(200);
        fourth.setFitHeight(150);
        fifth.setFitWidth(200);
        fifth.setFitHeight(150);
        first.setTranslateX(150);
        first.setTranslateY(150);
        second.setTranslateX(400);
        second.setTranslateY(150);
        third.setTranslateX(650);
        third.setTranslateY(150);
        fourth.setTranslateX(275);
        fourth.setTranslateY(350);
        fifth.setTranslateX(525);
        fifth.setTranslateY(350);
        first.setOnMouseClicked(value -> {
            chosen=0;
            first.setOpacity(1.0);
            second.setOpacity(0.7);
            third.setOpacity(0.7);
            fourth.setOpacity(0.7);
            fifth.setOpacity(0.7);
        });
        second.setOnMouseClicked(value -> {
            chosen=1;
            first.setOpacity(0.7);
            second.setOpacity(1.0);
            third.setOpacity(0.7);
            fourth.setOpacity(0.7);
            fifth.setOpacity(0.7);
        });
        third.setOnMouseClicked(value -> {
            chosen=2;
            first.setOpacity(0.7);
            second.setOpacity(0.7);
            third.setOpacity(1.0);
            fourth.setOpacity(0.7);
            fifth.setOpacity(0.7);
        });
        fourth.setOnMouseClicked(value -> {
            chosen=3;
            first.setOpacity(0.7);
            second.setOpacity(0.7);
            third.setOpacity(0.7);
            fourth.setOpacity(1.0);
            fifth.setOpacity(0.7);
        });
        fifth.setOnMouseClicked(value -> {
            chosen=4;
            first.setOpacity(0.7);
            second.setOpacity(0.7);
            third.setOpacity(0.7);
            fourth.setOpacity(0.7);
            fifth.setOpacity(1.0);
        });
        first.setOnMouseEntered(value -> {
            first.setOpacity(1.0);
        });
        second.setOnMouseEntered(value -> {
            second.setOpacity(1.0);
        });
        third.setOnMouseEntered(value -> {
            third.setOpacity(1.0);
        });
        fourth.setOnMouseEntered(value -> {
            fourth.setOpacity(1.0);
        });
        fifth.setOnMouseEntered(value -> {
            fifth.setOpacity(1.0);
        });
        first.setOnMouseExited(value -> {
            if(chosen!=0) {
                first.setOpacity(0.7);
            }
        });
        second.setOnMouseExited(value -> {
            if(chosen!=1) {
                second.setOpacity(0.7);
            }
        });
        third.setOnMouseExited(value -> {
            if(chosen!=2) {
                third.setOpacity(0.7);
            }
        });
        fourth.setOnMouseExited(value -> {
            if(chosen!=3) {
                fourth.setOpacity(0.7);
            }
        });
        fifth.setOnMouseExited(value -> {
            if(chosen!=4) {
                fifth.setOpacity(0.7);
            }
        });
        getChildren().addAll(first,second,third,fourth,fifth);
    }
}
