package org.dipl.rarefashion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dipl.rarefashion.controller.temp.ProductsFilter;
import org.dipl.rarefashion.entity.Product;
import org.dipl.rarefashion.service.ProductsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping(path = "/products/show/{id}")
    public String getProduct(@PathVariable Integer id, Model model) {

        Product product = productsService.getProductById(id);

        model.addAttribute("activeMenu", "products");
        model.addAttribute("product", product);

        return "showProduct";
    }

    @RequestMapping(path = "/products", method = {RequestMethod.GET})
    public String products(Model model) {

        model.addAttribute("activeMenu", "products");
        model.addAttribute("productsList", productsService.getAllProducts());

        return "products";
    }
}
