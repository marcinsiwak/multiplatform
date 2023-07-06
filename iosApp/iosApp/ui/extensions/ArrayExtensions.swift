import Foundation


extension Array where Element == Optional<String> {
    subscript(safe index: Index) -> String? {
        return indices.contains(index) ? self[index] : ""
    }
}
