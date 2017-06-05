package com.hnyp.ahp.core.data.populators;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.AlternativeData;
import com.hnyp.ahp.core.data.CriteriaData;
import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.data.VoteRequestData;
import com.hnyp.ahp.core.models.Alternative;
import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.VoteRequest;
import com.hnyp.ahp.core.services.AlternativeService;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.util.Converters;

public class ProjectDecisionDataDetailPopulator implements Populator<ProjectDecision, ProjectDecisionData> {

    @Resource
    private Converter<Alternative, AlternativeData> alternativeDataConverter;
    @Resource
    private Converter<Criteria, CriteriaData> criteriaDataConverter;
    @Resource
    private Converter<VoteRequest, VoteRequestData> voteRequestDataConverter;
    @Resource
    private CriteriaService criteriaService;
    @Resource
    private AlternativeService alternativeService;
    
    @Override
    public void populate(ProjectDecision source, ProjectDecisionData target) {
        List<Alternative> alternatives = alternativeService.getForProjectDecision(source);
        target.setAlternatives(Converters.convertAll(alternatives, alternativeDataConverter));
        
        List<Criteria> criterias = criteriaService.getForProjectDecision(source);
        target.setCriterias(Converters.convertAll(criterias, criteriaDataConverter));
        
        target.setVoteRequests(Converters.convertAll(source.getVoteRequests(), voteRequestDataConverter));
    }
    
}
