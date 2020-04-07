package jp.dagon.firstandroidbook.mysize2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_height.*

class HeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        // onItemSelectedListenerプロパティにAdapterView.OnItemSelectedListener インターフェイスを実装したクラスのインスタンスを設定
        // 無名インナークラスを使って直接渡す
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            /**
             * 項目が選択されずにビューが閉じられた時の処理
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            /**
             * 一覧から選択された時に呼ばれる(項目が選択された時の処理)
             *
             * @param parent 選択が発生した親ビュー
             * @param view 選択されたビュー
             * @param position 選択されたビューの位置
             * @param id 選択された項目のID
             */
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinner = parent as? Spinner
                // selectedItem:選択中の項目、またはnullを取得します
                val item = spinner?.selectedItem as? String
                item?.let {
                    if (it.isNotEmpty()) height.text = it
                }
            }
        }

        //
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            val heightVal = getInt("HEIGHT", 160)
            height.setText(heightVal.toString())
            // シークバーに値を設定(progress:設定する値)
            seekBar.progress = heightVal
        }

        // シークバーを操作した時のイベントリスナー登録
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                /**
                 * シークバーの値を変更した時の処理
                 */
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    height.text = progress.toString()
                }

                /**
                 * シークバーに触れた時の処理
                 */
                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                /**
                 * シークバーを話した時の処理
                 */
                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )

        // ラジオボタンの選択状態が変更された時に呼ばれるリスナーを登録
        // group:ラジオボタンの選択状態変更が発生したRadioGroup
        // checkedId:選択されたラジオボタンのID
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // ビューに割り当てられたリソースIDに対応するビューオブジェクトを取得
            // T:キャスト先のViewを継承したクラス
            // id:Rクラスに定義された該当するリソースIDs
            height.text = findViewById<RadioButton>(checkedId).text
        }
    }

    /**
     * アクティビティが非表示になると時に呼ばれる
     */
    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).edit()
            .putInt("HEIGHT", height.text.toString().toInt())
    }
}
