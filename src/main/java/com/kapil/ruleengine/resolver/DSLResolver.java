package com.kapil.ruleengine.resolver;

public interface DSLResolver {
    String getResolverKeyword();

    Object resolveValue(String keyword);
}
