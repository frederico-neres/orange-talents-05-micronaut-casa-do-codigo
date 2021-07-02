package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/api/autores")
class CadastrarAutorController(private val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest): HttpResponse<Any>{
        val autor: Autor = request.paraAutor()
        autorRepository.save(autor)

        val uri = UriBuilder.of("/api/autores/{id}").expand(mutableMapOf("id" to autor.id))
        return HttpResponse.created(uri);
    }
}