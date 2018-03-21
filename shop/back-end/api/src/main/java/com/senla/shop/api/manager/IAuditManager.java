package com.senla.shop.api.manager;

import com.senla.shop.model.Audit;

public interface IAuditManager {
    void create(Audit t) throws Exception;
}
