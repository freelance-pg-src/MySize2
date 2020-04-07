package jp.dagon.firstandroidbook.mysize2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val editNeck = getString("NECK", "")
            val editSleeve = getString("SLEEVE", "")
            val editWaist = getString("WAIST", "")

            // EditTextに文字列を設定(text:設定する文字列)
            neck.setText(editNeck)
            sleeve.setText(editSleeve)
            waist.setText(editWaist)
        }

        // 保存ボタンが押された時のリスナー設定
        save.setOnClickListener { onSaveTapped() }
    }

    /**
     * 共有プレファレンスに保存
     */
    private fun onSaveTapped() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putString("NECK", neck.text.toString())
            .putString("SLEEVE", sleeve.text.toString())
            .putString("WAIST", waist.text.toString())
            .putString("INSEAM", inseam.text.toString())
            .apply()
    }
}
