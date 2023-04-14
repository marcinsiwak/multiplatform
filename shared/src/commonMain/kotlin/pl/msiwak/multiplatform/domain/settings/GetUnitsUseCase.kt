package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.data.common.UnitType
import pl.msiwak.multiplatform.data.store.UnitStore

class GetUnitsUseCase(private val store: UnitStore) {
    operator fun invoke(): UnitType {
        return UnitType.valueOf(store.getUnit())
    }
}