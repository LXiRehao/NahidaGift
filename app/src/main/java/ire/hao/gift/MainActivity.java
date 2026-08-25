package ire.hao.gift;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button btnClose;
    private int clickCount = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 全屏（代码方式，双保险）
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // 1. 音量拉满
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        }

        // 2. 播放音频（循环播放）
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        // 3. 显示Toast嘲讽
        Toast.makeText(this, "没有的，不要做无用的尝试了", Toast.LENGTH_LONG).show();

        // 4. 关闭按钮逻辑
        btnClose = findViewById(R.id.btn_close);
        btnClose.setText("关闭 (" + clickCount + ")");
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount--;
                btnClose.setText("再点 " + clickCount + " 次关闭");

                // 每次点击都显示Toast
                Toast.makeText(MainActivity.this, "没有的，不要做无用的尝试了", Toast.LENGTH_SHORT).show();

                // ===== 彩蛋区域 =====
                if (clickCount == 66) {
                    Toast.makeText(MainActivity.this, "66大顺，加油！", Toast.LENGTH_SHORT).show();
                }
                if (clickCount == 50) {
                    Toast.makeText(MainActivity.this, "你已经点了一半了，继续！", Toast.LENGTH_SHORT).show();
                }
                if (clickCount == 33) {
                    Toast.makeText(MainActivity.this, "33，剩三分之一了！", Toast.LENGTH_SHORT).show();
                }
                if (clickCount == 0) {
                    Toast.makeText(MainActivity.this, "恭喜你，点了99下！但是...我骗你的哈哈哈", Toast.LENGTH_LONG).show();
                }
                if (clickCount == -10) {
                    Toast.makeText(MainActivity.this, "你都点到负数了，还不放弃吗？", Toast.LENGTH_SHORT).show();
                }
                if (clickCount == -50) {
                    Toast.makeText(MainActivity.this, "兄弟，你已经点了149下了...", Toast.LENGTH_SHORT).show();
                }
                if (clickCount == -99) {
                    Toast.makeText(MainActivity.this, "198下！你是我见过最执着的人！", Toast.LENGTH_SHORT).show();
                }
                if (clickCount == -999) {
                    Toast.makeText(MainActivity.this, "你是用连点器了吧？！", Toast.LENGTH_SHORT).show();
                }
                // ===== 彩蛋结束 =====

                // 如果小于0，额外嘲讽
                if (clickCount < 0) {
                    Toast.makeText(MainActivity.this, "哈哈哈哈，你点了我" + Math.abs(clickCount) + "下，真执着！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 5. 拦截音量键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Toast.makeText(this, "没有的，不要做无用的尝试了", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 6. 拦截返回键
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "没有的，不要做无用的尝试了", Toast.LENGTH_SHORT).show();
    }

    // 7. 双重拦截音量键（Android 8+兜底）
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Toast.makeText(this, "没有的，不要做无用的尝试了", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
