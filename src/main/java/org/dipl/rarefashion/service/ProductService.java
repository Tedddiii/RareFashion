package org.dipl.rarefashion.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dipl.rarefashion.entity.Product;
import org.dipl.rarefashion.repository.ProductsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductsRepository productsRepository;

    public void savePoduct(Product product) {
        Product p = productsRepository.save(product);
        log.info("Saved product: {}", p);
    }

    public Product getProductById(Integer id) {
        return productsRepository.findById(id).get();
    }

}