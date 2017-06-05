package com.hnyp.ahp.core.facades.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.CriteriaData;
import com.hnyp.ahp.core.facades.CriteriaFacade;
import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.core.services.ProjectDecisionService;
import com.hnyp.ahp.core.util.Converters;

@Transactional
public class DefaultCriteriaFacade implements CriteriaFacade {

    @Resource
    private ModelService modelService;
    @Resource
    private CriteriaService criteriaService;
    @Resource
    private ProjectDecisionService projectDecisionService;
    @Resource
    private Converter<Criteria, CriteriaData> criteriaDataConverter;
    
    @Override
    public List<CriteriaData> getCriteriasForDecision(long projectDecisionId) {
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, projectDecisionId);
        if (projectDecision == null) {
            throw new IllegalArgumentException(String.format("Project decision with id %s is not found", projectDecisionId));
        }
        List<Criteria> criterias = criteriaService.getForProjectDecision(projectDecision);
        return Converters.convertAll(criterias, criteriaDataConverter);
    }

    @Override
    public void create(long projectDecisionId, CriteriaData criteriaData) {
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, projectDecisionId);
        if (projectDecision == null) {
            throw new IllegalArgumentException(String.format("Project decision with id %s does not exist", projectDecisionId));
        }
        Criteria criteria = new Criteria();
        criteria.setDescription(criteriaData.getDescription());
        criteria.setName(criteriaData.getName());
        criteria.setProjectDecision(projectDecision);
        criteriaService.create(criteria);
    }

    @Override
    public void update(CriteriaData criteriaData) {
        Criteria criteria = modelService.getById(Criteria.class, criteriaData.getId());
        if (criteria == null) {
            throw new IllegalArgumentException(String.format("Criteria with id %s does not exist", criteriaData.getId()));
        }
        criteria.setDescription(criteriaData.getDescription());
        criteria.setName(criteriaData.getName());
        criteriaService.updateCriteria(criteria);
    }

    @Override
    public void remove(long criteriaId) {
        modelService.remove(Criteria.class, criteriaId);
    }

    @Override
    public boolean doesCriteriaExistForDecisionAndName(long projectDecisionId, String name) {
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, projectDecisionId);
        if (projectDecision == null) {
            throw new IllegalArgumentException(String.format("Project decision with id %s is not found", projectDecisionId));
        }
        return criteriaService.getForProjectDecisionAndName(projectDecision, name) != null;
    }

    @Override
    public CriteriaData getCriteriaForDecisionAndName(long projectDecisionId, String name) {
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, projectDecisionId);
        if (projectDecision == null) {
            throw new IllegalArgumentException(String.format("Project decision with id %s is not found", projectDecisionId));
        }
        Criteria criteria = criteriaService.getForProjectDecisionAndName(projectDecision, name);
        if (criteria != null) {
            return criteriaDataConverter.convert(criteria);
        }
        return null;
    }

}
