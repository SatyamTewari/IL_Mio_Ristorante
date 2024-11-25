package com.example.securitysdk.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object KeyStoreManager {

    val abc = "qwertyuiopasd"
    val xyz = "fghjklzxcvbnm"

    private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
    private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private val encryptCipher = Cipher.getInstance(TRANSFORMATION).apply {
        init(Cipher.ENCRYPT_MODE, getKey())
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getKey(): SecretKey {
        val existingkey = keyStore.getEntry("secretKeyStage2", null) as? KeyStore.SecretKeyEntry
        val key = existingkey?.secretKey ?: createKey()
        return key
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "secretKeyStage2",
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(false)
                    .build()
            )
        }.generateKey()
    }

    private fun encrypt(bytes: ByteArray): ByteArray {
        val encryptedBytes = encryptCipher.doFinal(bytes)
//        outputStream?.use {
//            it.write(encryptCipher.iv.size)
//            it.write(encryptCipher.iv)
//            it.write(encryptedBytes.size)
//            it.write(encryptedBytes)
//        }
        return encryptedBytes
    }

    private fun decrypt(enBytes: ByteArray): ByteArray {
        return getDecryptCipherForIv(encryptCipher.iv).doFinal(enBytes)
    }

    fun getIntermediatoryKey(): String {
        val enBytes = encrypt("#~~$abc$xyz~~#".encodeToByteArray())
        val myKey = decrypt(enBytes).decodeToString()
        return myKey
    }
}