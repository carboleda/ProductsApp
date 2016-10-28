package co.gdgcali.productosapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import co.gdgcali.productosapp.domain.interactor.ProductInteractor;
import co.gdgcali.productosapp.domain.model.Product;
import co.gdgcali.productosapp.repository.ProductRepository;
import co.gdgcali.productosapp.repository.impl.ProductRepositoryImpl;

/**
 * Created by krlosf on 22/10/16.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String NAME = "productosapp.db";
    private static final int VERSION = 1;

    private Dao<Product, Integer> productDao;

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //Mapea el objeto java a una tabla de SQLite
            TableUtils.createTable(connectionSource, Product.class);

            //TODO ELIMIANR ESTE CODIGO CUANDO SE IMPLEMENTE LA CREACION DE PRODUCTOS DESDE EL APP
            Product product;
            Dao productDao = getProductDao();

            for (int i = 1; i <= 2; i++) {
                product = new Product(
                        i,
                        "Producto "+i,
                        "Soy el producto "+i);
                productDao.create(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase, connectionSource);
    }

    public Dao<Product, Integer> getProductDao() throws SQLException {
        if(productDao == null) {
            productDao = getDao(Product.class);
        }

        return productDao;
    }

    public void close() {
        super.close();
        productDao = null;
    }
}
