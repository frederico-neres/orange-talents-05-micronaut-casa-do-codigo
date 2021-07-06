package br.com.zup.autores

import br.com.zup.validator.CPF
import br.com.zup.validator.UniqueEmail
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:CPF val cpf: String,
    @field:NotBlank @field:Email @field:UniqueEmail val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val numero: String,
    @field:NotBlank val cep: String
) {

    fun paraAutor(enderecoResponse: EnderecoResponse): Autor {
        val endereco = enderecoResponse.paraEndereco(numero, cep)

        return Autor(
            nome = nome,
            cpf = cpf,
            email = email,
            descricao = descricao,
            endereco = endereco
        )
    }
}