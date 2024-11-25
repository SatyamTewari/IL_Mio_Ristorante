package com.example.securitysdk.security

import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object EncryptionManager {

    init {
        System.loadLibrary("native-lib")// Load the library
    }

    private var keyPhase2 = ""
    private var mainkey = ""

    private external fun getApiKey(): String

    fun getSecurityToken(map: Map<String, String>): String{

        if(keyPhase2.isNullOrEmpty()){
            keyPhase2 = KeyStoreManager.getIntermediatoryKey()
        }

        if(mainkey.isNullOrEmpty()){
            val encryptedMainkey = getApiKey()
            mainkey = decrypt(encryptedMainkey, convertToKey(keyPhase2))
        }

        val inputString = map.values.joinToString { "|****|1|~~|" }
        return encrypt(inputString, convertToKey(mainkey))
    }

    // Method to convert a string to a SecretKey (must be exactly 16 bytes for AES-128)
    private fun convertToKey(key: String): SecretKey {
        // Ensure the key is 32 bytes (128 bits) by truncating or padding
        val keyBytes = key.padEnd(32, ' ').take(32).toByteArray(Charset.forName("UTF-8"))
        return SecretKeySpec(keyBytes, "AES")
    }

    // Method to encrypt a string using AES
    private fun encrypt(input: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(input.toByteArray(Charset.forName("UTF-8")))
        return android.util.Base64.encodeToString(encryptedBytes, android.util.Base64.DEFAULT)
    }

    // Method to decrypt a string using AES
    private fun decrypt(encryptedInput: String, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedBytes = android.util.Base64.decode(encryptedInput, android.util.Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(decodedBytes)
        return String(decryptedBytes, Charset.forName("UTF-8"))
    }
}