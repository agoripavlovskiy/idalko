package ut.com.agoripavlovskiy.jira.plugin.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import org.junit.*;
import com.agoripavlovskiy.jira.plugin.jira.customfields.PositiveIntegerCustomField;
import org.mockito.Mockito;

public class PositiveIntegerCustomFieldTest {

    private PositiveIntegerCustomField testClass;
    private CustomFieldValuePersister customFieldValuePersister = Mockito.mock(CustomFieldValuePersister.class);
    private GenericConfigManager genericConfigManager = Mockito.mock(GenericConfigManager.class);

    public PositiveIntegerCustomFieldTest() {
    }

    @Before
    public void setup() {
        testClass = new PositiveIntegerCustomField(customFieldValuePersister, genericConfigManager);
    }

    @Test
    public void test_getters_singular() {
        Assert.assertEquals(new Integer(5), testClass.getSingularObjectFromString("5"));
        Assert.assertEquals("5", testClass.getStringFromSingularObject(5));
    }

    @Test
    public void test_getters_db() {
        Assert.assertEquals(new Integer(5), testClass.getObjectFromDbValue("5"));
        Assert.assertEquals("5", testClass.getDbValueFromObject(5));
    }

    @Test(expected=FieldValidationException.class)
    public void test_get_singular_with_not_positive_value() {
        testClass.getSingularObjectFromString("-5");
    }

    @Test(expected=FieldValidationException.class)
    public void test_get_singular_with_string() {
        testClass.getSingularObjectFromString("string value");
    }

    @Test(expected=AssertionError.class)
    public void test_getters_and_setters_singular() {
        Assert.assertEquals(5, testClass.getDbValueFromObject(5));
    }

}
