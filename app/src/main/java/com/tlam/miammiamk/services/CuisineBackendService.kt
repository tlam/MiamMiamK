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

    /*
     * The following expect the api response to return a list of cuisine object:
     * [
     *   {
     *     "id": 2,
     *     "name": "Fish",
     *     "origin": "Sea",
     *     "foods": [
     *       {"name": "Arctic Char", "description": "$21.45 / lb", "image": ""},
      *      {"name": "Cod Fillet Pacific", "description": "$14.55 / lb", "image": ""},
      *      {"name": "Haddock Fillet", "description": "$17.95 / lb", "image": ""},
      *      {"name": "Halibut Fillet", "description": "$52.75 / lb", "image": ""},
      *      {"name": "Pickerel Fillet", "description": "$23.75 / lb", "image": ""}
      *    ]
      *  },
      *  {
      *    "id": 1,
      *    "name": "Japanese",
      *    "origin": "Asian",
      *    "foods": []
      *  }
      *]
     */
    @GET("cuisines/data.json") 
    fun getCuisines(): Call<List<Cuisine>>
}
