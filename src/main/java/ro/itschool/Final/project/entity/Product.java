package ro.itschool.Final.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private float price;

    private String notesDescription;

    private Integer quantity;

    private String image;
    private Boolean deleted;

    public Product(String name, float price, Boolean deleted) {
        this.name = name;
        this.price = price;
        this.deleted = deleted;
    }

    public Product(String name, float price, String notesDescription, Integer quantity, String image, Boolean deleted) {
        this.name = name;
        this.price = price;
        this.notesDescription = notesDescription;
        this.quantity = quantity;
        this.image = image;
        this.deleted = deleted;
    }

    public Product(String name, float price, Integer quantity, Boolean deleted) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.deleted = deleted;
    }

    public Product(String name, float price, String notesDescription, Integer quantity, Boolean deleted) {
        this.name = name;
        this.price = price;
        this.notesDescription = notesDescription;
        this.quantity = quantity;
        this.deleted = deleted;
    }
}
