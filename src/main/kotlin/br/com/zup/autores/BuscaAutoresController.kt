package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue


@Controller("/api/autores")
class BuscaAutoresController(private val autorRepository: AutorRepository) {

    @Get
    fun buscarTodos(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

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

class AutorResponse(autor: Autor) {
    val id = autor.id
    val nome = autor.nome
    val email = autor.email
    val descricao = autor.descricao
    val logradouro = autor.endereco.logradouro
    val complemento = autor.endereco.complemento
    val bairro = autor.endereco.bairro
    val localidade = autor.endereco.localidade
    val uf = autor.endereco.uf
    val numero = autor.endereco.numero
    val cep = autor.endereco.cep
}