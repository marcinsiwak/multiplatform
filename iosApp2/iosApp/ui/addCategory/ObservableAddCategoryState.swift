import shared

class ObservableAddCategoryState: ObservableObject {
    @Published var value: AddCategoryState
    
    init(value: AddCategoryState) {
        self.value = value
    }
}

extension AddCategoryState {
    func wrapAsObservable() -> ObservableAddCategoryState {
            return ObservableAddCategoryState(value: self)
        }
}


class ObservableState<T> : ObservableObject {
    @Published var value: T
    
    init(value: T) {
        self.value = value
    }
}
