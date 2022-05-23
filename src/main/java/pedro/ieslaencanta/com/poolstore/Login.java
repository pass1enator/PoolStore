package pedro.ieslaencanta.com.poolstore;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedro.ieslaencanta.com.poolstore.product.ProductsView;

/**
 * The main view contains a button and a click listener.
 */
@Route("login")
public class Login extends VerticalLayout implements BeforeEnterObserver {

    LoginForm login;
    Anchor link;
    LoginI18n.Form i18nForm;
    LoginI18n i18n;

    public Login() {
        this.setAlignItems(Alignment.CENTER);
        this.login = new LoginForm();
        //personalizar
        i18n = LoginI18n.createDefault();
        i18nForm = i18n.getForm();
        i18nForm.setTitle("PoolStore");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("Validarse");
        i18nForm.setForgotPassword("");
        i18n.setForm(i18nForm);
        this.login.setEnabled(true);
        this.login.setI18n(i18n);

        this.login.addLoginListener(listener -> {
            Logger.getLogger(ProductsView.class.getName()).log(Level.SEVERE, listener.getUsername() + " " + this.i18n.getForm().getUsername());

            if (Controller.getInstance().validate(listener.getUsername(), listener.getPassword())) {
                getUI().get().getPage().setLocation("/");

            } else {
                this.login.setEnabled(true);
            }

        });
        this.add(this.login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent bee) {
        Controller.getInstance().unloggin();
    }
}
