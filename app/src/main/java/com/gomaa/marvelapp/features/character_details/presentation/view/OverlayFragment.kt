package com.gomaa.marvelapp.features.character_details.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.gomaa.marvelapp.R
import com.gomaa.marvelapp.base.util.loadImage
import kotlinx.android.synthetic.main.overlay.view.*

class OverlayFragment : DialogFragment() {
    companion object {

        const val TAG = "OverlayFragment"

        private const val KEY_URL = "KEY_URL"

        fun newInstance(url: String): OverlayFragment {
            val args = Bundle()
            args.putString(KEY_URL, url)
            val fragment = OverlayFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.overlay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {
        val url = arguments?.getString(KEY_URL)
        loadImage(requireContext(), url!!, view.overlayImage)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}