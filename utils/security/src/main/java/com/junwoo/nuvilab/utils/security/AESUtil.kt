package com.junwoo.nuvilab.utils.security

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object AESUtil {

    private const val TRANSFORMATION = "AES/ECB/PKCS7Padding"

    private val secretKey: SecretKey = KeyStoreHelper.getOrCreateAESKey()

    fun encrypt(data: String): String{
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = ByteArray(cipher.blockSize)
        SecureRandom().nextBytes(iv)
        val ivParameterSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)

        val encryptedData = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        val encryptedText = Base64.encodeToString(encryptedData, Base64.DEFAULT)
        val ivText = Base64.encodeToString(iv, Base64.DEFAULT)
        return "$ivText::$encryptedText"
    }

    fun decrypt(data: String): String {
        val parts = data.split("::")
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = Base64.decode(parts[0], Base64.DEFAULT)
        val ivParameterSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)

        val decodedData = Base64.decode(parts[1], Base64.DEFAULT)
        val decryptedData = cipher.doFinal(decodedData)
        return String(decryptedData, Charsets.UTF_8)
    }
}