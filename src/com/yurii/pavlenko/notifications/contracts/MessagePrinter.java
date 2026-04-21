package com.yurii.pavlenko.notifications.contracts;

/**
 * Contract for a simple message printing operation.
 * Marked as a functional interface to allow lambda expression implementations.
 */
@FunctionalInterface
public interface MessagePrinter {
    /**
     * Prints the provided message.
     * @param message The text to be displayed.
     */
    void print(String message);
}