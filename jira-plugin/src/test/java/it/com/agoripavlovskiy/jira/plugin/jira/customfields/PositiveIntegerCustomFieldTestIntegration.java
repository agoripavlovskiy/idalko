package it.com.agoripavlovskiy.jira.plugin.jira.customfields;

import com.agoripavlovskiy.jira.plugin.jira.customfields.PositiveIntegerCustomField;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class PositiveIntegerCustomFieldTestIntegration {

    private final ApplicationProperties applicationProperties;
    private final PositiveIntegerCustomField positiveIntegerCustomField;

    public PositiveIntegerCustomFieldTestIntegration(@ComponentImport ApplicationProperties applicationProperties, @ComponentImport PositiveIntegerCustomField positiveIntegerCustomField)
    {
        this.applicationProperties = applicationProperties;
        this.positiveIntegerCustomField = positiveIntegerCustomField;
    }

    @Test
    public void testPositiveIntegerCustomField()
    {
        assertEquals("names do not match!", "myComponent:" + applicationProperties.getDisplayName(), "myComponent:JIRA");
        Assert.assertEquals(new Integer(5), positiveIntegerCustomField.getObjectFromDbValue("5"));
        Assert.assertEquals("5", positiveIntegerCustomField.getDbValueFromObject(5));
    }

    @Test
    public void test_getters_singular() {
        Assert.assertEquals(new Integer(5), positiveIntegerCustomField.getSingularObjectFromString("5"));
        Assert.assertEquals("5", positiveIntegerCustomField.getStringFromSingularObject(5));
    }

    @Test
    public void test_getters_db() {
        Assert.assertEquals(new Integer(5), positiveIntegerCustomField.getObjectFromDbValue("5"));
        Assert.assertEquals("5", positiveIntegerCustomField.getDbValueFromObject(5));
    }

    @Test(expected=FieldValidationException.class)
    public void test_get_singular_with_not_positive_value() {
        positiveIntegerCustomField.getSingularObjectFromString("-5");
    }

    @Test(expected=FieldValidationException.class)
    public void test_get_singular_with_string() {
        positiveIntegerCustomField.getSingularObjectFromString("string value");
    }

    @Test(expected=AssertionError.class)
    public void test_getters_and_setters_singular() {
        Assert.assertEquals(5, positiveIntegerCustomField.getDbValueFromObject(5));
    }
}
