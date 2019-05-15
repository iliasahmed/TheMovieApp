package com.iliasahmed.testappforhandymama.network

import com.iliasahmed.testappforhandymama.utils.UrlUtils
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Type
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

public class ApiClient{
    companion object {
        private var retrofit: Retrofit? = null
        private val apiBaseUrl = UrlUtils.BASE_URL

        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(apiBaseUrl)
                        .addConverterFactory(StringConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .client(getUnsafeOkHttpClient()).build()
            }
            return retrofit as Retrofit

        }

        fun getUnsafeOkHttpClient(): OkHttpClient {
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                        return arrayOf()
                    }
                })

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())

                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                val httpClient = OkHttpClient.Builder()
                        .connectTimeout(2, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                httpClient.addInterceptor(logging)
                httpClient.addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .method(original.method(), original.body())
                            .build()

                    chain.proceed(request)
                }
                httpClient.hostnameVerifier { hostname, session -> true }

                return httpClient.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }

        class StringConverterFactory : Converter.Factory() {
            private val MEDIA_TYPE = MediaType.parse("text/plain")

            override fun requestBodyConverter(
                    type: Type?,
                    parameterAnnotations: Array<Annotation>?,
                    methodAnnotations: Array<Annotation>?,
                    retrofit: Retrofit?
            ): Converter<*, RequestBody>? {
                return if (String::class.java == type) {
                    Converter<String, RequestBody> { value -> RequestBody.create(MEDIA_TYPE, value) }
                } else null

            }

            override fun responseBodyConverter(
                    type: Type?,
                    annotations: Array<Annotation>?,
                    retrofit: Retrofit?
            ): Converter<ResponseBody, *>? {
                return if (String::class.java == type) {
                    Converter<ResponseBody, String> { value -> value.string() }
                } else null
            }

            companion object {

                fun create(): StringConverterFactory {
                    return StringConverterFactory()
                }
            }
        }
    }

}