package pedro.ieslaencanta.com.poolstore;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import pedro.ieslaencanta.com.poolstore.categories.CategoriesView;
import pedro.ieslaencanta.com.poolstore.product.ProductsView;

/**
 * The main view contains a button and a click listener.
 */
//@PWA(name = "PoolStore", shortName = "PoolStore")
@Route
public class Main extends AppLayout implements RouterLayout, BeforeEnterObserver {

    public Main() {

        createHeader();
        createDrawer();

    }

    private void createHeader() {
        H1 logo = new H1("PooStore");
        logo.addClassNames("text-l", "m-m");
        Image i = new Image("images/pool.png", "logo");
        //logo.add(i);

        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                i,
                logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);

    }

    private void createDrawer() {
        RouterLink categoryLink = new RouterLink("Categorias", CategoriesView.class);
        RouterLink listLink = new RouterLink("Productos", ProductsView.class);

        RouterLink exitLink = new RouterLink("Salir", Login.class);

        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                categoryLink,
                listLink,
                exitLink
        ));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent bee) {

        if (!Controller.getInstance().islogged()) {
            bee.rerouteTo(Login.class);
            // getUI().get().getPage().setLocation("/login");

        }
    }

}
