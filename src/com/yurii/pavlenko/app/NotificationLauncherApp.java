package com.yurii.pavlenko.app;

import com.yurii.pavlenko.notifications.contracts.MessagePrinter;

/**
 * Demonstrates the power of lambda expressions in a notification context.
 */
public class NotificationLauncherApp {

    public static void main(String[] args) {
        // Implementing the interface "on the fly" using a lambda expression.
        // We don't need a concrete class; the logic is defined right here.
        MessagePrinter printer = System.out::println;

        // Executing the contract
        printer.print("Hello, Java!");
    }
}