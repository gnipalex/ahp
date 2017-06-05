package com.hnyp.ahp.core.services.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import com.hnyp.ahp.core.services.ModelService;

public class DefaultModelService implements ModelService {

    @Resource
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Object object) {
        sessionFactory.getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public void remove(Object object) {
        sessionFactory.getCurrentSession().delete(object);
    }
    
    @Override
    public <T> void remove(Class<T> type, long id) {
        T objectToRemove = loadById(type, id);
        remove(objectToRemove);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getById(Class<T> type, long id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T loadById(Class<T> type, long id) {
        return (T) sessionFactory.getCurrentSession().load(type, id);
    }

}
