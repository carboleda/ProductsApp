package co.gdgcali.productosapp.presentation.view.fragement;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Date;

import co.gdgcali.productosapp.R;
import co.gdgcali.productosapp.domain.model.Product;
import co.gdgcali.productosapp.presentation.presenter.ProductPresenter;
import co.gdgcali.productosapp.presentation.view.activity.CheckPermissionActivityManager;
import co.gdgcali.productosapp.utilities.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment
        implements ProductPresenter.View,
                    View.OnClickListener {

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    private ProductPresenter presenter;
    private EditText etName;
    private EditText etDescription;
    private ImageView ivImage;
    private Button btnSave;
    private MaterialDialog progressDialog;
    private Bitmap imageBitmap;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        etName = (EditText) view.findViewById(R.id.etName);
        etDescription = (EditText) view.findViewById(R.id.etDescription);
        ivImage = (ImageView) view.findViewById(R.id.ivImage);
        btnSave = (Button) view.findViewById(R.id.btnSave);

        progressDialog = new MaterialDialog.Builder(getContext())
                .title(R.string.procesando)
                .content(R.string.espere)
                .progress(true, 0)
                .build();

        ivImage.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        presenter = new ProductPresenter(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivImage:
                onClickImage(view);
                break;
            case R.id.btnSave:
                onClickSave(view);
                break;
        }
    }

    public void onClickImage(final View view) {
        //TODO GUARDAR IMAGEN
        if(CheckPermissionActivityManager.checkPermissions(getContext(),
                new CheckPermissionActivityManager.CheckingPermissionListener() {
                    @Override
                    public void onResult(boolean isGranted) {
                        if(isGranted) {
                            onClickImage(view);
                        }
                    }
                },
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void onClickSave(View view) {
        try {
            String name = etName.getText().toString();
            String description = etDescription.getText().toString();
            String imagePath = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
            imagePath += "product_"+ new Date().getTime()+".png";

            boolean saved = Utils.saveBitmap(imageBitmap, imagePath);
            if(saved) {
                Log.i("GUARDAR", "=== IMAGEN GUARDADA ===");
            }
            presenter.createOrUpdate(name, description, imagePath);
        } catch (Exception e) {
            showErrorMessage(null);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void showProduct(Product product) {
        etName.setText(product.getName());
        etDescription.setText(product.getDescription());
        //TODO: MOSTRAR IMAGEN
    }

    @Override
    public void showErrorMessage(String msg) {
        Snackbar.make(getView(), R.string.save_product_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessMessage(String msg) {
        Snackbar.make(getView(), R.string.save_product_success, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivImage.setImageBitmap(imageBitmap);
        }
    }
}
