# Using PMD

Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset. Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false negative). Explain why you would not solve this issue.

## Answer

- False negative: there are some `if` without `else` in code. It could be replaced by a `switch` statement. But in this case, it is not worth solving because the code is more readable with `if` statements.

- True positive:

```bash
src/main/java/org/apache/commons/cli/PatternOptionBuilder.java:115:     OnlyOneReturn:  A method should have only one exit point, and that should be the last statement in the method
```

In this code, it would be better to initialize a variable, assign the result of switch statement to it and return it at the end of the method. It would be more readable and easier to debug.

```java
public static Object getValueClass(final char ch) {
        Object result = null;
        switch (ch) {
        case '@':
            result = PatternOptionBuilder.OBJECT_VALUE;
        case ':':
            result = PatternOptionBuilder.STRING_VALUE;
        case '%':
            result = PatternOptionBuilder.NUMBER_VALUE;
        case '+':
            result = PatternOptionBuilder.CLASS_VALUE;
        case '#':
            result = PatternOptionBuilder.DATE_VALUE;
        case '<':
            result = PatternOptionBuilder.EXISTING_FILE_VALUE;
        case '>':
            result = PatternOptionBuilder.FILE_VALUE;
        case '*':
            result = PatternOptionBuilder.FILES_VALUE;
        case '/':
            result = PatternOptionBuilder.URL_VALUE;
        }

        return result;
    }
```
