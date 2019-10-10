package test20191011runner;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicTest {

    public static final Logger LOG = LoggerFactory.getLogger(BasicTest.class);

    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();

        ReleaseId newReleaseId = kieServices.newReleaseId("org.drools", "test20191011kjar", "0.0.1-SNAPSHOT");
        KieContainer kieContainer = kieServices.newKieContainer(newReleaseId);

        DMNRuntime dmnRuntime = KieRuntimeFactory.of(kieContainer.newKieBase(kieServices.newKieBaseConfiguration())).get(DMNRuntime.class);

        String namespace = "https://github.com/kiegroup/drools/kie-dmn/_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF";
        String modelName = "Traffic Violation";

        DMNModel dmnModel = dmnRuntime.getModel(namespace, modelName);
        DMNContext emptyContext = dmnRuntime.newContext();

        DMNResult evaluateAll = dmnRuntime.evaluateAll(dmnModel, emptyContext);
        LOG.info("{}", evaluateAll);
    }
}
