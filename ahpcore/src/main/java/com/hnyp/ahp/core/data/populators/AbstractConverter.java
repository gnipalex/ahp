package com.hnyp.ahp.core.data.populators;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

public class AbstractConverter<S, T> implements Converter<S, T> {

    private Class<T> targetClass;
    private List<Populator<S, T>> populators;
    
    @Override
    public T convert(S source) {
        T target = createTarget();
        populators.stream().forEach(p -> p.populate(source, target));
        return target;
    }
    
    protected T createTarget() {
        try {
            return targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("could not instantiate target class " + targetClass, e);
        }
    }
    
    @PostConstruct
    public void postConstruct() {
        if (CollectionUtils.isEmpty(populators)) {
            throw new IllegalStateException("populators aren't set, nothing will be converted!!!");
        }
        createTarget();
    }

    @Required
    public void setTargetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Required
    public void setPopulators(List<Populator<S, T>> populators) {
        this.populators = populators;
    }

}
