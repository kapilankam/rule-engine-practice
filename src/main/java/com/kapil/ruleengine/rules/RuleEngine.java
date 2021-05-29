package com.kapil.ruleengine.rules;

import com.kapil.ruleengine.model.Rule;
import com.kapil.ruleengine.service.KnowledgeBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RuleEngine {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    public Object run(InferenceEngine inferenceEngine, Object inputData) {
        String ruleNamespace = inferenceEngine.getRuleNamespace().toString();
        //TODO: Here for each call, we are fetching all rules from db. It should be cache.
        List<Rule> allRulesByNamespace = knowledgeBaseService.getAllRuleByNamespace(ruleNamespace);
        Object result = inferenceEngine.run(allRulesByNamespace, inputData);
        return result;
    }
}
