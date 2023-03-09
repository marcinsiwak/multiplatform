import SwiftUI
import shared

struct CategoryItem: View {
    let categoryData: CategoryData?
    
    init(categoryData: CategoryData?) {
        self.categoryData = categoryData
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(categoryData?.name ?? "")
                .frame(alignment: .leading)
                .padding(8)
                .background(.black)
                .cornerRadius(8, corners: [.topLeft, .bottomRight])
                .foregroundColor(.white)
                .font(.subheadline)
      
            ForEach(categoryData?.exercises ?? [ExerciseShort]()) { item in
                Text(item.name)
                    .padding(8)
                    .background(.black)
                    .foregroundColor(.white)
                    .font(.subheadline)
            }
            
            HStack {
                Spacer()
            }
            Spacer()
        }
        .frame(height: 164)
        .background(
            Image("bg_running_field")
                .resizable()
                .scaledToFill()
                .clipped()
        )
        .cornerRadius(8)
        .padding(.top, 8)
        .onTapGesture {
            
        }
        
    }
    
}

struct SummaryItem_Previews: PreviewProvider {
    static var previews: some View {
        CategoryItem(categoryData: nil)
    }
}

extension ExerciseShort: Identifiable {}
