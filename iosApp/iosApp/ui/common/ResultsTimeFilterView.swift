import SwiftUI
import shared

struct ResultsTimeFilterView: View {
    let tabs: [DateFilterType]
    let onTabClicked: (DateFilterType) -> Void
    let selectedFilter: Int32
    @State private var selectedItem: DateFilterType
    
    
    init(tabs: [DateFilterType], onTabClicked: @escaping (DateFilterType) -> Void, selectedFilter: Int32) {
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
                    Text("tab.nameResourceId.desc().localized()")
                }
            }
            .pickerStyle(.segmented)
            .onChange(of: selectedItem) { newValue in
                onTabClicked(newValue)
            }
        }
    }
}

