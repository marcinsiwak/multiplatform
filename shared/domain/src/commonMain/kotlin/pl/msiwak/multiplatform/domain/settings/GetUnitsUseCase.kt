package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.commonObject.UnitType

interface GetUnitsUseCase {
    operator fun invoke(): UnitType
}
