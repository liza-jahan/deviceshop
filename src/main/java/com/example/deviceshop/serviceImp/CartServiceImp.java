package com.example.deviceshop.serviceImp;

import com.example.deviceshop.entity.CartEntity;
import com.example.deviceshop.entity.UserEntity;
import com.example.deviceshop.product.ProductEntity;
import com.example.deviceshop.repository.CartRepository;
import com.example.deviceshop.repository.ProductRepository;
import com.example.deviceshop.repository.UserRepository;
import com.example.deviceshop.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServiceImp implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private  final ProductRepository productRepository;
    @Override
    public UUID addCart(UUID userId, Long productId) {
        UserEntity userEntity= userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Not found by id"));
        ProductEntity productEntity=productRepository.findById(productId).orElseThrow(() -> new RuntimeException("not found by home details id"));



        CartEntity cartEntity= new CartEntity();
        cartEntity.setUserEntity(userEntity);
        cartEntity.setProductEntity(productEntity);

        cartRepository.save(cartEntity) ;
        return  cartEntity.getId();

    }

    @Override
    public void removeCart(UUID userId, Long productId) {
        Optional<CartEntity> houseFavouriteList =cartRepository.findByIdAndProductEntityId(userId,productId);
        houseFavouriteList.ifPresent(cartRepository::delete);
    }
    public CartEntity getCartFromSession(HttpSession session) {
        CartEntity cart = (CartEntity) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartEntity();
            session.setAttribute("cart", cart);
        }

        return cart;
    }
}
