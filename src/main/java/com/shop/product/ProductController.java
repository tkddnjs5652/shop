package com.shop.product;

import com.shop.account.CurrentUser;
import com.shop.domain.Account;
import com.shop.domain.OrdersProduct;
import com.shop.domain.Product;
import com.shop.ordersProduct.OrdersProductForm;
import com.shop.ordersProduct.OrdersProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ProductController {

    static final String ROOT = "/";
    static final String BOARD = "board";
    static final String MAIN = "/main";
    static final String PRODUCT = "/product";
    static final String ADD = "/add";

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final OrdersProductService ordersProductService;



    @GetMapping(PRODUCT + "/{path}")
    public String viewProductForm(@CurrentUser Account account, Model model, OrdersProduct ordersProduct,
                                  @PathVariable Long path) {
        model.addAttribute(account);
        model.addAttribute(productRepository.findByPath(path));
        model.addAttribute(new OrdersProductForm(ordersProduct));
        return BOARD + PRODUCT;
    }

    @PostMapping(PRODUCT + "/{path}")
    public String viewProduct(@CurrentUser Account account, Model model, @PathVariable Long path,
                              RedirectAttributes redirectAttributes,
                              @Valid @ModelAttribute OrdersProductForm ordersProductForm, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute(account);
            model.addAttribute(productRepository.findByPath(path));
            return BOARD + PRODUCT + "/{path}";
        }
        Product product = productRepository.findByPath(path);

        model.addAttribute(productRepository.findByPath(path));
        OrdersProduct ordersProduct = ordersProductService.saveNewOrdersProduct(ordersProductForm);
        ordersProductService.saveProduct(account, ordersProduct, product);

        redirectAttributes.addFlashAttribute("message", "장바구니에 추가되었습니다");
        return "redirect:" + ROOT + BOARD + PRODUCT + "/{path}";
    }


    @GetMapping(MAIN)
    public String viewProductList(@CurrentUser Account account, Model model) {
        List<Product> products = productRepository.findAllByOrderByStartDateDesc();
        model.addAttribute("products", products);
        model.addAttribute(account);
        return BOARD + MAIN;
    }

    @GetMapping(ADD)
    public String addProductForm(@CurrentUser Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new ProductForm());
        return BOARD + ADD;
    }
    @PostMapping(ADD)
    public String addProduct(@CurrentUser Account account, Model model, @Valid @ModelAttribute ProductForm productForm
                             ,Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute(account);
            return BOARD + ADD;
        }
        Product product = productService.saveNewProduct(productForm);
        productService.uploadData(product);
        return "redirect:" + ROOT + BOARD + MAIN;
    }


}
