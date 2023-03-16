# TCC _vs_ LCC

Explain under which circumstances _Tight Class Cohesion_ (TCC) and _Loose Class Cohesion_ (LCC) metrics produce the same value for a given Java class. Build an example of such as class and include the code below or find one example in an open-source project from Github and include the link to the class below. Could LCC be lower than TCC for any given class? Explain.

## Answer
If a class has only one method, then TCC and LCC will be equal to 1.0. This is because the class has only one method, and that method is the only method that the class calls. Therefore, the class is both tightly and loosely cohesive.

```java
public class Main {

    private int attribute;

    public void addOne(int attribute) {
        attribute += 1;
    }
}
```

LCC cannot be lower than TCC at any time. This is because LCC is defined as the number of methods that a class calls divided by the total number of methods in the class. Therefore, LCC will always be greater than or equal to 1.0, which is the value of TCC for a class with only one method.
