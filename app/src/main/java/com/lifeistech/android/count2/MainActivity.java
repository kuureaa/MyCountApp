package com.lifeistech.android.count2;      //count2をパッケージcom.lifeistech.androidに所属させる（インターネットドメイン名を前後逆順にしたもの

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {        //AppCompatActivityクラスを継承したMainActivityクラスを作成

    private Button startButton;     //自分自身のクラスのみアクセスを許可するButtonクラス型のstartButtonというボタン
    private TextView timerText;     //自分自身のクラスのみアクセスを許可するタイマ－を表示させるためのテキストビュー

    TextView text;       //現在のカウントを表示させるためのテキストビュー
    TextView text2;      //10秒間でクリックした回数を表示させるためのテキストビュー

    int number;         //現在の値を保存しておくための変数

    @Override            //onCreateのオーバーライド
    protected void onCreate(Bundle savedInstanceState) {    /*自分と同じパッケージに属するか、自分を継承した子クラスにアクセスを許可するonCreate()メッソドの作成
                                                                (onCreate()メソッドの引数であるBundleは、アプリの状態を一時的に保存できるクラス)*/
        super.onCreate(savedInstanceState);                  //AppCompatActivityのonCreateを呼び出す
        setContentView(R.layout.activity_main);             //Rクラス内のlayoutクラスのactivity_mainフィールドを表示
        text = (TextView) findViewById(R.id.textView);      /*Rクラス内のidクラスのactivity_mainフィールドのIDをTextViewクラスにキャストしてtextに格納
                                                               (textViewという名前(ID)はXML独自のものなので、これを元にしたJava用のIDが用いられる*/
        text.setText("0");                                  //textに「0」と表示する

        text2 = (TextView) findViewById(R.id.textView2);   //画面内のIDがtextView2のテキストとtext2を関連づける
        text2.setText("ボタン連打");                       //text2に「ボタン連打」と表示する

        startButton = (Button)findViewById(R.id.start_button);      /*Rクラス内のidクラスのstart_buttonフィールドのIDをButtonクラスにキャストしてstartButtonに格納*/

        timerText = (TextView)findViewById(R.id.timer);             //Rクラス内のidクラスのtimerフィールドのIDをTextViewクラスにキャストしてtimerTextに格納
        timerText.setText("0:00.000");                              //timerTextに「0:00.000」と表示

        // インスタンス生成
        // CountDownTimer(long millisInFuture, long countDownInterval)
        // 3分= 3x60x1000 = 180000 msec
        final CountDown countDown = new CountDown(10000, 100);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDown.start();          //カウントダウン開始
            }
        });


    }
    public void  plus(View v) {                     //plusというボタンの関数を作成(ViewはButtonやTextViewなどのスーパークラス)
        number = number + 1;                        //numberを+1する
        text.setText(String.valueOf(number));       //textに整数numberの値を表示
    }

    public void  minus(View v) {                     //minusというボタンの関数を作成
        number = number - 1;                        //numberを-1する
        text.setText(String.valueOf(number));       //textに整数numberの値を表示
    }

    public void  clear(View v) {                      //clearというボタンの関数を作成
        number = 0;                                   //numberに0を代入
        text.setText(String.valueOf(number));        //textに整数numberの値を表示
    }

    class CountDown extends CountDownTimer {         // //CountDownTimerクラスを継承したCountDownクラスを作成

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // 完了
            timerText.setText("0:00.000");             //timerTextに「0:00.000」と表示
        }

        // インターバルで呼ばれる
        @Override
        public void onTick(long millisUntilFinished) {
            // 残り時間を分、秒、ミリ秒に分割
            long mm = millisUntilFinished / 1000 / 60;
            long ss = millisUntilFinished / 1000 % 60;
            long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;


            timerText.setText(String.format("%1$02d:%2$02d.%3$03d", mm, ss, ms));       //timerTextにタイマーを表示

            if( mm==0 && ss==0 && ms>0) {                         //タイマーが0になれば
                text2.setText(String.valueOf(number+"回"));      //text2に10秒間でクリックした回数を表示
            }
        }
    }

}
