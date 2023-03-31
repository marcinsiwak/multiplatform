import SwiftUI
import shared


struct AddExerciseScreen: View {
    let id: Int64
    let viewModel: AddExerciseViewModel
    @ObservedObject private var state: ObservableAddExerciseState
    
    init(id: Int64) {
        self.id = id
        self.viewModel = AddExerciseDiHelper(id: id).getAddExerciseViewModel()
        self.state = viewModel.observableState()
        observeState()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<AddExerciseState>{
            state in onStateReceived(state: state)
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    
    private func onStateReceived(state: AddExerciseState) {
        self.state.value = state
     }
    

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            TextField(MR.strings().exercise.desc().localized(), text: $state.value.exerciseTitle.onChange({ title in
                viewModel.onExerciseTitleChanged(title: title)
            }))
            .textFieldStyle(RoundedBorderTextFieldStyle())
            
            ResultsTableView(
                results: state.value.results,
                isNewResultEnabled: state.value.isResultFieldEnabled,
                newResultData: state.value.newResultData,
                onAddNewResultClicked: {
                viewModel.onAddNewResultClicked()
            }, onResultValueChanged: { text in
                viewModel.onResultValueChanged(text: text)
            }, onAmountValueChanged: { text in
                viewModel.onAmountValueChanged(text: text)
            }, onDateValueChanged: { date in
                viewModel.onDateValueChanged(text: date)
            }, onDateClicked: {
                viewModel.onDateClicked()
            }, onResultLongClick: { index in
                let newIndex = Int32(index)
                viewModel.onResultLongClicked(resultIndex: newIndex)
            })
            
            Button(action: {
                viewModel.onAddNewExerciseClicked()
            }) {
                Text(MR.strings().add_result_save.desc().localized())
            }
            .frame(maxWidth: .infinity)
            .padding(.vertical, 16)
            .padding(.horizontal, 80)
            .background(Color.gray)
            .foregroundColor(.black)
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .background(.black)
        .onDisappear {
            viewModel.onPause()
        }
    }
}

extension AddExerciseViewModel {
    func observableState() -> ObservableAddExerciseState {
        return (viewState.value as! AddExerciseState).wrapAsObservable()
    }
}

struct AddExerciseScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddExerciseScreen(id: 1)
    }
}
