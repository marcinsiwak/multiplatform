package pl.msiwak.multiplatform.ui.register

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.authorization.RegisterUseCase

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState

    fun onLoginChanged(text: String) {
        _registerState.value = registerState.value.copy(login = text)
    }

    fun onPasswordChanged(text: String) {
        _registerState.value = registerState.value.copy(password = text)
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            registerUseCase(
                RegisterUseCase.Params(
                    registerState.value.login,
                    registerState.value.password
                )
            )
        }
    }

}