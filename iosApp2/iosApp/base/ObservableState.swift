import SwiftUI

class ObservableState<T>: ObservableObject {
    @Published var value: T
    
    init(value: T) {
        self.value = value
    }
}
