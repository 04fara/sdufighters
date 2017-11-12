import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    ImageView imageView;
    private int count, columns,
            offsetX, offsetY,
            width, height;
    boolean cycleMade = false;

    SpriteAnimation(
            ImageView imageView, Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            int width, int height
    ) {
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    void setCount(int n) {
        this.count = n;
    }

    void setColumns(int n) {
        this.columns = n;
    }

    void setOffsetX(int x) {
        this.offsetX = x;
    }

    void setOffsetY(int y) {
        this.offsetY = y;
    }

    void setWidth(int w) {
        this.width = w;
    }

    void setHeight(int h) {
        this.height = h;
    }

    protected void interpolate(double frac) {
        final int index = Math.min((int) Math.floor(count * frac), count - 1);
        final int x = (index % columns) * width + offsetX;
        final int y = offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
}
