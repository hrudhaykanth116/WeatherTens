package com.hrudhaykanth116.weathertens.features.auth.ui.screens.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hrudhaykanth116.weathertens.R
import com.hrudhaykanth116.weathertens.common.udf.UDFFragment
import com.hrudhaykanth116.weathertens.databinding.FragmentLoginBinding as BINDING
import dagger.hilt.android.AndroidEntryPoint
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginEffect as Effect
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginUIEvent as Event
import com.hrudhaykanth116.weathertens.features.auth.domain.models.LoginUIState as State

@AndroidEntryPoint
class LoginFragment : UDFFragment<State, Event, Effect, BINDING>(
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

            sendEvent(Event.Login(email, password))

        }

    }

    override fun processNewEffect(effect: Effect) {

    }

    override fun processNewState(state: State) {
        if(state.isLoggedIn){
            val action = LoginFragmentDirections.actionLoginFragmentToHomeNavGraph()
            findNavController().navigate(action)
        }else{
            state.userMessage?.let {
                Toast.makeText(requireContext(), it.getText(requireContext()), Toast.LENGTH_LONG).show()
                sendEvent(Event.UserMessageShown(it))
            }

            binding.progressBar.isVisible = state.isLoading

            // TODO: Implement email password error cases

        }

    }


}
