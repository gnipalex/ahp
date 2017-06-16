package com.hnyp.ahp.core.data.populators;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.data.VoteRequestData;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.VoteRequest;

public class VoteRequestDataDecisionPopulator implements Populator<VoteRequest, VoteRequestData> {

    @Resource
    private Converter<ProjectDecision, ProjectDecisionData> basicProjectDecisionDataConverter;
    
    @Override
    public void populate(VoteRequest source, VoteRequestData target) {
        target.setProjectDecision(basicProjectDecisionDataConverter.convert(source.getProjectDecision()));
    }

}
