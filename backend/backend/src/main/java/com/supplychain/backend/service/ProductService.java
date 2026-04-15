package com.supplychain.backend.service;

import com.supplychain.backend.dto.ProductRequest;
import com.supplychain.backend.dto.ProductResponse;
import com.supplychain.backend.exception.ResourceNotFoundException;
import com.supplychain.backend.model.Product;
import com.supplychain.backend.repository.ProductRepository;
import com.supplychain.backend.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    public ProductResponse createProduct(ProductRequest request) {
        if (!supplierRepository.existsById(request.getSupplierId())) {
            throw new ResourceNotFoundException("Supplier not found with id: " + request.getSupplierId());
        }

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setUnitCost(request.getUnitCost());
        product.setSupplierId(request.getSupplierId());

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapToResponse(product);
    }

    public List<ProductResponse> getProductsBySupplierId(String supplierId) {
        return productRepository.findBySupplierId(supplierId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponse updateProduct(String id, ProductRequest request) {
        if (!supplierRepository.existsById(request.getSupplierId())) {
            throw new ResourceNotFoundException("Supplier not found with id: " + request.getSupplierId());
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setUnitCost(request.getUnitCost());
        product.setSupplierId(request.getSupplierId());

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getCategory(),
                product.getUnitCost(),
                product.getSupplierId()
        );
    }
}