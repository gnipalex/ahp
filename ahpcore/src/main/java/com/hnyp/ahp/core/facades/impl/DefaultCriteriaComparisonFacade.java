package com.hnyp.ahp.core.facades.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ComparisonPairData;
import com.hnyp.ahp.core.data.CriteriaComparisonTableData;
import com.hnyp.ahp.core.facades.CriteriaComparisonFacade;
import com.hnyp.ahp.core.models.ComparisonPair;
import com.hnyp.ahp.core.models.CriteriaComparisonTable;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.ComparisonTableService;
import com.hnyp.ahp.core.services.CriteriaComparisonService;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.core.services.ProjectDecisionService;
import com.hnyp.ahp.core.util.Converters;

@Transactional
public class DefaultCriteriaComparisonFacade implements CriteriaComparisonFacade {

    @Resource
    private ModelService modelService;
    @Resource
    private CriteriaService criteriaService;
    @Resource
    private CriteriaComparisonService criteriaComparisonService;
    @Resource
    private ComparisonTableService comparisonTableService;
    @Resource
    private ProjectDecisionService projectDecisionService;
    @Resource
    private Converter<CriteriaComparisonTable, CriteriaComparisonTableData> criteriaComparisonTableDataConverter;
    
    @Override
    public boolean doesComparisonExistForDecisionAndName(long decisionId, String name) {
        return getForDecisionAndNameInternal(decisionId, name) != null;
    }
    
    private CriteriaComparisonTable getForDecisionAndNameInternal(long decisionId, String name) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        return criteriaComparisonService.getForDecisionAndName(projectDecision, name);
    }

    @Override
    public void create(long decisionId, CriteriaComparisonTableData criteriaComparisonTableData) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        
        CriteriaComparisonTable comparisonTable = new CriteriaComparisonTable();
        comparisonTable.setDescription(criteriaComparisonTableData.getDescription());
        comparisonTable.setName(criteriaComparisonTableData.getName());
        comparisonTable.setProjectDecision(projectDecision);
        
        criteriaComparisonService.create(comparisonTable);
        
        comparisonTableService.calculate(comparisonTable);
    }

    @Override
    public void update(long comparisonTableId, List<ComparisonPairData> comparisonPairs) {
        CriteriaComparisonTable comparisonTable = modelService.loadById(CriteriaComparisonTable.class, comparisonTableId);
        if (comparisonTable == null) {
            throw new IllegalArgumentException("criteria comparison table is not found");
        }
        List<ComparisonPair> comparisonPairsForUpdate = comparisonPairs.stream()
                .map(this::createComparisonPairForUpdate).collect(Collectors.toList());
        comparisonTableService.update(comparisonTable, comparisonPairsForUpdate);
        comparisonTableService.calculate(comparisonTable);
    }
    
    private ComparisonPair createComparisonPairForUpdate(ComparisonPairData data) {
        ComparisonPair comparisonPair = new ComparisonPair();
        comparisonPair.setId(data.getId());
        comparisonPair.setValue(data.getValue());
        return comparisonPair;
    }

    @Override
    public void remove(long id) {
        modelService.remove(CriteriaComparisonTable.class, id);
    }

    @Override
    public CriteriaComparisonTableData getForDecisionAndName(long decisionId, String name) {
        CriteriaComparisonTable comparisonTable = getForDecisionAndNameInternal(decisionId, name);
        if (comparisonTable != null) {
            return criteriaComparisonTableDataConverter.convert(comparisonTable);
        } 
        return null;
    }

    @Override
    public List<CriteriaComparisonTableData> getForDecision(long decisionId) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        List<CriteriaComparisonTable> comparisonTables = criteriaComparisonService.getForDecision(projectDecision);
        return Converters.convertAll(comparisonTables, criteriaComparisonTableDataConverter);
    }

    @Override
    public boolean doesComparisonExistForDecision(long decisionId, long comparisonTableId) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        CriteriaComparisonTable criteriaComparisonTable = criteriaComparisonService.getForDecisionAndId(projectDecision, comparisonTableId);
        return criteriaComparisonTable != null;
    }

    @Override
    public CriteriaComparisonTableData getForDecisionAndId(long decisionId, long id) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        CriteriaComparisonTable comparisonTable = criteriaComparisonService.getForDecisionAndId(projectDecision, id);
        return criteriaComparisonTableDataConverter.convert(comparisonTable);
    }

}
