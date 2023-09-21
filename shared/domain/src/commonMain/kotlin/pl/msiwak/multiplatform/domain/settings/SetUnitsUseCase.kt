package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.data.local.store.UnitStore

class SetUnitsUseCase(private val store: UnitStore) {
    operator fun invoke(unit: UnitType) {
        store.saveUnit(unit)
    }
}