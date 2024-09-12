package com.eduplus.listadetarefascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eduplus.listadetarefascompose.ui.ListaDeTarefasScreen
import com.eduplus.listadetarefascompose.ui.TarefasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tarefaViewModel: TarefasViewModel = viewModel()
            ListaDeTarefasScreen(tarefasViewModel = tarefaViewModel)
        }
    }
}
