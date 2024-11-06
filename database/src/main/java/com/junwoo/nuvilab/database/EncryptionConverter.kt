package com.junwoo.nuvilab.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.junwoo.nuvilab.utils.security.AESUtil

data class PlainText(val value: String)

@ProvidedTypeConverter
class EncryptionConverter(private val aesUtil: AESUtil) {

    @TypeConverter
    fun fromPlainText(plainText: PlainText): String = aesUtil.encrypt(plainText.value)

    @TypeConverter
    fun toPlainText(encryptedText: String): PlainText = PlainText(aesUtil.decrypt(encryptedText))
}