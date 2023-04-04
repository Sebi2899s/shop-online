package ro.itschool.Final.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.itschool.Final.project.entity.MyUser;
import ro.itschool.Final.project.entity.Product;
import ro.itschool.Final.project.repository.ProductRepository;
import ro.itschool.Final.project.service.UserService;
import ro.itschool.Final.project.service.impl.ShoppingCartService;
import ro.itschool.Final.project.util.Constants;

import java.util.Optional;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;



    @RequestMapping(value = {"/all"})
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("products", productRepository.findByDeletedIsFalse());
        return "products";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        shoppingCartService.deleteProductByIdFromShoppingCart(id);
        productRepository.deleteById(id);
        return Constants.REDIRECT_TO_PRODUCTS;
    }

    @RequestMapping(value = "/add/{id}")
    public String addProductToShoppingCart(@PathVariable Integer id) {
        //searching the product after id
        Optional<Product> optionalProduct = productRepository.findById(id);

        //get username of user auth
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        //get the user from db
        MyUser userByUserName = userService.findUserByUserName(currentPrincipalName);

        //in user cart we will add the product sent from frontend
        optionalProduct.ifPresent(product -> {
            userByUserName.getShoppingCart().addProductToShoppingCart(product);
            userService.updateUser(userByUserName);
        });
        return Constants.REDIRECT_TO_PRODUCTS;
    }

    @GetMapping(value = "/add-new")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping(value = "/add-new")
    public String addProduct(@ModelAttribute("product") @RequestBody Product product) {
        product.setDeleted(false);
        productRepository.save(product);
        return Constants.REDIRECT_TO_PRODUCTS;
    }

}
