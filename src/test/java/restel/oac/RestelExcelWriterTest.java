package restel.oac;

import com.pramati.restel.core.parser.dto.BaseConfig;
import com.pramati.restel.core.parser.dto.TestDefinitions;
import com.pramati.restel.core.parser.dto.TestScenarios;
import com.pramati.restel.core.parser.dto.TestSuites;
import com.pramati.restel.exception.RestelException;
import com.pramati.restel.oas.RestelExcelWriter;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.testng.Assert;

public class RestelExcelWriterTest {

  private List<TestDefinitions> createTestDefinition() {
    TestDefinitions def = new TestDefinitions();
    def.setExpectedHeader("header");
    def.setExpectedResponse("response");
    def.setTags(Set.of("Sample"));
    def.setRequestBodyParams("body");
    def.setRequestHeaders("reqHeader");
    def.setRequestMethod("method");
    def.setRequestUrl("url");
    def.setRequestQueryParams("query");
    def.setCaseDescription("desc");
    def.setCaseUniqueName("name");
    def.setRequestPostCallHook("pre");
    def.setRequestPostCallHook("post");
    def.setExpectedHeaderMatcher("Matcher");
    def.setExpectedResponseMatcher("Matcher");
    def.setAcceptedStatusCodes(Collections.singletonList("200"));
    return Collections.singletonList(def);
  }

  private List<TestSuites> createTestSuite() {
    TestSuites def = new TestSuites();
    def.setSuiteEnable(false);
    def.setSuiteUniqueName("name");
    def.setSuiteParams("param");
    def.setSuiteDescription("des");
    def.setDependsOn("dep");
    return Collections.singletonList(def);
  }

  private List<TestScenarios> createTestSuiteExection() {
    TestScenarios def = new TestScenarios();
    def.setScenarioUniqueName("name");
    def.setScenarioEnabled(false);
    def.setTestSuite("suite");
    def.setScenarioParams("param");
    def.setTestCases(List.of("test"));
    def.setDependsOn("dep");
    return Collections.singletonList(def);
  }

  public BaseConfig createBaseConfig() {
    BaseConfig base = new BaseConfig();
    base.setAppName("app");
    base.setDefaultHeader("header");
    base.setBaseUrl("url");
    return base;
  }

  @Test
  public void testWriteTestDefinition() throws IOException {
    String file = "src/test/resources/test.xlsx";
    RestelExcelWriter writer = new RestelExcelWriter();
    writer.writeTestDefinitions(createTestDefinition());
    writer.writeToFile(file);
    FileUtils.forceDelete(new File(file));
    Assert.assertEquals(
        writer.getTestDefinitionSheet().getRow(1).getCell(0).getStringCellValue(), "name");
  }

  @Test
  public void testWriteTestSuite() {
    RestelExcelWriter writer = new RestelExcelWriter();
    writer.writeTestSuites(createTestSuite());
    Assert.assertEquals(
        writer.getTestSuitesSheet().getRow(1).getCell(0).getStringCellValue(), "name");
  }

  @Test
  public void testWriteBaseConfig() {
    RestelExcelWriter writer = new RestelExcelWriter();
    writer.writeBaseConfig(createBaseConfig());
    Assert.assertEquals(
        writer.getBaseConfigSheet().getRow(0).getCell(1).getStringCellValue(), "app");
  }

  @Test
  public void testWriteTestSuiteExec() {
    RestelExcelWriter writer = new RestelExcelWriter();
    writer.writeTestSuiteExecution(createTestSuiteExection());
    Assert.assertEquals(
        writer.getTestSuiteExecutionSheet().getRow(1).getCell(0).getStringCellValue(), "name");
  }

  @Test(expected = RestelException.class)
  public void testWritetoFileInvalid() {
    RestelExcelWriter writer = new RestelExcelWriter();
    writer.writeToFile(null);
  }
}
