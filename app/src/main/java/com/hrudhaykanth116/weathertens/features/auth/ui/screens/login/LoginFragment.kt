package com.hrudhaykanth116.weathertens.features.auth.ui.screens.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hrudhaykanth116.weathertens.R
import com.hrudhaykanth116.weathertens.common.udf.UDFFragment
import com.hrudhaykanth116.weathertens.databinding.FragmentLoginBinding
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginEffect
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginUIEvent
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginUIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : UDFFragment<LoginUIState, LoginUIEvent, LoginEffect, FragmentLoginBinding>(
    R.layout.fragment_login
) {

    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun initViews() {
        binding.signUpButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

        binding.loginButton.setOnClickListener {

            val email = binding.emailTextField.text?.toString()
            val password = binding.passwordTextField.text?.toString()

            processEvent(LoginUIEvent.Login(email, password))

        }

    }

    override fun processNewEffect(effect: LoginEffect) {

    }

    override fun processNewState(state: LoginUIState) {
        // if(state.isLoggedIn){
        //     val action = LoginFragmentDirections.actionLoginFragmentToHomeNavGraph()
        //     findNavController().navigate(action)
        // }else{
        //     state.userMessage?.let {
        //         Toast.makeText(requireContext(), state.userMessage.getText(requireContext()), Toast.LENGTH_LONG).show()
        //     }
        //     if (state.userMessage != null) {
        //         processEvent(LoginUIEvent.UserMessageShown(state.userMessage))
        //     }
        //
        //     binding.progressBar.isVisible = state.isLoading
        //
        //     // TODO: Implement email password error cases
        //
        // }

    }


}
