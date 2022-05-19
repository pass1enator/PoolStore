package pedro.ieslaencanta.com.poolstore.categories;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pedro.ieslaencanta.com.poolstore.Principal;
import pedro.ieslaencanta.com.poolstore.model.Category;

/**
 * The main view contains a button and a click listener.
 */
@Route("main")
public class CategoriesView extends VerticalLayout {

    Grid<Category> grid;
    Button nuevo;

    public CategoriesView() {

        this.setAlignItems(Alignment.CENTER);
        this.nuevo = new Button("Nuevo");
        this.nuevo.addClickListener(listener -> {
            getUI().get().getPage().setLocation("/category");

        });
        grid = new Grid<>(Category.class, false);
        grid.addColumn(Category::getId).setHeader("Identificador");
        grid.addColumn(Category::getName).setHeader("Name");
        grid.addComponentColumn(category -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                getUI().get().getPage().setLocation("/category/" + category.getName());

            });
            return editButton;
        });
        grid.addComponentColumn(category -> {
            Button deleteButton = new Button("Borrar");
            deleteButton.addClickListener(e -> {
               
                Category d=Principal.getInstance().getAplicacion().removeCategory(category);
                grid.getDataProvider().refreshAll();
                Notification.show(Principal.getInstance().getAplicacion().getCategorys().size()+" -"+category.getId());
            });
            return deleteButton;
        });
        grid.addComponentColumn(category -> {
            Button editButton = new Button("Productos");
            editButton.addClickListener(e -> {
                getUI().get().getPage().setLocation("/category/product/" + category.getName());

            });
            return editButton;
        });
        grid.setItems(Principal.getInstance().getAplicacion().getCategorys());
        add(grid);
        HorizontalLayout buttons = new HorizontalLayout(this.nuevo);
        nuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add( buttons);
    }
}
