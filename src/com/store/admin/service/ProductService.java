package com.store.admin.service;

import com.store.admin.dao.ProductDAO;
import com.store.admin.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
