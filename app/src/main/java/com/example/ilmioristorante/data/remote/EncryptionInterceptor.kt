import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class EncryptionInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val timestamp = System.currentTimeMillis()

        var headersString = ""
        val headers = originalRequest.headers
        for (name in headers.names()) {
            val value = headers[name]
            headersString += "$name:$value;"
        }

        val payload = "$headersString-$timestamp"

        val newRequestBuilder = originalRequest.newBuilder()

//        val myHeader = Headers.Builder().addUnsafeNonAscii("xAB", encryptedPayload.decodeToString()).build()
//        for (name in myHeader.names()) {
//            val value = myHeader[name]
//            newRequestBuilder.addHeader(name,value!!)
//        }

        val newRequest = newRequestBuilder.build()

        val headers2 = newRequest.headers
        for (name in headers2.names()) {
            val value = headers2[name]
        }

        return chain.proceed(newRequest)
    }
}