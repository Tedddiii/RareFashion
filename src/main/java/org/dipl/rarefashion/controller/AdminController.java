package org.dipl.rarefashion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dipl.rarefashion.controller.temp.ProductsFilter;
import org.dipl.rarefashion.entity.Product;
import org.dipl.rarefashion.repository.CategoriesRepository;
import org.dipl.rarefashion.repository.ProductsRepository;
import org.dipl.rarefashion.service.ProductsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final ProductsService productsService;
    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;

    @GetMapping
    public String adminIndex(Model model) {

        model.addAttribute("activeMenu", "home");
        return "admin/home";
    }

    @GetMapping(path = "/products")
    public String general(Model model) {

        model.addAttribute("activeMenu", "products");
        model.addAttribute("productsList", productsService.getAllProducts());

        return "admin/products";
    }

    @GetMapping(path = "/products/{id}/delete")
    public String deleteProduct(@PathVariable Integer id, Model model) {

        productsService.deleteProduct(id);

        return "redirect:/admin/products";
    }

    @GetMapping(path = "/products/new")
    public String newProduct(@ModelAttribute("productForm") Product productForm, Model model) {

        model.addAttribute("activeMenu", "products");
        model.addAttribute("categoriesList", categoriesRepository.findAll());

        return "admin/newProduct";
    }

    @PostMapping(path = "/products/new")
    public String newProduct(@ModelAttribute("productForm") @Valid Product productForm, BindingResult bindingResult,
                             Model model) {

        log.info("Saving new product: {}", productForm);

        if (bindingResult.hasErrors()) {
            model.addAttribute("activeMenu", "products");
            model.addAttribute("categoriesList", categoriesRepository.findAll());
            return "admin/newProduct";
        }

        Product product = productsRepository.save(productForm);

        log.info("Saved product: {}", product);

        return "redirect:/admin/products";
    }
}
