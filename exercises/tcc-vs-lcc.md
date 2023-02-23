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

LCC can be lower than TCC for a given class. This occurs when a class has a high number of attributes but the methods do not interact with all of them, resulting in a lower LCC value. In these kind of cases, LCC is lower than TCC because it measures the ratio of unshared method pairs to the total number of possible method pairs. If a class has a high number of attributes, but only a few methods, then the number of possible method pairs will be high, but the number of unshared method pairs will be low, resulting in a low LCC value.
