package co.gdgcali.productosapp.repository;

import java.util.List;

import co.gdgcali.productosapp.domain.model.Product;

/**
 * Created by krlosf on 21/10/16.
 */

public interface ProductRepository {

    void create(Product product) throws Exception;

    void update(Product product) throws Exception;

    void createOrUpdate(Product product) throws Exception;

    void delete(Product product) throws Exception;

    Product loadProduct(int id) throws Exception;

    List<Product> loadProducts() throws Exception;
}
