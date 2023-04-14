package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.data.common.UnitType
import pl.msiwak.multiplatform.data.store.UnitStore

class SetUnitsUseCase(private val store: UnitStore) {
    operator fun invoke(unit: UnitType) {
        store.saveUnit(unit)
    }
}