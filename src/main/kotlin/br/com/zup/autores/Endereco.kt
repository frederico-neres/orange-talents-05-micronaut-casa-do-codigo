package br.com.zup.autores

import javax.persistence.Embeddable

@Embeddable
class Endereco(
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val numero: String,
    val cep: String
)
