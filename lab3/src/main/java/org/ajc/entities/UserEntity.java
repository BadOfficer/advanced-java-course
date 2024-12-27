package org.ajc.entities;

import org.ajc.annotations.*;

/**
 * Represents a user entity with fields for the name, password, and age.
 * The class uses annotations to enforce validation constraints on these fields.
 */
public class UserEntity {
    /**
     * The name of the user.
     * Cannot be null.
     */
    @StringLength(min = 8, max = 100)
    private String name;

    /**
     * The password of the user.
     * Must have a length between 8 and 16 characters.
     */
    @StringLength(min = 8, max = 16)
    private String password;

    /**
     * The age of the user.
     * Must be between 1 and 100.
     */
    @MinValue(1)
    @MaxValue(100)
    private int age;

    /**
     * Constructs a new UserEntity object.
     *
     * @param name     the name of the user
     * @param password the password of the user
     * @param age      the age of the user
     */
    public UserEntity(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the age of the user.
     *
     * @return the age of the user
     */
    public int getAge() {
        return age;
    }
}
