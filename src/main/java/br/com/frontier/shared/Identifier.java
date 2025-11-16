package br.com.frontier.shared;

import java.util.Objects;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
public abstract class Identifier<T>   {
    private static final String ID_SHOULD_NOT_BE_NULL_MESSAGE = "'id' should not be null";

    private final T value;

    protected Identifier(final T value) {
        Objects.requireNonNull(value, ID_SHOULD_NOT_BE_NULL_MESSAGE);
        this.value =value;
    }

    public T getValue(){
        return value;
    }

    public String toString(){
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
