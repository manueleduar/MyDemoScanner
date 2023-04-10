package com.example.mydemoscanner.ui.barcode

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mydemoscanner.databinding.FragmentBarcodeBinding
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class BarcodeFragment : Fragment() {

    private var _binding: FragmentBarcodeBinding? = null
    private val binding get() = _binding!!

    private lateinit var scannerView: DecoratedBarcodeView
    private lateinit var scanButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBarcodeBinding.inflate(inflater, container, false)
        scannerView = binding.decoratedBarcodeView
        scanButton = binding.scanAgainButton
        scannerView.decodeContinuous(callback)
        scanButton.setOnClickListener { startScanAgain() }
        return binding.root
    }

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            result?.let {
                binding.resultTextView.text = result.text
                scannerView.visibility = View.GONE
                binding.scanAgainButton.visibility = View.VISIBLE
            }
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
    }

    private fun startScanAgain() {
        binding.resultTextView.text = ""
        binding.resultTextView.visibility = View.VISIBLE
        scannerView.visibility = View.VISIBLE
        binding.scanAgainButton.visibility = View.GONE
        scannerView.resume()
    }

    override fun onResume() {
        super.onResume()
        scannerView.resume()
    }

    override fun onPause() {
        super.onPause()
        scannerView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
