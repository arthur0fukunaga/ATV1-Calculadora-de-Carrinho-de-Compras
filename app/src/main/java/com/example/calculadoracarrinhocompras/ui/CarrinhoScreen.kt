package com.exemplo.calculadoracarrinho.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.exemplo.calculadoracarrinho.model.ItemCarrinho
import com.exemplo.calculadoracarrinho.model.Produto
import com.exemplo.calculadoracarrinho.utils.gerarRelatorioLogcat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarrinhoScreen() {

    val p1 = Produto(
        nome = "Notebook Dell Inspiron 15 3000 com processador Intel Core i5 de última geração",
        preco = 3499.00,
        descricao = "Um notebook rápido para tarefas do dia a dia e trabalho avançado.",
        descontoPercentual = 5.0
    )
    val p2 = Produto(
        nome = "Mouse sem fio",
        preco = 89.90,
        descricao = null,
        descontoPercentual = 0.0
    )
    val p3 = Produto(
        nome = "Teclado mecânico RGB com Switch azul, ABNT2",
        preco = 349.90,
        descricao = "Switch azul, ABNT2",
        descontoPercentual = 0.0
    )
    val p4 = Produto(
        nome = "Monitor Gamer 27 Polegadas Quad HD 165Hz ultra wide com ajuste de altura",
        preco = 1200.00,
        descricao = "Excelente para jogos e multitarefa.",
        descontoPercentual = 10.0
    )
    val p5 = Produto(
        nome = "Fone Bluetooth Noise Cancelling",
        preco = 250.00,
        descricao = null,
        descontoPercentual = 0.0
    )
    val p6 = Produto(
        nome = "Suporte Articulado para Monitor",
        preco = 150.00,
        descricao = "Suporta até 9kg com pistão a gás.",
        descontoPercentual = 0.0
    )

    val carrinho = listOf(
        ItemCarrinho(p1, quantidade = 2),
        ItemCarrinho(p2, quantidade = 1),
        ItemCarrinho(p3, quantidade = 1),
        ItemCarrinho(p4, quantidade = 1),
        ItemCarrinho(p5, quantidade = 1),
        ItemCarrinho(p6, quantidade = 1)
    )

    LaunchedEffect(Unit) {
        gerarRelatorioLogcat(carrinho)
    }

    val subtotalBruto = carrinho.sumOf { it.calcularSubtotalBruto() }
    val totalDescontos = carrinho.sumOf { it.calcularValorDesconto() }
    val valorTotalFinal = subtotalBruto - totalDescontos

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🛒 Meu Carrinho", style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(carrinho) { item ->
                    ItemCarrinhoRow(
                        nome = item.produto.nome,
                        descricao = item.produto.descricao,
                        precoUnitario = item.produto.preco,
                        quantidade = item.quantidade,
                        totalItem = item.calcularTotal()
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            val subtotalFmt = String.format(Locale("pt", "BR"), "R$ %.2f", subtotalBruto)
            val descontoFmt = String.format(Locale("pt", "BR"), "-R$ %.2f", totalDescontos)
            val totalFmt = String.format(Locale("pt", "BR"), "R$ %.2f", valorTotalFinal)

            ResumoLinha(label = "Subtotal", valor = subtotalFmt, style = MaterialTheme.typography.bodyLarge)
            ResumoLinha(label = "Descontos", valor = descontoFmt, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            ResumoLinha(label = "TOTAL", valor = totalFmt, style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun ResumoLinha(
    label: String,
    valor: String,
    style: androidx.compose.ui.text.TextStyle
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = style)
        Text(text = valor, style = style)
    }
}