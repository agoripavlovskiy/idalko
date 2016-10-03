package ut.com.agoripavlovskiy.jira.plugin.jira.customfields;

import com.agoripavlovskiy.jira.plugin.jira.customfields.PositiveIntegerCustomField;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class PositiveIntegerCustomFieldTestParametrized {

    private PositiveIntegerCustomField testClass;
    private CustomFieldValuePersister customFieldValuePersister = Mockito.mock(CustomFieldValuePersister.class);
    private GenericConfigManager genericConfigManager = Mockito.mock(GenericConfigManager.class);

    public PositiveIntegerCustomFieldTestParametrized() {
    }

    @Before
    public void setup() {
        testClass = new PositiveIntegerCustomField(customFieldValuePersister, genericConfigManager);
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data()
    {
        return Arrays.asList(new Object[][]{
                {3, "3"}, {7, "7"}, {50, "50"}, {190, "190"}, {1000, "1000"}
        });
    }

    public Integer input;
    public String expectedResult;

    public PositiveIntegerCustomFieldTestParametrized(int input, String expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Test
    public void test_getter_singular() {
        Assert.assertEquals(input, testClass.getSingularObjectFromString(expectedResult));
    }

    @Test
    public void test_getter_db() {
        Assert.assertEquals(new Integer(5), testClass.getObjectFromDbValue("5"));
    }
}
