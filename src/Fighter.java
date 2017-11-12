import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.*;

class Fighter extends Pane {
    private Image image1 = new Image(getClass().getResourceAsStream("2.png"));
    private Image image2 = new Image(getClass().getResourceAsStream("3.png"));
    private Fighter enemy;
    private Circle special = new Circle(0, 0, 5);;
    Deque<KeyCode> keys = new ArrayDeque<>();
    SpriteAnimation animation;
    private Controls controls;
    boolean moved = false,
            isMovingL = false,
            isMovingR = false,
            isSitting = false,
            isJumping = false,
            isKicking = false,
            isKicking1 = false,
            isHit = false,
            orientation = true,
            isPunching1 = false,
            isSpecial = false;
    //isPunching = false,
    Tuple<Boolean, Boolean> isPunching = new Tuple<>(false, false);

    Fighter(Controls controls, ImageView iv, boolean orientation, boolean isFirst) {
        this.controls = controls;
        this.orientation = orientation;
        iv.setViewport(new Rectangle2D(0, 0, 96, 142));
        animation = new SpriteAnimation(iv, Duration.millis(700), 9, 9, 0, 0, 96, 142);
        getChildren().addAll(iv, special);
        setScaleX(2);
        setScaleY(2);
        setStyle("-fx-border-color: black");
        setTranslateX(isFirst ? 100 : 1008);
        setTranslateY(530);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }

    void setEnemy(Fighter enemy) {
        this.enemy = enemy;
    }

    private boolean inBounds(double distance) {
        double newX = getTranslateX() + distance;
        return newX >= 48 && newX <= 1056;
    }

    boolean isMoving() {
        return isMovingR || isMovingL || moved;
    }

    private boolean isPressed(KeyCode key) {
        return keys.size() > 0 && keys.getFirst() == key;
    }

    private boolean arePressed(KeyCode a, KeyCode b) {
        return keys.contains(a) && keys.contains(b);
    }

    private void update() {
        if (!isBusy()) {
            resetAnimParams();
            if (isPressed(controls.right()) || (arePressed(controls.right(), controls.up()) && animation.cycleMade))
                move(1);
            else if (isPressed(controls.left()) || (arePressed(controls.left(), controls.up()) && animation.cycleMade))
                move(-1);
            else if (isPressed(controls.punch())) punch();
            else if (!animation.cycleMade && arePressed(controls.right(), controls.up())) jump(1);
            else if (!animation.cycleMade && arePressed(controls.left(), controls.up())) jump(-1);
            else if (!animation.cycleMade && isPressed(controls.up())) jump(0);
            else if (!animation.cycleMade && isPressed(controls.down())) sit();
            else if (!animation.cycleMade && isPressed(controls.kick())) kick();
            else if (!isSpecial && isPressed(controls.special())) special();
            else stance();
            if (enemy != null)
                if (enemy.getTranslateX() < getTranslateX()) reverse(image2, false);
                else reverse(image1, true);
        }
        if (isSpecial) {
            if (special.getTranslateX() > 1000 || special.getTranslateX() < -1000) {
                isSpecial = false;
                special.setVisible(false);
                special.setTranslateX(animation.imageView.getTranslateX());
            }
            special.setTranslateX(special.getTranslateX() + (orientation ? 1 : -1));
        }
        if (hit()) {
            animation.setOffsetY(2000);
            animation.stop();
        }
    }

    private boolean isBusy() {
        return isSitting || isJumping || isPunching.getX() || isKicking || isHit;
    }

    private void resetAnimParams() {
        animation.cycleMade = false;
        changeAnimParams(9, 0, 1);
        animation.onFinishedProperty().set(null);
    }

    private void changeAnimParams(int count, int offsetY, double rate) {
        animation.setCount(count);
        animation.setColumns(count);
        animation.setOffsetY(offsetY);
        animation.setRate(rate);
    }

    private void reverse(Image sprite, boolean orientation) {
        this.orientation = orientation;
        animation.imageView.setImage(sprite);
    }

    private void stance() {
        animation.setOffsetY(0);
        animation.play();
    }

    boolean hit() {
        return enemy.special.getTranslateX() == getTranslateX();
    }

    private void move(int dir) {
        moved = true;
        changeAnimParams(9, 140, dir * (orientation ? 1 : -1));
        if (dir == 1) {
            //double distance = orientation ? 3.5 : 2.5;
            double distance = 4;
            if (inBounds(distance))
                setTranslateX(getTranslateX() + distance);
        } else {
            //double distance = orientation ? 2.5 : 3.5;
            double distance = 4;
            if (inBounds(-distance))
                setTranslateX(getTranslateX() - distance);
        }
        animation.play();
    }

    void sit() {
        isSitting = true;
        changeAnimParams(3, 280, animation.cycleMade ? -5 : 5);
        animation.playFrom(animation.cycleMade ? Duration.millis(700) : Duration.millis(0));
    }

    private void jump(int dir) {
        int distance = 200 * (dir == 0 ? 0 : dir == 1 ? 1 : -1);
        isJumping = true;
        changeAnimParams(dir == 0 ? 3 : 10, dir == 0 ? 420 : 560, dir == 0 ? 2 : (orientation ? 1 : -1) * (dir == 1 ? 1 : -1));
        Path path = new Path();
        path.getElements().addAll(new MoveTo(getTranslateX() + 48, getTranslateY() + 71),
                new QuadCurveTo(
                        (inBounds(distance / 2) ? getTranslateX() + distance / 2 : (dir == 1 ? 1056 : 48)) + 48, 75,
                        (inBounds(distance) ? getTranslateX() + distance : (dir == 1 ? 1056 : 48)) + 48, getTranslateY() + 71
                ));
        /*
                new QuadCurveTo(
                        ((getTranslateX() + 48) * 2 + 200 * (dir == 0 ? 0 : dir == 1 ? 1 : -1)) / 2,
                        75,
                        getTranslateX() + 48 + 200 * (dir == 0 ? 0 : dir == 1 ? 1 : -1), getTranslateY() + 71
                ));
        */
        PathTransition pathTransition = new PathTransition(Duration.millis(700), path, this);
        pathTransition.setCycleCount(1);
        pathTransition.onFinishedProperty().set(event -> {
            animation.stop();
            animation.cycleMade = true;
            isJumping = false;
        });
        pathTransition.play();
        if (dir == 0)
            animation.setOnFinished(event -> {
                animation.setRate(-2);
                animation.play();
            });
        animation.playFrom(animation.getRate() > 0 ? Duration.millis(0) : Duration.millis(700));
    }

    private void punch() {
        isPunching.setX(true);
        isPunching1 = true;
        changeAnimParams(4, 700, 2);
        animation.setOnFinished(event1 -> {
            animation.cycleMade = false;
            isPunching.setX(false);
            keys.remove(controls.punch());
        });
        animation.playFromStart();
    }

    private void kick() {
        if (!animation.cycleMade) isKicking = isKicking1 = true;
        changeAnimParams(6, 1260, animation.cycleMade ? -2 : 2);
        animation.imageView.setViewport(new Rectangle2D(0, 0, 118, 142));
        animation.setWidth(118);
        if (!animation.cycleMade)
            animation.setOnFinished(event1 -> {
                animation.cycleMade = true;
                kick();
                animation.setOnFinished(event2 -> {
                    isKicking = false;
                    animation.cycleMade = false;
                    animation.imageView.setViewport(new Rectangle2D(0, 0, 96, 142));
                    animation.setWidth(96);
                });
            });
        animation.playFrom(animation.cycleMade ? Duration.millis(700) : Duration.millis(0));
    }

    private void special() {
        //special.setTranslateX(getTranslateX());
        special.setFill(Paint.valueOf("red"));
        special.setVisible(true);
        isSpecial = true;
    }
}