import SwiftUI
import shared

struct CategoryItem: View {
    let categoryData: CategoryData
    
    let backgroundImage: UIImage?
    
    init(categoryData: CategoryData) {
        self.categoryData = categoryData
        switch(categoryData.exerciseType){
        case ExerciseType.running:
            backgroundImage = MR.images().bg_running_field.toUIImage()
        case ExerciseType.gym:
            backgroundImage = MR.images().bg_gym.toUIImage()
    //        ExerciseType.OTHER -> null
        default:
            backgroundImage = MR.images().bg_gym.toUIImage()
        }
    }
        
    var body: some View {
        VStack(alignment: .leading) {
            Text(categoryData.name)
                .frame(alignment: .leading)
                .padding(Dimensions.space_8)
                .background(.black)
                .cornerRadius(Dimensions.button_corner, corners: [.topLeft, .bottomRight])
                .foregroundColor(.white)
                .font(.subheadline)
      
            ForEach(categoryData.exercises) { item in
                Text(item.name)
                    .foregroundColor(.white)
                    .font(.subheadline)
            }.padding(.horizontal, Dimensions.space_8)
            
            HStack {
                Spacer()
            }
            Spacer()
        }
        .frame(height: Dimensions.space_164)
        .background(
            ZStack {
                Image(uiImage: backgroundImage!)
                    .resizable()
                    .scaledToFill()
                    .clipped()
                Rectangle()
                    .frame(height: Dimensions.space_164)
                    .foregroundColor(.clear)
                    .padding(EdgeInsets(top: Dimensions.space_24, leading: 0, bottom: 0, trailing: 0))
                    .background(LinearGradient(gradient: Gradient(colors: [.clear, .clear, .black]), startPoint: .top, endPoint: .bottom))
            }
        )
        .cornerRadius(8)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(.white, lineWidth: 1)
        )
        
    }
    
}

//struct SummaryItem_Previews: PreviewProvider {
//    static var previews: some View {
//        CategoryItem(categoryData: nil)
//    }
//}

extension ExerciseShort: Identifiable {}
