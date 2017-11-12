import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.lang.*;
import java.util.*;

import static javafx.scene.input.KeyCode.*;

public class Main extends Application {
    private Image image1 = new Image(getClass().getResourceAsStream("2.png"));
    private Controls controls1 = new Controls(Arrays.asList(W, A, S, D, G, H, J)),
            controls2 = new Controls(Arrays.asList(UP, LEFT, DOWN, RIGHT, K, L, SEMICOLON));
    private Fighter player1;
    private Fighter player2;
    final ImageView ariv = new ImageView(new Image(Main.class.getResource("arena.png").toString()));
    int xb = 1369;

    private void onKeyPressed(Fighter player, KeyCode key, KeyCode punch) {
        if (!player.isJumping && !player.isPunching.getX() && !player.isKicking) {
            if (!player.keys.contains(key)) player.keys.addFirst(key);
        } else if (key.equals(punch) && player.animation.getCurrentTime().toMillis() < 475) {
            player.animation.setCount(7);
            player.animation.setColumns(7);
            player.animation.setRate(1);
            System.out.println("2");
        }
    }

    private void onKeyReleased(Fighter player) {
        player.animation.cycleMade = true;
        player.sit();
        player.animation.setOnFinished(event1 -> {
            player.isSitting = false;
            player.animation.cycleMade = false;
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ariv.setViewport(new Rectangle2D(xb, 0, 1200, 798));
        player1 = new Fighter(controls1, new ImageView(image1), true, true);
        player2 = new Fighter(controls2, new ImageView(image1), true, false);
        player1.setEnemy(player2);
        player2.setEnemy(player1);
        Pane root = new Pane();
        root.setPrefSize(1200, 798);
        root.getChildren().addAll(ariv, player1, player2);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case W:
                case A:
                    if (key.equals(A)) {
                        player1.isMovingR = false;
                        player1.isMovingL = true;
                    }
                case S:
                case D:
                    if (key.equals(D)) {
                        player1.isMovingR = true;
                        player1.isMovingL = false;
                    }
                case G:
                    if (player1.isPunching1) break;
                case H:
                    if (player1.isKicking1) break;
                case J:
                    onKeyPressed(player1, key, G);
                    break;
                case UP:
                case LEFT:
                    if (key.equals(LEFT)) {
                        player2.isMovingR = false;
                        player2.isMovingL = true;
                    }
                case DOWN:
                case RIGHT:
                    if (key.equals(RIGHT)) {
                        player2.isMovingR = true;
                        player2.isMovingL = false;
                    }
                case K:
                    if (player2.isPunching1) break;
                case L:
                    if (player2.isKicking1) break;
                case SEMICOLON:
                    onKeyPressed(player2, key, K);
                    break;
            }
        });
        scene.setOnKeyReleased(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case A:
                    if (key.equals(A)) {
                        player1.isMovingR = false;
                        player1.isMovingL = false;
                    }
                    break;
                case D:
                    if (key.equals(D)) {
                        player1.isMovingR = false;
                        player1.isMovingL = false;
                    }
                    break;
                case LEFT:
                    if (key.equals(LEFT)) {
                        player2.isMovingR = false;
                        player2.isMovingL = false;
                    }
                    break;
                case RIGHT:
                    if (key.equals(RIGHT)) {
                        player2.isMovingR = false;
                        player2.isMovingL = false;
                    }
                    break;
                case S:
                    onKeyReleased(player1);
                    break;
                case G:
                    player1.isPunching1 = false;
                    break;
                case H:
                    player1.isKicking1 = false;
                    break;
                case DOWN:
                    onKeyReleased(player2);
                    break;
                case K:
                    player2.isPunching1 = false;
                    break;
                case L:
                    player2.isKicking1 = false;
                    break;
            }
            if (!key.equals(G) && (key.equals(W) || key.equals(A) || key.equals(S) || key.equals(D) || key.equals(H) || key.equals(J)))
                player1.keys.remove(key);
            if (!key.equals(K) && (key.equals(UP) || key.equals(LEFT) || key.equals(DOWN) || key.equals(RIGHT) || key.equals(L) || key.equals(SEMICOLON)))
                player2.keys.remove(key);
        });
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(value -> {
            Platform.exit();
            System.exit(0);
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((player1.moved || player2.moved || player1.isJumping || player2.isJumping)) {
                    if (player1.isMovingR && player1.getTranslateX() == 1056 && player2.getTranslateX() - 4 > 48) {
                        player1.moved = player2.moved = false;
                        if (xb + 4 <= 2538) {
                            player2.setTranslateX(player2.getTranslateX() - 4);
                            xb += 4;
                        }
                        ariv.setViewport(new Rectangle2D(xb, 0, 1200, 798));
                    } else if (player1.isMovingL && player1.getTranslateX() == 48 && player2.getTranslateX() + 4 < 1056) {
                        player1.moved = false;
                        player2.moved = false;
                        if (xb - 4 >= 208) {
                            player2.setTranslateX(player2.getTranslateX() + 4);
                            xb -= 4;
                        }
                        ariv.setViewport(new Rectangle2D(xb, 0, 1200, 798));
                    } else if (player2.isMovingR && player2.getTranslateX() == 1056 && player1.getTranslateX() - 4 > 48) {
                        player1.moved = player2.moved = false;
                        if (xb + 4 <= 2538) {
                            player1.setTranslateX(player1.getTranslateX() - 4);
                            xb += 4;
                        }
                        ariv.setViewport(new Rectangle2D(xb, 0, 1200, 798));
                    } else if (player2.isMovingL && player2.getTranslateX() == 48 && player1.getTranslateX() + 4 < 1056) {
                        player1.moved = player2.moved = false;
                        if (xb - 4 >= 208) {
                            player1.setTranslateX(player1.getTranslateX() + 4);
                            xb -= 4;
                        }
                        ariv.setViewport(new Rectangle2D(xb, 0, 1200, 798));
                    }
                }
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
