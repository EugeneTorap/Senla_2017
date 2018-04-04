package com.senla.shop.manager;

import com.senla.shop.api.dao.IAuditDao;
import com.senla.shop.api.manager.IAuditManager;
import com.senla.shop.dao.hibernateutil.HibernateUtil;
import com.senla.shop.di.DependencyInjection;
import com.senla.shop.model.Audit;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AuditManager implements IAuditManager {

    private IAuditDao auditDao;
    private final static Logger LOGGER = Logger.getLogger(AuditManager.class);
    public AuditManager() {
        auditDao = (IAuditDao) DependencyInjection.getInstance().getObject(IAuditDao.class);
    }

    @Override
    public void create(Audit audit) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            auditDao.create(session, audit);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }
}
