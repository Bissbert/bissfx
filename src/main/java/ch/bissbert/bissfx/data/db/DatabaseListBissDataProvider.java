package ch.bissbert.bissfx.data.db;

import ch.bissbert.bissfx.data.provider.ListBissDataProvider;

import java.util.List;

public class DatabaseListBissDataProvider<T> extends Dao<T> implements ListBissDataProvider<T> {
    public DatabaseListBissDataProvider(Class<T> tClass) {
        super(tClass);
    }

    @Override
    public List<T> getData() {
        return this.findAll();
    }
}
