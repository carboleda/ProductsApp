package co.gdgcali.productosapp.presentation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import co.gdgcali.productosapp.R;
import co.gdgcali.productosapp.domain.model.Product;

/**
 * Created by krlosf on 21/10/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> dataSource;

    public ProductAdapter(List<Product> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item_product, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product product = dataSource.get(position);

        holder.tvId.setText("No. "+String.valueOf(product.getId()));
        holder.tvName.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        if(product.getImagePath() != null && !product.getImagePath().isEmpty()) {
            Picasso.with(holder.itemView.getContext())
                    .load(new File(product.getImagePath()))
                    .into(holder.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvName;
        TextView tvDescription;
        ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }
}
