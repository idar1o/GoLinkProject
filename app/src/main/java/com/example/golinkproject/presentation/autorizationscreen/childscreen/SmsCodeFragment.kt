package com.example.golinkproject.presentation.autorizationscreen.childscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.golinkproject.R
import com.example.golinkproject.data.retrofit.InitMainApi
import com.example.golinkproject.data.retrofit.models.modelRequestAuth.AuthRequest

import com.example.golinkproject.databinding.FragmentSmsCodeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class SmsCodeFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSmsCodeBinding
    private var authreq: AuthRequest? = null
    private var value: ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSmsCodeBinding.inflate(inflater)
        // Inflate the layout for this fragment
//        parentFragmentManager.setFragmentResultListener("userdata", this) { requestKey, result ->
            value = arguments?.getStringArrayList("keySmsScreen")!!
            authreq = AuthRequest( value!!.get(0), value!!.get(2), value!!.get(1), binding.editCode.text.toString())
            Log.d("LOL", value!!.get(1))
//        }

        sendtoUserSmsCode()
        init()
        return binding.root
    }

    private fun sendtoUserSmsCode() {
        val mainApi = InitMainApi().createService()
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        Log.d("LOL", if (value!= null){
            value!!.get(1)
        }else{
            "масс пуст"
        })


        CoroutineScope(Dispatchers.IO+coroutineExceptionHandler).launch {
            var response = mainApi.smsCode(value!!.get(1))
            if (response!= null){
                Log.d("LOL", response.code)
            }else(
                Log.d("LOL", "Код не пришел")
            )
        }
    }

    private fun init() {

        binding.checkButton.setOnClickListener{
            val mainApi = InitMainApi().createService()
            authreq!!.code = binding.editCode.text.toString()
            val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
                throwable.printStackTrace()
            }
            CoroutineScope(Dispatchers.IO+coroutineExceptionHandler).launch {
                var response = authreq?.let { it1 -> mainApi.smsCodeCheckingAuth(it1) }
                if (response!= null){
                    Log.d("LOL", response.data.code+" код пришел")
                }else(
                        Log.d("LOL", "Код не пришел")
                        )
            }
        }
    }
}