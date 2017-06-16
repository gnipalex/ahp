package com.hnyp.ahp.core.facades.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.AlternativeComparisonTableData;
import com.hnyp.ahp.core.data.ComparisonPairData;
import com.hnyp.ahp.core.facades.AlternativeComparisonFacade;
import com.hnyp.ahp.core.models.AlternativeComparisonTable;
import com.hnyp.ahp.core.models.ComparisonPair;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.AlternativeComparisonService;
import com.hnyp.ahp.core.services.ComparisonTableService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.core.services.ProjectDecisionService;
import com.hnyp.ahp.core.util.Converters;

@Transactional
public class DefaultAlternativeComparisonFacade implements AlternativeComparisonFacade {

    @Resource
    private ProjectDecisionService projectDecisionService;
    @Resource
    private AlternativeComparisonService alternativeComparisonService;
    @Resource
    private Converter<AlternativeComparisonTable, AlternativeComparisonTableData> alternativeComparisonTableDataConverter;
    @Resource
    private ModelService modelService; 
    @Resource
    private ComparisonTableService comparisonTableService;

    @Override
    public void create(long decisionId) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        alternativeComparisonService.createForProjectDecision(projectDecision);
    }

    @Override
    public List<AlternativeComparisonTableData> getAlternativeComparisonsForDecision(long decisionId) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        List<AlternativeComparisonTable> comparisonTables = alternativeComparisonService
                .getForProjectDecision(projectDecision);
        return Converters.convertAll(comparisonTables, alternativeComparisonTableDataConverter);
    }

    @Override
    public boolean doesComparisonExistForProjectDecision(long projectDecisionId, long id) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(projectDecisionId);
        return alternativeComparisonService.getForProjectDecisionAndId(projectDecision, id) != null;
    }

    @Override
    public void update(long comparisonTableId, List<ComparisonPairData> comparisonPairs) {
        AlternativeComparisonTable comparisonTable = modelService.loadById(AlternativeComparisonTable.class, comparisonTableId);
        if (comparisonTable == null) {
            throw new IllegalArgumentException("alternative comparison table is not found");
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
    public AlternativeComparisonTableData getForDecisionAndId(long projectDecisionId, long id) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(projectDecisionId);
        AlternativeComparisonTable alternativeComparisonTable = alternativeComparisonService.getForProjectDecisionAndId(projectDecision, id);
        return alternativeComparisonTableDataConverter.convert(alternativeComparisonTable);
    }
    
    

}
