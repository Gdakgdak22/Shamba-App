package com.steve_md.smartmkulima.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.steve_md.smartmkulima.R
import com.steve_md.smartmkulima.databinding.FragmentSignInDetailsWithPhoneBinding

class SignInDetailsWithPhoneFragment : Fragment() {

    private lateinit var binding: FragmentSignInDetailsWithPhoneBinding

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInDetailsWithPhoneBinding.inflate(layoutInflater, container, false)

//        binding.signInWithPhoneButton.setOnClickListener {
//            findNavController().navigate(R.id.action_signInDetailsFragment_to_homeDashboardFragment2)
//        }
        binding.signInWithEmailInsteadText.setOnClickListener {
            findNavController().navigate(R.id.action_signInDetailsFragment_to_signInDetailsWithEmailFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.mainAuthsToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.mainAuthsToolbar.title = null
    }

}