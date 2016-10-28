package co.gdgcali.productosapp.domain.interactor;

import co.gdgcali.productosapp.domain.model.Product;

/**
 * Created by krlosf on 21/10/16.
 */

public interface ProductInteractor {
    interface Callback {
        void success(Object result);

        void error(Throwable error);
    }

    void create(Product product, Callback callback);

    void update(Product product, Callback callback);

    void delete(Product product, Callback callback);

    void loadProduct(int id, Callback callback);

    void loadProducts(Callback callback);

    void syncProducts(Callback callback);
}
