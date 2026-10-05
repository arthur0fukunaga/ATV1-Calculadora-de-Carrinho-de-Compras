package com.exemplo.calculadoracarrinho.utils

import android.util.Log
import com.exemplo.calculadoracarrinho.model.ItemCarrinho
import java.util.Locale

fun gerarRelatorioLogcat(itens: List<ItemCarrinho>) {
    Log.d("CarrinhoRelatorio", "========== RELATÓRIO DE PRODUTOS COM DESCONTO ==========")

    itens
        .filter { it.produto.descontoPercentual > 0.0 }
        .sortedByDescending { it.calcularTotal() }
        .forEach { item ->
            val nome = item.produto.nome
            val valorFinalFormatado = String.format(Locale("pt", "BR"), "R$ %.2f", item.calcularTotal())
            Log.d("CarrinhoRelatorio", "Produto: $nome | Valor Final: $valorFinalFormatado")
        }

    Log.d("CarrinhoRelatorio", "=========================================================")
}