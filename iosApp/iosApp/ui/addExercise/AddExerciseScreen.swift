import SwiftUI
import shared


struct AddExerciseScreen: View {
    
    let id: Int64
    let viewModel: AddExerciseViewModel
    @ObservedObject private var state: ObservableState<AddExerciseState>
    @ObservedObject private var focusedFieldPos: ObservableEvent<Int32?>

        
    init(id: Int64) {
        self.id = id
        self.viewModel = AddExerciseDiHelper(id: id).getAddExerciseViewModel()
        self.state = ObservableState<AddExerciseState>(value: viewModel.viewState.value as! AddExerciseState)
        self.focusedFieldPos = ObservableEvent(value: 0)
        observeState()
        observeEvents()
    }
    
    private func observeState() {
        viewModel.viewState.collect(collector: Collector<AddExerciseState>{
            state in onStateReceived(state: state)
            
        }) { error in
            print("Error ocurred during state collection")
        }
    }
    
    private func observeEvents() {
        viewModel.viewEvent.collect(collector: Collector<AddExerciseEvent>{
            event in onEventReceived(event: event)
        }) { error in
            print("Error ocurred during event collection")
        }
    }
    
    private func onStateReceived(state: AddExerciseState) {
        self.state.value = state
     }
    
    private func onEventReceived(event: AddExerciseEvent) {
        if(event is AddExerciseEvent.FocusOnInput){
            let id = (event as? AddExerciseEvent.FocusOnInput)?.getArgument()
            self.focusedFieldPos.value = id
        }
     }
    

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
//            Text(state.value.exerciseTitle)
//                .padding(.horizontal, 8)
//                .foregroundColor(.white)
            
            ResultsTimeFilterView(
                tabs: state.value.filter,
                onTabClicked: { item in
                    viewModel.onTabClicked(item: item)
                },
                selectedFilter: state.value.selectedFilterPosition
            )
            .frame(width: 280)
            .padding(.vertical, 16)
            
            if !state.value.isResultFieldEnabled {
                Button(action: {
                        viewModel.onAddNewResultClicked()
                    }, label: {
                        Text(MR.strings().add_new_result.desc().localized())
                            .padding()
                            .foregroundColor(.black)
                            .background(Color.gray.opacity(0.2))
                            .cornerRadius(8)
                    }
                )
                .frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.horizontal, 16)
                .padding(.bottom, 16)
            } else {
                Button(action: {
                        viewModel.onSaveResultClicked()
                    }, label: {
                        Text(MR.strings().add_result_save.desc().localized())
                            .padding()
                            .foregroundColor(.black)
                            .background(Color.gray.opacity(0.2))
                            .cornerRadius(8)
                    }
                )
                .frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.horizontal, 16)
                .padding(.bottom, 16)
            }
            
            ResultsTableView(
                resultDataTitles: state.value.resultDataTitles,
                unit: state.value.unit,
                results: state.value.results,
                exerciseType: state.value.exerciseType,
                sortType: state.value.sortType,
                isNewResultEnabled: state.value.isResultFieldEnabled,
                newResultData: state.value.newResultData,
                focusedFieldPos: focusedFieldPos,
                onAddNewResultClicked: {
                viewModel.onAddNewResultClicked()
            }, onLabelClicked: { pos in
                viewModel.onLabelClicked(labelPosition: Int32(pos))
            }, onResultValueChanged: { text in
                viewModel.onResultValueChanged(text: text)
            }, onAmountValueChanged: { text in
                viewModel.onAmountValueChanged(text: text)
            }, onDatePicked: { date in
                viewModel.onDatePicked(date: date)
            }, onDateClicked: {
                viewModel.onDateClicked()
            }, onResultLongClick: { index in
                let newIndex = Int32(index)
                viewModel.onResultLongClicked(resultIndex: newIndex)
            })
        }
        .background(.black)
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        .onDisappear {
            viewModel.onPause()
        }
        .navigationTitle(Text(state.value.exerciseTitle))
    }
}

struct AddExerciseScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddExerciseScreen(id: 1)
    }
}
