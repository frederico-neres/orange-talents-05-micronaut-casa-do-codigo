package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/api/autores")
class BuscaAutoresController(private val autorRepository: AutorRepository) {

    @Get
    fun buscarTodos(): HttpResponse<List<AutorResponse>> {
        val autores = autorRepository.findAll()
        val autoresResponse = autores.map { autor -> AutorResponse(autor) }

        return HttpResponse.ok(autoresResponse);
    }
}

class AutorResponse(autor: Autor) {
    val id = autor.id
    val nome = autor.nome
    val descricao = autor.descricao
}