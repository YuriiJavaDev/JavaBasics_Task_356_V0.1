# Notification System: Functional Interfaces (JavaBasics_Task_356_V0.1)

## 📖 Description
Modern Java development prioritizes conciseness and readability. This project introduces **Functional Interfaces** and **Lambda Expressions**. By using the `@FunctionalInterface` annotation, we explicitly define a contract with exactly one abstract method. Instead of writing verbose concrete classes for every notification type, we use lambdas to implement the `MessagePrinter` logic inline. This approach is essential for event-driven programming and functional data processing in Java.

## 📋 Requirements Compliance
- **Functional Contract**: Declared the `MessagePrinter` interface with a single abstract method.
- **Annotation Usage**: Applied `@FunctionalInterface` to ensure architectural integrity and compiler-level checks.
- **Lambda Implementation**: Realized the printing logic using a compact lambda expression `(message -> ...)`.
- **Dynamic Execution**: Demonstrated how the interface variable can execute the assigned lambda logic with specific data.

## 🚀 Architectural Stack
- Java 8+ (Functional Interfaces, Lambda Expressions, Annotations)

## 🏗️ Implementation Details
- **MessagePrinter**: The functional interface serving as the contract for all notification modules.
- **NotificationLauncherApp**: The entry point demonstrating the inline implementation of business logic.

## 📋 Expected result
```text
Hello, Java!
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_356/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── NotificationLauncherApp.java
    │                 └── notifications/
    │                     └── contracts/
    │                         └── MessagePrinter.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.notifications.contracts.MessagePrinter;

public class NotificationLauncherApp {

    public static void main(String[] args) {

        MessagePrinter printer = System.out::println;
     // MessagePrinter printer = message -> System.out.println(message);

        printer.print("Hello, Java!");
    }
}
```
```java
package com.yurii.pavlenko.notifications.contracts;

@FunctionalInterface
public interface MessagePrinter {
    void print(String message);
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
