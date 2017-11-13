package main;

import facade.OnlineBookStore;
import java.text.ParseException;

public class FileFiller {
    private OnlineBookStore store = new OnlineBookStore();

    public OnlineBookStore fillData() throws ParseException {
        store.loadAllData();
        return store;
    }
}
