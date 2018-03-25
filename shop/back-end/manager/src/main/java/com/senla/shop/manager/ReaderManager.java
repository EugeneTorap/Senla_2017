package com.senla.shop.manager;

import com.senla.shop.api.dao.IReaderDao;
import com.senla.shop.api.manager.IReaderManager;
import com.senla.shop.csv.CSVWorker;
import com.senla.shop.csv.Parser;
import com.senla.shop.dao.hibernateutil.HibernateUtil;
import com.senla.shop.di.DependencyInjection;
import com.senla.shop.model.Reader;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReaderManager implements IReaderManager {

    private IReaderDao readerDao;
    private final static Logger LOGGER = Logger.getLogger(ReaderManager.class);

    public ReaderManager() {
        readerDao = (IReaderDao) DependencyInjection.getInstance().getObject(IReaderDao.class);
    }

    @Override
    public void create(Reader reader) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            readerDao.create(session, reader);
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
            Reader reader = readerDao.getById(session, id);
            if (reader != null){
                readerDao.delete(session, reader);
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
    public Reader getById(int id) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Reader reader = readerDao.getById(session, id);
            transaction.commit();
            return reader;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    @Override
    public List<Reader> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Reader> readers = readerDao.getAll(session, sort);
            transaction.commit();
            return readers;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    public Reader getByToken(String token) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Reader reader = readerDao.getByToken(session, token);
            transaction.commit();
            return reader;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }

    public Reader getByLogin(String name) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Reader reader = readerDao.getByLogin(session, name);
            transaction.commit();
            return reader;
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
        List<String> lines = CSVWorker.loadCsvStrings(Reader.class);

        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for (Reader reader: (List<Reader>) Parser.parse(Reader.class, lines)){
                readerDao.saveOrUpdate(session, reader);
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
