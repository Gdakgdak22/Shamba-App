package com.steve_md.smartmkulima.ui.fragments.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.steve_md.smartmkulima.R
import com.steve_md.smartmkulima.databinding.FragmentHomeDashboardBinding
import com.steve_md.smartmkulima.utils.displaySnackBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class HomeDashboardFragment : Fragment() {

    private lateinit var binding: FragmentHomeDashboardBinding

    private lateinit var databaseReference: DatabaseReference

    var firebaseAuth: FirebaseAuth? = null

    //private val getUserViewModel: MainViewModel by viewModels()

    // private lateinit var currentFragment: Fragment

    // private val args: HomeDashboardFragmentArgs by navArgs()
    //private var username = ""

    private lateinit var userProfileTxt: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeDashboardBinding.inflate(layoutInflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        val username = view?.findViewById<TextView>(R.id.userNameTextView)

        val userId =  firebaseAuth!!.uid

         username?.text = databaseReference.child("users").child(userId!!).child("username")
             .setValue(username).toString()


        binding.includeToolBar.notificationIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeDashboardFragment2_to_notificationsFragment)
        }

        binding.includeToolBar.menuIcon.setOnClickListener {
            AlertDialog.Builder(
                requireActivity()
            ).setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes") { dialog, which ->
                    performLogout()
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
        return binding.root
    }

    private fun performLogout() {
        firebaseAuth?.signOut()
        displaySnackBar("Logged out successfully")
        findNavController().navigate(R.id.action_homeDashboardFragment2_to_signInDetailsWithEmailFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userProfileTxt = view.findViewById<TextView>(R.id.userNameTextView)

        val greetingDateTime = view.findViewById<TextView>(R.id.greetingsTextView)
        val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        greetingDateTime.text = when (currentTime) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            else -> "Good Evening"
        }


        //fetchCurrentlyLoggeinUser()


        setUpBinding()
    }

    /**
    private fun fetchCurrentlyLoggeinUser() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val name = user.email
            userProfileTxt.text = name?.substringBefore("@").toString()
        }
    }
    */


    private fun setUpBinding() {
        binding.apply {
            cardView1.setOnClickListener {
                findNavController().navigate(R.id.action_homeDashboardFragment2_to_didYouKnow)
            }
            cardView9.setOnClickListener {
                // TODO()
            }
        }
    }
}