package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.data.local.store.UnitStore

class GetUnitsUseCaseImpl(private val store: UnitStore) : GetUnitsUseCase {
    override operator fun invoke(): UnitType {
        return UnitType.valueOf(store.getUnit())
    }
}
