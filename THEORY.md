## Functional interfaces: @FunctionalInterface

### 1. Introduction

A **functional interface** is an interface that contains exactly one abstract method (that is, a method without an implementation). Such an interface can be used to concisely write a method implementation—in Java, this is done using lambda expressions (we'll study them later).

**Why only one method?**

Because a functional interface describes exactly one operation. If there were two or more methods, it would be unclear which method should be implemented. Therefore, the rule is simple: **one interface, one abstract method**.

**Standard Library Examples**

- **Runnable** — for tasks in threads (**void run**())
- **Callable<V>** — for tasks that return a result (**V call**())
- **Comparator<T>** — for comparing objects (**int compare**(T o1, T o2))
- **Consumer<T>** — a value consumer (**void accept**(T t))
- **Supplier<T>** — a value supplier (T **get**())
- **Function<T, R>** — a function from T to R (R **apply**(T t))
- **Predicate<T>** — a conditional test (**boolean test**(T t))

Here, for example, is the **Runnable** interface:

```java
public interface Runnable {
    void run();
}
```

And here's the Comparator interface:

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
    // ... there are also default and static methods, but only one abstract method!
}
```

**Important point:** **default** and **static** methods are not considered abstract, so you can have as many of them as you want!

### 2. @FunctionalInterface Annotation

Java is a strict and principled lady. To avoid confusion, it allows you to explicitly mark an interface as functional using the **@FunctionalInterface** annotation. It's like a sticker that says "Only works with one button!"—to prevent anyone from adding unnecessary information.

```java
@FunctionalInterface
public interface Operation {
    int apply(int a, int b);
}
```

Now, if you suddenly forget and add a second abstract method, the compiler will immediately report an error:

```java
@FunctionalInterface
public interface Oops {
    void doIt();
    void doSomethingElse(); // Error! Two abstract methods
}
```

#### Is the annotation required?

No, it's not required. An interface will be functional even without it if it contains exactly one abstract method. But with the annotation, you clearly indicate your intent and protect yourself from accidental errors.

#### Can I add default and static methods?

Yes, I can! The main thing is that there is only **one** abstract method. All other methods can be **default** or **static**, as many as you like.

**Example:**

```java
@FunctionalInterface
public interface FancyOperation {
    int apply(int a, int b);
    
    default void printInfo() {
        System.out.println("I am a fancy operation!");
    }
    
    static void description() {
        System.out.println("A functional interface for arithmetic.");
    }
}
```

### 3. Declaration and Usage Examples

Let's say you want to describe an operation on two numbers. Here's how it's done:

```java
@FunctionalInterface
public interface Operation {
    int apply(int a, int b);
}
```

Now this interface can be implemented in different ways.

#### Implementation via a regular class

```java
public class SumOperation implements Operation {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}
```

**Usage:**

```java
    Operation sum = new SumOperation();
    System.out.println(sum.apply(2, 3)); // 5
```

#### Implementation via an anonymous class

```java
Operation multiply = new Operation() {
    @Override
    public int apply(int a, int b) {
        return a * b;
    }
};
    System.out.println(multiply.apply(2, 3)); // 6
```

#### A Note on Lambdas

Starting with Java 8, such interfaces are conveniently implemented using lambda expressions—a shorter syntax. We'll learn about lambdas in a couple of lectures, so for now it's enough to know that functional interfaces are designed specifically to be as convenient as possible.

### 4. Practice: Write Your Own Functional Interface

#### Task 1. Create Your Own Action!

Create an **Action** interface that accepts a string and returns nothing. Implement it with an anonymous class that prints the string in uppercase.

```java
@FunctionalInterface
interface Action {
    void act(String s);
}

public class ActionDemo {
    public static void main(String[] args) {
        Action shout = new Action() {
            @Override
            public void act(String text) {
                System.out.println(text.toUpperCase());
            }
        };
        
        shout.act("I'm learning java!"); // I'M LEARNING JAVA!
    }
}
```

(Later we'll see how this can be written more concisely using lambda expressions.)

#### Task 2. Number Filter

Create a **NumberPredicate** interface with a **boolean test(int n)** method. Implement the parity check using an anonymous class.

```java
@FunctionalInterface
interface NumberPredicate {
    boolean test(int n);
}

public class PredicateDemo {
    public static void main(String[] args) {
        NumberPredicate isEven = new NumberPredicate() {
            @Override
            public boolean test(int n) {
                return n % 2 == 0;
            }
        };
        
        System.out.println(isEven.test(4)); // true
        System.out.println(isEven.test(7)); // false
    }
}
```

#### Task 3. Using standard interfaces

Instead of writing your own interface, you can use the ready-made **Predicate<Integer>**:

```java
import java.util.function.Predicate;

Predicate<Integer> isPositive = new Predicate<Integer>() {
    @Override
    public boolean test(Integer x) {
        return x > 0;
    }
};

    System.out.println(isPositive.test(10)); // true
    System.out.println(isPositive.test(-5)); // false
```

### Table: Functional interfaces from the standard library

| **Interface** | **Method** | **Description** | **Usage example** |
| --- | --- | --- | --- |
| `Runnable` | `void run()` | Task without arguments and result | Threads, timers |
| `Callable<V>` | `V call()` | Task with result | Threads, ExecutorService |
| `Comparator<T>` | `int compare(T, T)` | Compare two objects | Sorting collections |
| `Consumer<T>` | `void accept(T)` | Value consumer | Collection iteration |
| `Supplier<T>` | `T get()` | Value supplier | Lazy initialization, data generation |
| `Function<T, R>` | `R apply(T)` | Function from T to R | Data conversion |
| `Predicate<T>` | `boolean test(T)` | Conditional check | Collection filtering |

### 5. Common Mistakes When Working with Functional Interfaces

**Mistake №1: Adding a second abstract method.** If an interface has more than one abstract method, it ceases to be functional. The compiler (especially with **@FunctionalInterface**) will immediately report an error.

**Mistake №2: Forgetting that default and static methods are not considered abstract.** You can safely add them to a functional interface—this will not violate the "one abstract method" rule.

**Mistake №3: Incorrectly implementing a method signature.** For example, an interface requires two arguments, but you wrote a method with only one. Always check signatures.

**Mistake №4: Not using @FunctionalInterface and accidentally breaking the interface.** If you don't mark an interface with an annotation, you can accidentally add an extra method—and then spend a long time trying to figure out why the code doesn't work. It's always better to add an annotation for clarity.
