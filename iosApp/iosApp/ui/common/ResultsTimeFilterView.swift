import SwiftUI
import shared

struct ResultsTimeFilterView: View {
    let tabs: [DateFilter]
    let onTabClicked: (DateFilter) -> Void
    let selectedFilter: Int32
    @State private var selectedItem: DateFilter
    
    init(tabs: [DateFilter], onTabClicked: @escaping (DateFilter) -> Void, selectedFilter: Int32) {
        self.tabs = tabs
        self.onTabClicked = onTabClicked
        self.selectedFilter = selectedFilter
        self.selectedItem = tabs[Int(selectedFilter)]
        UISegmentedControl.appearance().selectedSegmentTintColor = .white
        UISegmentedControl.appearance().backgroundColor = .black
        UISegmentedControl.appearance().setTitleTextAttributes([.foregroundColor: UIColor.black], for: .selected)
        UISegmentedControl.appearance().setTitleTextAttributes([.foregroundColor: UIColor.white], for: .normal)

    }
    
    var body: some View {
        
        VStack {
            
            Picker("Sort type", selection: $selectedItem) {
                ForEach(tabs, id: \.self) { tab in
                    Text(tab.type.nameResourceId.desc().localized())
                        .onTapGesture {
                            onTabClicked(tab)
                        }
                }
            }
            .pickerStyle(.segmented)
        }
    }
}

