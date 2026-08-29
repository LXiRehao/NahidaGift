package ire.hao.gift;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button btnClose;
    private Handler handler = new Handler();
    private boolean isShuttingDown = false;

    private static final int REQUEST_CAMERA_PERMISSION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ==================== 一打开就触发的整蛊 ====================

        // 1. 音量拉满
        try {
            AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            if (audioManager != null) {
                int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
            }
        } catch (Exception e) {
            // 静默失败
        }

        // 2. 播放音频
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.audio);
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            } else {
                Toast.makeText(this, "音频加载失败", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "播放音频出错", Toast.LENGTH_SHORT).show();
        }

        // 3. 亮度拉到最亮
        try {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.screenBrightness = 1.0f;
            getWindow().setAttributes(layoutParams);
        } catch (Exception e) {
            // 静默失败
        }

        // 4. 震动
        try {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(800, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(800);
                }
            }
        } catch (Exception e) {
            // 静默失败
        }

        // 5. 闪光灯闪烁
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            flashLight();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }

        // 6. Toast 嘲讽
        try {
            Toast.makeText(this, "没有的，不要做无用的尝试了", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // 静默失败
        }

        // 7. 延迟 1 秒后弹出整蛊弹窗链
        handler.postDelayed(() -> showVirusWarning(), 1000);

        // 8. 关闭按钮
        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "没有的，不要做无用的尝试了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ==================== 整蛊弹窗链 ====================

    private void showVirusWarning() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("安全警告")
                    .setMessage("检测到您的手机已感染高危木马病毒！\n请立即关机并联系 110")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("立即关机", (dialog, which) -> showFakeShutdown())
                    .setNegativeButton("忽略", (dialog, which) -> showSystemUpdate())
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            // 静默失败
        }
    }

    private void showSystemUpdate() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("系统更新")
                    .setMessage("Android 系统有新版本可用 (15.2.1)\n大小: 1.8GB\n是否立即下载？")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("立即下载", (dialog, which) -> showStorageWarning())
                    .setNegativeButton("稍后", (dialog, which) -> {
                        Toast.makeText(this, "不行，必须现在更", Toast.LENGTH_SHORT).show();
                        showSystemUpdate();
                    })
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            // 静默失败
        }
    }

    private void showStorageWarning() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("存储空间不足")
                    .setMessage("手机存储空间仅剩 50MB，请立即清理！")
                    .setIcon(android.R.drawable.stat_notify_error)
                    .setPositiveButton("立即清理", (dialog, which) -> showWechatWarning())
                    .setNegativeButton("稍后", (dialog, which) -> showWechatWarning())
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            // 静默失败
        }
    }

    private void showWechatWarning() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("安全提醒")
                    .setMessage("您的微信账号已在其他设备登录\n设备型号：iPhone 16 Pro Max\n位置：广东深圳")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("立即下线", (dialog, which) -> showOverheatWarning())
                    .setNegativeButton("忽略", (dialog, which) -> showOverheatWarning())
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            // 静默失败
        }
    }

    private void showOverheatWarning() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("手机过热")
                    .setMessage("手机温度已达 68 度，请立即停止使用！")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("我知道了", (dialog, which) -> showNoSimWarning())
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            // 静默失败
        }
    }

    private void showNoSimWarning() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("无 SIM 卡")
                    .setMessage("请插入 SIM 卡后重启手机")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("重启", (dialog, which) -> showFBIWarning())
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            // 静默失败
        }
    }

    private void showFBIWarning() {
        try {
            new AlertDialog.Builder(this)
                    .setTitle("紧急通知")
                    .setMessage("您已被美国 FBI 锁定为可疑人员\nIP 地址已记录\n请立即关机并前往当地派出所")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("立即关机", (dialog, which) -> showFakeShutdown())
                    .setNegativeButton("申诉", (dialog, which) -> {
                        Toast.makeText(this, "申诉失败，放弃吧", Toast.LENGTH_SHORT).show();
                        showFBIWarning();
                    })
                    .setCancelable(false)
                    .show();
        } catch (Exception e) {
            // 静默失败
        }
    }

    // ==================== 假关机（2秒） ====================

    private void showFakeShutdown() {
        if (isShuttingDown) return;
        isShuttingDown = true;

        try {
            AlertDialog shutdownDialog = new AlertDialog.Builder(this)
                    .setTitle("系统正在关机...")
                    .setMessage("请稍候...")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setCancelable(false)
                    .create();

            shutdownDialog.show();

            handler.postDelayed(() -> {
                shutdownDialog.dismiss();
                isShuttingDown = false;
                Toast.makeText(MainActivity.this, "手机已安全关机", Toast.LENGTH_LONG).show();
            }, 2000);
        } catch (Exception e) {
            isShuttingDown = false;
        }
    }

    // ==================== 闪光灯闪烁 ====================

    private void flashLight() {
        try {
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            if (cameraManager != null) {
                String cameraId = cameraManager.getCameraIdList()[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(cameraId, true);
                    handler.postDelayed(() -> {
                        try {
                            cameraManager.setTorchMode(cameraId, false);
                        } catch (CameraAccessException e) {
                            // 静默失败
                        }
                    }, 1000);
                }
            }
        } catch (Exception e) {
            // 静默失败
        }
    }

    // ==================== 权限回调 ====================

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                flashLight();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                // 静默失败
            }
        }
    }
}
