package com.dicoding.themovieapps.data.source.local

import com.dicoding.themovieapps.data.entity.TvEntity
import com.dicoding.themovieapps.data.source.local.db.TvDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface TvLocalDataSource {
    fun getTvList(series: String): Flow<List<TvEntity>>

    suspend fun insertAllTv(tvList: List<TvEntity>)
}

@Singleton
class TvLocalDataSourceImpl @Inject constructor(private val tvDao: TvDao) : TvLocalDataSource {
    override fun getTvList(series: String): Flow<List<TvEntity>> = tvDao.getAllTv(series)

    override suspend fun insertAllTv(tvList: List<TvEntity>) = tvDao.insertAllTv(tvList)
}