package br.com.zup.validator

import br.com.zup.autores.AutorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Documented
@Constraint(validatedBy = [UniqueEmailValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
annotation class UniqueEmail(
    val message: String = "Email duplicado",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)

@Singleton
class UniqueEmailValidator(val autorRepository: AutorRepository): ConstraintValidator<UniqueEmail, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<UniqueEmail>,
        context: ConstraintValidatorContext,
    ): Boolean {
        return !autorRepository.existsByEmail(value ?: "")
    }

}

