package com.tlam.miammiamk.services 
 
import com.tlam.miammiamk.models.Cuisine 
import retrofit2.Call 
import retrofit2.http.* 
 
interface CuisineBackendService { 

     companion object { 
        fun obtain(): CuisineBackendService { 
            return BackendServiceRetrofit 
                    .obtain() 
                    .create(CuisineBackendService::class.java) 
        } 
    } 
 
    @GET("cuisines/data.json") 
    fun getCuisines( 
          @HeaderMap headers: Map<String, String> 
    ): Call<List<Cuisine>>
}
