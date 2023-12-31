package com.steve_md.smartmkulima.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.steve_md.smartmkulima.R
import com.steve_md.smartmkulima.databinding.FragmentSignInDetailsWithEmailBinding
import com.steve_md.smartmkulima.utils.displaySnackBar
import com.steve_md.smartmkulima.viewmodel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SignInDetailsWithEmailFragment : Fragment() {

    // According to the docs this is a nice way get binding
    private var _binding: FragmentSignInDetailsWithEmailBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    // view model
    private val loginWithEmailViewModel: AuthenticationViewModel by viewModels()

    // Firebase
    var firebaseAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignInDetailsWithEmailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        navController = findNavController()


     /**   val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.mainAuthsToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.mainAuthsToolbar.title = null
      */

        // Initiate firebase
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInWithEmailButton.setOnClickListener {
            val email: String = binding.inputLoginEmail.text.toString()
            val password: String = binding.inputLoginPassword.text.toString()

            if (email.isEmpty()) {
                binding.inputLoginEmail.error = "Empty Email or invalid";
            } else if (password.isEmpty()) {
                binding.inputLoginPassword.error = "Empty Password or invalid";
            } else {
                binding.progressBar.visibility = View.VISIBLE
                firebaseAuth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            displaySnackBar("Logged in Successfully")
                            binding.progressBar.visibility = View.INVISIBLE
                            navigateToHomeDashboardFragment()
                        } else {
                            displaySnackBar("Could not login. Unsuccessful.")
                            binding.progressBar.visibility = View.INVISIBLE
                        }

                    }
            }
        }




        binding.signInWithPhoneInsteadText.setOnClickListener {
            navigateToSignInWithPhoneFragment()
        }

        binding.dontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_signInDetailsWithEmailFragment_to_signUpDetailsWithEmailFragment)
        }

        binding.forgotPasswordText.setOnClickListener {
            // findNavController().navigate(R.id.action_signInDetailsWithEmailFragment_to_recoverPasswordWithEmailFragment)
            displaySnackBar("Feature coming soon")
        }

        /**
        binding.signInWithEmailButton.setOnClickListener {
        //findNavController().navigate(directions)
        binding.progressBar.isVisible = true
        if (isValidCredentials()) {
        val directions = binding.inputLoginEmail.text?.let { it1 ->
        SignInDetailsWithEmailFragmentDirections.actionSignInDetailsWithEmailFragmentToHomeDashboardFragment2(
        it1.substring(0, binding.inputLoginEmail.text!!.indexOf('@')), binding.inputLoginPassword.text.toString()
        )
        }
        findNavController().navigate(directions!!)
        loginUser()
        this.hideKeyboard()
        }
        else {
        binding.progressBar.isVisible = false
        displaySnackBar("Empty Strings or Invalid credentials")
        }


        }
         */

        /**

        lifecycleScope.launchWhenResumed {
        loginWithEmailViewModel.loginResult.collectLatest { result ->
        when (result) {
        is Resource.Loading -> {
        toast("Loading...")
        binding.progressBar.isVisible = false
        }
        is Resource.Error -> {
        val exception = result.errorBody
        if (exception is SocketException && exception.message == "Socket closed") {
        displaySnackBar("An error occurred. Please try again later.")
        // Optionally, you can reset the login form or perform any necessary cleanup here.
        } else {
        // Handle other types of failures or display a generic error message.
        displaySnackBar("An error occurred during login.")
        }

        displaySnackBar("Couldn't log in! Invalid Login credentials.")
        binding.progressBar.isVisible = false
        }
        is Resource.Success -> {

        val userId = result.value.data.email

        val token = result.value.data.token


        if (token != null) {
        binding.progressBar.isVisible = false
        displaySnackBar("You Logged in successfully")
        navigateToHomeDashboardFragment()
        } else {
        displaySnackBar("Invalid email or account does not exist.")
        }

        /**

        if (userId.isNotEmpty() && token.isNotEmpty()) {
        binding.progressBar.isVisible = false
        displaySnackBar("You Logged in successfully")
        navigateToHomeDashboardFragment()
        } else {
        displaySnackBar("Invalid email or account does not exist.")
        }
        */
        // check whether user data is null or available in the backend db api

        //                        token.let {
        //                            binding.progressBar.isVisible = false
        //                            // if available then login the user successfully.
        //                             displaySnackBar("You Logged in successfully")
        //                             navigateToHomeDashboardFragment()
        //                           //  savePrefsToken("token")
        //                        }

        //                        displaySnackBar("Invalid details or account does not exist.")

        }
        null -> {

        }
        }
        }
        }
         */

    }


    /** TODO (not working)
    private fun savePrefsToken(token:String) {
    requireContext().getSharedPreferences("login", Context.MODE_PRIVATE)
    .edit().putString("login", token)
    .apply()
    }*/

    private fun navigateToHomeDashboardFragment() {
        findNavController().navigate(R.id.action_signInDetailsWithEmailFragment_to_homeDashboardFragment2)
    }

    private fun loginUser() {
        Timber.i("$loginWithEmailViewModel == null")
        loginWithEmailViewModel.loginUserUsingEmail(
            binding.inputLoginEmail.text.toString(),
            binding.inputLoginPassword.text.toString()
        )
    }

    private fun isValidCredentials(): Boolean {
        return binding.inputLoginEmail.text.isNullOrEmpty().not().also {
            if (!it) binding.enterLoginEmailAddress.error = "Invalid Email"
        } && binding.inputLoginPassword.text.isNullOrEmpty().not().also {
            if (!it) binding.enterLoginPassword.error = "Invalid Password"
        }
    }

    private fun navigateToSignInWithPhoneFragment() {
        displaySnackBar("Feature coming soon")
        // findNavController().navigate(R.id.action_signInDetailsWithEmailFragment_to_signInDetailsFragment)
    }
}