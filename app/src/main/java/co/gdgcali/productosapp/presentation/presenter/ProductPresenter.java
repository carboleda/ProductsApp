package co.gdgcali.productosapp.presentation.presenter;

import co.gdgcali.productosapp.domain.interactor.ProductInteractor;
import co.gdgcali.productosapp.domain.interactor.impl.ProductInteractorImpl;
import co.gdgcali.productosapp.domain.model.Product;

/**
 * Created by krlosf on 28/10/16.
 */

public class ProductPresenter {
    public interface View {
        void showProgress();
        void hideProgress();
        void showProduct(Product product);
        void showErrorMessage(String msg);
        void showSuccessMessage(String msg);
    }

    private View view;
    private ProductInteractor productInteractor;

    public ProductPresenter(View view) {
        this.view = view;
        productInteractor = new ProductInteractorImpl();
    }

    public void createOrUpdate(String name, String description, String imagePath) {
        view.showProgress();

        Product product = new Product(0, name, description, imagePath);

        productInteractor.createOrUpdate(product, new ProductInteractor.Callback() {
            @Override
            public void success(Object result) {
                view.hideProgress();

                view.showSuccessMessage(null);
            }

            @Override
            public void error(Throwable error) {
                view.hideProgress();

                view.showErrorMessage(null);
            }
        });
    }
}
