import shared

extension KotlinArray<ExerciseType> {
    func parseToSwiftArray() -> Array<ExerciseType> {
        let list = Int(self.size).makeIterator()
        var array: [ExerciseType] = []
        for id in list {
            let item : ExerciseType = self.get(index: Int32(id))!
            array.append(item)
        }
        return array
    }
}

extension Int: Sequence {
    public func makeIterator() -> CountableRange<Int>.Iterator {
        return (0..<self).makeIterator()
    }
}
