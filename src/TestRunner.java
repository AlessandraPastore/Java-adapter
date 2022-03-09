import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * <b>Test Runner of all the tests for the adapters.</b>
 * @safe.testsuiteexecutionrecords this test suite is based on Junit 4.13 (no 4.12 nor 5)
 * @safe.executionvariables requires junit-4.13.jar and hamcrest-core-1.3.jar to be present in the classpath
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MapAdapterTest.class);
        System.out.println("Test per MapAdapter: ");
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());

        result = JUnitCore.runClasses(SetAdapterTest.class);
        System.out.println("Test per SetAdapter: ");
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());

        result = JUnitCore.runClasses(CollectionAdapterTest.class);
        System.out.println("Test per CollectionAdapter: ");
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());

        result = JUnitCore.runClasses(ListAdapterTest.class);
        System.out.println("Test per ListAdapter: ");
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
