package com.koakh;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * Generates entities and DAOs for the example project DaoExample.
 * Run it as a Java application (not Android).
 */
public class DaoGenerator {

  public static void main(String[] args) throws Exception {
    Schema schema = new Schema(1000, "com.koakh.greendaostartup.model");

    addNote(schema);
    addCustomerOrder(schema);

    //use . to get current directory
    //ex Written C:\Users\mario\Desktop\myData\Development\AndroidStudioProjects\GreenDaoStartup\com\koakh\daostartup\Order.java
    new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "app/src/main/java");
  }

  private static void addNote(Schema schema) {
    Entity note = schema.addEntity("Note");
    note.addIdProperty();
    note.addStringProperty("text").notNull();
    note.addStringProperty("comment");
    note.addDateProperty("date");
  }

  private static void addCustomerOrder(Schema schema) {
    Entity customer = schema.addEntity("Customer");
    customer.addIdProperty();
    customer.addStringProperty("name").notNull();

    Entity order = schema.addEntity("Order");
    order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
    order.addIdProperty();
    Property orderDate = order.addDateProperty("date").getProperty();
    Property customerId = order.addLongProperty("customerId").notNull().getProperty();
    order.addToOne(customer, customerId);

    ToMany customerToOrders = customer.addToMany(order, customerId);
    customerToOrders.setName("orders");
    customerToOrders.orderAsc(orderDate);
  }

}
