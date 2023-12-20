import shared
import SwiftUI

struct CategoryScreen: View {
    let id: String
    let viewModel: CategoryViewModel
    @ObservedObject private var state: ObservableState<CategoryState>

    init(id: String) {
        self.id = id
        self.viewModel = CategoryDiHelper(id: id).getCategoryViewModel()
        self.state = ObservableState<CategoryState>(value: viewModel.viewState.value as! CategoryState)

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
    
    private func selectBackgroundImage() -> UIImage {
        switch(self.state.value.exerciseType){
        case ExerciseType.running:
            return MR.images().bg_running_field.toUIImage()!
        case ExerciseType.gym:
            return MR.images().bg_gym.toUIImage()!
    //        ExerciseType.OTHER -> null
        default:
            return MR.images().bg_gym.toUIImage()!
        }
    }
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 0) {
                ZStack {
                    let backgroundImage = selectBackgroundImage()

                    Image(uiImage: backgroundImage)
                        .resizable()
                        .scaledToFill()
                        .clipped()
                        .frame(height: Dimensions.space_264)
                    Rectangle()
                        .frame(height: Dimensions.space_264)
                        .foregroundColor(.clear)
                        .padding(EdgeInsets(top: Dimensions.space_12, leading: 0, bottom: 0, trailing: 0))
                        .background(LinearGradient(gradient: Gradient(colors: [.clear, .clear, .colorPrimary]), startPoint: .top, endPoint: .bottom))
                }
                ForEach($state.value.exerciseList) { item in
                    ListItemView(title: item.exerciseTitle.wrappedValue, onClicked: {
                        viewModel.onExerciseClicked(id: item.id)
                    })
                    .frame(height: Dimensions.space_64)
                }
                Spacer()
                Button(action: {
                    viewModel.onAddNewExerciseClicked()
                }, label: {
                    Text(MR.strings().add_new_result.desc().localized())
                        .padding(Dimensions.space_16)
                        .foregroundColor(Color.colorPrimary)
                        .background(Color.colorTertiary)
                        .clipShape(RoundedCorner())
                        .frame(maxWidth: .infinity, alignment: .center)
                })
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
            .background(Color.colorPrimary)
            .showDialog(isPresented: $state.value.isDialogVisible, onDismiss: {
                viewModel.onDialogClosed()
            }) {
                VStack {
                    Text(MR.strings().exercise_name.desc().localized())
                        .foregroundColor(.onPrimary)
                        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                        .padding()
                    InputView(value: $state.value.newExerciseName, trailingIcon: {}, onValueChange: { text in
                        viewModel.onAddExerciseNameChanged(name: text)
                    })
                    .padding(.horizontal, Dimensions.space_16)
                    
                    Button(action: {
                        viewModel.onAddExerciseClicked()
                    }, label: {
                        Text(MR.strings().add_new_exercise.desc().localized())
                            .padding(Dimensions.space_16)
                            .foregroundColor(Color.colorPrimary)
                            .background(Color.colorTertiary)
                            .clipShape(RoundedCorner())
                            .frame(maxWidth: .infinity, alignment: .center)
                    })
                    .padding()
                }
            }
            .navigationTitle(Text(state.value.categoryName))
    }
}

struct CategoryScreen_Previews: PreviewProvider {
    static var previews: some View {
        CategoryScreen(id: "S")
    }
}
