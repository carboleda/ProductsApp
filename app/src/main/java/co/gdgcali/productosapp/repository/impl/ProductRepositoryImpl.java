package co.gdgcali.productosapp.repository.impl;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.gdgcali.productosapp.MyApplication;
import co.gdgcali.productosapp.database.DBHelper;
import co.gdgcali.productosapp.domain.model.Product;
import co.gdgcali.productosapp.repository.ProductRepository;

/**
 * Created by krlosf on 21/10/16.
 */

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public void create(Product product) throws Exception {
        DBHelper dbHelper = MyApplication.getInstance().getDBHelper();
        try {
            Dao dao = dbHelper.getProductDao();
            dao.create(product);
        } finally {
            MyApplication.getInstance().releaseHelper();
        }
    }

    @Override
    public void update(Product product) throws Exception {
        DBHelper dbHelper = MyApplication.getInstance().getDBHelper();
        try {
            Dao dao = dbHelper.getProductDao();
            dao.update(product);
        } finally {
            MyApplication.getInstance().releaseHelper();
        }
    }

    @Override
    public void createOrUpdate(Product product) throws Exception {
        DBHelper dbHelper = MyApplication.getInstance().getDBHelper();
        try {
            Dao dao = dbHelper.getProductDao();
            dao.createOrUpdate(product);
        } finally {
            MyApplication.getInstance().releaseHelper();
        }
    }

    @Override
    public void delete(Product product) throws Exception {
        DBHelper dbHelper = MyApplication.getInstance().getDBHelper();
        try {
            Dao dao = dbHelper.getProductDao();
            dao.delete(product);
        } finally {
            MyApplication.getInstance().releaseHelper();
        }
    }

    @Override
    public Product loadProduct(int id) throws Exception {
        try {
            DBHelper dbHelper = MyApplication.getInstance().getDBHelper();
            //Buscar el producto en la fuente de datos
            Dao dao = dbHelper.getProductDao();
            Product product = (Product) dao.queryForId(id);

            return product;
        } finally {
            MyApplication.getInstance().releaseHelper();
        }
    }

    @Override
    public List<Product> loadProducts() throws Exception {
        try {
            DBHelper dbHelper = MyApplication.getInstance().getDBHelper();
            //Buscar los productos en la fuente de datos
            Dao dao = dbHelper.getProductDao();
            List<Product> lstProduct = dao.queryForAll();
            //Thread.sleep(3000);

            return lstProduct;
        } finally {
            MyApplication.getInstance().releaseHelper();
        }
    }
}
