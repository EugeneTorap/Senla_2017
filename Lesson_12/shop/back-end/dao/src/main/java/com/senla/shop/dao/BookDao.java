package com.senla.shop.dao;

import com.senla.shop.api.dao.IBookDao;
import com.senla.shop.api.model.IBook;
import com.senla.shop.model.Book;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class BookDao extends GenericDao<IBook> implements IBookDao {

    public BookDao() {
        super(Book.class);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<IBook> getAllUnsold(Session session, String sort) {
        /*LocalDate date = LocalDate.now().minusMonths(6);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IBook> criteria = cb.createQuery(IBook.class);
        Root<IBook> root = criteria.from(IBook.class);
        criteria.select(root).where(cb.lessThan(root.get("dateReceipted"), date)).orderBy(cb.asc(root.get(sort)));

        return session.createQuery(criteria).getResultList();*/
        return null;
    }
}
