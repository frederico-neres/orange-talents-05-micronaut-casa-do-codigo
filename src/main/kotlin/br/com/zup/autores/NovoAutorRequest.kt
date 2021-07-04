package br.com.zup.autores

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val numero: String,
    @field:NotBlank val cep: String
) {

    fun paraAutor(enderecoResponse: EnderecoResponse): Autor {
        val endereco = enderecoResponse.paraEndereco(numero, cep)
        println(endereco.logradouro)

        return Autor(
            nome = nome,
            email = email,
            descricao = descricao,
            endereco = endereco
        )
    }
}