package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.commonObject.UnitType

interface SetUnitsUseCase {
    operator fun invoke(unit: UnitType)
}
