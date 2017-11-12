class Tuple<X, Y> {

    private X x;
    private Y y;

    Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    void set(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    void setX(X x) {
        this.x = x;
    }

    void setY(Y y) {
        this.y = y;
    }

    X getX() {
        return x;
    }

    Y getY() {
        return y;
    }
}
/*
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
 */