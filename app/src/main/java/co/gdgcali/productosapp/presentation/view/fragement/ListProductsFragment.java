package co.gdgcali.productosapp.presentation.view.fragement;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import co.gdgcali.productosapp.R;
import co.gdgcali.productosapp.domain.model.Product;
import co.gdgcali.productosapp.presentation.presenter.ListProductsPresenter;
import co.gdgcali.productosapp.presentation.view.adapter.ProductAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListProductsFragment extends Fragment
        implements ListProductsPresenter.View {

    private ListProductsPresenter presenter;
    private RecyclerView rvProducts;
    private List<Product> lstProducts;
    private MaterialDialog dialog;
    public static final String ACTION_REFRESH_PRODUCTS = "co.gdgcali.productosapp.REFRESH_PRODUCTS";
    private BroadcastReceiver brRefreshProducts = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            presenter.loadProducts();
        }
    };

    public ListProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_products, container, false);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_REFRESH_PRODUCTS);
        getContext().registerReceiver(brRefreshProducts, intentFilter);

        presenter = new ListProductsPresenter(this);
        lstProducts = new ArrayList<>(0);
        dialog = new MaterialDialog.Builder(getContext())
                .title(R.string.procesando)
                .content(R.string.espere)
                .progress(true, 0)
                .build();

        rvProducts = (RecyclerView) view.findViewById(R.id.rvProducts);
        ProductAdapter productAdapter = new ProductAdapter(lstProducts);
        rvProducts.setAdapter(productAdapter);
        rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));

        //Cargamos los productos
        presenter.loadProducts();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(brRefreshProducts);
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.hide();
    }

    @Override
    public void showProducts(List<Product> lstProducts) {
        this.lstProducts.clear();
        this.lstProducts.addAll(lstProducts);

        //Actualizamos el adaptador
        rvProducts.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void showSuccessMessage(String msg) {

    }

    @Override
    public void goToProductDetail() {

    }
}
