package com.example.deviceshop.sales;

import com.example.deviceshop.model.request.SaleRequest;
import com.example.deviceshop.model.request.ProductRequest;
import com.example.deviceshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class SealController {

        private final SaleService saleService;
        private final ProductRepository productRepository;

        // Display the form to create a new sale

        @GetMapping("/createSale")
        public String showSaleForm(Model model) {
//            SaleEntity saleEntity = new SaleEntity();
//            saleEntity.setId(System.currentTimeMillis());
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("saleRequest",new SaleRequest());

            return "saleForm";
        }


    // Save sale information and generate cash memo
    @PostMapping("/createSale")
    public String createSale(
            @ModelAttribute("saleRequest") SaleRequest saleRequest,
            ProductRequest productRequest,
            Model model
    ) {
        try {
            saleService.saveCashMemo(saleRequest, productRequest); // Save sale request
            model.addAttribute("successMessage", "Cash memo created successfully!");
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            // Log the error message for troubleshooting
            System.out.println("Error: " + e.getMessage());
        }
        return "cashMemoView";
    }



    // Retrieve and display all sales
        @GetMapping("/sales")
        public String getAllSales(Model model) {
            List<SaleEntity> sales = saleService.getAllInfo();
            model.addAttribute("sales", sales);
            return "cashMemoView";
        }


}
