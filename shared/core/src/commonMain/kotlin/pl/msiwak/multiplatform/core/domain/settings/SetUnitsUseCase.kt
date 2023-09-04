package pl.msiwak.multiplatform.core.domain.settings

import pl.msiwak.multiplatform.core.data.common.UnitType
import pl.msiwak.multiplatform.core.data.store.UnitStore

class SetUnitsUseCase(private val store: UnitStore) {
    operator fun invoke(unit: UnitType) {
        store.saveUnit(unit)
    }
}