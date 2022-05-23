package pedro.ieslaencanta.com.poolstore.product;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.StatusChangeEvent;
import com.vaadin.flow.data.binder.StatusChangeListener;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import java.util.logging.Level;
import java.util.logging.Logger;
import pedro.ieslaencanta.com.poolstore.Main;
import pedro.ieslaencanta.com.poolstore.Controller;
import pedro.ieslaencanta.com.poolstore.model.Category;
import pedro.ieslaencanta.com.poolstore.model.Product;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "product/:category/:product?", layout = Main.class)
public class ProductEditView extends FormLayout implements BeforeEnterObserver, StatusChangeListener {

    private TextField name;
    private NumberField price;
    private IntegerField stock;
    private IntegerField minstock;
    private TextArea description;

    private Controller p;
    private Product item;
    private Binder<Product> binder;
    private Button guardar;
    private Button cancelar;
    private Integer product_id;
    private String category_id;

    public ProductEditView() {

        this.p = Controller.getInstance();
        this.init();
        this.createButtons();
        HorizontalLayout buttons = new HorizontalLayout(guardar, cancelar);
        VerticalLayout formulario = new VerticalLayout(this.name, this.stock, this.minstock, this.description);

        guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(this.name, this.stock, this.price, this.minstock, this.description, buttons);

    }

    private void bindForm() {
        binder.forField(name).withValidator(
                value -> value.length() > 3,
                "El nombre ha de ser de longitud mayor de 0");
        binder.forField(stock)
                .withConverter(String::valueOf, Integer::valueOf, "")
                .withValidator(
                        value -> Integer.valueOf(value) >= 0,
                        "El stock ha de ser mayor o igual que 0");
        binder.forField(price)
                .withConverter(String::valueOf, Double::valueOf, "")
                .withValidator(
                        value -> Double.valueOf(value) > 0,
                        "El precio ha de ser mayor que 0");//.bind(Product::getStock, Product::setStock);
        binder.forField(minstock).
                withConverter(String::valueOf, Integer::valueOf, "").withValidator(
                value -> Integer.valueOf(value) >= 0,
                "El stock minimo ha de ser mayor o igual que 0");;//.bind(Product::getStock, Product::setStock);
        binder.forField(description).withValidator(
                value -> value.length() > 3,
                "El nombre ha de ser de longitud mayor de 0");
        binder.bindInstanceFields(this);
    }

    private void init() {
        this.name = new TextField("Name");
        // this.name.setRequired(true);
        this.price = new NumberField("Price");
        this.price.setRequiredIndicatorVisible(true);

        this.stock = new IntegerField("Stock");
        this.minstock = new IntegerField("Min Stock");
        //  this.minstock.setRequiredIndicatorVisible(true);
        this.description = new TextArea("Description");
        // this.description.setRequired(true);
    }

    private void createButtons() {
        this.guardar = new Button("Save", e -> {
            try {
                //se ve si el formulario es correcto y se gualda
                if (binder.isValid()) {
                    binder.writeBean(item);
                    //en caso de no tener id;
                    if (item.getId() == -1) {
                        Category c = this.p.getAplicacion().getCategory(Integer.valueOf(this.category_id));
                        this.p.getAplicacion().addProduct(c, item);
                    }
                    UI.getCurrent().getPage().getHistory().back();
                } else {
                    this.openDialogError();
                }

// UI.getCurrent().getPage().getHistory().back();
            } catch (ValidationException ex) {
                Logger.getLogger(ProductEditView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ProductEditView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.guardar.setEnabled(false);

        this.cancelar = new Button("Cancel", e -> {
            binder.readBean(item);
            UI.getCurrent().getPage().getHistory().back();
        });
    }

    public void openDialogError() {
        Dialog dialog = new Dialog();

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.add(cancelButton);

        Button button = new Button("Show dialog", e -> dialog.open());

        add(dialog);
    }

    /**
     * se evalua la URL y se obtiene el producto si es posible
     *
     * @param event
     */
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        this.category_id = event.getRouteParameters().get("category").get();
        String tempo = event.getRouteParameters().get("product").orElse(null);
        if (tempo != null) {
            this.product_id = Integer.valueOf(tempo);
        } else {
            this.product_id = null;
        }

        //se obtiene si se puede el elemento
        Category c = this.p.getAplicacion().getCategory(Integer.valueOf(this.category_id));
        if (c != null) {
            if (this.product_id != null) {

                this.item = c.getProduct(product_id);
            }
            if (this.item == null) {
                this.item = new Product();
            }

        }
        this.binder = new BeanValidationBinder<>(Product.class);

        //ahora se enlaza
        this.binder.setBean(this.item);
        this.bindForm();
        //se escuchan los cambios
        this.binder.addStatusChangeListener(this);
    }

    @Override
    public void statusChange(StatusChangeEvent sce) {
        Logger.getLogger(ProductEditView.class.getName()).log(Level.SEVERE, "a ver si funciona" + this.binder.isValid());

        if (this.binder.isValid()) {
            this.guardar.setEnabled(true);
        } else {
            this.guardar.setEnabled(false);
        }
    }

}
