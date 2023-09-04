package pl.msiwak.multiplatform.core.domain.settings

import pl.msiwak.multiplatform.core.data.common.UnitType
import pl.msiwak.multiplatform.core.data.store.UnitStore

class GetUnitsUseCase(private val store: UnitStore) {
    operator fun invoke(): UnitType {
        return UnitType.valueOf(store.getUnit())
    }
}