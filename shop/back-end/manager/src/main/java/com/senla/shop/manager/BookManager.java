package com.senla.shop.manager;

import com.senla.shop.api.dao.IBookDao;
import com.senla.shop.api.manager.IBookManager;
import com.senla.shop.csv.CSVWorker;
import com.senla.shop.csv.Parser;
import com.senla.shop.dao.hibernateutil.HibernateUtil;
import com.senla.shop.di.DependencyInjection;
import com.senla.shop.model.Book;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookManager implements IBookManager {

    private IBookDao bookDao;
    private final static Logger LOGGER = Logger.getLogger(BookManager.class);
    public BookManager() {
        bookDao = (IBookDao) DependencyInjection.getInstance().getObject(IBookDao.class);
    }

    @Override
    public void create(Book book) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            bookDao.create(session, book);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = bookDao.getById(session, id);
            if (book != null){
                bookDao.delete(session, book);
            }
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public Book getById(int id) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book book = bookDao.getById(session, id);
            transaction.commit();
            return book;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public List<Book> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Book> books = bookDao.getAll(session, sort);
            transaction.commit();
            return books;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public List<Book> getAllUnsold(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Book> books = bookDao.getAllUnsold(session, sort);
            transaction.commit();
            return books;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() throws Exception {
        List<String> lines = CSVWorker.loadCsvStrings(Book.class);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (Book book: (List<Book>) Parser.parse(Book.class, lines)){
                bookDao.saveOrUpdate(session, book);
            }
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public void exportToCsv() throws Exception {
        CSVWorker.saveToCSV(getAll(null));
    }
}
