package br.com.zup.autores

class EnderecoResponse(
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String
) {
    fun paraEndereco(numero: String, cep: String): Endereco {
        return Endereco(
            logradouro = logradouro,
            complemento = complemento,
            bairro = bairro,
            localidade = localidade,
            uf = uf,
            numero = numero,
            cep = cep
        )
    }
}