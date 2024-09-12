package com.eduplus.listadetarefascompose.data

import androidx.room.*


@Dao
interface TarefaDao {
    @Query("SELECT * FROM tarefas WHERE data = :data")
    suspend fun obterTarefasPorData(data: String): List<Tarefa>

    @Insert
    suspend fun adicionarTarefa(tarefa: Tarefa)

    @Update
    suspend fun editarTarefa(tarefa: Tarefa)

    @Delete
    suspend fun excluirTarefa(tarefa: Tarefa)
}
