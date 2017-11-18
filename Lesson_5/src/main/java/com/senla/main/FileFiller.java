package com.senla.main;

import com.senla.facade.OnlineBookStore;
import java.text.ParseException;

public class FileFiller {
    private OnlineBookStore store = OnlineBookStore.getInstance();

    public OnlineBookStore fillData() throws ParseException {
        store.loadAllData();
        return store;
    }
}
