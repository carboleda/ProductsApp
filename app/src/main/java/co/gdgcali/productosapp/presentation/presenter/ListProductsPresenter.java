package co.gdgcali.productosapp.presentation.presenter;

import java.util.List;

import co.gdgcali.productosapp.domain.interactor.ProductInteractor;
import co.gdgcali.productosapp.domain.interactor.impl.ProductInteractorImpl;
import co.gdgcali.productosapp.domain.model.Product;

/**
 * Created by krlosf on 21/10/16.
 */

public class ListProductsPresenter {
    public interface View {
        void showProgress();
        void hideProgress();
        void showProducts(List<Product> lstProducts);
        void showErrorMessage(String msg);
        void showSuccessMessage(String msg);
        void goToProductDetail();
    }

    private View view;
    private ProductInteractor productInteractor;

    public ListProductsPresenter(View view) {
        this.view = view;
        productInteractor = new ProductInteractorImpl();
    }

    public void loadProducts() {
        view.showProgress();

        productInteractor.loadProducts(new ProductInteractor.Callback() {
            @Override
            public void success(Object result) {
                view.showProducts((List<Product>) result);

                view.hideProgress();
            }

            @Override
            public void error(Throwable error) {
                view.hideProgress();

                view.showErrorMessage(error.getMessage());
            }
        });


    }
}
