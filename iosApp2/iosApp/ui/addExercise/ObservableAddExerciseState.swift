import shared

class ObservableAddExerciseState: ObservableObject {
    @Published var value: AddExerciseState
    
    init(value: AddExerciseState) {
        self.value = value
    }
}

extension AddExerciseState {
    func wrapAsObservable() -> ObservableAddExerciseState {
            return ObservableAddExerciseState(value: self)
        }
}
