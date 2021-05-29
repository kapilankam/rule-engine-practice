package com.kapil.ruleengine.controller;

import com.google.common.base.Enums;
import com.kapil.ruleengine.model.Rule;
import com.kapil.ruleengine.model.RuleNamespace;
import com.kapil.ruleengine.rules.RuleEngine;
import com.kapil.ruleengine.rules.impl.insuranceRuleEngine.InsuranceDetails;
import com.kapil.ruleengine.rules.impl.insuranceRuleEngine.InsuranceInferenceEngine;
import com.kapil.ruleengine.rules.impl.insuranceRuleEngine.PolicyHolderDetails;
import com.kapil.ruleengine.rules.impl.loanRuleEngine.LoanDetails;
import com.kapil.ruleengine.rules.impl.loanRuleEngine.LoanInferenceEngine;
import com.kapil.ruleengine.rules.impl.loanRuleEngine.UserDetails;
import com.kapil.ruleengine.service.KnowledgeBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RuleEngineController {
    @Autowired
    private KnowledgeBaseService knowledgeBaseService;
    @Autowired
    private RuleEngine ruleEngine;
    @Autowired
    private LoanInferenceEngine loanInferenceEngine;
    @Autowired
    private InsuranceInferenceEngine insuranceInferenceEngine;

    @GetMapping(value = "/get-all-rules/{ruleNamespace}")
    public ResponseEntity<?> getRulesByNamespace(@PathVariable("ruleNamespace") String ruleNamespace) {
        RuleNamespace namespace = Enums.getIfPresent(RuleNamespace.class, ruleNamespace.toUpperCase()).or(RuleNamespace.DEFAULT);
        List<Rule> allRules = knowledgeBaseService.getAllRuleByNamespace(namespace.toString());
        return ResponseEntity.ok(allRules);
    }

    @GetMapping(value = "/get-all-rules")
    public ResponseEntity<?> getAllRules() {
        List<Rule> allRules = knowledgeBaseService.getAllRules();
        return ResponseEntity.ok(allRules);
    }

    @PostMapping(value = "/loan")
    public ResponseEntity<?> postUserLoanDetails(@RequestBody UserDetails userDetails) {
        LoanDetails result = (LoanDetails) ruleEngine.run(loanInferenceEngine, userDetails);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/insurance")
    public ResponseEntity<?> postCarLoanDetails(@RequestBody PolicyHolderDetails policyHolderDetails) {
        InsuranceDetails result = (InsuranceDetails) ruleEngine.run(insuranceInferenceEngine, policyHolderDetails);
        return ResponseEntity.ok(result);
    }
}