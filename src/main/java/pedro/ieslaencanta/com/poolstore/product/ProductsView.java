package pedro.ieslaencanta.com.poolstore.product;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedro.ieslaencanta.com.poolstore.Main;
import pedro.ieslaencanta.com.poolstore.Controller;
import pedro.ieslaencanta.com.poolstore.model.Product;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "/products/:category_id?", layout = Main.class)

public class ProductsView extends VerticalLayout implements BeforeEnterObserver, RouterLayout {

    Grid<Product> grid;
    private Controller p;
    Button nuevo;
    Integer category_id;

    public ProductsView() {
        this.p = Controller.getInstance();
        this.setAlignItems(Alignment.CENTER);
        this.nuevo = new Button("Nuevo");
        this.nuevo.addClickListener(listener -> {
            getUI().get().getPage().setLocation("product/" + this.category_id);

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
                getUI().get().getPage().setLocation("/product/" + p.getCategory().getId() + "/" + p.getId());

            });
            return editButton;
        });
        grid.addComponentColumn(pr -> {
            Button deleteButton = new Button("Borrar");
            deleteButton.addClickListener(e -> {
               
                     Logger.getLogger(ProductsView.class.getName()).log(Level.SEVERE, pr.getCategory().getId()+" ------------"+pr.getId());
               Logger.getLogger(ProductsView.class.getName()).log(Level.SEVERE,  p.getAplicacion().getCategory(pr.getCategory().getId()).toString());
            
                    p.getAplicacion().getCategory(pr.getCategory().getId()).removeProduct(pr);
                    //  PCategory d=Principal.getInstance().getAplicacion().removeCategory(category);
                    grid.getDataProvider().refreshAll();
                    //  Notification.show(Principal.getInstance().getAplicacion().getCategorys().size()+" -"+category.getId());
                
            });
            return deleteButton;
        });

        //  grid.setItems(Principal.getInstance().getAplicacion().getCategorys());
        add(grid);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String tempo = event.getRouteParameters().get("category_id").orElse(null);
        //si recibe algún parámetro
        if (tempo != null) {
            this.category_id = Integer.valueOf(tempo);
            this.grid.setItems(this.p.getAplicacion().getCategory(Integer.valueOf(category_id)).getProducts().values());
            //solo se añade el botón si viene de la categoria
            HorizontalLayout buttons = new HorizontalLayout(this.nuevo);
            nuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            add(buttons);
        } else {
            this.grid.setItems(this.p.getAplicacion().getAllProducts());
        }

    }
}
