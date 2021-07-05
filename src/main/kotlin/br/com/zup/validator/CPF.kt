package br.com.zup.validator

import io.micronaut.validation.validator.constraints.ConstraintValidator
import java.lang.annotation.Documented
import javax.inject.Singleton
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Documented
@Constraint(validatedBy = [CPFValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
annotation class CPF(val message: String = "CPF Inválido",
                     val groups: Array<KClass<*>> = [],
                     val payload: Array<KClass<out Payload>> = [])
@Singleton
class CPFValidator: ConstraintValidator<CPF, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: io.micronaut.core.annotation.AnnotationValue<CPF>,
        context: io.micronaut.validation.validator.constraints.ConstraintValidatorContext,
    ): Boolean {
        return isCPF(value ?: "")
    }

    fun isCPF(document: String): Boolean {
        if (document.isEmpty()) return false

        val numbers = document.filter { it.isDigit() }.map {
            it.toString().toInt()
        }

        if (numbers.size != 11) return false

        //repeticao
        if (numbers.all { it == numbers[0] }) return false

        //digito 1
        val dv1 = ((0..8).sumOf { (it + 1) * numbers[it] }).rem(11).let {
            if (it >= 10) 0 else it
        }

        val dv2 = ((0..8).sumOf { it * numbers[it] }.let { (it + (dv1 * 9)).rem(11) }).let {
            if (it >= 10) 0 else it
        }

        return numbers[9] == dv1 && numbers[10] == dv2
    }
}
