package pl.msiwak.multiplatform.core.data.store

import pl.msiwak.multiplatform.core.data.common.UnitType
import pl.msiwak.multiplatform.core.utils.KMMPreferences

class UnitStore(private val sharedKMMPreferences: KMMPreferences) {
    fun saveUnit(unit: UnitType) {
        sharedKMMPreferences.put(PREFS_UNIT_KEY, unit.name)
    }

    fun getUnit(): String {
        return sharedKMMPreferences.getString(PREFS_UNIT_KEY) ?: DEFAULT_UNIT_VALUE
    }

    companion object {
        const val PREFS_UNIT_KEY = "PREFS_UNIT_KEY"
        const val DEFAULT_UNIT_VALUE = "METRIC"
    }
}