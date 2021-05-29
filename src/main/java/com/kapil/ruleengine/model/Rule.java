package com.kapil.ruleengine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rule {
    RuleNamespace ruleNamespace;
    String ruleId;
    String condition;
    String action;
    Integer priority;
    String description;
}
