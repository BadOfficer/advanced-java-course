package org.ajc.entities;

import org.ajc.annotations.*;

/**
 * Represents a product entity with fields for the title, description, and price.
 * The class uses annotations to enforce validation constraints on these fields.
 */
public class ProductEntity {
    /**
     * The title of the product.
     * Cannot be null and must have a length between 10 and 50 characters.
     */
    @NotNull
    @StringLength(min = 10, max = 50)
    private String title;

    /**
     * The description of the product.
     * Cannot be null and must have a length between 10 and 250 characters.
     */
    @NotNull
    @StringLength(min = 10, max = 250)
    private String description;

    /**
     * The price of the product.
     * Cannot be null and must be between 10 and 250.
     */
    @NotNull
    @MinValue(10)
    @MaxValue(250)
    @StringLength(min = 10, max = 250)
    private int price;

    /**
     * Constructs a new ProductEntity object.
     *
     * @param title       the title of the product
     * @param description the description of the product
     * @param price       the price of the product
     */
    public ProductEntity(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
