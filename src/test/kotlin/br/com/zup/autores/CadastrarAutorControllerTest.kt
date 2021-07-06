package br.com.zup.autores

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

import javax.inject.Inject

@MicronautTest
internal class CadastrarAutorControllerTest {

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve cadastrar um novo autor`() {

        val novoAutorRequest = NovoAutorRequest(
            nome = "Fred",
            cpf = "375.821.440-82", // cpf fake
            email = "fred@email.com", // email fake
            descricao = "Desenvolvedor Pleno Java|Spring|Kotlin|Micronaut",
            numero = "3",
            cep = "89237-452"
        )

        val enderecoResponse = EnderecoResponse(
            logradouro = "Rua Antônio Meras Sagas",
            complemento = "casa",
            bairro = "Vila Nova",
            localidade = "Joinville",
            uf = "SC"
        )

        Mockito.`when`(enderecoClient.buscar(novoAutorRequest.cep))
            .thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/api/autores", novoAutorRequest)
        val response = client.toBlocking().exchange(request, Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("location")!!.matches("/api/autores/\\d+".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}