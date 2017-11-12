import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

class Controls {
    private ArrayList<KeyCode> controls;

    Controls(List<KeyCode> controls) {
        this.controls = new ArrayList<>(controls);
    }

    KeyCode up() {
        return controls.get(0);
    }

    KeyCode left() {
        return controls.get(1);
    }

    KeyCode down() {
        return controls.get(2);
    }

    KeyCode right() {
        return controls.get(3);
    }

    KeyCode punch() {
        return controls.get(4);
    }

    KeyCode kick() {
        return controls.get(5);
    }

    KeyCode special() {
        return controls.get(6);
    }
}