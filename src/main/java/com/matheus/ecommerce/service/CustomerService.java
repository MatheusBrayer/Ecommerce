package com.matheus.ecommerce.service;

import com.matheus.ecommerce.dto.CustomerRequestDTO;
import com.matheus.ecommerce.dto.CustomerResponseDTO;
import com.matheus.ecommerce.entity.Customer;
import com.matheus.ecommerce.mapper.CustomerMapper;
import com.matheus.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));
        return CustomerMapper.toDTO(customer);
    }

    public CustomerResponseDTO saveCustomer(CustomerRequestDTO dto) {
        Customer customer = CustomerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
        return CustomerMapper.toDTO(saved);
    }

    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPassword(dto.getPassword());
        existing.setAddress(dto.getAddress());
        Customer updated = customerRepository.save(existing);
        return CustomerMapper.toDTO(updated);
    }

    public String deleteCustomer(Long id) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));
        customerRepository.delete(existing);
        return "Cliente deletado com sucesso!";
    }
}