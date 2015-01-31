package com.koakh.greendaostartup.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.koakh.greendaostartup.model.Note;
import com.koakh.greendaostartup.model.Customer;
import com.koakh.greendaostartup.model.Order;

import com.koakh.greendaostartup.model.NoteDao;
import com.koakh.greendaostartup.model.CustomerDao;
import com.koakh.greendaostartup.model.OrderDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 *
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

  private final DaoConfig noteDaoConfig;
  private final DaoConfig customerDaoConfig;
  private final DaoConfig orderDaoConfig;

  private final NoteDao noteDao;
  private final CustomerDao customerDao;
  private final OrderDao orderDao;

  public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
          daoConfigMap) {
    super(db);

    noteDaoConfig = daoConfigMap.get(NoteDao.class).clone();
    noteDaoConfig.initIdentityScope(type);

    customerDaoConfig = daoConfigMap.get(CustomerDao.class).clone();
    customerDaoConfig.initIdentityScope(type);

    orderDaoConfig = daoConfigMap.get(OrderDao.class).clone();
    orderDaoConfig.initIdentityScope(type);

    noteDao = new NoteDao(noteDaoConfig, this);
    customerDao = new CustomerDao(customerDaoConfig, this);
    orderDao = new OrderDao(orderDaoConfig, this);

    registerDao(Note.class, noteDao);
    registerDao(Customer.class, customerDao);
    registerDao(Order.class, orderDao);
  }

  public void clear() {
    noteDaoConfig.getIdentityScope().clear();
    customerDaoConfig.getIdentityScope().clear();
    orderDaoConfig.getIdentityScope().clear();
  }

  public NoteDao getNoteDao() {
    return noteDao;
  }

  public CustomerDao getCustomerDao() {
    return customerDao;
  }

  public OrderDao getOrderDao() {
    return orderDao;
  }

}
