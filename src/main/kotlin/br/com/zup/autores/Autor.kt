package br.com.zup.autores

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String,
    @Embedded val endereco: Endereco,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    val criadoEm: LocalDateTime = LocalDateTime.now()
}
