package com.senla.shop.api.manager;

import com.senla.shop.model.Reader;

public interface IReaderManager extends IManager<Reader> {
    Reader getByToken(String token) throws Exception;

    Reader getByLogin(String name) throws Exception;
}
