package br.com.zup.autores

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/api/autores")
class CadastrarAutorController {

    @Post
    fun cadastrar(@Body request: NovoAutorRequest){
        println(request)
    }
}