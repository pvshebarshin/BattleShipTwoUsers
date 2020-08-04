package server;

import org.junit.jupiter.api.Test;

class ControllerTest {

    @Test
    void checkText() {
        assert (Controller.checkText("1"));
        assert (Controller.checkText("6"));
    }
}