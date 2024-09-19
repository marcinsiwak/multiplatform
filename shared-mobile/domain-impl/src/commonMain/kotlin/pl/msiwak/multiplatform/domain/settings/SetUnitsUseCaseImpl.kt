package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.data.local.store.UnitStore

class SetUnitsUseCaseImpl(private val store: UnitStore) : SetUnitsUseCase {
    override operator fun invoke(unit: UnitType) {
        store.saveUnit(unit)
    }
}
