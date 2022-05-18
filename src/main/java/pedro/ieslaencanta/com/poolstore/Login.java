package pedro.ieslaencanta.com.poolstore;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route
public class Login extends VerticalLayout {

    LoginForm login;
    Anchor link;
    LoginI18n.Form i18nForm;

    public Login() {
        this.setAlignItems(Alignment.CENTER);
        this.login = new LoginForm();
        //personalizar
        LoginI18n i18n = LoginI18n.createDefault();
        i18nForm = i18n.getForm();
        i18nForm.setTitle("PoolStore");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("Validarse");
        i18nForm.setForgotPassword("");
        i18n.setForm(i18nForm);

        this.login.setI18n(i18n);

        this.login.addLoginListener(listener -> {
            //if (Principal.getInstance().validate(this.i18nForm.getUsername(), this.i18nForm.getPassword())) {
                getUI().get().getPage().setLocation("/main");
           // } else {
           //     Notification.show(String.valueOf(AppShell.contador));
           //     this.login.setEnabled(true);
           // }

        });
        this.add(this.login);
    }
}
