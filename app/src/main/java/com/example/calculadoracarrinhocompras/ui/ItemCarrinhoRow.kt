package com.exemplo.calculadoracarrinho.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun ItemCarrinhoRow(
    nome: String,
    descricao: String?,
    precoUnitario: Double,
    quantidade: Int,
    totalItem: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = nome,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = descricao ?: "Sem descrição",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val precoFmt = String.format(Locale("pt", "BR"), "R$ %.2f", precoUnitario)
                val totalFmt = String.format(Locale("pt", "BR"), "R$ %.2f", totalItem)

                Text(
                    text = precoFmt,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "x$quantidade",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = totalFmt,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}