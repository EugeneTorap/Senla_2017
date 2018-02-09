package com.senla.controller.manager;

import com.senla.api.manager.IRequestManager;
import com.senla.controller.dao.mysql.RequestDao;
import com.senla.model.entity.Request;

public class RequestManager implements IRequestManager{
    private RequestDao requestDao;


    public RequestManager() {
        requestDao = new RequestDao();
    }

    @Override
    public void add(Request request) {
        requestDao.create(request);
    }

    /*@Override
    public void setRequestAmount() {
        int count = 0;
        for (Book book: BookDao.getInstance().getBooks()) {
            for (Request request : requestRepository.getRequests()) {
                if (request.getBook().getId() == book.getId()) {
                    count++;
                }
            }
            book.setRequestAmount(count);
            count = 0;
        }
    }*/
}
