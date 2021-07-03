package br.com.zup.autores

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.PathVariable
import io.micronaut.validation.Validated
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@Validated
@Controller("/api/autores/{id}")
class AtualizaAutorController(private val autorRepository: AutorRepository) {

    @Patch
    fun atualiza(
        @PathVariable id: Long,
        @Body @Valid atualizarAutorDescricaoRequest: AtualizarAutorDescricaoRequest
    ): HttpResponse<Any>{

        val optionalAutor = autorRepository.findById(id)
        if(optionalAutor.isEmpty) return HttpResponse.notFound()

        val autor = optionalAutor.get()
        autor.descricao = atualizarAutorDescricaoRequest.descricao
        autorRepository.update(autor)

        return HttpResponse.ok(AutorResponse(autor))
    }

}

@Introspected
class AtualizarAutorDescricaoRequest(@field:NotBlank val descricao: String)
