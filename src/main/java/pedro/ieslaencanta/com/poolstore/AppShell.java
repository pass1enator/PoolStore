package pedro.ieslaencanta.com.poolstore;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

/**
 * This class is used to configure the generated html host page used by the app
 */
@PWA(name = "PoolStore", shortName = "PoolStore")
public class AppShell implements AppShellConfigurator {
    public static int contador=40;
    public static void inc(){
        contador++;
    }
}