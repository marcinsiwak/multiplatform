import SwiftUI

class ObservableEvent<T>: ObservableObject {
    @Published var value: T
    
    init(value: T) {
        self.value = value
    }
}
