package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable

@Controller("/api/autores/{id}")
class DeletarAutorController(private val autorRepository: AutorRepository) {

    @Delete
    fun deletar(@PathVariable id: Long): HttpResponse<Any> {
        val existsById = autorRepository.existsById(id)
        if(!existsById) return HttpResponse.notFound()

        autorRepository.deleteById(id)
        return HttpResponse.ok()
    }

}