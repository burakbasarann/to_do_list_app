package com.basaran.odev7.room

import androidx.room.*
import com.basaran.odev7.entity.Yapilacaklar

@Dao
interface YapilacaklarDao {
    @Query("SELECT * FROM yapilacaklar")
    suspend fun tumYapilacaklar(): List<Yapilacaklar>

    @Insert
    suspend fun yapilacakIsEkle(yapilacaklar: Yapilacaklar)

    @Update
    suspend fun yapilacakIsGuncelle(yapilacaklar: Yapilacaklar)

    @Delete
    suspend fun yapilacakIsSil(yapilacaklar: Yapilacaklar)

    @Query("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%'|| :aramaKelimesi ||'%'")
    suspend fun yapilacakIsArama(aramaKelimesi: String): List<Yapilacaklar>
}