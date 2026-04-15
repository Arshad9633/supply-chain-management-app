package com.supplychain.backend.service;

import com.supplychain.backend.dto.SupplierRequest;
import com.supplychain.backend.dto.SupplierResponse;
import com.supplychain.backend.exception.ResourceNotFoundException;
import com.supplychain.backend.model.Supplier;
import com.supplychain.backend.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierResponse createSupplier(SupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(request.getSupplierName());
        supplier.setCountry(request.getCountry());
        supplier.setLeadTimeDays(request.getLeadTimeDays());
        supplier.setRating(request.getRating());

        Supplier savedSupplier = supplierRepository.save(supplier);
        return mapToResponse(savedSupplier);
    }

    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SupplierResponse getSupplierById(String id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        return mapToResponse(supplier);
    }

    public SupplierResponse updateSupplier(String id, SupplierRequest request) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        supplier.setSupplierName(request.getSupplierName());
        supplier.setCountry(request.getCountry());
        supplier.setLeadTimeDays(request.getLeadTimeDays());
        supplier.setRating(request.getRating());

        Supplier updatedSupplier = supplierRepository.save(supplier);
        return mapToResponse(updatedSupplier);
    }

    public void deleteSupplier(String id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    private SupplierResponse mapToResponse(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getSupplierName(),
                supplier.getCountry(),
                supplier.getLeadTimeDays(),
                supplier.getRating()
        );
    }
}