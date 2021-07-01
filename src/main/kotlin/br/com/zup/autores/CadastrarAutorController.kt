package br.com.zup.autores

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/api/autores")
class CadastrarAutorController {

    @Post
    fun cadastrar(@Body @Valid request: NovoAutorRequest){
        println(request)
    }
}