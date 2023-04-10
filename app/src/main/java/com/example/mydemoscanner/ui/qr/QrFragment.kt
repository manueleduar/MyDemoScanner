package com.example.mydemoscanner.ui.qr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.mydemoscanner.databinding.FragmentQrBinding
import com.example.mydemoscanner.ui.CamActivity

class QrFragment : Fragment() {

    private lateinit var binding: FragmentQrBinding

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val barcode = it?.data?.getStringExtra("BarcodeResult")
                binding.txtResult.text = barcode
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // code here

        binding.btnStart.setOnClickListener {
            val intent = Intent(requireContext(), CamActivity::class.java)
            intent.putExtra("title", "Example")
            intent.putExtra("msg", "Scan Barcode")
            getContent.launch(intent)
        }
    }

}

