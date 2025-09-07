package com.example.meucartaodevisitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meucartaodevisitas.ui.theme.MeuCartaoDeVisitasTheme
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuCartaoDeVisitasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MeuCartaoDeVisitas()
                }
            }
        }
    }
}

@Composable
fun MeuCartaoDeVisitas() {
    // Layout principal: Column para organizar tudo verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize() // Faz a coluna ocupar toda a tela
            .padding(32.dp), // Adiciona um padding de 32dp em todos os lados
        horizontalAlignment = Alignment.CenterHorizontally, // Centraliza todos os itens horizontalmente
        verticalArrangement = Arrangement.Center // Centraliza todos os itens verticalmente (opcional, para um layout mais centralizado)
    ) {
        // Imagem de Perfil (Avatar)
        Image(
            painter = painterResource(id = R.drawable.avatar), // Carrega a imagem da pasta drawable
            contentDescription = "Foto de Perfil do Usuário", // Importante para acessibilidade
            modifier = Modifier
                .size(150.dp) // Define o tamanho da imagem
                .clip(CircleShape) // Corta a imagem em um círculo
        )

        // Espaçamento entre o avatar e o nome
        Spacer(modifier = Modifier.height(24.dp))

        // Nome Completo
        Text(
            text = "Francisco Jucinery A. Vieira", // Nome completo
            fontSize = 26.sp, // Tamanho da fonte grande
            fontWeight = FontWeight.Bold, // Texto em negrito
            color = Color.Black // Cor do texto
        )

        // Título/Curso
        Text(
            text = "Tecnólogo em Sistemas para Internet", // Substitua pelo seu título
            fontSize = 18.sp, // Tamanho da fonte menor que o nome
            color = Color.Gray // Cor do texto em cinza para diferenciar
        )

        // Espaçamento maior antes da seção de contato
        Spacer(modifier = Modifier.height(48.dp))

        // Seção de Contato
        // Linha de Contato 1: E-mail
        Row(
            verticalAlignment = Alignment.CenterVertically, // Alinha ícone e texto no centro vertical
            modifier = Modifier.fillMaxWidth() // Ocupa toda a largura disponível
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_email), // Ícone de e-mail
                contentDescription = "Ícone de E-mail",
                modifier = Modifier.size(24.dp) // Tamanho do ícone
            )
            Spacer(modifier = Modifier.width(8.dp)) // Espaço pequeno entre ícone e texto
            Text(
                text = "francisco20240029708@alu.uern.br", // e-mail
                fontSize = 16.sp
            )
        }

        // Espaçamento entre as linhas de contato
        Spacer(modifier = Modifier.height(16.dp))

        // Linha de Contato 2: Telefone
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_phone), // Ícone de telefone
                contentDescription = "Ícone de Telefone",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "(99) 99999-9999", //telefone
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeuCartaoDeVisitasPreview() {
    MeuCartaoDeVisitasTheme {
        MeuCartaoDeVisitas()
    }
}