package nethical.digipaws.ui.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import nethical.digipaws.R
import nethical.digipaws.databinding.DialogConfigTrackerBinding
import nethical.digipaws.services.UsageTrackingService
import nethical.digipaws.utils.SavedPreferencesLoader

class TweakUsageTracker(
    savedPreferencesLoader: SavedPreferencesLoader
) : BaseDialog(savedPreferencesLoader) {

    private lateinit var trackerPreferences: SharedPreferences

    @SuppressLint("ApplySharedPref")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogConfigurationTracker = DialogConfigTrackerBinding.inflate(layoutInflater)

        // Load tracker preferences
        trackerPreferences =
            requireContext().getSharedPreferences("config_tracker", Context.MODE_PRIVATE)
        dialogConfigurationTracker.cbReelCounter.isChecked =
            trackerPreferences.getBoolean("is_reel_counter", true)
        dialogConfigurationTracker.cbTimeElapsed.isChecked =
            trackerPreferences.getBoolean("is_time_elapsed", false)
        dialogConfigurationTracker.cbEnableGlow.isChecked = trackerPreferences.getBoolean("is_glow_enabled",true)
        dialogConfigurationTracker.txtTimeAnimation.setText(trackerPreferences.getInt("time_animation",5).toString())


        // Build and display dialog
        return MaterialAlertDialogBuilder(requireContext())
            .setView(dialogConfigurationTracker.root)
            .setCancelable(false)
            .setPositiveButton(getString(R.string.save)) { dialog, _ ->

                // Save updated settings
                with(trackerPreferences.edit()) {
                    putBoolean(
                        "is_reel_counter",
                        dialogConfigurationTracker.cbReelCounter.isChecked
                    )
                    putBoolean(
                        "is_time_elapsed",
                        dialogConfigurationTracker.cbTimeElapsed.isChecked
                    )
                    putInt(
                        "time_animation",
                        dialogConfigurationTracker.txtTimeAnimation.text.toString().toInt()
                    )
                    putBoolean(
                        "is_glow_enabled",
                        dialogConfigurationTracker.cbEnableGlow.isChecked
                    )
                    apply() // Apply changes asynchronously"
                    commit() // Apply changes immediately
                }

                // Send broadcast to refresh UsageTrackingService
                sendRefreshRequest(UsageTrackingService.INTENT_ACTION_REFRESH_USAGE_TRACKER)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}
