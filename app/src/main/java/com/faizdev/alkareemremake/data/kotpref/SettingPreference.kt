package com.faizdev.alkareemremake.data.kotpref

import com.chibatching.kotpref.KotprefModel

object SettingPreference: KotprefModel(){

     const val INDONESIA = 0
    private const val ENGLISH = 0

    var currentQari by intPref(0)
    var currentLanguage by intPref(INDONESIA)

    data class Qari(
        val qariName : String, // Nama Qari
        val qariId : String, // Untuk ID Qari
        val qariImgUrl : String // Gambar Qari
    )

    val listQari = listOf(
        Qari(
            "Mishary Rashid Alafasy",
            "Alafasy_64kbps",
            "https://lyricstranslate.com/files/styles/artist/public/5_6.jpg"
        ),
        Qari(
            "Abdurrahman As-Sudais",
            "Abdurrahman_As-Sudais_64kbps",
            "https://cdns.klimg.com/dream.co.id/resources//real/2017/07/06/177477/pembaca-quran-terpopuler-di-youtube.jpg"

        ),
        Qari(
            "Abdul Basit Abdul Samad",
            "AbdulSamad_64kbps_QuranExplorer.Com",
            "https://i1.sndcdn.com/artworks-000589378877-jj0uj1-t500x500.jpg"

        ),
        Qari(
            "Hudhaify",
            "Hudhaify_64kbps",
            "https://i.pinimg.com/originals/d4/3a/fe/d43afef3ae04b26f2deea396aa22a982.jpg"

        ),
    )



}

