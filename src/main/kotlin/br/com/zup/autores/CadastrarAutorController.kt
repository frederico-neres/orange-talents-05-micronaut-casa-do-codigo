package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/api/autores")
class CadastrarAutorController(private val autorRepository: AutorRepository) {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest){
        val autor: Autor = request.paraAutor()
        autorRepository.save(autor)
    }
}