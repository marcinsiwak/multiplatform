package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.data.local.store.UnitStore

class GetUnitsUseCase(private val store: UnitStore) {
    operator fun invoke(): UnitType {
        return UnitType.valueOf(store.getUnit())
    }
}