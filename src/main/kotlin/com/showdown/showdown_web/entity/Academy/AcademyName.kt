package com.showdown.showdown_web.entity.Academy

enum class AcademyName(val id: Long) {
    OneMillion(1), YGX(2), OFD(3);

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