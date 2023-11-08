import SwiftUI
import shared


struct AddExerciseScreen: View {
    
    let id: String
    let viewModel: AddExerciseViewModel
    @ObservedObject private var state: ObservableState<AddExerciseState>
    @ObservedObject private var focusedFieldPos: ObservableEvent<Int32?>

    @State private var hours: String = ""
    @State private var minutes: String = ""
    @State private var seconds: String = ""
    @State private var milliseconds: String = ""

        
    init(id: String) {
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
            Text(state.value.exerciseTitle)
                .padding(.horizontal, 8)
                .foregroundColor(.onPrimary)
            
            ResultsTimeFilterView(
                tabs: state.value.filter,
                onTabClicked: { item in
                    viewModel.onTabClicked(item: item)
                },
                selectedFilter: state.value.selectedFilterPosition
            )
            .frame(width: Dimensions.result_view_width)
            .padding(.vertical, Dimensions.space_16)
            
            if !state.value.isResultFieldEnabled {
                Button(action: {
                        viewModel.onAddNewResultClicked()
                    }, label: {
                        Text(MR.strings().add_new_result.desc().localized())
                            .padding()
                            .foregroundColor(.onTertiary)
                            .background(Color.colorTertiary)
                            .cornerRadius(Dimensions.button_corner)
                    }
                )
                .frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.horizontal, Dimensions.space_16)
                .padding(.bottom, Dimensions.space_16)
            } else {
                Button(action: {
                        viewModel.onSaveResultClicked()
                    }, label: {
                        Text(MR.strings().add_result_save.desc().localized())
                            .padding()
                            .foregroundColor(.onTertiary)
                            .background(Color.colorTertiary)
                            .cornerRadius(Dimensions.button_corner)
                    }
                )
                .frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.horizontal, Dimensions.space_16)
                .padding(.bottom, Dimensions.space_16)
            }
            
            ResultsTableView(
                resultDataTitles: state.value.resultDataTitles,
                unit: state.value.unit,
                results: state.value.results,
                exerciseType: state.value.exerciseType,
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
            }, onAmountClicked: {
                viewModel.onAmountClicked()
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
        .showDialog(isPresented: $state.value.isTimeInputDialogVisible, onDismiss: {
            viewModel.onDismissAmountDialog()
        }) {
            VStack {
                Text(MR.strings().running_time_input_insert_result.desc().localized())
                    .multilineTextAlignment(.center)
                    .foregroundColor(.white)
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .center)
                    .padding()
                
                HStack {
                    
                    TextField(MR.strings().hours.desc().localized(), text: $hours)
                        .padding()
//                        .background(backgroundColor)
                        .cornerRadius(Dimensions.input_view_corner)
                        .overlay(RoundedRectangle(cornerRadius: Dimensions.input_view_corner).stroke(Color.black, lineWidth: 1))
                        .padding()
                        .foregroundColor(.onPrimary)
                        .keyboardType(.decimalPad)
                    
                    TextField(MR.strings().minutes.desc().localized(), text: $minutes)
                        .padding()
//                        .background(backgroundColor)
                        .cornerRadius(Dimensions.input_view_corner)
                        .overlay(RoundedRectangle(cornerRadius: Dimensions.input_view_corner).stroke(Color.primary, lineWidth: 1))
                        .padding()
                        .foregroundColor(.onPrimary)
                        .keyboardType(.decimalPad)
                }
                
                HStack {
                    TextField(MR.strings().seconds.desc().localized(), text: $seconds)
                        .padding()
//                        .background(backgroundColor)
                        .cornerRadius(Dimensions.input_view_corner)
                        .overlay(RoundedRectangle(cornerRadius: Dimensions.input_view_corner).stroke(Color.black, lineWidth: 1))
                        .padding()
                        .foregroundColor(.onPrimary)
                        .keyboardType(.decimalPad)
                    
                    TextField(MR.strings().milliseconds.desc().localized(), text: $milliseconds)
                        .padding()
//                        .background(backgroundColor)
                        .cornerRadius(Dimensions.input_view_corner)
                        .overlay(RoundedRectangle(cornerRadius: Dimensions.input_view_corner).stroke(Color.primary, lineWidth: 1))
                        .padding()
                        .foregroundColor(.onPrimary)
                        .keyboardType(.decimalPad)
                }
                
                
                Button(action: {
                    viewModel.onConfirmRunningAmount(hours: hours, minutes: minutes, seconds: seconds, milliseconds: milliseconds)
                }, label: {
                    Text(MR.strings().confirm.desc().localized())
                        .padding(Dimensions.space_16)
                        .foregroundColor(Color.onTertiary)
                        .background(Color.colorTertiary)
                        .clipShape(RoundedCorner())
                        .frame(maxWidth: .infinity, alignment: .center)
                })
                .padding()
            }
        }.onTapGesture {
            UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
        }
    }
}

struct AddExerciseScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddExerciseScreen(id: "s")
    }
}
