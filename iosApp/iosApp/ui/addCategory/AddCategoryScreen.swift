import SwiftUI
import shared

struct AddCategoryScreen: View {
    let viewModel = AddCategoryDiHelper().getViewModel()

    @State private var title = ""
    
    @Environment(\.dismiss) var dismiss

    @ObservedObject private var state: ObservableState<AddCategoryState>
    
    init() {
        self.state = ObservableState<AddCategoryState>(value: viewModel.viewState.value as! AddCategoryState)
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<AddCategoryState>{ state in
            self.state.value = state
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    
    var body: some View {
        ZStack {
            Color.black.edgesIgnoringSafeArea(.all)
            VStack {
                InputView(value: $state.value.name, hintText: "Category name...", trailingIcon: {}, onValueChange: { name in
                    viewModel.onCategoryNameChanged(name: name)
                })
                .padding(.horizontal, Dimensions.space_8)
                .padding(.top)
                .frame(maxWidth: .infinity)
                    HStack {
                        Text("Category type")
                            .foregroundColor(.white)
                            .padding(.horizontal, Dimensions.space_16)
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
                    action: {
                        viewModel.onSaveCategoryClicked()
                        dismiss()
                    },
                    label: {
                        Text(MR.strings().summary_add_category.desc().localized())
                            .padding(Dimensions.space_16)
                            .foregroundColor(Color.onTertiary)
                            .background(Color.colorTertiary)
                            .clipShape(RoundedCorner())
                            .frame(maxWidth: .infinity, alignment: .center)
                    }
                )
                .frame(width: .none, height: Dimensions.space_48)
                .padding(.vertical, Dimensions.space_16)
                .padding(.horizontal, Dimensions.space_80)
            }
        }
    }
}

struct AddCategoryScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddCategoryScreen()
    }
}
