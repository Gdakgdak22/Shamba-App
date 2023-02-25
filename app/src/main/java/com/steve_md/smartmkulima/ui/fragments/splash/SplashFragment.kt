package com.steve_md.smartmkulima.ui.fragments.splash

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.steve_md.smartmkulima.R
import com.steve_md.smartmkulima.databinding.FragmentSplashBinding
import com.steve_md.smartmkulima.others.isOnline
import com.steve_md.smartmkulima.utils.snackBar


class SplashFragment : Fragment() {

    // private lateinit var splashViewModel: SplashViewModel
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            if (isOnline(requireContext())) {
                findNavController().navigate(R.id.action_splashFragment_to_authsMainFragment)

            } else {
                snackBar("No internet connection")
            }
        }, 3000)
    }
}


/*
        lifecycleScope.launchWhenCreated {
            splashViewModel.splashValue.collectLatest {
                findNavController().navigate(R.id.action_splashFragment_to_authsMainFragment)
            lifecycleScope.launchWhenCreated {
            splashViewModel.splashValue.collectLatest {
                findNavController().navigate(R.id.action_splashFragment_to_authsMainFragment)
            }
        }
        splashViewModel.setValue()
    }  }
        }
        splashViewModel.setValue()
    }
    }
*/