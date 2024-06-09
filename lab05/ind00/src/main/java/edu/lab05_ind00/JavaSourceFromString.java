package edu.lab05_ind00;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * A custom implementation of the SimpleJavaFileObject class that represents Java source code as a string.
 */
public class JavaSourceFromString extends SimpleJavaFileObject {
    private final String code;

    /**
     * Constructs a new JavaSourceFromString object with the specified name and code.
     *
     * @param name The name of the Java source file.
     * @param code The source code represented as a string.
     */
    protected JavaSourceFromString(String name, String code) {
        super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    /**
     * Retrieves the source code content of this JavaSourceFromString object.
     *
     * @param ignoreEncodingErrors Indicates whether encoding errors should be ignored.
     * @return The source code content as a CharSequence.
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}
