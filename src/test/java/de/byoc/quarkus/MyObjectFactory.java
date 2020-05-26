package de.byoc.quarkus;

import io.cucumber.core.backend.ObjectFactory;
import io.quarkus.bootstrap.app.RunningQuarkusApplication;
import io.quarkus.test.junit.QuarkusTestExtension;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstances;
import org.junit.jupiter.engine.execution.ExtensionValuesStore;
import org.junit.jupiter.engine.execution.NamespaceAwareStore;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class MyObjectFactory implements ObjectFactory {

    private QuarkusTestExtension quarkus;
    private StubContext context;
    private RunningQuarkusApplication rqa;

    @Override
    public void start() {
        try {
            quarkus = new QuarkusTestExtension();
            context = new StubContext();
            quarkus.beforeAll(context);
            Field theField = QuarkusTestExtension.class.getDeclaredField("runningQuarkusApplication");
            theField.setAccessible(true);
            rqa = (RunningQuarkusApplication) theField.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        try {
            quarkus.afterAll(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> aClass) {
        return (T) rqa.instance(aClass);
    }

    private static class StubContext implements ExtensionContext {
        @Override
        public Optional<ExtensionContext> getParent() {
            return Optional.empty();
        }

        @Override
        public ExtensionContext getRoot() {
            return this;
        }

        @Override
        public String getUniqueId() {
            return "stub-extension-context";
        }

        @Override
        public String getDisplayName() {
            return "Stub Context";
        }

        @Override
        public Set<String> getTags() {
            return Collections.emptySet();
        }

        @Override
        public Optional<AnnotatedElement> getElement() {
            return Optional.empty();
        }

        @Override
        public Optional<Class<?>> getTestClass() {
            return Optional.of(RunCucumber.class);
        }

        @Override
        public Optional<TestInstance.Lifecycle> getTestInstanceLifecycle() {
            return Optional.empty();
        }

        @Override
        public Optional<Object> getTestInstance() {
            return Optional.empty();
        }

        @Override
        public Optional<TestInstances> getTestInstances() {
            return Optional.empty();
        }

        @Override
        public Optional<Method> getTestMethod() {
            return Optional.empty();
        }

        @Override
        public Optional<Throwable> getExecutionException() {
            return Optional.empty();
        }

        @Override
        public Optional<String> getConfigurationParameter(String s) {
            return Optional.empty();
        }

        @Override
        public void publishReportEntry(Map<String, String> map) {

        }

        @Override
        public Store getStore(Namespace namespace) {
            return new NamespaceAwareStore(new ExtensionValuesStore(null), namespace);
        }
    }
}
