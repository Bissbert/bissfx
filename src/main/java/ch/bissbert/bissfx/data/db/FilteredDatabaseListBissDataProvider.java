package ch.bissbert.bissfx.data.db;

import ch.bissbert.bissfx.data.provider.ListBissDataProvider;

import java.util.List;

public class FilteredDatabaseListBissDataProvider<T> extends Dao<T> implements ListBissDataProvider<T> {
    private final String field;
    private final String value;

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
