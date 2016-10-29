package co.gdgcali.productosapp.domain.interactor.impl;

import java.util.List;

import co.gdgcali.productosapp.domain.interactor.ProductInteractor;
import co.gdgcali.productosapp.domain.model.Product;
import co.gdgcali.productosapp.repository.ProductRepository;
import co.gdgcali.productosapp.repository.impl.ProductRepositoryImpl;
import co.gdgcali.productosapp.repository.impl.ProductRespositoryRest;
import co.gdgcali.productosapp.utilities.ThreadExecutor;

/**
 * Created by krlosf on 21/10/16.
 */

public class ProductInteractorImpl implements ProductInteractor {

    private ProductRepository productRepository;
    private ProductRepository productRepositoryRest;

    public ProductInteractorImpl() {
        productRepository = new ProductRepositoryImpl();
        productRepositoryRest = new ProductRespositoryRest();
    }

    @Override
    public void create(final Product product, final Callback callback) {
        //Realizar validaciones de logica de negocio (opcional)
        new ThreadExecutor(new ThreadExecutor.Executor() {
            @Override
            public Object execute() {
                try {
                    productRepository.create(product);
                    return null;
                } catch (Exception e) {
                    return e;
                }
            }

            @Override
            public void finish(Object result) {
                if(result instanceof Throwable) {
                    callback.error((Throwable) result);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void update(final Product product, final Callback callback) {
        //Realizar validaciones de logica de negocio (opcional)
        new ThreadExecutor(new ThreadExecutor.Executor() {
            @Override
            public Object execute() {
                try {
                    productRepository.update(product);
                    return null;
                } catch (Exception e) {
                    return e;
                }
            }

            @Override
            public void finish(Object result) {
                if(result instanceof Throwable) {
                    callback.error((Throwable) result);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void createOrUpdate(final Product product, final Callback callback) {
        //Realizar validaciones de logica de negocio (opcional)
        new ThreadExecutor(new ThreadExecutor.Executor() {
            @Override
            public Object execute() {
                try {
                    productRepository.createOrUpdate(product);
                    return null;
                } catch (Exception e) {
                    return e;
                }
            }

            @Override
            public void finish(Object result) {
                if(result instanceof Throwable) {
                    callback.error((Throwable) result);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void delete(final Product product, final Callback callback) {
        //Realizar validaciones de logica de negocio (opcional)
        new ThreadExecutor(new ThreadExecutor.Executor() {
            @Override
            public Object execute() {
                try {
                    productRepository.delete(product);
                    return null;
                } catch (Exception e) {
                    return e;
                }
            }

            @Override
            public void finish(Object result) {
                if(result instanceof Throwable) {
                    callback.error((Throwable) result);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void loadProduct(final int id, final Callback callback) {
        //Realizar validaciones de logica de negocio (opcional)
        new ThreadExecutor(new ThreadExecutor.Executor() {
            @Override
            public Object execute() {
                try {
                    Product product = productRepository.loadProduct(id);
                    return product;
                } catch (Exception e) {
                    return e;
                }
            }

            @Override
            public void finish(Object result) {
                if(result instanceof Throwable) {
                    callback.error((Throwable) result);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void loadProducts(final Callback callback) {
        //Realizar validaciones de logica de negocio (opcional)
        new ThreadExecutor(new ThreadExecutor.Executor() {
            @Override
            public Object execute() {
                try {
                    List<Product> lstProduct = productRepository.loadProducts();
                    return lstProduct;
                } catch (Exception e) {
                    return e;
                }
            }

            @Override
            public void finish(Object result) {
                if(result instanceof Throwable) {
                    callback.error((Throwable) result);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void syncProducts(final Callback callback) {
        //Realizar validaciones de logica de negocio (opcional)
        new ThreadExecutor(new ThreadExecutor.Executor() {
            @Override
            public Object execute() {
                try {
                    //Obtenemos los productos del servidor
                    List<Product> lstProduct = productRepositoryRest.loadProducts();

                    //Creamos o actualizamos en la base de datos local
                    for (Product product : lstProduct) {
                        productRepository.createOrUpdate(product);
                    }
                    return lstProduct;
                } catch (Exception e) {
                    return e;
                }
            }

            @Override
            public void finish(Object result) {
                if(result instanceof Throwable) {
                    callback.error((Throwable) result);
                } else {
                    callback.success(result);
                }
            }
        }).execute();
    }
}
