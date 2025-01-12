package com.example.deviceshop.product;

import com.example.deviceshop.model.request.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String showCreateProductForm(Model model) {
        model.addAttribute("productRequest", new ProductEntity());
        return "productAddedForm";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute("productRequest") ProductRequest productRequest, Model model) {
        System.out.println("Product created: " + productRequest);
        try {
            productService.saveProduct(productRequest);
            model.addAttribute("successMessage", "product registered successfully!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "productAddedForm";
    }


    @GetMapping("/product/edit/{id}")
    public String editDonnerForm(@PathVariable Long id, Model model) {

        model.addAttribute("donnerEdit", productService.getProductDetails(id));
        return "editProductInfo";
    }

    @PutMapping("/product/{id}")
    public String editProductList(@PathVariable Long id, @ModelAttribute("updateRequest") ProductRequest productRequest) {
        productService.updateProductInfo(id, productRequest);
        return "redirect:/viewList";
    }

    @GetMapping("/all-products")
    public String getAllProducts(Model model) {
        List<ProductEntity> products = productService.getAllProduct(); // Fetch product list
        model.addAttribute("products", products);

        return "productCardView";
    }
    @GetMapping("/product/view/{id}")
    public String viewProductDetails(Model model, @PathVariable Long id) {
        ProductEntity product = productService.getProductById(id);
        if (product != null) {
            String imagePath = product.getImage(); // image.jpg"
            product.setImage(imagePath);
        }
        model.addAttribute("product", product);
        return "productDetailsView";
    }



//    @GetMapping("/products/search")
//    public String searchProducts(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
//        List<ProductDto> products = productService.searchProduct(keyword);
//        model.addAttribute("products", products);
//        model.addAttribute("keyword", keyword);
//        return "search"; // The Thymeleaf template name
//    }
}
