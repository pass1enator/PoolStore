package pedro.ieslaencanta.com.poolstore.categories;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedro.ieslaencanta.com.poolstore.Main;
import pedro.ieslaencanta.com.poolstore.Controller;
import pedro.ieslaencanta.com.poolstore.model.Category;

/**
 * The main view contains a button and a click listener.
 */
//@Route("category/:id?")
@Route(value = "category/:id?", layout = Main.class)
public class CategoryEditView extends FormLayout implements BeforeEnterObserver {

    private TextField name = new TextField("Name");
    private String parametro;
    private Controller p;
    private Category c;
    private Binder<Category> binder;
    
    private Button guardar;
    private Button cancelar;
    private Button newproduct;

    public CategoryEditView() {
       
        //boton para guardar
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
        this.guardar.setEnabled(true);
        this.cancelar = new Button("Volver", e -> {
            binder.readBean(c);
            UI.getCurrent().getPage().getHistory().back();
        });
      
        this.p = Controller.getInstance();
        HorizontalLayout buttons = new HorizontalLayout(guardar, cancelar);
        add(name, buttons);
        

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        String tempo = event.getRouteParameters().get("id").orElse(null);
        Logger.getLogger(CategoriesView.class.getName()).log(Level.SEVERE, "El nombre es:" + tempo);

        if (tempo != null) {
            this.c = this.p.getAplicacion().getCategory(Integer.valueOf(tempo));

        }
        Logger.getLogger(CategoriesView.class.getName()).log(Level.SEVERE, "Es nuloo" + this.c + " " + tempo);

        if (this.c == null) {
            this.c = new Category();

        }
         this.binder = new BeanValidationBinder<>(Category.class);
       
        this.binder.bindInstanceFields(this);

        this.binder.setBean(c);
      

    }

}
