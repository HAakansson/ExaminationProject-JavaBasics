package com.niklas;

import java.io.Serializable;

/**
 * <h1 style="color:goldenrod;">The Abstract Employee Class</h1>
 * <p>
 *      Employee is the abstract "model class" from which both player and coach inherits. All essential information
 *      about the model classes is stated here.
 * </p>
 *@author Niklas Håkansson
 *
 */
public abstract class Employee implements Serializable {

    private String firstName;
    private String lastName;
    private int age;

    /**
     * <h2 style="color:goldenrod;">The Constructor</h2>
     * <p>A simple constructor which sets the values of the field variables.</p>
     *
     * @param firstName The first name of the employee.
     * @param lastName  The last name of the employee.
     * @param age       The age of the employee.
     */
    public Employee(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * <h2 style="color:goldenrod;">Getter for First Name</h2>
     * <p>A simple get method.</p>
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * <h2 style="color:goldenrod;">Getter for Last Name</h2>
     * <p>A simple get method.</p>
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * <h2 style="color:goldenrod;">Getter for Age</h2>
     * <p>A simple get method.</p>
     *
     * @return The age.
     */
    public int getAge() {
        return age;
    }

    /**
     * <h2 style="color.goldenrod;">The abstract method "Present Yourself".</h2>
     * <p>A method that prints out the info of the employee..</p>
     */
    public abstract void presentYourself();
}
