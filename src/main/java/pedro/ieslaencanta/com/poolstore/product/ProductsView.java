package pedro.ieslaencanta.com.poolstore.product;

import pedro.ieslaencanta.com.poolstore.categories.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import pedro.ieslaencanta.com.poolstore.Principal;
import pedro.ieslaencanta.com.poolstore.model.Category;
import pedro.ieslaencanta.com.poolstore.model.Product;

/**
 * The main view contains a button and a click listener.
 */
@Route("/products/:category_name")
public class ProductsView extends VerticalLayout implements BeforeEnterObserver{

    Grid<Product> grid;
    private Principal p;
    Button nuevo;
    String category_name;
    public ProductsView() {
        this.p= Principal.getInstance();
        this.setAlignItems(Alignment.CENTER);
        this.nuevo = new Button("Nuevo");
        this.nuevo.addClickListener(listener -> {
            getUI().get().getPage().setLocation("product/"+this.category_name);

        });
        grid = new Grid<>(Product.class, false);
        grid.addColumn(Product::getId).setHeader("Identificador");
        grid.addColumn(Product::getName).setHeader("Name");
        grid.addColumn(Product::getPrice).setHeader("Precio");
        grid.addColumn(Product::getStock).setHeader("Stock");
        grid.addColumn(Product::getMinstock).setHeader("Min Stock");
        grid.addComponentColumn(p -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                getUI().get().getPage().setLocation("/product/" + p.getName());

            });
            return editButton;
        });
        grid.addComponentColumn(p -> {
            Button deleteButton = new Button("Borrar");
            deleteButton.addClickListener(e -> {
               
              //  PCategory d=Principal.getInstance().getAplicacion().removeCategory(category);
                grid.getDataProvider().refreshAll();
              //  Notification.show(Principal.getInstance().getAplicacion().getCategorys().size()+" -"+category.getId());
            });
            return deleteButton;
        });
       
      //  grid.setItems(Principal.getInstance().getAplicacion().getCategorys());
        add(grid);
        HorizontalLayout buttons = new HorizontalLayout(this.nuevo);
        nuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add( buttons);
    }
    
     @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.category_name=event.getRouteParameters().get("category_name").get();
        this.grid.setItems(this.p.getAplicacion().getCategory(category_name).getProducts().values());
      
    }
}
