import shared

class IosNavigationCommand: ObservableObject {
    @Published var command: NavigationCommand
    
    init(command: NavigationCommand) {
        self.command = command
    }
}

extension NavigationCommand {
    func wrapAsObservable() -> IosNavigationCommand {
        return IosNavigationCommand(command: self)
    }
}
