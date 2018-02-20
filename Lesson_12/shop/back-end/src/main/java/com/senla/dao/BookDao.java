package com.senla.dao;

import com.senla.api.dao.IBookDao;
import com.senla.api.model.IBook;
import com.senla.executor.Executor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class BookDao extends GenericDao<IBook> implements IBookDao {

    protected BookDao(Class clazz) {
        super(clazz);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<IBook> getAllUnsold(String sort) {
        return Executor.transact(session -> {
            LocalDate date = LocalDate.now().minusMonths(6);

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<IBook> criteria = cb.createQuery(IBook.class);
            Root<IBook> root = criteria.from(IBook.class);
            criteria.select(root).where(cb.lessThan(root.get("dateReceipted"), date)).orderBy(cb.asc(root.get(sort)));

            return session.createQuery(criteria).getResultList();
        });
    }
}
