package pedro.ieslaencanta.com.poolstore;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import pedro.ieslaencanta.com.poolstore.model.Category;

/**
 * The main view contains a button and a click listener.
 */
@Route("category/:name")
public class CategoryEditView extends FormLayout implements BeforeEnterObserver {

    private TextField  name = new TextField("name");
   // private TextField id = new TextField("id");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private String parametro;
    private Principal p;
    private Binder<Category> binder = new Binder<>(Category.class);
    public CategoryEditView() {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(name, buttons);
        binder.bindInstanceFields(this);
       

    }
     @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.parametro = event.getRouteParameters().get("name").get();
        p.getAplicacion().getCategory(this.parametro);
       // this.etiqueta.setText(parametro);
      //  this.add(this.etiqueta);
    }
}
