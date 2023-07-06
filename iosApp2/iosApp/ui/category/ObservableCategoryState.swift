import shared

class ObservableCategoryState: ObservableObject {
    @Published var value: CategoryState
    
    init(value: CategoryState) {
        self.value = value
    }
}

extension CategoryState {
    func wrapAsObservable() -> ObservableCategoryState {
            return ObservableCategoryState(value: self)
        }
}
