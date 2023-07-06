import shared

class IosNavigator: ObservableObject {
    @Published var command: NavigationCommand
    
    init(command: NavigationCommand) {
        self.command = command
    }
}

extension NavigationCommand {
    func wrapAsObservable() -> IosNavigator {
        return IosNavigator(command: self)
    }
}
