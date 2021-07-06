package br.com.zup.autores

import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue


@Controller("/api/autores")
class BuscaAutoresController(private val autorRepository: AutorRepository) {

    @Get
    fun buscarTodos(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        return executa(email)
    }

    @Get(value = "/xml", produces = [ MediaType.APPLICATION_XML ])
    fun buscarTodosXml(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        return executa(email)
    }

    fun executa(email: String): HttpResponse<Any> {
        if(email.isBlank()) {
            val autores = autorRepository.findAll()
            val autoresResponse = autores.map { autor -> AutorResponse(autor) }

            return HttpResponse.ok(autoresResponse);
        }

        val optionalAutor = autorRepository.buscaPorAutor(email)
        if(optionalAutor.isEmpty) return HttpResponse.notFound()

        val autor = optionalAutor.get()
        return HttpResponse.ok(AutorResponse(autor))
    }
}

data class AutorResponse(
    val id: Long,
    val nome: String,
    val cpf: String,
    val email: String,
    val descricao: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val numero: String,
    val cep: String
) {
    constructor(autor: Autor): this(
            id = autor.id!!,
            nome = autor.nome,
            cpf = autor.cpf,
            email = autor.email,
            descricao = autor.descricao,
            logradouro = autor.endereco.logradouro,
            complemento = autor.endereco.complemento,
            bairro = autor.endereco.bairro,
            localidade = autor.endereco.localidade,
            uf = autor.endereco.uf,
            numero = autor.endereco.numero,
            cep = autor.endereco.cep,
    )
}



