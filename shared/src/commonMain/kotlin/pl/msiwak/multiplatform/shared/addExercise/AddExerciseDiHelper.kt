package pl.msiwak.multiplatform.shared.addExercise

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseViewModel

class AddExerciseDiHelper(val id: String) : KoinComponent {
    private val addExerciseVM: AddExerciseViewModel by inject(parameters = { parametersOf(id) })
    fun getAddExerciseViewModel() = addExerciseVM
}
