package co.gdgcali.productosapp.repository.impl;

import java.util.List;

import co.gdgcali.productosapp.domain.model.Product;
import co.gdgcali.productosapp.repository.ProductRepository;
import co.gdgcali.productosapp.utilities.RetrofitSingleton;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by krlosf on 26/10/16.
 */

public class ProductRespositoryRest implements ProductRepository {

    public interface ProductService {
        @GET("/Service.php")
        Call<List<Product>> listAllProducts();

        @POST("/Service.php")
        Call<Product> create(@Body Product product);
    }

    @Override
    public void create(Product product) throws Exception {

    }

    @Override
    public void update(Product product) throws Exception {

    }

    @Override
    public void createOrUpdate(Product product) throws Exception {

    }

    @Override
    public void delete(Product product) throws Exception {

    }

    @Override
    public Product loadProduct(int id) throws Exception {
        return null;
    }

    @Override
    public List<Product> loadProducts() throws Exception {
        //Obtenemos la instancia de retrofit
        Retrofit retrofit = RetrofitSingleton.getInstance();
        //Creamos una instancia del Service
        ProductService service = retrofit.create(ProductService.class);
        //Obtener el obtejeto para hacer la llamada
        Call<List<Product>> call = service.listAllProducts();
        //Consumimos el servicio y obtenemos la respuesta de forma sincrona
        Response<List<Product>> lstProducts = call.execute();

        //Retornamos la respuesta
        return lstProducts.body();
    }
}
