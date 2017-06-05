package com.hnyp.ahp.core.facades.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.AlternativeData;
import com.hnyp.ahp.core.facades.AlternativeFacade;
import com.hnyp.ahp.core.models.Alternative;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.AlternativeService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.core.util.Converters;

@Transactional
public class DefaultAlternativeFacade implements AlternativeFacade {

    @Resource
    private AlternativeService alternativeService;
    @Resource
    private ModelService modelService;
    @Resource
    private Converter<Alternative, AlternativeData> alternativeDataConverter;
    
    @Override
    public List<AlternativeData> getAlternativesForDecision(long decisionId) {
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, decisionId);
        List<Alternative> alternativesForProjectDecision = alternativeService.getForProjectDecision(projectDecision);
        return Converters.convertAll(alternativesForProjectDecision, alternativeDataConverter);
    }

    @Override
    public void createAlternative(long projectDecisionId, AlternativeData alternativeData) {
        Alternative alternative = new Alternative();
        alternative.setDescription(alternativeData.getDescription());
        alternative.setName(alternativeData.getName());
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, projectDecisionId);
        alternative.setProjectDecision(projectDecision);
        alternativeService.create(alternative);
    }

    @Override
    public void update(AlternativeData alternativeData) {
        Alternative alternative = modelService.getById(Alternative.class, alternativeData.getId());
        alternative.setName(alternativeData.getName());
        alternative.setDescription(alternativeData.getDescription());
        alternativeService.update(alternative);
    }

    @Override
    public void remove(long alternativeId) {
        modelService.remove(Alternative.class, alternativeId);
    }

    @Override
    public boolean doesAlternativeExistForProjectDecision(long projectDecisionId, String alternativeName) {
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, projectDecisionId);
        return alternativeService.doesAlternativeExist(projectDecision, alternativeName);
    }

    @Override
    public AlternativeData getAlternativeForDecisionAndName(long projectDecisionId, String alternativeName) {
        ProjectDecision projectDecision = modelService.loadById(ProjectDecision.class, projectDecisionId);
        Alternative alternative = alternativeService.getForProjectDecisionAndName(projectDecision, alternativeName);
        if (alternative != null) {
            return alternativeDataConverter.convert(alternative);
        }
        // ? exception
        return null;
    }
    
}
