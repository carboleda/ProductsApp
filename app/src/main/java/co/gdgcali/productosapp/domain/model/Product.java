package co.gdgcali.productosapp.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by krlosf on 21/10/16.
 */
@DatabaseTable(tableName = "product")
public class Product {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE_PATH = "image_path";

    @DatabaseField(columnName = ID, generatedId = true, canBeNull = false)
    private int id;
    @DatabaseField(columnName = NAME)
    private String name;
    @DatabaseField(columnName = DESCRIPTION)
    private String description;
    @DatabaseField(columnName = IMAGE_PATH)
    private String imagePath;

    public Product() {
    }

    public Product(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Product(int id, String name, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
