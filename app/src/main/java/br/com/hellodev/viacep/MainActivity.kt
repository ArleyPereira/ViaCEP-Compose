package br.com.hellodev.viacep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.hellodev.viacep.features.form.screen.FormScreen
import br.com.hellodev.viacep.ui.theme.ViaCEPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViaCEPTheme {
                FormScreen()
            }
        }
    }
}
