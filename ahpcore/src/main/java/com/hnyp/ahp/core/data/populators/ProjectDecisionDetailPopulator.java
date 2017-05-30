package com.hnyp.ahp.core.data.populators;

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
import com.hnyp.ahp.core.util.Converters;

public class ProjectDecisionDetailPopulator implements Populator<ProjectDecision, ProjectDecisionData> {

    @Resource
    private Converter<Alternative, AlternativeData> alternativeConverter;
    @Resource
    private Converter<Criteria, CriteriaData> criteriaConverter;
    @Resource
    private Converter<VoteRequest, VoteRequestData> voteRequestConverter;
    
    @Override
    public void populate(ProjectDecision source, ProjectDecisionData target) {
        target.setAlternatives(Converters.convertAll(source.getAlternatives(), alternativeConverter));
        target.setCriterias(Converters.convertAll(source.getCriterias(), criteriaConverter));
        target.setVoteRequests(Converters.convertAll(source.getVoteRequests(), voteRequestConverter));
    }
    
}
