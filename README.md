# 纳西妲送你的礼物

> 一个充满“惊喜”的整蛊 Android 应用

---

## 简介

**纳西妲送你的礼物** 是一个以整蛊为目的的 Android 小应用，打开即触发一系列“系统警告”，让朋友体验从惊慌到放弃的心路历程

**仅供娱乐整蛊，请勿恶意使用！**

---

## 功能列表

| 功能 | 说明 |
|------|------|
| 音量拉满 | 打开 App 自动将媒体音量调至最大 |
| 音频循环播放 | 自带音效循环播放，无法暂停 |
| 亮度拉满 | 自动将屏幕亮度调至最高 |
| 震动 | 打开时震动 800ms |
| 闪光灯闪烁 | 自动闪光一次（需授权） |
| 假病毒警告 | “手机已感染高危木马病毒” |
| 假系统更新 | “Android 15.2.1 可用，大小 1.8GB” |
| 假存储不足 | “存储空间仅剩 50MB” |
| 假微信被盗 | “微信已在其他设备登录” |
| 假手机过热 | “手机温度已达 68°C” |
| 假无 SIM 卡 | “请插入 SIM 卡后重启” |
| 假 FBI 锁定 | “您已被美国 FBI 锁定” |
| 假关机 | 模拟系统关机 2 秒 |
| 无法正常关闭 | “关闭”按钮无效，只能划掉后台 |

---

## 整蛊流程

```

打开 App
│
├── 音量拉满 + 音频循环 + 亮度拉满 + 震动 + 闪光灯
│
└── 1 秒后弹出“病毒警告”
│
├── 点“立即关机” → 假关机 2 秒
│
└── 点“忽略” →
系统更新 → 存储不足 → 微信被盗 → 手机过热 → 无 SIM 卡 → FBI 锁定 → 假关机 2 秒

```

---

## 技术栈

- Java

---

## 编译

#### 本地编译

```bash
# 克隆项目
git clone https://github.com/LXiRehao/NahidaGift.git
cd NahidaGift

# 编译 Release APK
./gradlew assembleRelease
```

---

## 项目结构

```
NahidaGift/
├── .github/workflows/
│   └── build.yml
├── app/
│   ├── src/main/
│   │   ├── java/ire/hao/gift/
│   │   │   └── MainActivity.java
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   └── pic.png    # 主界面图片
│   │   │   ├── mipmap/
│   │   │   │   └── icon.png   # 应用图标
│   │   │   ├── raw/
│   │   │   │   └── audio.mp3  # 整蛊音频
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml
│   │   │   └── values/
│   │   │       ├── strings.xml
│   │   │       └── styles.xml
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── gradle.properties
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 作者

LXiRehao

- GitHub: @LXiRehao

---

## 许可证

本项目基于 MIT 许可证开源

---

#### 免责声明

本应用仅供娱乐整蛊用途，不得用于任何非法或恶意目的
使用本应用造成的任何后果由使用者自行承担

---

#### Star

如果这个项目对你有帮助，请点个Star支持一下！

---

整蛊愉快！

---
