package cybergammagroup.ndsmd

import android.app.Application
import com.google.android.material.color.DynamicColors

class NDSMD: Application() {
  override fun onCreate() {
    DynamicColors.applyToActivitiesIfAvailable(this)
    Thread.setDefaultUncaughtExceptionHandler(CrashLogger(this))
    super.onCreate()
  }
}
