package pedro.ieslaencanta.com.poolstore;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import pedro.ieslaencanta.com.poolstore.model.Category;

/**
 * The main view contains a button and a click listener.
 */
@Route("main")
public class CategoryEditView extends VerticalLayout {

    Grid<Category> grid;
    Button nuevo;
    public CategoryEditView() {

        this.setAlignItems(Alignment.CENTER);
        this.nuevo= new Button("Nuevo");
        this.nuevo.addClickListener(listener->{
                  getUI().get().getPage().setLocation("/category");
           
        });
        grid = new Grid<>(Category.class, false);
        grid.addColumn(Category::getId).setHeader("Identificador");
        grid.addColumn(Category::getName).setHeader("Name");
        grid.addComponentColumn(category -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                 getUI().get().getPage().setLocation("/category/"+category.getId());
                /* if (editor.isOpen())
            editor.cancel();
        grid.getEditor().editItem(person);*/
            });
            return editButton;
        });
        grid.addComponentColumn(category -> {
            Button deleteButton = new Button("Borrar");
            deleteButton.addClickListener(e -> {
                Principal.getInstance().getAplicacion().removeCategory(category.getId());
                //   getUI().get().getPage().setLocation("/category/"+category.getId());
                /* if (editor.isOpen())
            editor.cancel();
        grid.getEditor().editItem(person);*/
            });
            return deleteButton;
        });
          grid.addComponentColumn(category -> {
            Button editButton = new Button("Productos");
            editButton.addClickListener(e -> {
                 getUI().get().getPage().setLocation("/category/product/"+category.getId());
                /* if (editor.isOpen())
            editor.cancel();
        grid.getEditor().editItem(person);*/
            });
            return editButton;
        });
        //   grid.addColumn(editColumn);
        // List<Person> people = DataService.getPeople();
        grid.setItems(Principal.getInstance().getAplicacion().getCategorys());
        add(grid);
        

        /*  GridLayout grid = new GridLayout(4, 4);
        grid.addStyleName("example-gridlayout");

// Fill out the first row using the cursor.
        grid.addComponent(new Button("R/C 1"));
        for (int i = 0; i < 3; i++) {
            grid.addComponent(new Button("Col "
                    + (grid.getCursorX() + 1)));
        }

// Fill out the first column using coordinates.
        for (int i = 1; i < 4; i++) {
            grid.addComponent(new Button("Row " + i), 0, i);
        }

// Add some components of various shapes.
        grid.addComponent(new Button("3x1 button"), 1, 1, 3, 1);
        grid.addComponent(new Label("1x2 cell"), 1, 2, 1, 3);
        InlineDateField date
                = new InlineDateField("A 2x2 date field");
        date.setResolution(DateField.RESOLUTION_DAY);
        grid.addComponent(date, 2, 2, 3, 3);
        this.add(grid);*/
    }
}
