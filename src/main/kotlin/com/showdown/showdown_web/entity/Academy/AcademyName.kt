package com.showdown.showdown_web.entity.Academy

enum class AcademyName {
    OneMillion, YGX, OFD;

    companion object {
        fun convertStringToAcademyName(name: String?): AcademyName? {
            return when (name) {
                OneMillion.name -> OneMillion
                YGX.name -> YGX
                OFD.name -> OFD
                else -> null
            }
        }
    }
}