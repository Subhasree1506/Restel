package com.pramati.restel.core.model;

import com.pramati.restel.core.model.assertion.RestelAssertion;
import com.pramati.restel.core.model.functions.RestelFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * Represent the execution definition, which will actually be exeucted, by pulling in the referenced
 * test definition.
 *
 * @author kannanr
 */
@Data
public class RestelExecutionGroup {
  private String executionGroupName;
  private String testDefinitionName;
  private String testSuiteName;
  private List<RestelExecutionGroup> dependsOn;
  private Map<String, Object> executionParams;
  private boolean testExecutionEnable;
  private List<String> parentExecutions = new ArrayList<>();
  private List<RestelAssertion> assertions;
  private Map<String, RestelFunction> functions;

  public void addParentExecution(String parentExec) {
    parentExecutions.add(parentExec);
  }
}
