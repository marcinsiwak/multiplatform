import shared

class ObservableSummaryState: ObservableObject {
    @Published var value: SummaryState
    
    init(value: SummaryState) {
        self.value = value
    }
}

extension SummaryState {
    func wrapAsObservable() -> ObservableSummaryState {
            return ObservableSummaryState(value: self)
        }
}
