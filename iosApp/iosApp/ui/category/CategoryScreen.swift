import SwiftUI
import shared

struct CategoryScreen: View {
    let id: Int64
    let viewModel: CategoryViewModel
    @ObservedObject private var state: ObservableCategoryState
    
    init(id: Int64) {
        self.id = id
        self.viewModel = CategoryDiHelper(id: id).getCategoryViewModel()
        self.state = viewModel.observableState()
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<CategoryState>{
            state in onStateReceived(state: state)
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    
    private func onStateReceived(state: CategoryState) {
        self.state.value = state
     }
    
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            ZStack {
                Image("bg_running_field")
                    .resizable()
                    .scaledToFill()
                    .clipped()
                    .frame(height: 264)
                    .padding(EdgeInsets(top: 10, leading: 0, bottom: 0, trailing: 0))
                Rectangle()
                    .frame(height: 264)
                    .foregroundColor(.clear)
                    .padding(EdgeInsets(top: 20, leading: 0, bottom: 0, trailing: 0))
                    .background(LinearGradient(gradient: Gradient(colors: [.clear, .clear, .black]), startPoint: .top, endPoint: .bottom))
            }
            ForEach(state.value.exerciseList) { item in
                ListItemView(title: item.name, onClicked: {
                    viewModel.onExerciseClicked(id: item.id)
                })
                    .frame(height: 64)
            }
            Spacer()
            Button(action: {
                viewModel.onAddNewExerciseClicked()
            }, label: {
                Text(MR.strings().add_new_result.desc().localized())
                    .padding(16)
                    .foregroundColor(Color.black)
                    .background(Color.gray)
                    .clipShape(RoundedCorner())
                    .frame(maxWidth: .infinity, alignment: .center)
            })
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .background(.black)
        .customDialog(isPresented: $state.value.isDialogVisible, onDismiss: {
            viewModel.onDialogClosed()
        }) {
            VStack {
                Text(MR.strings().exercise_name.desc().localized())
                    .padding()
                InputView(value: $state.value.newExerciseName, trailingIcon: {}, onValueChange: { text in
                    viewModel.onAddExerciseNameChanged(name: text)
                })
                Button(MR.strings().add_new_exercise.desc().localized()) {
                    viewModel.onAddExerciseClicked()
                }
                .padding()
            }
        }

    }
}

extension CategoryViewModel {
    func observableState() -> ObservableCategoryState {
        return (viewState.value as! CategoryState).wrapAsObservable()
    }
}


struct CategoryScreen_Previews: PreviewProvider {
    static var previews: some View {
        CategoryScreen(id: 1)
    }
}
