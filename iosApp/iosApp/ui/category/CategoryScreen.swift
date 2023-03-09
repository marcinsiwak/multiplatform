import SwiftUI
import shared

struct CategoryScreen: View {
    private let viewModel = CategoryDiHelper().getCategoryViewModel()
    @ObservedObject private var state: ObservableCategoryState
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Image("bg_running_field")
                .resizable()
                .scaledToFill()
                .clipped()
            
            ForEach(state.value.exerciseList) { item in
                Text(item.name)
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .background(.gray)

    }
}


extension CategoryViewModel {
    func observableState() -> ObservableCategoryState {
        return (viewState.value as! CategoryState).wrapAsObservable()
    }
}

//
//struct CategoryScreen_Previews: PreviewProvider {
//    static var previews: some View {
////        CategoryScreen()
//    }
//}
