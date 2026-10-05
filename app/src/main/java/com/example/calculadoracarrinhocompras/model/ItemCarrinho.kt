package com.exemplo.calculadoracarrinho.model

data class ItemCarrinho(
    val produto: Produto,
    val quantidade: Int
) : Pagavel {

    fun precoComDesconto(): Double {
        return produto.preco * (1.0 - (produto.descontoPercentual / 100.0))
    }

    override fun calcularTotal(): Double {
        return precoComDesconto() * quantidade
    }

    fun calcularSubtotalBruto(): Double {
        return produto.preco * quantidade
    }

    fun calcularValorDesconto(): Double {
        return (produto.preco - precoComDesconto()) * quantidade
    }
}