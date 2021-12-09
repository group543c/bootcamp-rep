package Ex19.tests;

import Ex19.app.Application;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @BeforeEach
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }
        app = new Application();
        tlApp.set(app);
        Runtime.getRuntime().addShutdownHook(
            new Thread(() -> {
                app.quit();
                app = null;
            }));
        }
    }
