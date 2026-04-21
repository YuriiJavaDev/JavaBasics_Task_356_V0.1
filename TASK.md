### Imagine you're building a flexible notification system for your application. You want to be able to quickly define how a message will be displayed without creating a full-fledged class for each notification type.

#### - Declare a special "contract" for printing messages—the MessagePrinter interface. It must have exactly one abstract method: void print(String message), which accepts the message text. Mark this interface with the @FunctionalInterface annotation to indicate that it is intended for compact implementations.

#### - In the main body of your program, create a MessagePrinter variable and assign it a lambda expression. This lambda expression should simply accept the passed message and print it to the screen. Then call this method, passing in the string "Hello, Java!", and test that your notification works.

```java
public class NotificationLauncherApp {
    public static void main(String[] args) {
        // Create an interface variable and assign it a lambda expression,
        // which simply prints the received message
        MessagePrinter printer = message -> System.out.println(message);

        // Call the method and display the message on the screen
        printer.print("Hello, Java!");
    }
}
```
