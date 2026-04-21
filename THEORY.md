## Multiple implementation of interfaces.

### 1. Introduction

In Java, you can't inherit from multiple classes at once. This is done intentionally to avoid the "diamond of death"—a situation where two parent classes define the same method, and it's unclear which implementation to use. But you can implement as many interfaces as you like! Why? Because an interface is just a contract; it doesn't contain an implementation (before Java 8, it had no implementation; since Java 8, both default and static methods are possible). This means there's no confusion about code inheritance.

This is similar to a real-life situation: you can be a "Driver," a "Computer User," and a "Swimmer" all at once. Each of these "interfaces" describes specific skills, but doesn't force you to be a copy of another person.

#### Syntax for Multiple Interface Implementations

In Java, a class can implement multiple interfaces by listing them separated by commas after the **implements** keyword. Here's a basic example:

```java
public interface Movable {
    void move(int x, int y);
}

public interface Chargeable {
    void charge();
}

public class Robot implements Movable, Chargeable {
    @Override
    public void move(int x, int y) {
        System.out.println("The robot is moving to point (" + x + ", " + y + ")");
    }

    @Override
    public void charge() {
        System.out.println("The robot is charging.");
    }
}
```

In this example, **Robot** is a versatile guy: it both moves and charges. It's just like in real life: the more you can do, the more interviews you get!

### 2. Why is this necessary? Practical Examples

#### Example 1. Different "Roles" of an Object

Imagine you're designing a game character:

- It can move (**Movable**)
- It can attack (**Attackable**)
- It can be saved to a file (**Serializable** — this interface exists in the Java standard library)

```java
public interface Attackable {
    void attack();
}

public class Hero implements Movable, Attackable, java.io.Serializable {
    @Override
    public void move(int x, int y) {
        System.out.println("Hero moves to a new position.");
    }
    
    @Override
    public void attack() {
        System.out.println("Hero attacks!");
    }
}
```

Now your class can be used in a wide variety of contexts: it can be passed to methods that require any of these interfaces.

#### Example 2. Combining Standard Interfaces

The Java standard library often uses the **Comparable** interface (for comparing objects) and the **Serializable** interface (for saving objects to a file or transmitting them over a network). Sometimes you need an object to be both:

```java
public class Person implements Comparable<Person>, java.io.Serializable {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}
```

Now **Person** objects can be sorted (for example, in a list) and written to a file.

Serialize - convert to a sequence of bytes.

### 3. Features and Limitations

#### One Implementation Per Method

If two interfaces define a method with the same signature, it only needs to be implemented once. Example:

```java
public interface A {
    void doSomething();
}
public interface B {
    void doSomething();
}
public class MyClass implements A, B {
    @Override
    public void doSomething() {
        System.out.println("Implementation of doSomething for both interfaces.");
    }
}
```

Java won't complain—the main thing is that the signatures match. If methods have different signatures, they are considered different methods—you need to implement each.

#### No "Diamond of Death"

Unlike multiple inheritance in classes, implementing multiple interfaces doesn't create the situation where two different implementations of the same method are inherited. Before Java 8, interfaces didn't have implementations at all, and with the introduction of default methods, if a conflict arises, you must explicitly resolve it (more on this in the next lecture).

#### No State

Interfaces can't contain regular fields (only constants — **public static final**). Therefore, there's no confusion about "two parent fields with the same name."

### 4. Example: Implementing Multiple Interfaces in a Single Class

Let's add new features to our example application (for example, a zoo). Let's say we have animals that can move and make sounds:

```java
public interface Movable {
    void move(int x, int y);
}

public interface Soundable {
    void makeSound();
}

public class Dog implements Movable, Soundable {
    private String name;
    
    public Dog(String name) {
        this.name = name;
    }
    
    @Override
    public void move(int x, int y) {
        System.out.println(name + " runs to (" + x + ", " + y + ")");
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof-woof!");
    }
}

public class Cat implements Movable, Soundable {
    private String name;
    
    public Cat(String name) {
        this.name = name;
    }
    
    @Override
    public void move(int x, int y) {
        System.out.println(name + " sneaks to (" + x + ", " + y + ")");
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}
```

Now we can write a universal method for working with any "moving" or "sounding" object:

```java
public static void testMovable(Movable m) {
    m.move(10, 20);
}

public static void testSoundable(Soundable s) {
    s.makeSound();
}

public static void main(String[] args) {
    Dog rex = new Dog("Rex");
    Cat murka = new Cat("Murka");
    
    testMovable(rex); // Rex runs to (10, 20)
    testSoundable(murka); // Murka says: Meow!
}
```

And, of course, if an object implements both interfaces, it can be passed to both!

### 5. Useful nuances

#### What if interfaces conflict?

Sometimes, two interfaces define methods with the same signature but different meanings. For example, one interface expects the **reset()** method to reset the coordinates, while the other expects the same method to turn off the device. In this case, you need to be careful: the method only needs to be implemented once, and it must be able to implement both behaviors (or at least choose which to do). In real life, such situations are rare, but if you do encounter them, it's worth considering the correctness of the design.

#### Example with a collection of objects with different interfaces

Let's say we have a list of objects implementing different interfaces. We can iterate over them and call the necessary methods:

```java
Movable[] movables = {
    new Dog("Ball"),
    new Cat("Barsik"),
    new Robot()
};

    for (Movable m : movables) {
        m.move(0, 0);
    }
```

The same can be done for any interface.

### 6. Common Errors with Multiple Interface Implementations

**Error №1: Not all interface methods are implemented.**

If a class declares that it implements an interface but fails to implement at least one of its methods, the compiler will immediately generate an error. Don't forget all the methods, even if they seem "unnecessary."

**Error №2: Conflicting methods with the same signature.**

If two interfaces define the same methods, they only need to be implemented once. However, if the meaning of these methods differs, this can lead to confusion and bugs. In this case, it's best to rethink the design.

**Error №3: Attempting to inherit an interface via extends in a class.**

In a class, implement an interface using implements, not extends. For example:

```java
public class MyClass implements A, B { ... } // correct
public class MyClass extends A, B { ... } // error!
```

**Error №4: Attempting to create an interface object.**

An interface is a contract; it cannot be created directly:

```java
Movable m = new Movable(); // compilation error
```

Only objects of classes that implement an interface can be created.
