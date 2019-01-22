package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.database.ShopDBCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {
    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product;";

        List<Product> resultList = new ArrayList<>();


        try(Connection connection = ShopDBCreator.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Float defaultPrice = resultSet.getFloat("default_price");
                String currencyString = resultSet.getString("currency_string");
                String description = resultSet.getString("description");
                int productCategoryId = resultSet.getInt("product_category_id");
                int supplierId = resultSet.getInt("supplier_id");

                ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
                SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

                ProductCategory productCategory = productCategoryDataStore.find(productCategoryId);
                Supplier supplier = supplierDataStore.find(supplierId);

                Product product = new Product(name, defaultPrice, description, currencyString, productCategory, supplier);
                System.out.println(resultList);
                resultList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;

    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<Product> getByComplex(int productCategoryId, int supplierId) {
        return null;
    }
}
