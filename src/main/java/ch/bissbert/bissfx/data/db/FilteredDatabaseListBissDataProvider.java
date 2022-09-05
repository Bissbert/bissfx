package ch.bissbert.bissfx.data.db;

import ch.bissbert.bissfx.data.provider.ListBissDataProvider;

import java.util.List;

/**
 * A class to access a list of objects from a database filtered by a field and a value.
 *
 * @param <T> the type of the objects to access
 * @author Bissbert
 */
public class FilteredDatabaseListBissDataProvider<T> extends Dao<T> implements ListBissDataProvider<T> {
    private final String field;
    private final String value;

    /**
     * Creates a new instance of this class.
     *
     * @param tClass the class of the objects to access
     * @param field  the field to filter by
     * @param value  the value to filter by
     */
    public FilteredDatabaseListBissDataProvider(Class<T> tClass, String field, String value) {
        super(tClass);
        this.field = field;
        this.value = value;
    }

    @Override
    public List<T> getData() {
        return this.findAllBy(field, value);
    }
}
