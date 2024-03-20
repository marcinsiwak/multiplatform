import shared
import SwiftUI

typealias Category = shared.Category
typealias Exercise = shared.Exercise
typealias ExerciseType = shared.ExerciseType

struct CategoryItem: View {
    let category: Category
    
    let backgroundImage: UIImage?
    
    init(category: Category) {
        self.category = category
        switch category.exerciseType {
        case ExerciseType.running:
            backgroundImage = Res.drawable().bg_running_field.toUIImage()
        case ExerciseType.gym:
            backgroundImage = Res.drawable().bg_gym.toUIImage()
    //        ExerciseType.OTHER -> null
        default:
            backgroundImage = Res.drawable().bg_gym.toUIImage()
        }
    }

    var body: some View {
        VStack(alignment: .leading) {
            Text(category.name)
                .frame(alignment: .leading)
                .padding(Dimensions.space_8)
                .background(Color.colorPrimary)
                .cornerRadius(Dimensions.button_corner, corners: [.topLeft, .bottomRight])
                .foregroundColor(.onPrimary)
                .font(.subheadline)

            ForEach(category.exercises) { item in
                Text(item.exerciseTitle)
                    .foregroundColor(.onPrimary)
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
                    .background(
                        LinearGradient(
                            gradient: Gradient(
                                colors: [.clear, .clear, .colorPrimary]
                                ),
                            startPoint: .top, endPoint: .bottom
                        )
                    )
            }
        )
        .cornerRadius(8)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(.tertiary, lineWidth: 1)
        )
    }
}

// struct SummaryItem_Previews: PreviewProvider {
//     static var previews: some View {
//         CategoryItem(category: nil)
//     }
// }

extension Exercise: Identifiable {}
