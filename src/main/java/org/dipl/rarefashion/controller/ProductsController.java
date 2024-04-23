package org.dipl.rarefashion.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dipl.rarefashion.controller.temp.ProductsFilter;
import org.dipl.rarefashion.entity.Product;
import org.dipl.rarefashion.service.ProductService;
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

    private final ProductService productsService;

    @GetMapping(path = "/products/save")
    @ResponseBody
    public String save() {

        //productsService.savePoduct(Product.builder().name("test").build());
        return "OK";
    }

    @GetMapping(path = "/products/{productId}")
    @ResponseBody
    public Product getProduct(@PathVariable Integer productId) {

        return productsService.getProductById(productId);
    }

    @RequestMapping(path = "/products", method = {RequestMethod.GET})
    public String events(@RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                         @RequestParam(name = "i", defaultValue = "20") Integer itemsPerPage,
                         @ModelAttribute("filterForm") ProductsFilter filterForm,
                         Model model) {

/*
        Pageable pageable = PageRequest.of(pageIndex - 1, itemsPerPage);
        PageWrapper<Product> pageWrp = new PageWrapper<>(productsService.getProducts(pageable, filterForm));
        model.addAttribute("page", pageWrp);
        model.addAttribute("activeMenu", "products");
*/

        return "products";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/products/saveProduct")
    public String saveProduct(@ModelAttribute("productForm") @Valid Product productForm,
                              BindingResult bindingResult, Model model) {

        log.info("Saving product request: {}", productForm);

        if (bindingResult.hasErrors()) {
            log.debug("Errors in product form: {}", bindingResult.getAllErrors());
            return "products/fragments :: #divModalProductContent";
        }

        try {
            productsService.savePoduct(productForm);
            model.addAttribute("saveSuccess", true);
        } catch (Exception e) {
            log.error("Error saving product!", e);
            throw e;
        }

        return "products/fragments :: #divModalProductContent";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/products/deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam(name = "productId") Integer productId) {

        log.info("Deleting productId: {}", productId);

/*
        Optional<Product> product = productsRepository.findById(productId);

        if (product.isPresent()) {
            productsRepository.deleteById(productId);
        }
*/

        return "OK";
    }

}
