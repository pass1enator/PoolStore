package pedro.ieslaencanta.com.poolstore.categories;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedro.ieslaencanta.com.poolstore.Principal;
import pedro.ieslaencanta.com.poolstore.model.Category;

/**
 * The main view contains a button and a click listener.
 */
@Route("category/")
public class CategoryEditView extends FormLayout implements BeforeEnterObserver, HasUrlParameter<String> {

    private TextField name = new TextField("name");
    private String parametro;
    private Principal p;
    private Category c;
    private Binder<Category> binder = new Binder<>(Category.class);
    private Button guardar;
    private Button cancelar;
    private Button newproduct;

    public CategoryEditView() {
        this.guardar = new Button("Guardar", e -> {
            try {
                binder.writeBean(c);
                if (c.getId() == -1) {
                    p.getAplicacion().addCategory(c);
                }
                UI.getCurrent().getPage().getHistory().back();

            } catch (ValidationException ex) {
                Logger.getLogger(CategoryEditView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.cancelar = new Button("Volver", e -> {
            binder.readBean(c);
            UI.getCurrent().getPage().getHistory().back();
        });

        this.p = Principal.getInstance();
        HorizontalLayout buttons = new HorizontalLayout(guardar, cancelar);
        guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, buttons);
        binder.bindInstanceFields(this);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

    }

    @Override
    public void setParameter(BeforeEvent event,
            @OptionalParameter String parameter) {
        if (parameter == null) {
            c = new Category();
        } else {
            //this.parametro = event.getRouteParameters().get("name").get();
            c = p.getAplicacion().getCategory(parameter);

        }
        this.binder.setBean(c);

    }
}
