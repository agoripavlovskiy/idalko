package com.agoripavlovskiy.jira.plugin.jira.customfields;

import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.plugin.spring.scanner.annotation.component.ClasspathComponent;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService
@Named("positiveIntegerCustomField")
public class PositiveIntegerCustomField extends AbstractSingleFieldType<Integer> {

    @Inject
    public PositiveIntegerCustomField(@ComponentImport CustomFieldValuePersister customFieldValuePersister, @ComponentImport GenericConfigManager genericConfigManager) {
        super(customFieldValuePersister, genericConfigManager);
    }

    @Nonnull
    @Override
    protected PersistenceFieldType getDatabaseType() {
        return PersistenceFieldType.TYPE_LIMITED_TEXT;
    }

    @Nullable
    @Override
    public Object getDbValueFromObject(Integer customFieldObject) {
        return getStringFromSingularObject(customFieldObject);
    }

    @Nullable
    @Override
    public Integer getObjectFromDbValue(@Nonnull Object databaseValue) throws FieldValidationException {
        return getSingularObjectFromString((String) databaseValue);
    }

    @Override
    public String getStringFromSingularObject(Integer singularObject) {
        if (singularObject == null)
            return null;
        else
            return singularObject.toString();
    }

    @Override
    public Integer getSingularObjectFromString(String string) throws FieldValidationException {
        if (string == null)
            return null;
        try
        {
            final Integer integer = new Integer(string);
            // Check that we have a positive value
            if (integer < 0)
            {
                throw new FieldValidationException(
                        "Value should be a positive integer.");
            }
            return integer;
        }
        catch (NumberFormatException ex)
        {
            throw new FieldValidationException("Not a valid number.");
        }
    }

}