package pl.msiwak.multiplatform.data.local.store

import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.utils.KMPPreferences

class UnitStore(private val sharedKMPPreferences: KMPPreferences) {
    fun saveUnit(unit: UnitType) {
        sharedKMPPreferences.put(PREFS_UNIT_KEY, unit.name)
    }

    fun getUnit(): String {
        return sharedKMPPreferences.getString(PREFS_UNIT_KEY) ?: DEFAULT_UNIT_VALUE
    }

    companion object {
        const val PREFS_UNIT_KEY = "PREFS_UNIT_KEY"
        const val DEFAULT_UNIT_VALUE = "METRIC"
    }
}
