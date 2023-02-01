package ro.itschool.Final.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.Final.project.entity.MyUser;
import ro.itschool.Final.project.entity.Order;
import ro.itschool.Final.project.repository.OrderRepository;
import ro.itschool.Final.project.repository.UserRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/get-all/{userId}")
    public List<Order> getAllOrders(@PathVariable Long userId) throws UserPrincipalNotFoundException {
        Optional<MyUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            return optionalUser.get().getOrders();
        else
            //TODO Custom exception
            throw new UserPrincipalNotFoundException("User not found");
    }

}
