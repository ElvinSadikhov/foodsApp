package com.example.foodsapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        fun getClient(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
//                .client(
//                    OkHttpClient.Builder()
//                        .addInterceptor(LoggingInterceptor())
//                        .build()
//                )
//                .addConverterFactory(object : Converter.Factory() {
//                    fun converterFactory() = this
//                    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
//                        val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
//                        override fun convert(value: ResponseBody) = if (value.contentLength() >= 10L) nextResponseBodyConverter.convert(value) else null
//                    }
//                })
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}