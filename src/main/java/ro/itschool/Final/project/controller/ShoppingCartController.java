package ro.itschool.Final.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.Final.project.entity.MyUser;
import ro.itschool.Final.project.entity.Product;
import ro.itschool.Final.project.repository.OrderRepository;
import ro.itschool.Final.project.repository.ProductRepository;
import ro.itschool.Final.project.service.impl.ShoppingCartService;
import ro.itschool.Final.project.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/to-order")
    public String convertToOrder() {

        //stabilim care e username-ul user-ului autentificat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        //aducem userul din db pe baza username-ului
        MyUser user = userService.findUserByUserName(currentPrincipalName);

        orderRepository.save(shoppingCartService.convertShoppingCartToOrder(user.getShoppingCart()));
        user.getShoppingCart().getProducts().clear();
        userService.updateUser(user);

        return "order-successful";
    }

    @RequestMapping
    public String getShoppingCartForPrincipal(Model model) {
        //stabilim care e username-ul user-ului autentificat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        //aducem userul din db pe baza username-ului
        MyUser userByUserName = userService.findUserByUserName(currentPrincipalName);

        model.addAttribute("products", userByUserName.getShoppingCart().getProducts());

        return "shopping-cart";
    }

    @RequestMapping(value = "/product/remove/{productId}")
    public String removeProductFromShoppingCart(@PathVariable Integer productId) {
        //stabilim care e username-ul user-ului autentificat
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        //aducem userul din db pe baza username-ului
        MyUser userByUserName = userService.findUserByUserName(currentPrincipalName);

        Optional<Product> optionalProduct = productRepository.findById(productId);

//        userByUserName.getShoppingCart().getProducts().removeIf(product -> product.getId().equals(productId));
        userByUserName.getShoppingCart().getProducts().stream().filter(product -> product.getId().equals(productId)).findFirst().ifPresent(product -> userByUserName.getShoppingCart().getProducts().remove(product));
        userService.updateUser(userByUserName);

        return "redirect:/shopping-cart";
    }

}
