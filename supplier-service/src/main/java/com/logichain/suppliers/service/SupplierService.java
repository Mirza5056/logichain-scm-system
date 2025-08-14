package com.logichain.suppliers.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logichain.suppliers.model.Suppliers;
import com.logichain.suppliers.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public Suppliers createSupplier(Suppliers suppliers) {
    if(supplierRepository.existsBySupplierName(suppliers.getSupplierName())) {
            throw new RuntimeException("Supplier name already exits.");
        }
        return supplierRepository.save(suppliers);
    }    

    public Suppliers getSupplierById(Long id) {
        return supplierRepository.findSupplierById(id).orElseThrow(()-> new RuntimeException("Inavlid supplier id"));
    }

    @Transactional
    public Suppliers updateSuppliers(Long id,Suppliers suppliersDetails) {
        Suppliers suppliers = getSupplierById(id);
        suppliers.setSupplierName(suppliersDetails.getSupplierName());
        suppliers.setContactEmail(suppliersDetails.getContactEmail());
        suppliers.setPhoneNumber(suppliersDetails.getPhoneNumber());
        suppliers.setRating(suppliersDetails.getRating());
        return supplierRepository.save(suppliers);
    }

    public List<Suppliers> getAllSuppliersList() {
        return supplierRepository.findAll();
    }

    @Transactional
    public void deleteSuppliers(Long id) {
        Suppliers suppliers = getSupplierById(id);
        supplierRepository.deleteById(suppliers.getId());
    }
}
