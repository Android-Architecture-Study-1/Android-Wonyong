package com.example.musinsa.util

import java.util.*

object RandomData {
    fun getName(): String {
        val randomInt = Random(7).nextInt()
        val nameList = mutableListOf(
            "강",
            "정",
            "박",
            "김",
            "문",
            "심",
            "우",
        )
        return nameList[randomInt]
    }

    fun getLastName(): String {
        val randomInt = Random(7).nextInt()
        val lastNameList = mutableListOf(
            "원용",
            "지연",
            "현정",
            "수빈",
            "다빈",
            "채영",
            "진실",
        )
        return lastNameList[randomInt]
    }

    fun getProfile(): String {
        val randomInt = Random(7).nextInt()
        val profileList = mutableListOf(
            "https://avatars.githubusercontent.com/u/81394850?v=4",
            "https://avatars.githubusercontent.com/u/62979643?v=4",
            "https://avatars.githubusercontent.com/u/62291759?v=4",
            "https://avatars.githubusercontent.com/u/82709044?v=4",
            "https://avatars.githubusercontent.com/u/62435316?v=4",
            "https://avatars.githubusercontent.com/u/70698151?v=4",
            "https://avatars.githubusercontent.com/u/97952129?v=4"
        )
        return profileList[randomInt]
    }
}