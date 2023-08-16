package com.example.golinkproject.presentation.autorizationscreen

import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.Toast
import com.example.golinkproject.data.retrofit.InitMainApi
import com.example.golinkproject.data.retrofit.models.modelRequestAuth.AuthRequest
import com.example.golinkproject.data.retrofit.MainAPI
import com.example.golinkproject.databinding.FragmentLoginBinding
import com.example.golinkproject.presentation.autorizationscreen.childscreen.SmsCodeFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private lateinit var mainApi: MainAPI
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        mainApi = InitMainApi().createService()
        binding.button.setOnClickListener{
            smsCodeCheckingScreen()
//            checkingAuthData(binding.numberUser.text.toString(), binding.username.text.toString())

        }
        return binding.root
    }



    private fun checkingAuthData(number: String, login: String) {
        Log.d("LOL", "Metod checkingAuthData")
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        var toasttxt = "Данный"
        var authNeed = false
        CoroutineScope(Dispatchers.IO+coroutineExceptionHandler).launch {
            val response = mainApi.checkNumber(number)

            if (response!=null){
                requireActivity().runOnUiThread {
                    if(response.data.response){
                        authNeed = true
                    }else{
                        toasttxt += " номер "
                        authNeed = false
                    }
                    Log.d("LOL","Ответ номера пришел" )
                }
            }else{
                Log.d("LOL","Ошибка номера" )
            }
        }
        CoroutineScope(Dispatchers.IO+coroutineExceptionHandler).launch {
            val response = mainApi.checkLogin(login)
            if (response!=null){
                requireActivity().runOnUiThread {
                    if(response.data.response){
                        authNeed = true
                    }else{
                        if (!authNeed){
                            toasttxt += "и логин "
                        }else{
                            toasttxt += " логин "
                        }
                        binding.username.setText("")

                    }
                    Log.d("LOL","Ответ логин пришел" )
                }
            }else{
                Log.d("LOL","Ошибка логин" )
            }
        }
        if (authNeed){

            smsCodeCheckingScreen()
//            auth(
//                AuthRequest(
//                    binding.username.text.toString(),
//                    binding.passwordUser.text.toString(),
//                    WebSettings.getDefaultUserAgent(context)
//            )
//            )
        }else{
            Toast.makeText(context, toasttxt + "занят", Toast.LENGTH_LONG).show()
            Log.d("LOL",toasttxt + "занят" )
        }
    }

    private fun smsCodeCheckingScreen(){
        var smsBottomSheet=SmsCodeFragment()
        var bundle = Bundle()
        var arr:ArrayList<String> = arrayListOf(binding.username.text.toString(), binding.numberUser.text.toString(), binding.passwordUser.text.toString())
        bundle.putStringArrayList("keySmsScreen", arr)
        smsBottomSheet.arguments = bundle
        Log.d("LOL"," 0 "+ arr.get(0) + " 1 "+ arr.get(1) + " 2 "+ arr.get(2) )
//        childFragmentManager.setFragmentResult("userdata", bundle)
        smsBottomSheet.show(requireFragmentManager(), smsBottomSheet.tag)
        Log.d("LOL","Открываем окно sms" )
    }
}