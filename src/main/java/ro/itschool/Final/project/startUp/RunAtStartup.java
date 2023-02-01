package ro.itschool.Final.project.startUp;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.Final.project.entity.MyUser;
import ro.itschool.Final.project.entity.Product;
import ro.itschool.Final.project.entity.Role;
import ro.itschool.Final.project.entity.ShoppingCart;
import ro.itschool.Final.project.repository.ProductRepository;
import ro.itschool.Final.project.repository.RoleRepository;
import ro.itschool.Final.project.repository.UserRepository;
import ro.itschool.Final.project.service.impl.ShoppingCartService;
import ro.itschool.Final.project.service.UserService;
import ro.itschool.Final.project.util.Constants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RunAtStartup {

    private final UserService userService;

    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;


    private final ShoppingCartService shoppingCartService;

    private final UserRepository userRepository;


    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        roleRepository.save(new Role(Constants.ROLE_USER));
        roleRepository.save(new Role(Constants.ROLE_ADMIN));

        save10Fragrances();
        saveUser();
        saveAdminUser();
//        save50Products();
    }

    private void saveAdminUser() {
        MyUser myUser = new MyUser();
        myUser.setUsername("admin");
        myUser.setPassword("admin");
        myUser.setRandomToken("randomToken");
        final Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Constants.ROLE_USER));
        roles.add(roleRepository.findByName(Constants.ROLE_ADMIN));
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("admin1@gmail.com");
        myUser.setFullName("Ion Admin");
        myUser.setPasswordConfirm("admin");
        myUser.setRandomTokenEmail("randomToken");

        userService.saveUser(myUser);
    }

    public void saveUser() {
        Faker faker = new Faker();
        MyUser myUser = new MyUser();
        myUser.setUsername("user");
        myUser.setPassword("user");
        myUser.setRandomToken("randomToken");
        final Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Constants.ROLE_USER));
        myUser.setRoles(roles);
        myUser.setEnabled(true);
        myUser.setAccountNonExpired(true);
        myUser.setAccountNonLocked(true);
        myUser.setCredentialsNonExpired(true);
        myUser.setEmail("conttesttest4@gmail.com");
        myUser.setFullName("Ion User");
        myUser.setPasswordConfirm("user");
        myUser.setRandomTokenEmail("randomToken");

        MyUser myUser1 = userService.saveUser(myUser);

        ShoppingCart cart = myUser1.getShoppingCart();


//        ShoppingCart myCart = shoppingCartService.save(cart);

        Product f1 = new Product();
        f1.setName("\"Euphoria\" by Calvin Klein");
        f1.setNotesDescription("floral and fruity scent");
        f1.setPrice(70);
        f1.setQuantity(100);
        f1.setDeleted(faker.bool().bool());

        Product f2 = new Product();
        f2.setName("\"Light Blue\" by Dolce & Gabbana");
        f2.setNotesDescription("citrus and floral scent");
        f2.setPrice(78);
        f2.setQuantity(100);
        f2.setDeleted(faker.bool().bool());


        Product f3 = new Product();
        f3.setName("\"Coco Mademoiselle\" by Chanel");
        f3.setNotesDescription("oriental and floral scent");
        f3.setPrice(138);
        f3.setQuantity(100);
        f3.setDeleted(faker.bool().bool());


        Product f4 = new Product();
        f4.setName("\"Acqua di Gio\" by Giorgio Armani");
        f4.setNotesDescription(" aquatic and floral scent");
        f4.setPrice(92);
        f4.setQuantity(100);
        f4.setDeleted(faker.bool().bool());


        Product f5 = new Product();
        f5.setName("\"Very Irresistible\" by Givenchy");
        f5.setNotesDescription("floral and fruity scent");
        f5.setPrice(70);
        f5.setQuantity(100);
        f5.setDeleted(faker.bool().bool());

        Product f6 = new Product();
        f6.setName("\"Gucci Bloom\"");
        f6.setNotesDescription("floral and woody scent");
        f6.setPrice(100);
        f6.setQuantity(100);
        f6.setDeleted(faker.bool().bool());

        Product f7 = new Product();
        f7.setName("\"Yves Saint Laurent La Nuit de l'Homme\"");
        f7.setNotesDescription("spicy and woody scent");
        f7.setPrice(94);
        f7.setQuantity(100);
        f7.setDeleted(faker.bool().bool());


        Product f8 = new Product();
        f8.setName("Tom Ford Noir");
        f8.setNotesDescription("oriental and spicy scent");
        f8.setPrice(120);
        f8.setQuantity(100);
        f8.setDeleted(faker.bool().bool());

        Product f9 = new Product();
        f9.setName("Hugo Boss Bottled");
        f9.setNotesDescription("woody and spicy scent");
        f9.setPrice(84);
        f9.setQuantity(100);
        f9.setDeleted(faker.bool().bool());

        Product f10 = new Product();
        f10.setName("Versace Eros Flame");
        f10.setNotesDescription("fresh and woody scent");
        f10.setPrice(90);
        f10.setQuantity(100);
        f10.setDeleted(faker.bool().bool());
        productRepository.save(f1);
        productRepository.save(f2);
        productRepository.save(f3);
        productRepository.save(f4);
        productRepository.save(f5);
        productRepository.save(f6);
        productRepository.save(f7);
        productRepository.save(f8);
        productRepository.save(f9);
        productRepository.save(f10);
        List<Product> products = new ArrayList<>();
        products.add(f1);
        products.add(f2);
        products.add(f3);
        products.add(f4);
        products.add(f5);
        products.add(f6);
        products.add(f7);
        products.add(f8);
        products.add(f9);
        products.add(f10);



        cart.setUser(myUser1);
        cart.setProducts(products);
//        myUser1.setShoppingCart(cart);
        userService.updateUser(myUser1);
    }
    public void save10Fragrances() {

        Faker faker = new Faker();
        Product f1 = new Product();
        f1.setName("\"Euphoria\" by Calvin Klein");
        f1.setNotesDescription("floral and fruity scent");
        f1.setPrice(70);
        f1.setQuantity(100);
        f1.setDeleted(faker.bool().bool());

        Product f2 = new Product();
        f2.setName("\"Light Blue\" by Dolce & Gabbana");
        f2.setNotesDescription("citrus and floral scent");
        f2.setPrice(78);
        f2.setQuantity(100);
        f2.setDeleted(faker.bool().bool());


        Product f3 = new Product();
        f3.setName("\"Coco Mademoiselle\" by Chanel");
        f3.setNotesDescription("oriental and floral scent");
        f3.setPrice(138);
        f3.setQuantity(100);
        f3.setDeleted(faker.bool().bool());


        Product f4 = new Product();
        f4.setName("\"Acqua di Gio\" by Giorgio Armani");
        f4.setNotesDescription(" aquatic and floral scent");
        f4.setPrice(92);
        f4.setQuantity(100);
        f4.setDeleted(faker.bool().bool());


        Product f5 = new Product();
        f5.setName("\"Very Irresistible\" by Givenchy");
        f5.setNotesDescription("floral and fruity scent");
        f5.setPrice(70);
        f5.setQuantity(100);
        f5.setDeleted(faker.bool().bool());

        Product f6 = new Product();
        f6.setName("\"Gucci Bloom\"");
        f6.setNotesDescription("floral and woody scent");
        f6.setPrice(100);
        f6.setQuantity(100);
        f6.setDeleted(faker.bool().bool());

        Product f7 = new Product();
        f7.setName("\"Yves Saint Laurent La Nuit de l'Homme\"");
        f7.setNotesDescription("spicy and woody scent");
        f7.setPrice(94);
        f7.setQuantity(100);
        f7.setDeleted(faker.bool().bool());


        Product f8 = new Product();
        f8.setName("Tom Ford Noir");
        f8.setNotesDescription("oriental and spicy scent");
        f8.setPrice(120);
        f8.setQuantity(100);
        f8.setDeleted(faker.bool().bool());

        Product f9 = new Product();
        f9.setName("Hugo Boss Bottled");
        f9.setNotesDescription("woody and spicy scent");
        f9.setPrice(84);
        f9.setQuantity(100);
        f9.setDeleted(faker.bool().bool());

        Product f10 = new Product();
        f10.setName("Versace Eros Flame");
        f10.setNotesDescription("fresh and woody scent");
        f10.setPrice(90);
        f10.setQuantity(100);
        f10.setDeleted(faker.bool().bool());
        Product f11 = new Product();
        f11.setName("\"Aqua Pour Homme\" by Versace");
        f11.setNotesDescription("fresh and aquatic scent");
        f11.setPrice(70);
        f11.setQuantity(100);
        f11.setDeleted(faker.bool().bool());
        Product f12 = new Product();
        f12.setName("\"Terre d'Hermes\" by Hermes");
        f12.setNotesDescription("woody and spicy scent");
        f12.setPrice(115);
        f12.setQuantity(100);
        f12.setDeleted(faker.bool().bool());
        Product f13 = new Product();
        f13.setName("\"Armani Code\" by Giorgio Armani");
        f13.setNotesDescription("oriental and floral scent");
        f13.setPrice(85);
        f13.setQuantity(100);
        f13.setDeleted(faker.bool().bool());
        Product f14 = new Product();
        f14.setName("\"Drakkar Noir\" by Guy Laroche");
        f14.setNotesDescription("spicy and woody scent");
        f14.setPrice(70);
        f14.setQuantity(100);
        f14.setDeleted(faker.bool().bool());
        Product f15 = new Product();
        f15.setName("\"Invictus\" by Paco Rabanne");
        f15.setNotesDescription("fresh and woody scent");
        f15.setPrice(85);
        f15.setQuantity(100);
        f15.setDeleted(faker.bool().bool());

        productRepository.save(f1);
        productRepository.save(f2);
        productRepository.save(f3);
        productRepository.save(f4);
        productRepository.save(f5);
        productRepository.save(f6);
        productRepository.save(f7);
        productRepository.save(f8);
        productRepository.save(f9);
        productRepository.save(f10);
        productRepository.save(f11);
        productRepository.save(f12);
        productRepository.save(f13);
        productRepository.save(f14);
        productRepository.save(f15);
    }
}
