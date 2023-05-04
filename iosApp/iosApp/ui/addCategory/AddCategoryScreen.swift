import SwiftUI
import shared

struct AddCategoryScreen: View {
    let viewModel = AddCategoryDiHelper().getViewModel()
    @ObservedObject private var state: ObservableAddCategoryState

    @State private var title = ""

    
    init() {
        self.state = viewModel.observableState()
    }
    
    var body: some View {
        ZStack {
            Color.black.edgesIgnoringSafeArea(.all)
            VStack {
                InputView(value: $state.value.name, hintText: "Category name...", trailingIcon: {}, onValueChange: { name in
                    viewModel.onCategoryNameChanged(name: name)
                })
                .padding(.horizontal, 8)
                .padding(.top)
                .frame(maxWidth: .infinity)
                    HStack {
                        Text("Category type")
                            .foregroundColor(.white)
                            .padding(.horizontal, 16)
                        Spacer()
                        Picker("Select category type", selection: $state.value.exerciseType, content: {
                            let exercises = ExerciseType.values().parseToSwiftArray()
                            ForEach(exercises, id: \.self) { item in
                                Text(item.name)
                            }
                        })
                        .onChange(of: state.value.exerciseType, perform: { item in
                            print(item)
                            viewModel.onTypePicked(exerciseType: item)
                        })
                        .padding(.vertical)
                    
                }
                Spacer()
                Button(
                    action: { viewModel.onSaveCategoryClicked() },
                    label: {
                        Text("Add category")
                            .font(.system(size: 16))
                            .padding(8)
                            .background(.gray)
                            .foregroundColor(Color.black)
                    }
                )
                .frame(width: .none, height: 48)
                .padding(.vertical, 16)
                .padding(.horizontal, 80)
            }
        }
    }
}

extension AddCategoryViewModel {
    func observableState() -> ObservableAddCategoryState {
        return (viewState.value as! AddCategoryState).wrapAsObservable()
    }
}

//struct AddCategoryScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        AddCategoryScreen()
//    }
//}
