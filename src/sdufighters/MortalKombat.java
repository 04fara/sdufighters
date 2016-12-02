import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.*;
import javafx.scene.text.Font;
import java.util.Random;

class MortalKombat extends Pane {

    private final Image toRight1 = new Image(MortalKombat.class.getResource("res/sprites/sprite1toright.png").toString());
    private final Image toLeft1 = new Image(MortalKombat.class.getResource("res/sprites/sprite1toleft.png").toString());
    private final Image toRight2 = new Image(MortalKombat.class.getResource("res/sprites/sprite2toright.png").toString());
    private final Image toLeft2 = new Image(MortalKombat.class.getResource("res/sprites/sprite2toleft.png").toString());
    Image arena;
    final ImageView ariv = new ImageView(arena);
    private final Image finishhim = new Image(MortalKombat.class.getResource("res/ui/finishhim.png").toString());
    private final Image p1 = new Image(MortalKombat.class.getResource("res/ui/player1win.png").toString());
    private final Image p2 = new Image(MortalKombat.class.getResource("res/ui/player2win.png").toString());

    private final String backmusicpath = MortalKombat.class.getResource("res/sfx/music.mp3").toString();
    private final String fightpath = MortalKombat.class.getResource("res/sfx/fight.mp3").toString();
    private final String finishhimpath = MortalKombat.class.getResource("res/sfx/finishhim.mp3").toString();
    private final String hitpath = MortalKombat.class.getResource("res/sfx/hit.mp3").toString();
    private final String ishitpath = MortalKombat.class.getResource("res/sfx/ishit.mp3").toString();
    final String punchpath = MortalKombat.class.getResource("res/sfx/punching.mp3").toString();
    final String kickpath = MortalKombat.class.getResource("res/sfx/kicking.mp3").toString();
    final String jumppath = MortalKombat.class.getResource("res/sfx/jump.mp3").toString();

    private final Label player1 = new Label("Player 1");
    final Label player2 = new Label("Player 2");
    final Label com = new Label("COM");
    final Label start2 = new Label("Press ENTER to start");
    private final Label end = new Label("Press ESC to continue...");
    final ImageView img1 = new ImageView(toRight1);
    final ImageView img2 = new ImageView(toLeft2);
    private final ProgressBar health1 = new ProgressBar();
    final ProgressBar health2 = new ProgressBar();
    private final Thread game;

    private final Media backmusic = new Media(backmusicpath);
    private final MediaPlayer backmusicmp = new MediaPlayer(backmusic);

    double x = 0;
    double y = 0;
    double x2 = 0;
    double y2 = 0;
    private double xb = 450;
    private final double yb = 100;
    private final double width = 96;
    private final double height = 142;
    private final double widthb = 1000;
    private final double heightb = 800;
    boolean isMovingL1 = false;
    boolean isMovingR1 = false;
    boolean isOnGround1 = true;
    boolean isSitting1 = false;
    boolean isSat1 = false;
    boolean isJumping1 = false;
    boolean isJumpingP1 = false;
    boolean isJumpingR1 = false;
    boolean isJumpingL1 = false;
    boolean isUp1 = false;
    boolean isDown1 = false;
    boolean isPunching1 = false;
    boolean isKicking1 = false;
    private boolean kicked1 = false;
    boolean hit1 = false;
    boolean isDead1 = false;
    private boolean isWin1 = false;
    private boolean isLose1 = false;
    boolean isMovingL2 = false;
    boolean isMovingR2 = false;
    boolean isOnGround2 = true;
    boolean isSitting2 = false;
    boolean isSat2 = false;
    boolean isJumping2 = false;
    boolean isJumpingP2 = false;
    boolean isJumpingR2 = false;
    boolean isJumpingL2 = false;
    boolean isUp2 = false;
    boolean isDown2 = false;
    boolean isPunching2 = false;
    boolean isKicking2 = false;
    private boolean kicked2 = false;
    boolean hit2 = false;
    boolean isDead2 = false;
    private boolean isWin2 = false;
    private boolean isLose2 = false;
    boolean p1r = true;
    private boolean isFinishHimPlayed = false;
    boolean isBotIn = false;
    final boolean isOnline = false;
    boolean isTraining = false;

    private boolean fht = false;
    private boolean st2 = false;
    public boolean isStartOnline = false;
    boolean isStart = false;
    boolean isEnd = false;
    public boolean threadDone = false;
    boolean isGameOn = false;

    String name;

    private final Random r = new Random();
    private int hp1 = 100;
    private int hp2 = 100;
    int count = 2;
    int count2 = 2;
    private final long tBeginS;
    private long tBeginE;
    private long tBeginD;
    private long tStart;
    private long tEnd;
    private long tDelta;
    private double tBeginEl;
    double elapsedSeconds;

    public boolean shutdown() {
        return threadDone;
    }

    float sfxv = 70 / 100;
    float musicv = 70 / 100;

    public void sit() {
        if (name.equals("player1") || isBotIn) {
            if (!isSitting2 && !isSat2 && !isJumping2 && !isPunching1 && !isKicking1 && !isJumpingP2 && isOnGround2 && !isDead2) {
                y2 = 280;
                x2 = 0;
                isSitting2 = true;
            }
        } else {
            if (!isSitting1 && !isSat1 && !isJumping1 && !isPunching1 && !isKicking1 && !isJumpingP1 && isOnGround1 && !isDead1) {
                y = 280;
                x = 0;
                isSitting1 = true;
            }
        }
    }

    public void stand(String xc, String yc) {
        if (name.equals("player1") || isBotIn) {
            if (isSitting2) {
                isSitting2 = false;
            }
            img2.setTranslateX(Double.parseDouble(xc));
            img2.setTranslateY(Double.parseDouble(yc));
        } else {
            if (isSitting1) {
                isSitting1 = false;
            }
            img1.setTranslateX(Double.parseDouble(xc));
            img1.setTranslateY(Double.parseDouble(yc));
        }
    }

    public void jump() {
        if (name.equals("player1") || isBotIn) {
            if (!isJumping2 && !isJumpingP2 && isOnGround2 && !isPunching2 && !isKicking2 && !isDead2) {
                if (!isMovingR2 && !isMovingL2) {
                    y2 = 420;
                    x2 = 0;
                    isJumping2 = true;
                } else if (isMovingR2 || isMovingL2) {
                    y2 = 560;
                    if (isMovingR2) {
                        isJumpingR2 = true;
                        if (p1r) {
                            x2 = 864;
                        } else {
                            x2 = 0;
                        }
                    } else {
                        isJumpingL2 = true;
                        if (p1r) {
                            x2 = 0;
                        } else {
                            x2 = 864;
                        }
                    }
                    isJumpingP2 = true;
                }
                isSat2 = false;
                isSitting2 = false;
                isUp2 = true;
                isOnGround2 = false;
                AudioClip sound = new AudioClip(jumppath);
                sound.setVolume(sfxv);
                sound.setCycleCount(1);
                sound.play();
            }
        } else {
            if (!isJumping1 && !isJumpingP1 && isOnGround1 && !isPunching1 && !isKicking1 && !isDead1) {
                if (!isMovingR1 && !isMovingL1) {
                    y = 420;
                    x = 0;
                    isJumping1 = true;
                } else if (isMovingR1 || isMovingL1) {
                    y = 560;
                    if (isMovingR1) {
                        isJumpingR1 = true;
                        if (!p1r) {
                            x = 864;
                        } else {
                            x = 0;
                        }
                    } else {
                        isJumpingL1 = true;
                        if (!p1r) {
                            x = 0;
                        } else {
                            x = 864;
                        }
                    }
                    isJumpingP1 = true;
                }
                isSat1 = false;
                isSitting1 = false;
                isUp1 = true;
                isOnGround1 = false;
                AudioClip sound = new AudioClip(jumppath);
                sound.setVolume(sfxv);
                sound.setCycleCount(1);
                sound.play();
            }
        }
    }

    public void moveL() {
        if (name.equals("player1") || isBotIn) {
            if (!isSitting2 && !isSat2 && isOnGround2 && !isDead2) {
                y2 = 140;
                if (p1r) {
                    if (!isMovingL2) {
                        x2 = 768;
                    }
                } else {
                    if (!isMovingL2) {
                        x2 = 0;
                    }
                }
                isMovingL2 = true;
                isMovingR2 = false;
            }
        } else {
            if (!isSitting1 && !isSat1 && isOnGround1 && !isPunching1 && !isKicking1 && !isDead1) {
                y = 140;
                if (p1r) {
                    if (!isMovingL1) {
                        x = 768;
                    }
                } else {
                    if (!isMovingL1) {
                        x = 0;
                    }
                }
                isMovingL1 = true;
                isMovingR1 = false;
            }
        }
    }

    public void stopL(String xc, String yc) {
        if (name.equals("player1") || isBotIn) {
            if (isMovingL2 && isOnGround2) {
                isMovingL2 = false;
                x2 = 0;
                y2 = 0;
            }
            img2.setTranslateX(Double.parseDouble(xc));
            img2.setTranslateY(Double.parseDouble(yc));
        } else {
            if (isMovingL1 && isOnGround1) {
                isMovingL1 = false;
                x = 0;
                y = 0;
            }
            img1.setTranslateX(Double.parseDouble(xc));
            img1.setTranslateY(Double.parseDouble(yc));
        }
    }

    public void moveR() {
        if (name.equals("player1") || isBotIn) {
            if (!isSitting2 && !isSat2 && isOnGround2 && !isDead2) {
                y2 = 140;
                if (p1r) {
                    if (!isMovingR2) {
                        x2 = 0;
                    }
                } else {
                    if (!isMovingR2) {
                        x2 = 768;
                    }
                }
                isMovingR2 = true;
                isMovingL2 = false;
            }
        } else {
            if (!isSitting1 && !isSat1 && isOnGround1 && !isPunching1 && !isKicking1 && !isDead1) {
                y = 140;
                if (p1r) {
                    if (!isMovingR1) {
                        x = 0;
                    }
                } else {
                    if (!isMovingR1) {
                        x = 768;
                    }
                }
                isMovingR1 = true;
                isMovingL1 = false;
            }
        }
    }

    public void stopR(String xc, String yc) {
        if (name.equals("player1") || isBotIn) {
            if (isMovingR2 && isOnGround2) {
                isMovingR2 = false;
                x2 = 0;
                y2 = 0;
            }
            img2.setTranslateX(Double.parseDouble(xc));
            img2.setTranslateY(Double.parseDouble(yc));
        } else {
            if (isMovingR1 && isOnGround1) {
                isMovingR1 = false;
                x = 0;
                y = 0;
            }
            img1.setTranslateX(Double.parseDouble(xc));
            img1.setTranslateY(Double.parseDouble(yc));
        }
    }

    public void punch(String num) {
        if (name.equals("player1") || isBotIn) {
            if (!isPunching2 && !isKicking2 && isOnGround2 && !isUp2 && !isDown2 && !isJumping2 && !isJumpingP2 && !hit1 && !isDead1) {
                isMovingL2 = false;
                isMovingR2 = false;
                isSat2 = false;
                isSitting2 = false;
                isPunching2 = true;
                if (isOnline) {
                    count2 = Integer.parseInt(num);
                } else {
                    count2 = 1;
                }
                y2 = 700;
                x2 = 96;
                AudioClip sound = new AudioClip(punchpath);
                sound.setVolume(sfxv);
                sound.setCycleCount(1);
                sound.play();
            } else if (isPunching2 && count2 == 1 && !hit1) {
                y2 = 700;
                if (isOnline) {
                    count2 = Integer.parseInt(num);
                } else {
                    count2 = 2;
                }
                AudioClip sound = new AudioClip(punchpath);
                sound.setVolume(sfxv);
                sound.setCycleCount(1);
                sound.play();
            }
        } else {
            if (!isPunching1 && !isKicking1 && isOnGround1 && !isUp1 && !isDown1 && !isJumping1 && !isJumpingP1 && !hit2 && !isDead2) {
                isMovingL1 = false;
                isMovingR1 = false;
                isSat1 = false;
                isSitting1 = false;
                isPunching1 = true;
                if (isOnline) {
                    count = Integer.parseInt(num);
                } else {
                    count = 1;
                }
                y = 700;
                x = 96;
                AudioClip sound = new AudioClip(punchpath);
                sound.setVolume(sfxv);
                sound.setCycleCount(1);
                sound.play();
            } else if (isPunching1 && count == 1 && !hit2) {
                y = 700;
                if (isOnline) {
                    count = Integer.parseInt(num);
                } else {
                    count = 2;
                }
                AudioClip sound = new AudioClip(punchpath);
                sound.setVolume(sfxv);
                sound.setCycleCount(1);
                sound.play();
            }
        }
    }

    public void kick() {
        if (name.equals("player1") || isBotIn) {
            isMovingL2 = false;
            isMovingR2 = false;
            isSat2 = false;
            isSitting2 = false;
            isKicking2 = true;
            x2 = 0;
            AudioClip sound = new AudioClip(kickpath);
            sound.setVolume(sfxv);
            sound.setCycleCount(1);
            sound.play();
        } else {
            isMovingL1 = false;
            isMovingR1 = false;
            isSat1 = false;
            isSitting1 = false;
            isKicking1 = true;
            x = 0;
            AudioClip sound = new AudioClip(kickpath);
            sound.setVolume(sfxv);
            sound.setCycleCount(1);
            sound.play();
        }
    }

    public MortalKombat() {

        name = "player1";
        tBeginS = System.currentTimeMillis();
        ariv.setViewport(new Rectangle2D(xb, yb, widthb, heightb));
        ariv.toBack();
        ImageView fh = new ImageView();
        fh.setOpacity(0);
        fh.setVisible(false);
        fh.toFront();
        fh.setTranslateX(25);
        fh.setTranslateY(100);
        img1.setViewport(new Rectangle2D(x, y, width, height));
        img1.setScaleX(2);
        img1.setScaleY(2);
        img2.setViewport(new Rectangle2D(x2, y2, width, height));
        img2.setScaleX(2);
        img2.setScaleY(2);
        health1.setPrefSize(400, 50);
        health1.setProgress(hp1 / 100.0);
        health1.setStyle("-fx-accent: green; -fx-control-inner-background: red;");
        health2.setPrefSize(400, 50);
        health2.setProgress(hp2 / 100.0);
        health2.setStyle("-fx-accent: green; -fx-control-inner-background: red;");
        start2.setTranslateY(3);
        start2.setTranslateX(550);
        start2.setFont(Font.font(20));
        end.setTranslateY(600);
        end.setTranslateX(300);
        end.setFont(Font.font(40));
        end.setStyle("-fx-text-fill: red;");
        end.setVisible(false);
        if (!isBotIn) {
            start2.setVisible(false);
        }
        getChildren().addAll(ariv, img1, img2, health1, health2, player1, com, player2, fh, start2, end);
        health1.setTranslateX(50);
        health2.setTranslateX(550);
        health1.setTranslateY(30);
        health2.setTranslateY(30);
        player1.setTranslateX(60);
        player1.setFont(Font.font(35));
        player2.setFont(Font.font(35));
        com.setFont(Font.font(35));
        player1.setTranslateY(25);
        player2.setTranslateY(25);
        player2.setTranslateX(810);
        com.setTranslateX(855);
        com.setTranslateY(25);
        img1.setTranslateX(150);
        img2.setTranslateX(750);
        img1.setTranslateY(370);
        img2.setTranslateY(370);
        game = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isGameOn && !isStart && !isEnd) {
                        backmusicmp.setVolume(musicv);
                        backmusicmp.play();
                        backmusicmp.setCycleCount(-1);
                        break;
                    }
                }
                while (!shutdown()) {
                    if (!isStart && !isEnd) {
                        if (isBotIn) {
                            com.setVisible(true);
                            player2.setVisible(false);
                        } else {
                            com.setVisible(false);
                            player2.setVisible(true);
                        }
                        tBeginE = System.currentTimeMillis();
                        tBeginD = tBeginE - tBeginS;
                        tBeginEl = tBeginD / 1000.0;
                        if (tBeginEl >= 1.0) {
                            AudioClip sound = new AudioClip(fightpath);
                            sound.setVolume(sfxv);
                            sound.setCycleCount(1);
                            sound.play();
                            isStart = true;
                        }
                    }
                    if (start2.isVisible()) {
                        if (start2.getOpacity() <= 0.0) st2 = true;
                        else if (start2.getOpacity() >= 1.0) st2 = false;
                        if (!st2) {
                            start2.setOpacity(start2.getOpacity() - 0.2);
                        } else {
                            start2.setOpacity(start2.getOpacity() + 0.2);
                        }
                    }
                    //move arena
                    if (xb <= 1600 && xb >= 200) {
                        if (img1.getTranslateX() >= 850 && img2.getTranslateX() > 100 && isMovingR1) {
                            if (xb + 10 < 1600) {
                                xb += 10;
                                img2.setTranslateX(img2.getTranslateX() - 10);
                            }
                            ariv.setViewport(new Rectangle2D(xb, yb, widthb, heightb));
                        } else if (img2.getTranslateX() >= 850 && img1.getTranslateX() > 100 && isMovingR2) {
                            if (xb + 10 < 1600) {
                                xb += 10;
                                img1.setTranslateX(img1.getTranslateX() - 10);
                            }
                            ariv.setViewport(new Rectangle2D(xb, yb, widthb, heightb));
                        } else if (img1.getTranslateX() <= 50 && img2.getTranslateX() < 850 && isMovingL1) {
                            if (xb - 10 > 50) {
                                xb -= 10;
                                img2.setTranslateX(img2.getTranslateX() + 10);
                            }
                            ariv.setViewport(new Rectangle2D(xb, yb, widthb, heightb));
                        } else if (img2.getTranslateX() <= 50 && img1.getTranslateX() < 850 && isMovingL2) {
                            if (xb - 10 > 50) {
                                xb -= 10;
                                img1.setTranslateX(img1.getTranslateX() + 10);
                            }
                            ariv.setViewport(new Rectangle2D(xb, yb, widthb, heightb));
                        }
                    }

                    //time for fatality
                    if (!isLose1 && isDead1 && elapsedSeconds >= 5) {
                        if (!isEnd) {
                            x = 0;
                            isStart = false;
                            isEnd = true;
                            if (p1r) {
                                img1.setTranslateX(img1.getTranslateX() - 150);
                            } else {
                                img1.setTranslateX(img1.getTranslateX() + 150);
                            }
                            x2 = 0;
                        }
                        if (!isLose1) {
                            img1.setTranslateY(370);
                            y = 980;
                            img1.setViewport(new Rectangle2D(x, y, 150, height));
                        }
                        if (!isWin2) {
                            img2.setTranslateY(370);
                            y2 = 1120;
                            img2.setViewport(new Rectangle2D(x2, y2, width, height));
                        }
                        x += 150;
                        x2 += 96;
                        if (x == 1200) {
                            if (y != 980) {
                                y = 980;
                            }
                            end.setVisible(true);
                            end.toFront();
                            isLose1 = true;
                        }
                        if (x2 == 480) {
                            if (y2 != 1120) {
                                y2 = 1120;
                            }
                            fh.setOpacity(1.0);
                            fh.setImage(p2);
                            isWin2 = true;
                        }
                    } else if (!isLose2 && isDead2 && elapsedSeconds >= 5) {
                        if (!isEnd) {
                            x2 = 0;
                            isStart = false;
                            isEnd = true;
                            if (p1r) {
                                img2.setTranslateX(img2.getTranslateX() + 150);
                            } else {
                                img2.setTranslateX(img2.getTranslateX() - 150);
                            }
                            x = 0;
                        }
                        if (!isLose2) {
                            img2.setTranslateY(370);
                            y2 = 980;
                            img2.setViewport(new Rectangle2D(x2, y2, 150, height));
                        }
                        if (!isWin1) {
                            img1.setTranslateY(370);
                            y = 1120;
                            img1.setViewport(new Rectangle2D(x, y, width, height));
                        }
                        x2 += 150;
                        x += 96;
                        if (x2 == 1200) {
                            if (y2 != 980) {
                                y2 = 980;
                            }
                            end.setVisible(true);
                            end.toFront();
                            isLose2 = true;
                        }
                        if (x == 480) {
                            if (y != 1120) {
                                y = 1120;
                            }
                            fh.setOpacity(1.0);
                            fh.setImage(p1);
                            isWin1 = true;
                        }
                    }

                    //detect hits
                    if (hit1 && !isDead2 && !isSat2) {
                        if (hp2 <= 10) {
                            if (img2.getTranslateY() != 370) {
                                img2.setTranslateY(370);
                            }
                            hp2 = 0;
                            isDead2 = true;
                            isMovingL2 = false;
                            isMovingR2 = false;
                            isOnGround2 = true;
                            isSitting2 = false;
                            isSat2 = false;
                            isJumping2 = false;
                            isJumpingP2 = false;
                            isJumpingR2 = false;
                            isJumpingL2 = false;
                            isUp2 = false;
                            isDown2 = false;
                            isPunching2 = false;
                            isKicking2 = false;
                            tStart = System.currentTimeMillis();
                            y2 = 840;
                            x2 = 0;
                        } else {
                            if (!isTraining)
                                hp2 -= 10;
                        }
                        health2.setProgress(hp2 / 100.0);
                        if (p1r) {
                            img2.setTranslateX(img2.getTranslateX() + 10);
                        } else {
                            img2.setTranslateX(img2.getTranslateX() - 10);
                        }
                        hit1 = false;
                        AudioClip sound = new AudioClip(hitpath);
                        sound.setVolume(sfxv);
                        sound.setCycleCount(1);
                        sound.play();
                        AudioClip sound1 = new AudioClip(ishitpath);
                        sound1.setVolume(sfxv);
                        sound1.setCycleCount(1);
                        sound1.play();
                        img2.setViewport(new Rectangle2D(960, 0, width, height));
                    } else if (hit2 && !isDead1 && !isSat1) {
                        if (hp1 <= 10) {
                            hp1 = 0;
                            isDead1 = true;
                            if (img2.getTranslateY() != 370) {
                                img2.setTranslateY(370);
                            }
                            isMovingL1 = false;
                            isMovingR1 = false;
                            isOnGround1 = true;
                            isSitting1 = false;
                            isSat1 = false;
                            isJumping1 = false;
                            isJumpingP1 = false;
                            isJumpingR1 = false;
                            isJumpingL1 = false;
                            isUp1 = false;
                            isDown1 = false;
                            isPunching1 = false;
                            isKicking1 = false;
                            tStart = System.currentTimeMillis();
                            y = 840;
                            x = 0;
                        } else {
                            hp1 -= 10;
                        }
                        if (p1r) {
                            img1.setTranslateX(img1.getTranslateX() - 10);
                        } else {
                            img1.setTranslateX(img1.getTranslateX() + 10);
                        }
                        health1.setProgress(hp1 / 100.0);
                        hit2 = false;
                        AudioClip sound = new AudioClip(hitpath);
                        sound.setVolume(sfxv);
                        sound.setCycleCount(1);
                        sound.play();
                        img1.setViewport(new Rectangle2D(960, 0, width, height));
                    }

                    //change orientation
                    if (img1.getTranslateX() >= img2.getTranslateX()) {
                        if (!isJumpingP1 && !isJumpingP2) {
                            img1.setImage(toLeft1);
                            img2.setImage(toRight2);
                            p1r = false;
                        }
                        try {
                            Thread.sleep(75);
                        } catch (Exception ex) {
                        }
                    } else if (img1.getTranslateX() <= img2.getTranslateX()) {
                        if (!isJumpingP1 && !isJumpingP2) {
                            img1.setImage(toRight1);
                            img2.setImage(toLeft2);
                            p1r = true;
                        }
                        try {
                            Thread.sleep(75);
                        } catch (Exception ex) {
                        }
                    }

                    //actions of p1
                    if (!isSitting1 && !isSat1 && !isJumping1 && !isJumpingP1 && isOnGround1 && !isPunching1 && !isKicking1 && !isEnd) {
                        if (isDead1) {
                            if (img1.getTranslateY() != 370) {
                                img1.setTranslateY(370);
                            }
                            if (!isFinishHimPlayed) {
                                AudioClip sound = new AudioClip(finishhimpath);
                                sound.setVolume(sfxv);
                                sound.setCycleCount(1);
                                sound.play();
                                isFinishHimPlayed = true;
                            }
                            fh.setImage(finishhim);
                            fh.setVisible(true);
                            if (y != 840) {
                                y = 840;
                            }
                            img1.setViewport(new Rectangle2D(x, y, width, height));
                            tEnd = System.currentTimeMillis();
                            tDelta = tEnd - tStart;
                            elapsedSeconds = tDelta / 1000.0;
                            if (x == 576) {
                                x = 0;
                            } else {
                                x += 96;
                                if (fh.getOpacity() <= 0.0) fht = true;
                                else if (fh.getOpacity() >= 1.0) fht = false;
                                if (!fht) {
                                    fh.setOpacity(fh.getOpacity() - 0.4);
                                } else {
                                    fh.setOpacity(fh.getOpacity() + 0.4);
                                }
                            }
                        } else if (!isMovingR1 && !isMovingL1) {
                            if (y != 0) {
                                y = 0;
                            }
                            img1.setViewport(new Rectangle2D(x, y, width, height));
                            if (x == 768) {
                                x = 0;
                            } else {
                                x += 96;
                            }
                        } else if (isMovingR1 && !isMovingL1 && !hit2) {
                            if (y != 140) {
                                y = 140;
                            }
                            img1.setViewport(new Rectangle2D(x, y, width, height));
                            if (p1r) {
                                if (x == 768) {
                                    x = 0;
                                } else {
                                    x += 96;
                                }
                            } else {
                                if (x == 0) {
                                    x = 768;
                                } else {
                                    x -= 96;
                                }
                            }
                            if (img1.getTranslateX() <= 850 && ((img2.getTranslateX() < img1.getTranslateX()) || (Math.abs(img1.getTranslateX() - img2.getTranslateX()) >= 100))) {
                                img1.setTranslateX(img1.getTranslateX() + 15);
                            } else if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) < 100 && p1r && img2.getTranslateX() <= 850) {
                                img1.setTranslateX(img1.getTranslateX() + 10);
                                img2.setTranslateX(img2.getTranslateX() + 10);
                            }
                        } else if (!isMovingR1 && isMovingL1 && !hit2) {
                            if (y != 140) {
                                y = 140;
                            }
                            img1.setViewport(new Rectangle2D(x, y, width, height));
                            if (p1r) {
                                if (x == 0) {
                                    x = 768;
                                } else {
                                    x -= 96;
                                }
                            } else {
                                if (x == 768) {
                                    x = 0;
                                } else {
                                    x += 96;
                                }
                            }
                            if (img1.getTranslateX() >= 50 && ((img2.getTranslateX() > img1.getTranslateX()) || (Math.abs(img1.getTranslateX() - img2.getTranslateX()) >= 100))) {
                                img1.setTranslateX(img1.getTranslateX() - 15);
                            } else if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) < 100 && !p1r && img2.getTranslateX() >= 50) {
                                img1.setTranslateX(img1.getTranslateX() - 10);
                                img2.setTranslateX(img2.getTranslateX() - 10);
                            }
                        }
                    }
                    if (isSitting1 && !isSat1) {
                        img1.setViewport(new Rectangle2D(x, y, width, height));
                        x += 96;
                        if (x == 288) {
                            isSat1 = true;
                            x = 192;
                        }
                    }
                    if (!isSitting1 && isSat1) {
                        img1.setViewport(new Rectangle2D(x, y, width, height));
                        x -= 96;
                        if (x == 0) {
                            y = 0;
                            isSat1 = false;
                            isSitting1 = false;
                        }
                    }
                    if (isJumping1 && !isOnGround1 && !hit2) {
                        if (isUp1 && !isDown1) {
                            if (x <= 192) {
                                img1.setViewport(new Rectangle2D(x, y, width, height));
                                x += 96;
                                img1.setTranslateY(img1.getTranslateY() - 80);
                            } else {
                                isUp1 = false;
                                isDown1 = true;
                                x = 192;
                            }
                        } else if (!isUp1 && isDown1) {
                            if (x >= 0) {
                                img1.setViewport(new Rectangle2D(x, y, width, height));
                                x -= 96;
                                img1.setTranslateY(img1.getTranslateY() + 80);
                            } else {
                                isDown1 = false;
                                isOnGround1 = true;
                                isJumping1 = false;
                                x = 0;
                                y = 0;
                            }
                        }
                    }
                    if (isJumpingP1 && !isOnGround1 && !hit2) {
                        if (isJumpingR1) {
                            if (p1r) {
                                if (x <= 864) {
                                    img1.setViewport(new Rectangle2D(x, y, width, height));
                                    x += 96;
                                    if (isUp1) {
                                        img1.setTranslateY(img1.getTranslateY() - 60);
                                        if (img1.getTranslateY() == 70) {
                                            isUp1 = false;
                                            isDown1 = true;
                                        }
                                    } else {
                                        img1.setTranslateY(img1.getTranslateY() + 60);
                                    }
                                    if (img1.getTranslateX() + 30 <= 850) {
                                        img1.setTranslateX(img1.getTranslateX() + 30);
                                    }
                                } else {
                                    isJumpingP1 = false;
                                    isJumpingR1 = false;
                                    isMovingR1 = false;
                                    isOnGround1 = true;
                                    isDown1 = false;
                                    x = 0;
                                    y = 0;
                                }
                            } else {
                                if (x >= 0) {
                                    img1.setViewport(new Rectangle2D(x, y, width, height));
                                    x -= 96;
                                    if (isUp1) {
                                        img1.setTranslateY(img1.getTranslateY() - 60);
                                        if (img1.getTranslateY() == 70) {
                                            isUp1 = false;
                                            isDown1 = true;
                                        }
                                    } else {
                                        img1.setTranslateY(img1.getTranslateY() + 60);
                                    }
                                    if (img1.getTranslateX() + 30 <= 850) {
                                        img1.setTranslateX(img1.getTranslateX() + 30);
                                    }
                                } else {
                                    isJumpingP1 = false;
                                    isJumpingR1 = false;
                                    isMovingR1 = false;
                                    isOnGround1 = true;
                                    isDown1 = false;
                                    x = 0;
                                    y = 0;
                                }
                            }
                        } else {
                            if (p1r) {
                                if (x >= 0) {
                                    img1.setViewport(new Rectangle2D(x, y, width, height));
                                    x -= 96;
                                    if (isUp1) {
                                        img1.setTranslateY(img1.getTranslateY() - 60);
                                        if (img1.getTranslateY() == 70) {
                                            isUp1 = false;
                                            isDown1 = true;
                                        }
                                    } else {
                                        img1.setTranslateY(img1.getTranslateY() + 60);
                                    }
                                    if (img1.getTranslateX() - 30 >= 50) {
                                        img1.setTranslateX(img1.getTranslateX() - 30);
                                    }
                                } else {
                                    isJumpingP1 = false;
                                    isJumpingL1 = false;
                                    isMovingL1 = false;
                                    isOnGround1 = true;
                                    isDown1 = false;
                                    x = 0;
                                    y = 0;
                                }
                            } else {
                                if (x <= 864) {
                                    img1.setViewport(new Rectangle2D(x, y, width, height));
                                    x += 96;
                                    if (isUp1) {
                                        img1.setTranslateY(img1.getTranslateY() - 60);
                                        if (img1.getTranslateY() == 70) {
                                            isUp1 = false;
                                            isDown1 = true;
                                        }
                                    } else {
                                        img1.setTranslateY(img1.getTranslateY() + 60);
                                    }
                                    if (img1.getTranslateX() - 30 >= 50) {
                                        img1.setTranslateX(img1.getTranslateX() - 30);
                                    }
                                } else {
                                    isJumpingP1 = false;
                                    isJumpingL1 = false;
                                    isMovingL1 = false;
                                    isOnGround1 = true;
                                    isDown1 = false;
                                    x = 0;
                                    y = 0;
                                }
                            }
                        }
                    }
                    if (!hit2 && isPunching1) {
                        if (count > 0) {
                            if (count == 2) {
                                if (x <= 576) {
                                    img1.setViewport(new Rectangle2D(x, y, width, height));
                                    if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) <= 110 && (x == 288 || x == 576) && !isSat2 && img2.getTranslateY() > 220) {
                                        isPunching2 = false;
                                        isKicking2 = false;
                                        hit1 = true;
                                    } else {
                                        hit1 = false;
                                    }
                                    x += 96;
                                } else {
                                    count = 0;
                                    x = 96;
                                    y = 0;
                                    isPunching1 = false;
                                }
                            } else if (count == 1) {
                                if (x <= 288) {
                                    img1.setViewport(new Rectangle2D(x, y, width, height));
                                    x += 96;
                                    if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) <= 110 && x == 288 && !isSat2 && img2.getTranslateY() > 220) {
                                        isPunching2 = false;
                                        isKicking2 = false;
                                        hit1 = true;
                                    } else {
                                        hit1 = false;
                                    }
                                } else {
                                    count = 0;
                                    x = 96;
                                    y = 0;
                                    isPunching1 = false;
                                }
                            }
                        }
                    }
                    if (!hit2 && isKicking1) {
                        if (!kicked1) {
                            if (x <= 600) {
                                img1.setViewport(new Rectangle2D(x, 1260, 120, height));
                            }
                            x += 120;
                            if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) <= 160 && x == 600 && !isSat2 && img2.getTranslateY() > 220) {
                                isPunching2 = false;
                                isKicking2 = false;
                                hit1 = true;
                            } else {
                                hit1 = false;
                            }
                            if (x == 720) {
                                kicked1 = true;
                            }
                        }
                        if (kicked1) {
                            if (x <= 600) {
                                img1.setViewport(new Rectangle2D(x, 1260, 120, height));
                            }
                            x -= 120;
                            if (x == -120) {
                                kicked1 = false;
                                x = 0;
                                y = 0;
                                isKicking1 = false;
                            }
                        }
                    }

                    //AI
                    if (isStart && !isEnd && elapsedSeconds < 5 && isBotIn && !isDead1 && !isDead2) {
                        tBeginE = System.currentTimeMillis();
                        tBeginD = tBeginE - tBeginS;
                        tBeginEl = tBeginD / 1000.0;
                        if (tBeginEl >= 2.0) {
                            if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) >= 150) {
                                if (p1r) {
                                    moveL();
                                } else {
                                    moveR();
                                }
                            } else if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) <= 110) {
                                if (r.nextInt(2) == 0) {
                                    if (r.nextInt(2) == 0) {
                                        punch("1");
                                    } else {
                                        punch("2");
                                    }
                                } else if (r.nextInt(2) == 1) {
                                    //kick();
                                }
                            } else if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) > 110) {
                                if (isPunching1 || isKicking1) {
                                    switch (r.nextInt(4)) {
                                        case 0:
                                            sit();
                                            break;
                                        case 1:
                                            isMovingL2 = true;
                                            jump();
                                            break;
                                        case 2:
                                            isMovingR2 = true;
                                            jump();
                                            break;
                                        case 3:
                                            jump();
                                            break;
                                    }
                                }
                            }
                        }
                    }

                    //actions of p2
                    if (!isSitting2 && !isSat2 && !isJumping2 && !isJumpingP2 && isOnGround2 && !isPunching2 && !isKicking2 && !isEnd) {
                        if (isDead2) {
                            if (img2.getTranslateY() != 370) {
                                img2.setTranslateY(370);
                            }
                            if (!isFinishHimPlayed) {
                                AudioClip sound = new AudioClip(finishhimpath);
                                sound.setVolume(sfxv);
                                sound.setCycleCount(1);
                                sound.play();
                                isFinishHimPlayed = true;
                            }
                            fh.setImage(finishhim);
                            fh.setVisible(true);
                            if (y2 != 840) {
                                y2 = 840;
                            }
                            img2.setViewport(new Rectangle2D(x2, y2, width, height));
                            tEnd = System.currentTimeMillis();
                            tDelta = tEnd - tStart;
                            elapsedSeconds = tDelta / 1000.0;
                            if (x2 == 576) {
                                x2 = 0;
                            } else {
                                x2 += 96;
                                if (fh.getOpacity() <= 0.0) fht = true;
                                else if (fh.getOpacity() >= 1.0) fht = false;
                                if (!fht) {
                                    fh.setOpacity(fh.getOpacity() - 0.4);
                                } else {
                                    fh.setOpacity(fh.getOpacity() + 0.4);
                                }
                            }
                        } else if (!isMovingR2 && !isMovingL2) {
                            if (y2 != 0) {
                                y2 = 0;
                            }
                            img2.setViewport(new Rectangle2D(x2, y2, width, height));
                            if (x2 == 768) {
                                x2 = 0;
                            } else {
                                x2 += 96;
                            }
                        } else if (isMovingR2 && !isMovingL2 && !hit1) {
                            if (y2 != 140) {
                                y2 = 140;
                            }
                            img2.setViewport(new Rectangle2D(x2, y2, width, height));
                            if (!p1r) {
                                if (x2 == 768) {
                                    x2 = 0;
                                } else {
                                    x2 += 96;
                                }
                            } else {
                                if (x2 == 0) {
                                    x2 = 768;
                                } else {
                                    x2 -= 96;
                                }
                            }
                            if (img2.getTranslateX() <= 850 && ((img1.getTranslateX() < img2.getTranslateX()) || (Math.abs(img1.getTranslateX() - img2.getTranslateX()) >= 100))) {
                                img2.setTranslateX(img2.getTranslateX() + 15);
                            } else if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) < 100 && !p1r && img1.getTranslateX() <= 850) {
                                img1.setTranslateX(img1.getTranslateX() + 10);
                                img2.setTranslateX(img2.getTranslateX() + 10);
                            }
                        } else if (!isMovingR2 && isMovingL2 && !hit1) {
                            if (y2 != 140) {
                                y2 = 140;
                            }
                            img2.setViewport(new Rectangle2D(x2, y2, width, height));
                            if (!p1r) {
                                if (x2 == 0) {
                                    x2 = 768;
                                } else {
                                    x2 -= 96;
                                }
                            } else {
                                if (x2 == 768) {
                                    x2 = 0;
                                } else {
                                    x2 += 96;
                                }
                            }
                            if (img2.getTranslateX() >= 50 && ((img1.getTranslateX() > img2.getTranslateX()) || (Math.abs(img1.getTranslateX() - img2.getTranslateX()) >= 100))) {
                                img2.setTranslateX(img2.getTranslateX() - 15);
                            } else if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) < 100 && p1r && img1.getTranslateX() >= 50) {
                                img1.setTranslateX(img1.getTranslateX() - 10);
                                img2.setTranslateX(img2.getTranslateX() - 10);
                            }
                        }
                    }
                    if (isSitting2 && !isSat2) {
                        img2.setViewport(new Rectangle2D(x2, y2, width, height));
                        x2 += 96;
                        if (x2 == 288) {
                            isSat2 = true;
                            x2 = 192;
                        }
                    }
                    if (!isSitting2 && isSat2) {
                        img2.setViewport(new Rectangle2D(x2, y2, width, height));
                        x2 -= 96;
                        if (x2 == 0) {
                            y2 = 0;
                            isSat2 = false;
                            isSitting2 = false;
                        }
                    }
                    if (isJumping2 && !isOnGround2 && !hit1) {
                        if (isUp2 && !isDown2) {
                            if (x2 <= 192) {
                                img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                x2 += 96;
                                img2.setTranslateY(img2.getTranslateY() - 60);
                            } else {
                                isUp2 = false;
                                isDown2 = true;
                                x2 = 192;
                            }
                        } else if (!isUp2 && isDown2) {
                            if (x2 >= 0) {
                                img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                x2 -= 96;
                                img2.setTranslateY(img2.getTranslateY() + 60);
                            } else {
                                isDown2 = false;
                                isOnGround2 = true;
                                isJumping2 = false;
                                x2 = 0;
                                y2 = 0;
                            }
                        }
                    }
                    if (isJumpingP2 && !isOnGround2 && !hit1) {
                        if (isJumpingR2) {
                            if (!p1r) {
                                if (x2 <= 864) {
                                    img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                    x2 += 96;
                                    if (isUp2) {
                                        img2.setTranslateY(img2.getTranslateY() - 60);
                                        if (img2.getTranslateY() == 70) {
                                            isUp2 = false;
                                            isDown2 = true;
                                        }
                                    } else {
                                        img2.setTranslateY(img2.getTranslateY() + 60);
                                    }
                                    if (img2.getTranslateX() + 30 <= 850) {
                                        img2.setTranslateX(img2.getTranslateX() + 30);
                                    }
                                } else {
                                    isJumpingP2 = false;
                                    isJumpingR2 = false;
                                    isMovingR2 = false;
                                    isOnGround2 = true;
                                    isDown2 = false;
                                    x2 = 0;
                                    y2 = 0;
                                }
                            } else {
                                if (x2 >= 0) {
                                    img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                    x2 -= 96;
                                    if (isUp2) {
                                        img2.setTranslateY(img2.getTranslateY() - 60);
                                        if (img2.getTranslateY() == 70) {
                                            isUp2 = false;
                                            isDown2 = true;
                                        }
                                    } else {
                                        img2.setTranslateY(img2.getTranslateY() + 60);
                                    }
                                    if (img2.getTranslateX() + 30 <= 850) {
                                        img2.setTranslateX(img2.getTranslateX() + 30);
                                    }
                                } else {
                                    isJumpingP2 = false;
                                    isJumpingR2 = false;
                                    isMovingR2 = false;
                                    isOnGround2 = true;
                                    isDown2 = false;
                                    x2 = 0;
                                    y2 = 0;
                                }
                            }
                        } else {
                            if (!p1r) {
                                if (x2 >= 0) {
                                    img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                    x2 -= 96;
                                    if (isUp2) {
                                        img2.setTranslateY(img2.getTranslateY() - 60);
                                        if (img2.getTranslateY() == 70) {
                                            isUp2 = false;
                                            isDown2 = true;
                                        }
                                    } else {
                                        img2.setTranslateY(img2.getTranslateY() + 60);
                                    }
                                    if (img2.getTranslateX() - 30 >= 50) {
                                        img2.setTranslateX(img2.getTranslateX() - 30);
                                    }
                                } else {
                                    isJumpingP2 = false;
                                    isJumpingL2 = false;
                                    isMovingL2 = false;
                                    isOnGround2 = true;
                                    isDown2 = false;
                                    x2 = 0;
                                    y2 = 0;
                                }
                            } else {
                                if (x2 <= 864) {
                                    img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                    x2 += 96;
                                    if (isUp2) {
                                        img2.setTranslateY(img2.getTranslateY() - 60);
                                        if (img2.getTranslateY() == 70) {
                                            isUp2 = false;
                                            isDown2 = true;
                                        }
                                    } else {
                                        img2.setTranslateY(img2.getTranslateY() + 60);
                                    }
                                    if (img2.getTranslateX() - 30 >= 50) {
                                        img2.setTranslateX(img2.getTranslateX() - 30);
                                    }
                                } else {
                                    isJumpingP2 = false;
                                    isJumpingL2 = false;
                                    isMovingL2 = false;
                                    isOnGround2 = true;
                                    isDown2 = false;
                                    x2 = 0;
                                    y2 = 0;
                                }
                            }
                        }
                    }
                    if (!hit1 && isPunching2) {
                        if (count2 > 0) {
                            if (count2 == 2) {
                                if (x2 <= 576) {
                                    img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                    if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) <= 110 && (x2 == 288 || x2 == 576) && !isSat1 && img1.getTranslateY() > 220) {
                                        isPunching1 = false;
                                        hit2 = true;
                                    } else {
                                        hit2 = false;
                                    }
                                    x2 += 96;
                                } else {
                                    count2 = 0;
                                    x2 = 96;
                                    y2 = 0;
                                    isPunching2 = false;
                                }
                            } else if (count2 == 1) {
                                if (x2 <= 288) {
                                    img2.setViewport(new Rectangle2D(x2, y2, width, height));
                                    x2 += 96;
                                    if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) <= 110 && x2 == 288 && !isSat1 && img1.getTranslateY() > 220) {
                                        isPunching1 = false;
                                        isKicking1 = false;
                                        hit2 = true;
                                    } else {
                                        hit2 = false;
                                    }
                                } else {
                                    count2 = 0;
                                    x2 = 96;
                                    y2 = 0;
                                    isPunching2 = false;
                                }
                            }
                        }
                    }
                    if (!hit1 && isKicking2) {
                        if (!kicked2) {
                            if (x2 <= 600) {
                                img2.setViewport(new Rectangle2D(x2, 1260, 120, height));
                            }
                            x2 += 120;
                            if (Math.abs(img1.getTranslateX() - img2.getTranslateX()) <= 160 && x2 == 600 && !isSat1 && img1.getTranslateY() > 220) {
                                isPunching1 = false;
                                isKicking1 = false;
                                hit2 = true;
                            } else {
                                hit2 = false;
                            }
                            if (x2 == 720) {
                                kicked2 = true;
                            }
                        }
                        if (kicked2) {
                            if (x2 <= 600) {
                                img2.setViewport(new Rectangle2D(x2, 1260, 120, height));
                            }
                            x2 -= 120;
                            if (x2 == -120) {
                                kicked2 = false;
                                x2 = 0;
                                y2 = 0;
                                isKicking2 = false;
                            }
                        }
                    }
                }
                backmusicmp.stop();
            }
        });
        game.start();
    }
}