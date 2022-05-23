package pedro.ieslaencanta.com.poolstore.categories;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;
import pedro.ieslaencanta.com.poolstore.Main;
import pedro.ieslaencanta.com.poolstore.Controller;

import pedro.ieslaencanta.com.poolstore.model.Category;

/**
 * The main view contains a button and a click listener.
 */


@Route(value = "categories", layout = Main.class)
public class CategoriesView extends VerticalLayout  implements RouterLayout {

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
                getUI().get().getPage().setLocation("/category/" + category.getId());

            });
            return editButton;
        });
        grid.addComponentColumn(category -> {
            Button deleteButton = new Button("Borrar");
            deleteButton.addClickListener(e -> {
               
                Category d=Controller.getInstance().getAplicacion().removeCategory(category);
                grid.getDataProvider().refreshAll();
                   
            });
            return deleteButton;
        });
        grid.addComponentColumn(category -> {
            Button productButton = new Button("Productos");
            productButton.addClickListener(e -> {
                getUI().get().getPage().setLocation("/products/" + category.getId());

            });
            return productButton;
        });
        grid.setItems(Controller.getInstance().getAplicacion().getCategorys());
        add(grid);
        HorizontalLayout buttons = new HorizontalLayout(this.nuevo);
        nuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add( buttons);
    }
}
