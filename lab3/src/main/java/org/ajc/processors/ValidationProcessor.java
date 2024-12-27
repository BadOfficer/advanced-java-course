package org.ajc.processors;

import com.google.auto.service.AutoService;
import org.ajc.annotations.MaxValue;
import org.ajc.annotations.MinValue;
import org.ajc.annotations.NotNull;
import org.ajc.annotations.StringLength;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * Annotation processor to validate field-level annotations such as @NotNull, @StringLength,
 * @MinValue, and @MaxValue. This processor ensures that these annotations are applied to
 * the correct field types during compile-time, preventing misconfigurations.
 */
@SupportedAnnotationTypes({
        "org.ajc.annotations.NotNull",
        "org.ajc.annotations.StringLength",
        "org.ajc.annotations.MaxValue",
        "org.ajc.annotations.MinValue"
})
@SupportedSourceVersion(SourceVersion.RELEASE_22)
@AutoService(Processor.class)
public class ValidationProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;

    /**
     * Initializes the processor by retrieving utility classes for type and element operations.
     *
     * @param processingEnv The processing environment providing facilities to access elements,
     *                      types, and diagnostics.
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
    }

    /**
     * Processes the annotations by validating fields in classes containing supported annotations.
     *
     * @param annotations The set of annotation types processed by this processor.
     * @param roundEnv    Environment for the current and prior annotation processing rounds.
     * @return true if the annotations are processed by this processor, false otherwise.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getRootElements()) {
            if (element.getKind() == ElementKind.CLASS) {
                validateFields((TypeElement) element);
            }
        }
        return true;
    }

    /**
     * Iterates over fields in the specified class and validates each based on their annotations.
     *
     * @param classElement The class element whose fields are to be validated.
     */
    private void validateFields(TypeElement classElement) {
        for (Element field : classElement.getEnclosedElements()) {
            if (field.getKind() == ElementKind.FIELD) {
                if (field.getAnnotation(NotNull.class) != null) {
                    validateNotNullField(field);
                }

                if (field.getAnnotation(StringLength.class) != null) {
                    validateStringLengthField(field);
                }

                if (field.getAnnotation(MinValue.class) != null) {
                    validateMinValueField(field);
                }

                if (field.getAnnotation(MaxValue.class) != null) {
                    validateMaxValueField(field);
                }
            }
        }
    }

    /**
     * Validates fields annotated with @NotNull. Ensures the field type is String.
     *
     * @param field The field element to validate.
     */
    private void validateNotNullField(Element field) {
        if (!isTypeOf(field, "java.lang.String")) {
            reportError(field, "@NotNull can only be applied to fields of type String");
        }
    }

    /**
     * Validates fields annotated with @StringLength. Ensures the field type is String.
     *
     * @param field The field element to validate.
     */
    private void validateStringLengthField(Element field) {
        if (!isTypeOf(field, "java.lang.String")) {
            reportError(field, "@StringLength can only be applied to fields of type String");
        }
    }

    /**
     * Validates fields annotated with @MinValue. Ensures the field type is int or Integer.
     *
     * @param field The field element to validate.
     */
    private void validateMinValueField(Element field) {
        if (!isTypeOf(field, "int") && !isTypeOf(field, "java.lang.Integer")) {
            reportError(field, "@MinValue can only be applied to fields of type int or Integer");
        }
    }

    /**
     * Validates fields annotated with @MaxValue. Ensures the field type is int or Integer.
     *
     * @param field The field element to validate.
     */
    private void validateMaxValueField(Element field) {
        if (!isTypeOf(field, "int") && !isTypeOf(field, "java.lang.Integer")) {
            reportError(field, "@MaxValue can only be applied to fields of type int or Integer");
        }
    }

    /**
     * Checks if the field is of the expected type.
     *
     * @param element     The field element to check.
     * @param expectedType The fully qualified name of the expected type.
     * @return true if the field type matches the expected type, false otherwise.
     */
    private boolean isTypeOf(Element element, String expectedType) {
        TypeMirror fieldType = element.asType();
        TypeElement expectedElement = elementUtils.getTypeElement(expectedType);

        if (expectedElement != null) {
            TypeMirror expectedTypeMirror = expectedElement.asType();
            return typeUtils.isSameType(fieldType, expectedTypeMirror);
        }

        return false;
    }

    /**
     * Reports an error during annotation processing.
     *
     * @param element The element that caused the error.
     * @param message The error message to display.
     */
    private void reportError(Element element, String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message, element);
    }
}
