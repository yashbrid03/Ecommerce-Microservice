package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Product;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> findAll() {

        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
    }

    public Product findById(Integer productId) {
        try {
            String sql = "SELECT * FROM products WHERE product_id = ?";
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Product.class), productId);
        } catch (Exception e) {
            return null;
        }

    }

    public List<Product> findByCategory(Integer categoryId) {
        String sql = "SELECT * FROM products WHERE category_id = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class), categoryId);
    }

    public int save(Product product) {
        String sql = "INSERT INTO products (name, description, price, quantity, image_url, category_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(),
                product.getQuantity(), product.getImageUrl(), product.getCategoryId(), product.getCreatedAt(),
                product.getUpdatedAt());
    }

    public void deleteById(Integer productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        jdbcTemplate.update(sql, productId);
    }

    public int update(Product product) {
        String sql = "UPDATE products SET name=?, description=?, price=?, quantity=?, image_url=?, category_id=?, updated_at=? WHERE product_id = ?;";
        return jdbcTemplate.update(sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                product.getCategoryId(),
                product.getUpdatedAt(),
                product.getProductId());
    }

}
