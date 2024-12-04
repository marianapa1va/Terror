package com.example.filmesdeterror

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmesdeterror.ui.theme.FilmesDeTerrorTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmesDeTerrorTheme {

                    AppScreen()

            }
        }
    }
}

@Composable
fun AppScreen() {

    val currentScreen = remember { mutableStateOf("tela_inicial") }

    when (currentScreen.value) {
        "tela_inicial" -> TelaInicial(onNavigateToEscolherFilme = {
            currentScreen.value = "escolher_filme"
        })
        "escolher_filme" -> EscolherFilme(onBackClick = {
            currentScreen.value = "tela_inicial"
        })
    }
}

@Composable
fun TelaInicial(onNavigateToEscolherFilme: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo),
            contentDescription = "Background da primeira tela do Terror",
            modifier = Modifier
                .fillMaxWidth()
                .height(1250.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(40.dp)
        ) {
            Spacer(modifier = Modifier.height(38.dp))

            Text(
                text = "Bem-vindo ao Terror do Cinema!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = """
                    Não sabe qual filme de terror assistir hoje? Deixe que o destino decida por você!
                    
                    Aqui, cada escolha é uma surpresa, e o susto é garantido!
                    
                    Com um simples toque, você será levado para um universo de pesadelos, monstros e suspense. Prepare a pipoca (e os nervos!) porque a próxima experiência aterrorizante está a apenas um clique de distância.
                """.trimIndent(),
                color = Color.White,
                fontSize = 16.sp,
                lineHeight = 22.sp
            )
        }

        Button(
            onClick = onNavigateToEscolherFilme,
            modifier = Modifier
                .padding(top = 600.dp)
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(
                text = "Escolher Filme Aleatório",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun EscolherFilme(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.TopStart
    ) {


        Image(
            painter = painterResource(id = R.drawable.fundo),
            contentDescription = "Segundo Background",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )



        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
        ) {
            Text(
                text = "Esta noite, você será assombrado por:",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


        val imagens = listOf(
            R.drawable.imagem1,
            R.drawable.imagem2,
            R.drawable.imagem3,
            R.drawable.imagem4,
            R.drawable.imagem5,
            R.drawable.imagem6,
            R.drawable.imagem7,
            R.drawable.imagem8,
            R.drawable.imagem9,
            R.drawable.imagem10,
            R.drawable.imagem11
        )


        var imagemAtual by remember { mutableStateOf(imagens.first()) }


        var sorteando by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = imagemAtual),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))


            Button(
                onClick = {
                    sorteando = true
                },
                enabled = !sorteando
            ) {
                Text(text = "Sortear filme")
            }
        }


        if (sorteando) {
            LaunchedEffect(Unit) {
                for (i in 1..20) {
                    imagemAtual = imagens[Random.nextInt(imagens.size)]
                    delay(100)
                }
                sorteando = false
            }
        }
    }
}


